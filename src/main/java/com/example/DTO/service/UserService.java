package com.example.DTO.service;

import java.util.List;
import java.util.stream.Collectors;
import com.example.DTO.dto.UserLocationDTO;
import com.example.DTO.model.User;
import com.example.DTO.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserLocationDTO> getAllUsersLocation() {
        return userRepo.findAll().stream().map(user -> modelMapper.map(user, UserLocationDTO.class))
                .collect(Collectors.toList());
    }

    public ResponseEntity<Object> getUserLocation(String id) {
        User user = userRepo.findById(id).orElse(new User());
        if (user.getName() == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(convertEntityToDto(user));
    }

    public ResponseEntity<Object> deleteUser(String id) {
        User user = userRepo.findById(id).orElse(new User());
        if (user.getName() == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        userRepo.delete(user);
        return ResponseEntity.ok("User deleted");
    }

    private UserLocationDTO convertEntityToDto(User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(user, UserLocationDTO.class);
    }

    // private UserLocationDTO convertEntityToDto(User user) {
    // return new UserLocationDTO(user.getId(), user.getEmail(),
    // user.getLocation().getPlace(),
    // user.getLocation().getLatitude(), user.getLocation().getLongitude());
    // }
}
