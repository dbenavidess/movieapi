package com.example.movieapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActorDto {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
}