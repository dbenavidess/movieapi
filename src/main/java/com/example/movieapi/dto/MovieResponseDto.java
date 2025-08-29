package com.example.movieapi.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class MovieResponseDto {
    private String id;
    private String title;
    private LocalDate releaseDate;
    private int runtime;

    private List<ActorDto> actors;
    private List<DirectorDto> directors;
}