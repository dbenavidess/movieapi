package com.example.movieapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data                   // Lombok: getters, setters, equals, hashCode, toString
@Builder                // Lombok: builder pattern
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "actors") // MongoDB collection name
public class Actor {
    @Id
    private String id;           // MongoDB primary key

    private String name;         // Actor's full name
    private LocalDate dateOfBirth;
}