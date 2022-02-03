package com.example.DTO.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserService.class)
public class UserServiceTest {

    @Test
    public void testGetUserLocation() {
        UserService userService = new UserService();
        assertEquals("User not found", userService.getUserLocation("1").getBody().toString());
    }
}
