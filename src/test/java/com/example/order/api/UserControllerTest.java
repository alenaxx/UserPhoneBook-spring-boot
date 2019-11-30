package com.example.order.api;

import com.example.order.model.User;
import com.example.order.service.UserService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController sut;

    @Test
    public void testGetAllUsers() throws Exception {
     /*   //prepare
        when(userService.getAllUsers()).thenReturn(ImmutableList.of());
        //testing
        List<User> userServices = sut.getAllUsers();
        //validate
        verify(userService).getAllUsers();*/
    }


}