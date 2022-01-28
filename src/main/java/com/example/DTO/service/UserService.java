package com.example.DTO.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.DTO.dto.UserLocationDTO;
import com.example.DTO.model.User;
import com.example.DTO.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<UserLocationDTO> getAllUsersLocation() {
        return userRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserLocationDTO convertEntityToDto(User user) {
        return new UserLocationDTO(user.getId(), user.getEmail(), user.getLocation().getPlace(),
                user.getLocation().getLatitude(), user.getLocation().getLongitude());
    }
}
