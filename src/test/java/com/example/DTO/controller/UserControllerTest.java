package com.example.DTO.controller;

import static org.junit.Assert.assertEquals;

import com.example.DTO.service.UserService;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Test
    public void testGetAllUsersLocation() throws Exception {
        UserService userService = new UserService();
        assertEquals(1, userService.getAllUsersLocation().size());
    }
}
