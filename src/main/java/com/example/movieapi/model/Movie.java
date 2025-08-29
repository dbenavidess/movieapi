package com.example.movieapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "movies")
public class Movie {
    @Id
    private String id;

    private String title;
    private LocalDate releaseDate;
    private int runtime; // in minutes

    @DBRef
    private List<Actor> actors;

    @DBRef
    private List<Director> directors;
}