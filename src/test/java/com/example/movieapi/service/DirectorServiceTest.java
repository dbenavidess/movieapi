package com.example.movieapi.service;

import com.example.movieapi.dto.DirectorDto;
import com.example.movieapi.exception.ResourceNotFoundException;
import com.example.movieapi.model.Director;
import com.example.movieapi.repository.DirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DirectorServiceTest {

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorService directorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDirectors_returnsList() {
        when(directorRepository.findAll()).thenReturn(Arrays.asList(
                new Director("1", "Dir A", LocalDate.now())
        ));
        assertFalse(directorService.getAllDirectors().isEmpty());
    }

    @Test
    void getDirectorById_found_returnsDto() {
        when(directorRepository.findById("1")).thenReturn(Optional.of(new Director("1","X", LocalDate.now())));
        assertTrue(directorService.getDirectorById("1").isPresent());
    }

    @Test
    void createDirector_saves() {
        DirectorDto dto = new DirectorDto();
        dto.setName("Dir");
        dto.setDateOfBirth(LocalDate.now());

        when(directorRepository.save(any())).thenReturn(new Director("1","Dir", LocalDate.now()));
        assertNotNull(directorService.createDirector(dto).getId());
    }

    @Test
    void updateDirector_found_updates() {
        when(directorRepository.findById("1")).thenReturn(Optional.of(new Director("1","Dir", LocalDate.now())));
        when(directorRepository.save(any())).thenReturn(new Director("1","Updated", LocalDate.now()));

        DirectorDto dto = new DirectorDto();
        dto.setName("Updated");
        dto.setDateOfBirth(LocalDate.now());

        assertEquals("Updated", directorService.updateDirector("1", dto).getName());
    }

    @Test
    void updateDirector_notFound_throws() {
        when(directorRepository.findById("x")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> directorService.updateDirector("x", new DirectorDto()));
    }

    @Test
    void deleteDirector_deletes() {
        directorService.deleteDirector("1");
        verify(directorRepository).deleteById("1");
    }

    @Test
    void searchByName_returnsList() {
        when(directorRepository.findByNameContainingIgnoreCase("a"))
                .thenReturn(List.of(new Director("1","ABC", LocalDate.now())));
        assertEquals(1, directorService.searchByName("a").size());
    }
}