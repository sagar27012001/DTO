package com.example.DTO.repo;

import com.example.DTO.model.Location;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepo extends MongoRepository<Location, String> {

}
