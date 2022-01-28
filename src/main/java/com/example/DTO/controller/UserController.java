package com.example.DTO.controller;

import java.util.List;

import com.example.DTO.dto.UserLocationDTO;
import com.example.DTO.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users-location")
    public List<UserLocationDTO> getAllUsersLocation() {
        return userService.getAllUsersLocation();
    }
}
