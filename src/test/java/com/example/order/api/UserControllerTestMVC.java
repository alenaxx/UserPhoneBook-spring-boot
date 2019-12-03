package com.example.order.api;

import com.example.order.model.PhoneRecord;
import com.example.order.model.User;
import com.example.order.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)

public class UserControllerTestMVC {
    @Autowired
    private UserController controller;
    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoadsTest() throws Exception {
        assertThat(controller).isNotNull();
    }

    @MockBean
    UserService userService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void getAllUsersTest() throws Exception {
        List<User> expected = Arrays.asList(new User(UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6"), "Mark Tven", "+798508189"));
        when(userService.getAllUsers())
                .thenReturn(expected);
        String contentAsString = mvc.perform(MockMvcRequestBuilders
                .get("/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> usersResponse = objectMapper.readValue(contentAsString, new TypeReference<List<User>>() {
        });
        assertEquals(usersResponse.size(), expected.size());
    }


    @Test
    public void addUserTest() throws Exception {
                mvc.perform(MockMvcRequestBuilders
                .post("/user")
                .content(asJsonString(new User("Vasya Petrov", "+79050379948")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

    }


    @Test
    public void updateUserByIdTest() throws Exception {
                mvc.perform(MockMvcRequestBuilders
                .put("/user/{id}", UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6"))
                .content(asJsonString(new User(UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6"), "Mark Tven", "+798508189")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/user/{id}", "61f31926-ef83-4a74-a65b-a3f50e43a0d6"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserByIdTest() throws Exception {
        User user1 = new User("Test1", "+7123");
        when(userService.getUserById(UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6")))
                .thenReturn(java.util.Optional.of(user1));
        mvc.perform(MockMvcRequestBuilders
                .get("/user/{id}", "61f31926-ef83-4a74-a65b-a3f50e43a0d6")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").exists());
    }

    @Test
    public void getUserByNameTest() throws Exception {
        User user1 = new User("Mark Tven", "+7123");
        when(userService.getUserByName("Mark Tven")).thenReturn(java.util.Optional.of(user1));
        mvc.perform(MockMvcRequestBuilders
                .get("/user/user/{name}", "Mark Tven")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mark Tven"));
    }

    @Test
    public void addPhoneBookTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/user/user/{userId}", "2bf303c7-7777-4e3c-a14d-457251cb4d8d")
                .content(asJsonString(new PhoneRecord(UUID.fromString("2bf303c7-7777-4e3c-a14d-457251cb4d8d"), "Ivan Sokolov", "+79110379947", UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    public void getAllPhoneBook() throws Exception {
        List<PhoneRecord> expected = Arrays.asList(new PhoneRecord(UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6"),"Ivan Sokolov", "+79110379947", UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d")),new PhoneRecord(UUID.fromString("61f31926-9999-4a74-a65b-a3f50e43a0d6"),"Vasua Sokolov", "+79110379947", UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d")));
        when(userService.getAllPhoneBook(UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6"))).thenReturn(expected);
        String contentAsString  = mvc.perform(MockMvcRequestBuilders
                .get("/user/user/{userId}/recordList", "61f31926-ef83-4a74-a65b-a3f50e43a0d6")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();;
        ObjectMapper objectMapper = new ObjectMapper();
        List<PhoneRecord> usersResponse = objectMapper.readValue(contentAsString, new TypeReference<List<PhoneRecord>>() {
        });
        assertEquals(usersResponse.size(), expected.size());
    }

    @Test
    public void getPhoneBookById() throws Exception {
        PhoneRecord ph = new PhoneRecord(UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6"),"Ivan Sokolov", "+79110379947", UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"));
        when(userService.getPhoneBookById(UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6"),UUID.fromString("2bf303c7-7777-4e3c-a14d-457251cb4d8d"))).thenReturn(java.util.Optional.of(ph));
        mvc.perform(MockMvcRequestBuilders
                .get("/user/user/{userId}/recordList/{id}", "61f31926-ef83-4a74-a65b-a3f50e43a0d6", "2bf303c7-7777-4e3c-a14d-457251cb4d8d")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty());

    }

    @Test
    public void updatePhoneBookById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .put("/user/user/{userId}/recordList/{id}", "61f31926-ef83-4a74-a65b-a3f50e43a0d6", "2bf303c7-7777-4e3c-a14d-457251cb4d8d")
                .content(asJsonString(new PhoneRecord(UUID.fromString("2bf303c7-7777-4e3c-a14d-457251cb4d8d"), "Vasya Sokolov", "+79110379947", UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void getPhoneBookByNumber() throws Exception {
        PhoneRecord ph = new PhoneRecord(UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6"),"Ivan Sokolov", "+79110379947", UUID.fromString("2bf303c7-166b-4e3c-a14d-457251cb4d8d"));
        when(userService.getPhoneBookByNumber(UUID.fromString("61f31926-ef83-4a74-a65b-a3f50e43a0d6"),"+79110379947")).thenReturn(java.util.Optional.of(ph));
        mvc.perform(MockMvcRequestBuilders
                .get("/user/user/{userId}/recordList/number/{number}", "61f31926-ef83-4a74-a65b-a3f50e43a0d6", "+79110379947")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value("+79110379947"));

    }
}