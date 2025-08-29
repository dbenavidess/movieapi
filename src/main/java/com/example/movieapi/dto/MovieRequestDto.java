package com.example.movieapi.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class MovieRequestDto {
    private String title;
    private LocalDate releaseDate;
    private int runtime;

    // IDs of existing actors and directors
    private List<String> actorIds;
    private List<String> directorIds;
}