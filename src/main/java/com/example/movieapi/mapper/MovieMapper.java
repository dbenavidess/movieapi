package com.example.movieapi.mapper;

import com.example.movieapi.dto.*;
import com.example.movieapi.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {
    public static MovieResponseDto toDto(Movie movie) {
        if (movie == null) return null;

        MovieResponseDto dto = new MovieResponseDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setRuntime(movie.getRuntime());

        dto.setActors(movie.getActors()
                .stream()
                .map(ActorMapper::toDto)
                .collect(Collectors.toList())
        );

        dto.setDirectors(movie.getDirectors()
                .stream()
                .map(DirectorMapper::toDto)
                .collect(Collectors.toList())
        );

        return dto;
    }
}