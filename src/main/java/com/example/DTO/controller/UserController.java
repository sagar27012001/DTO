package com.example.DTO.controller;

import java.util.List;

import com.example.DTO.dto.UserLocationDTO;
import com.example.DTO.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/users-location", produces = { "application/json", "application/xml" })
    public List<UserLocationDTO> getAllUsersLocation() {
        return userService.getAllUsersLocation();
    }

    @GetMapping(path = "/user/{id}", produces = { "application/json", "application/xml" })
    public ResponseEntity<Object> getUserLocation(@PathVariable String id) {
        return userService.getUserLocation(id);
    }
}
