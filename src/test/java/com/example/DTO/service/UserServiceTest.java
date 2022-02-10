package com.example.DTO.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.example.DTO.controller.UserController;
import com.example.DTO.dto.CustomDTO;
import com.example.DTO.dto.UserLocationDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @Test
    public void getAllUsersLocationTest() {
        when(userController.getAllUsersLocation()).thenReturn(
                Stream.of(new UserLocationDTO("1", "sagar@logic.com", "Bangalore", 12.9,
                        77.5),
                        new UserLocationDTO("2", "random@logic.com", "Chennai", 13.9, 80.5))
                        .collect(Collectors.toList()));

        assertEquals(2, userController.getAllUsersLocation().size());
        assertEquals("1", userController.getAllUsersLocation().get(0).getId());
        assertEquals("Chennai",
                userController.getAllUsersLocation().get(1).getPlace());
        assertEquals(13.9, userController.getAllUsersLocation().get(1).getLatitude(),
                0.0);
        assertEquals(77.5,
                userController.getAllUsersLocation().get(0).getLongitude(), 0.0);
    }

    @Test
    public void getUserLocationTest() {
        String id = "1";
        when(userController.getUserLocation(id))
                .thenReturn(ResponseEntity.ok(new UserLocationDTO(id, "sagar@logic.com",
                        "surat", 23.33, 34.56)));

        assertEquals(id, ((UserLocationDTO) userController.getUserLocation(id).getBody()).getId());
        assertEquals(200, userController.getUserLocation(id).getStatusCodeValue());
        assertEquals("surat", ((UserLocationDTO) userController.getUserLocation(id).getBody()).getPlace());
        assertEquals(23.33, ((UserLocationDTO) userController.getUserLocation(id).getBody()).getLatitude(), 0.0);
        assertEquals(34.56, ((UserLocationDTO) userController.getUserLocation(id).getBody()).getLongitude(), 0.0);
    }

    @Test
    public void deleteUserTest() {
        String id = "1";
        when(userController.deleteUser(id)).thenReturn(ResponseEntity.ok("User deleted"));

        assertEquals("User deleted", userController.deleteUser(id).getBody());
        assertEquals(200, userController.deleteUser(id).getStatusCodeValue());
    }

    @Test
    public void addUserTest() {
        CustomDTO customDTO = new CustomDTO("new user", "new@user.com", "123", "place", "desc", 23.33, 33.33);
        when(userController.addUser(customDTO)).thenReturn(ResponseEntity.ok(customDTO));

        assertEquals(customDTO, userController.addUser(customDTO).getBody());
        assertEquals(200, userController.addUser(customDTO).getStatusCodeValue());
    }

    @Test
    public void updateUserTest() {
        String email = "new@email.com";
        CustomDTO customDTO = new CustomDTO("new user", "modified email", "modified pass", null, null, 0.0, 0.0);
        when(userController.updateUser(email, customDTO)).thenReturn(ResponseEntity.ok(customDTO));

        assertEquals(customDTO, userController.updateUser(email, customDTO).getBody());
        assertEquals(200, userController.updateUser(email, customDTO).getStatusCodeValue());
    }

    @Test
    public void updateLocationTest() {
        String place = "new place";
        CustomDTO customDTO = new CustomDTO(null, null, null, "new place", "new desc", 23.33, 33.33);
        when(userController.updateLocation(place, customDTO)).thenReturn(ResponseEntity.ok(customDTO));

        assertEquals(customDTO, userController.updateLocation(place, customDTO).getBody());
        assertEquals(200, userController.updateLocation(place, customDTO).getStatusCodeValue());
    }
}