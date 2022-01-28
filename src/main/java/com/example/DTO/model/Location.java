package com.example.DTO.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "locations")
public class Location {
    @Id
    private String id;
    private String place;
    private String description;
    private double longitude;
    private double latitude;
}
