package com.example.order.service;

import com.example.order.dao.PhoneRecordDao;
import com.example.order.dao.UserDao;
import com.example.order.model.PhoneRecord;
import com.example.order.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;
    private final PhoneRecordDao phoneRecordDao;


    @Autowired
    public UserService(@Qualifier("postgres") UserDao userDao, PhoneRecordDao phoneRecordDao) {
        this.userDao = userDao;
        this.phoneRecordDao = phoneRecordDao;
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public List<User> getAllUsers(){
       return  userDao.getAllUsers();
    }

    public Optional<User> getUserById(UUID id){
        return  userDao.getUserById(id);
    }

    public void deleteUserById(UUID id){
        userDao.deleteUserById(id);
    }

    public void updateUserById(UUID id, User user){
        userDao.updateUserById(id,user);
    }

    public Optional<User> getUserByName(String name){
        return  userDao.getUserByName(name);
    }

    public  void addPhoneBook(UUID userId, PhoneRecord phoneRecord){
        phoneRecordDao.addPhoneBook(userId,phoneRecord);
    }
    public void getAllPhoneBook(UUID userId) {
        phoneRecordDao.getAllPhoneBook(userId);
    }
    public Optional<PhoneRecord> getPhoneBookById(UUID userId, UUID id) {
        return phoneRecordDao.getPhoneBookById(userId,id);
    }
    public void deletePhoneBookById(UUID userId, UUID id) {
        phoneRecordDao.deletePhoneBookById(userId,id);
    }
    public void updatePhoneBookById(UUID userId, UUID id, PhoneRecord phoneBook) {
      phoneRecordDao.updatePhoneBookById(userId,id,phoneBook);
    }

    public Optional<PhoneRecord> getPhoneBookByNumber(UUID userId, String number) {
       return phoneRecordDao.getPhoneBookByNumber(userId,number);
    }
}

