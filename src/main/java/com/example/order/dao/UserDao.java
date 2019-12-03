package com.example.order.dao;

import com.example.order.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    void addUser(UUID id, User user);

    default User addUser(User user) {
        UUID id = UUID.randomUUID();
        addUser(id, user);
        return user;
    }

    List<User> getAllUsers();

    Optional<User> getUserById(UUID id);

    int deleteUserById(UUID id);

    void updateUserById(UUID id, User user);

    Optional<User> getUserByName(String name);





}
