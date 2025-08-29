package com.example.movieapi.repository;

import com.example.movieapi.model.Director;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends MongoRepository<Director, String> {

    // Search directors by name (case-insensitive)
    List<Director> findByNameContainingIgnoreCase(String name);
}