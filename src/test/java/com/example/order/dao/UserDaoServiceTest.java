package com.example.order.dao;


import com.example.order.model.PhoneRecord;
import com.example.order.model.User;
import org.junit.jupiter.api.Test;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoServiceTest {
    private EmbeddedDatabase embeddedDatabase;

    //@Mock
    private JdbcTemplate jdbcTemplate;

    //@InjectMocks
    private UserDaoService userDaoService;

    @Before
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        userDaoService = new UserDaoService(jdbcTemplate);
    }


    @org.junit.Test
    public void getAllUsers() {
        Assert.assertNotNull(userDaoService.getAllUsers());
        Assert.assertEquals(1, userDaoService.getAllUsers().size());
    }


    @org.junit.Test
    public void addUser() {
        User user = userDaoService.addUser(new User(UUID.fromString("2bf303c7-9999-4e3c-a14d-457251cb4d8d"),"Vasya Petrov","+79050379948"));
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals("Vasya Petrov", user.getName());
    }

    //обработка сохранения данных с невалидными данными
   /* @Test(expected = DataIntegrityViolationException.class)
    public void testSaveInvalid() {
        personRepository.save(new Person());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveConflict() {
        personRepository.save(new Person("Jim Beam", "jackdaniels@example.com"));
    }*/

    @Test
    void getUserById() {
        Assert.assertNotNull(userDaoService.getUserById((UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"))));
        Assert.assertNull(userDaoService.getUserById((UUID.fromString("39303244-166b-4e3c-a14d-457251cb4d8d"))));
    }

    @Test
    void deleteUserById() {
            Assert.assertEquals(1, userDaoService.deleteUserById((UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"))));
            Assert.assertEquals(0,userDaoService.deleteUserById(UUID.fromString("nonexistent-id")));

    }

    @Test
    void updateUserById() {
        final String sql ="select * from userr where id = '2bf303c7-166b-4e3c-a14d-457251cb4d8d'";
        User user = jdbcTemplate.queryForObject(sql,userDaoService.USER_ROW_MAPPER);
        user.setName("Johny Walker");
        userDaoService.updateUserById(UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"),user);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals("Johny Walker", user.getName());
    }

    //обработка сохранения данных с невалидными данными
    /*
    @Test(expected = DataIntegrityViolationException.class)
public void testUpdateInvalid() {
    Person person = jdbcTemplate.queryForObject("select * from person where id = 'jack-daniels'", PersonRepository.ROW_MAPPER);
    person.setName(null);

    personRepository.save(person);
}

@Test(expected = DataIntegrityViolationException.class)
public void testUpdateConflict() {
    Person person = jdbcTemplate.queryForObject("select * from person where id = 'jack-daniels'", PersonRepository.ROW_MAPPER);
    person.setEmail("georgedickel@example.com");

    personRepository.save(person);
}
     */

    @Test
    void getUserByName() {
        Assert.assertNotNull(userDaoService.getUserByName("Jon Smith"));
        Assert.assertNull(userDaoService.getUserByName("Kate Jons"));
    }

    @Test
    void addPhoneBook() {
        PhoneRecord phoneRecord = userDaoService.addPhoneBook(UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"),new PhoneRecord(UUID.fromString("2bf303c7-7777-4e3c-a14d-457251cb4d8d"),"Ivan Sokolov","+79110379947",UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d")));
        Assert.assertNotNull(phoneRecord);
        Assert.assertNotNull(phoneRecord.getId());
        Assert.assertEquals("Ivan Sokolov", phoneRecord.getName());
    }

    @Test
    void getAllPhoneBook() {
        Assert.assertNotNull(userDaoService.getAllPhoneBook(UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d")));
        Assert.assertEquals(1, userDaoService.getAllPhoneBook(UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d")).size());
    }

    @Test
    void getPhoneBookById() {
        Assert.assertNotNull(userDaoService.getPhoneBookById((UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d")),UUID.fromString("7383b98a-d87b-4b70-a454-333cfebc00b9")));
        Assert.assertNull(userDaoService.getUserById((UUID.fromString("39303244-166b-4e3c-a14d-457251cb4d8d"))));

    }

    @Test
    void deletePhoneBookById() {
        Assert.assertEquals(1, userDaoService.deletePhoneBookById((UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d")),UUID.fromString("7383b98a-d87b-4b70-a454-333cfebc00b9")));
        Assert.assertEquals(0,userDaoService.deletePhoneBookById((UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d")),UUID.fromString("7383b98a-d87b-4b70-a454-333c99999999")));
    }

    @Test
    void updatePhoneBookById() {
        final String sql ="select * from phoneBook where id = 'c71c6387-29e1-43da-9de1-9aee3b917cc0'";
        PhoneRecord phoneRecord = jdbcTemplate.queryForObject(sql,userDaoService.PHONE_RECORD_ROW_MAPPER);
        phoneRecord.setName("Johny Walker");
        userDaoService.updatePhoneBookById(UUID.fromString("7383b98a-d87b-4b70-a454-333cfebc00b9"),UUID.fromString("c71c6387-29e1-43da-9de1-9aee3b917cc0"),phoneRecord);
        Assert.assertNotNull(phoneRecord);
        Assert.assertNotNull(phoneRecord.getId());
        Assert.assertEquals("Johny Walker", phoneRecord.getName());
    }

    @Test
    void getPhoneBookByNumber() {
        Assert.assertNotNull(userDaoService.getPhoneBookByNumber(UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"),"+79110379947"));
        Assert.assertNull(userDaoService.getPhoneBookByNumber(UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"),"+79100079947"));
    }

    @After
    public void tearDown() {
        embeddedDatabase.shutdown();
    }
}
