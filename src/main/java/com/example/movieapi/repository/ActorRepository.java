package com.example.movieapi.repository;

import com.example.movieapi.model.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends MongoRepository<Actor, String> {

    // Search actors by name (case-insensitive)
    List<Actor> findByNameContainingIgnoreCase(String name);
}