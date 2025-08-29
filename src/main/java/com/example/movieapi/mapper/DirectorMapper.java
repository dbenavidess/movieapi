package com.example.movieapi.mapper;

import com.example.movieapi.dto.DirectorDto;
import com.example.movieapi.model.Director;

public class DirectorMapper {
    public static DirectorDto toDto(Director director) {
        if (director == null) return null;
        DirectorDto dto = new DirectorDto();
        dto.setId(director.getId());
        dto.setName(director.getName());
        dto.setDateOfBirth(director.getDateOfBirth());
        return dto;
    }
}