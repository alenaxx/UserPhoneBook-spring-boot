package com.example.order.api;

import com.example.order.model.PhoneRecord;
import com.example.order.model.User;
import com.example.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }

    @GetMapping(path = "{id}")
    public Optional<User> getUserById(@PathVariable("id") UUID id){
        return userService.getUserById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") UUID id){
         userService.deleteUserById(id);
    }

    @PutMapping(path = "{id}")
    public void updateUserById(@PathVariable("id") UUID id,@RequestBody User user){
        userService.updateUserById(id, user);
    }

    @GetMapping(path = "user/{name}")
    public Optional<User> getUserByName(@PathVariable("name") String name){
        return userService.getUserByName(name);
    }

    @PostMapping(path = "user/{userId}")
    public void addPhoneBook(@PathVariable("userId") UUID userId, @RequestBody PhoneRecord phoneRecord){
        userService.addPhoneBook(userId,phoneRecord);
    }

    @GetMapping(path = "user/{userId}/recordList")
    public void getAllPhoneBook(@PathVariable ("userId") UUID userId) {
         userService.getAllPhoneBook(userId);
    }
    @GetMapping(path = "user/{userId}/recordList/{id}")
    public Optional<PhoneRecord> getPhoneBookById(@PathVariable("userId") UUID userId,@PathVariable("id") UUID id) {
        return userService.getPhoneBookById(userId,id);
    }

    @DeleteMapping(path = "user/{userId}/recordList/{id}")
    public void deletePhoneBookById(@PathVariable("userId") UUID userId,@PathVariable("id") UUID id) {
        userService.deletePhoneBookById(userId,id);
    }

    @PutMapping(path = "user/{userId}/recordList/{id}")
    public void updatePhoneBookById(@PathVariable("userId") UUID userId, @PathVariable("id") UUID id, @RequestBody PhoneRecord phoneBook) {
        userService.updatePhoneBookById(userId,id,phoneBook);
    }
    @GetMapping(path = "user/{userId}/recordList/number/{number}")
    public Optional<PhoneRecord> getPhoneBookByNumber( @PathVariable("userId")UUID userId,@PathVariable("number") String number) {
        return userService.getPhoneBookByNumber(userId,number);
    }

    }