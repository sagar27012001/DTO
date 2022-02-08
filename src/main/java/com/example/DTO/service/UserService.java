package com.example.DTO.service;

import java.util.List;
import java.util.stream.Collectors;
import com.example.DTO.dto.CustomDTO;
import com.example.DTO.dto.UserLocationDTO;
import com.example.DTO.model.Location;
import com.example.DTO.model.User;
import com.example.DTO.repo.LocationRepo;
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

    @Autowired
    private LocationRepo locationRepo;

    public List<UserLocationDTO> getAllUsersLocation() {
        return userRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
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

    public ResponseEntity<Object> addUser(CustomDTO customDTO) {
        User user = new User();
        Location location = new Location();
        location.setPlace(customDTO.getPlace());
        location.setDescription(customDTO.getDescription());
        location.setLatitude(customDTO.getLatitude());
        location.setLongitude(customDTO.getLongitude());
        locationRepo.save(location);
        Location location1 = locationRepo.findByPlace(customDTO.getPlace());
        user.setName(customDTO.getName());
        user.setEmail(customDTO.getEmail());
        user.setPassword(customDTO.getPassword());
        user.setLocation(location1);
        if (userRepo.findByEmail(customDTO.getEmail()) != null) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } else {
            userRepo.save(user);
            return ResponseEntity.ok(user);
        }
    }

    public ResponseEntity<Object> updateUser(String email, CustomDTO customDTO) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        user.setName(customDTO.getName());
        user.setEmail(customDTO.getEmail());
        user.setPassword(customDTO.getPassword());
        userRepo.save(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<Object> updateLocation(String place, CustomDTO customDTO) {
        Location location = locationRepo.findByPlace(place);
        if (location == null) {
            return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
        }
        location.setPlace(customDTO.getPlace());
        location.setDescription(customDTO.getDescription());
        location.setLatitude(customDTO.getLatitude());
        location.setLongitude(customDTO.getLongitude());
        locationRepo.save(location);
        return ResponseEntity.ok(location);
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
