package com.example.movieapi.controller;

import com.example.movieapi.dto.MovieRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For JSON serialization

    @Test
    void createMovie_returnsCreatedMovie() throws Exception {
        MovieRequestDto dto = new MovieRequestDto();
        dto.setTitle("Integration Test Movie");
        dto.setReleaseDate(LocalDate.of(2024, 6, 10));
        dto.setRuntime(100);
        dto.setActorIds(new ArrayList<>());
        dto.setDirectorIds(new ArrayList<>());

        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Integration Test Movie"));
    }

    @Test
    void getMovies_returnsOk() throws Exception {
        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk());
    }
}