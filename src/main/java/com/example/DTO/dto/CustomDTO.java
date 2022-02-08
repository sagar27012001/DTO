package com.example.DTO.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomDTO {
    String name;
    String email;
    String password;
    String place;
    String description;
    double longitude;
    double latitude;
}
