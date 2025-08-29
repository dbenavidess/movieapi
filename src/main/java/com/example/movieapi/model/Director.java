package com.example.movieapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "directors")
public class Director {
    @Id
    private String id;

    private String name;
    private LocalDate dateOfBirth;
}