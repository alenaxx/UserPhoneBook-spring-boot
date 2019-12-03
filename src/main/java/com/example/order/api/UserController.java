package com.example.order.api;

import com.example.order.model.PhoneRecord;
import com.example.order.model.User;
import com.example.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public Optional<User> getUserById(@NotNull @PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@NotNull @PathVariable("id") UUID id) {
        userService.deleteUserById(id);
    }

    @PutMapping(path = "{id}")
    public void updateUserById(@NotNull @PathVariable("id") UUID id, @Valid @RequestBody User user) { //TODO работает ли NotNull
        userService.updateUserById(id, user);
    }

    @GetMapping(path = "user/{name}")
    public Optional<User> getUserByName(@NotNull @PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @PostMapping(path = "user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPhoneBook(@NotNull @PathVariable("userId") UUID userId, @Valid @RequestBody PhoneRecord phoneRecord) {
        userService.addPhoneBook(userId, phoneRecord);
    }

    @GetMapping(path = "user/{userId}/recordList")
    public List<PhoneRecord> getAllPhoneBook(@PathVariable("userId") UUID userId) {
        return userService.getAllPhoneBook(userId);
    }

    @GetMapping(path = "user/{userId}/recordList/{id}")
    public Optional<PhoneRecord> getPhoneBookById(@NotNull @PathVariable("userId") UUID userId, @NotNull @PathVariable("id") UUID id) {
        return userService.getPhoneBookById(userId, id);
    }

    @DeleteMapping(path = "user/{userId}/recordList/{id}")
    public void deletePhoneBookById(@NotNull @PathVariable("userId") UUID userId, @NotNull @PathVariable("id") UUID id) {
        userService.deletePhoneBookById(userId, id);
    }

    @PutMapping(path = "user/{userId}/recordList/{id}")
    public void updatePhoneBookById(@NotNull @PathVariable("userId") UUID userId, @NotNull @PathVariable("id") UUID id, @Valid @RequestBody PhoneRecord phoneBook) {
        userService.updatePhoneBookById(userId, id, phoneBook);
    }

    @GetMapping(path = "user/{userId}/recordList/number/{number}")
    public Optional<PhoneRecord> getPhoneBookByNumber(@NotNull @PathVariable("userId") UUID userId, @NotNull @PathVariable("number") String number) {
        return userService.getPhoneBookByNumber(userId, number);
    }

}