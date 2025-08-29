package com.example.movieapi.controller;

import com.example.movieapi.dto.ActorDto;
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
class ActorControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAndGetActor() throws Exception {
        ActorDto dto = new ActorDto();
        dto.setName("Integration Actor");
        dto.setDateOfBirth(LocalDate.of(1990,1,1));

        String response = mockMvc.perform(post("/api/actors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Actor"))
                .andReturn().getResponse().getContentAsString();

        ActorDto created = objectMapper.readValue(response, ActorDto.class);

        mockMvc.perform(get("/api/actors/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Actor"));
    }

    @Test
    void getNonExistingActor_returnsNotFound() throws Exception {
        mockMvc.perform(get("/api/actors/doesnotexist"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"));
    }
}