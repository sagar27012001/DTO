package com.example.DTO.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationDTO {
    private String id;
    private String email;
    private String place;
    private double latitude;
    private double longitude;
}
