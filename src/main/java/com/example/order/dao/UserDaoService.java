package com.example.order.dao;

import com.example.order.model.PhoneRecord;
import com.example.order.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository("postgres")
public class UserDaoService implements UserDao, PhoneRecordDao {

    private final RowMapper<PhoneRecord> phoneRecordRowMapper = (resultSet, i) -> {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String name = resultSet.getString("name");
        String thisNumber = resultSet.getString("number");
        UUID thisUserId = UUID.fromString(resultSet.getString("userId"));
        return new PhoneRecord(id, name, thisNumber, thisUserId);
    };
    private final RowMapper<User> userRowMapper = (resultSet, i) -> {
        UUID userId = UUID.fromString(resultSet.getString("id"));
        String findName = resultSet.getString("name");
        String number = resultSet.getString("number");
        return new User(userId, findName, number);
    };
    private final JdbcTemplate jdbcTemplate;

    private static final String addUserSql = "INSERT INTO userr ( id,name,number) VALUES ( ?,?, ?)";
    private static final String selectAllSql = "SELECT * FROM userr";
    private static final String selectUserByIdSql = "SELECT * FROM userr WHERE id =?";
    private static final String deleteUserSql = "DELETE FROM userr WHERE id=?";
    private static final String updateUserSql = "UPDATE userr SET  name = ? , number = ? WHERE id =?";
    private static final String selectUserByNameSql = "SELECT name, number FROM userr WHERE name LIKE ?";

    private static final String addPhoneRecordSql = "INSERT INTO phoneBook ( id,name,number,userId) VALUES ( ?,?, ?,?)";
    private static final String selectAllPhoneRecordsSql = "SELECT *  FROM phoneBook WHERE userId=?";
    private static final String selectPhoneBookByIdSql = "SELECT * FROM phoneBook WHERE userId = ? AND id =? ";
    private static final String deletePhoneBookSql = "DELETE FROM phoneBook WHERE userId = ? AND id = ?";
    private static final String updatePhoneBookSql = "UPDATE phoneBook SET  name = ? , number = ? WHERE userId = ? AND id = ?";
    private static final String selectPhoneBookByNumberSql = "SELECT * FROM phoneBook WHERE userId = ? AND number = ?";

    @Autowired
    public UserDaoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(UUID id, User user) {
        jdbcTemplate.update(addUserSql,
                id, user.getName(), user.getNumber()
        );
    }

    @Override
    public List<User> getAllUsers() {

        return jdbcTemplate.query(selectAllSql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String number = resultSet.getString("number");
            return new User(id, name, number);
        });

    }

    @Override
    public Optional<User> getUserById(UUID id) {
        User user = jdbcTemplate.queryForObject(selectUserByIdSql, new Object[]{id}, userRowMapper);
        return Optional.ofNullable(user);
    }

    @Override
    public int deleteUserById(UUID id) {
        return jdbcTemplate.update(deleteUserSql,
                id);
    }

    @Override
    public void updateUserById(UUID id, User user) {
        jdbcTemplate.update(updateUserSql,
                user.getName(), user.getNumber(), id);

    }

    @Override
    public Optional<User> getUserByName(String name) {
        User user = jdbcTemplate.queryForObject(selectUserByNameSql, new Object[]{name}, userRowMapper);
        return Optional.ofNullable(user);
    }


    @Override
    public void addPhoneBook(UUID userId, UUID id, PhoneRecord phoneBook) {
        jdbcTemplate.update(addPhoneRecordSql,
                id, phoneBook.getName(), phoneBook.getNumber(), userId
        );

    }

    @Override
    public List<PhoneRecord> getAllPhoneBook(UUID userId) {
        List<PhoneRecord> phoneRecords = new ArrayList<>();
        List<Map<String, Object>> resultSet = jdbcTemplate
                .queryForList(selectAllPhoneRecordsSql, userId);
        for (Map res : resultSet) {
            PhoneRecord phoneRecord = new PhoneRecord();
            phoneRecord.setId((UUID) (res.get("id")));
            phoneRecord.setName((String) (res.get("name")));
            phoneRecord.setNumber((String) (res.get("number")));
            phoneRecord.securerId((UUID) (res.get("userId")));
            phoneRecords.add(phoneRecord);
        }

        return phoneRecords;

    }

    @Override
    public Optional<PhoneRecord> getPhoneBookById(UUID userId, UUID id) {
        PhoneRecord phoneRecord = jdbcTemplate.queryForObject(selectPhoneBookByIdSql, new Object[]{userId, id},
                phoneRecordRowMapper);
        return Optional.ofNullable(phoneRecord);
    }

    @Override
    public int deletePhoneBookById(UUID userId, UUID id) {
        return jdbcTemplate.update(deletePhoneBookSql
                , userId, id);
    }

    @Override
    public void updatePhoneBookById(UUID userId, UUID id, PhoneRecord phoneBook) {
        jdbcTemplate.update(updatePhoneBookSql,
                phoneBook.getName(), phoneBook.getNumber(), userId, id);
    }

    @Override
    public Optional<PhoneRecord> getPhoneBookByNumber(UUID userId, String number) {
        PhoneRecord phoneRecord = jdbcTemplate.queryForObject(selectPhoneBookByNumberSql,
                new Object[]{userId, number}, phoneRecordRowMapper);
        return Optional.ofNullable(phoneRecord);
    }
}
