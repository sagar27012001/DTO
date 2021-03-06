package com.example.DTO.controller;

import java.util.List;

import com.example.DTO.dto.CustomDTO;
import com.example.DTO.dto.UserLocationDTO;
import com.example.DTO.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/users-location/{id}", produces = { "application/json", "application/xml" })
    public List<UserLocationDTO> getAllUsersLocation(@PathVariable String id) {
        return userService.getAllUsersLocation();
    }

    @GetMapping(path = "/user/{id}", produces = { "application/json", "application/xml" })
    public ResponseEntity<Object> getUserLocation(@PathVariable String id) {
        return userService.getUserLocation(id);
    }

    @DeleteMapping(path = "/user/{id}", produces = { "application/json", "application/xml" })
    public String deleteUser(@PathVariable String id) {
        if (id == null) {
            return "User id can't be null";
        }
        if (id.isEmpty()) {
            return "User id is empty";
        }
        return userService.deleteUser(id);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Object> addUser(@RequestBody CustomDTO customDTO) {
        return userService.addUser(customDTO);
    }

    @PutMapping(path = "/user/{email}")
    public ResponseEntity<Object> updateUser(@PathVariable String email, @RequestBody CustomDTO customDTO) {
        return userService.updateUser(email, customDTO);
    }

    @PutMapping(path = "/location/{place}")
    public ResponseEntity<Object> updateLocation(@PathVariable String place, @RequestBody CustomDTO customDTO) {
        return userService.updateLocation(place, customDTO);
    }

}
