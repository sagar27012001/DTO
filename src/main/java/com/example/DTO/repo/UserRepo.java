package com.example.DTO.repo;

import com.example.DTO.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

}
