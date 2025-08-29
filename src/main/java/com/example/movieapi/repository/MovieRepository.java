package com.example.movieapi.repository;

import com.example.movieapi.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    // Search by exact title
    List<Movie> findByTitle(String title);

    // Search by partial title (case insensitive)
    List<Movie> findByTitleContainingIgnoreCase(String title);

    // Search by release date
    List<Movie> findByReleaseDate(LocalDate releaseDate);

    // Search by release date range
    List<Movie> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
}