package com.example.movieapi.service;

import com.example.movieapi.dto.MovieRequestDto;
import com.example.movieapi.dto.MovieResponseDto;
import com.example.movieapi.exception.ResourceNotFoundException;
import com.example.movieapi.model.Actor;
import com.example.movieapi.model.Director;
import com.example.movieapi.model.Movie;
import com.example.movieapi.repository.ActorRepository;
import com.example.movieapi.repository.DirectorRepository;
import com.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ActorRepository actorRepository;
    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie movie;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movie = Movie.builder()
                .id("1")
                .title("Test")
                .releaseDate(LocalDate.now())
                .runtime(120)
                .actors(Collections.emptyList())
                .directors(Collections.emptyList())
                .build();
    }

    @Test
    void getAllMovies_returnsList() {
        when(movieRepository.findAll()).thenReturn(List.of(movie));
        List<MovieResponseDto> result = movieService.getAllMovies();
        assertEquals(1, result.size());
    }

    @Test
    void getMovieById_found_returnsDto() {
        when(movieRepository.findById("1")).thenReturn(Optional.of(movie));
        assertTrue(movieService.getMovieById("1").isPresent());
    }

    @Test
    void getMovieById_notFound_returnsEmpty() {
        when(movieRepository.findById("x")).thenReturn(Optional.empty());
        assertFalse(movieService.getMovieById("x").isPresent());
    }

    @Test
    void createMovie_saves() {
        MovieRequestDto dto = new MovieRequestDto();
        dto.setTitle("New Movie");
        dto.setReleaseDate(LocalDate.of(2024,1,1));
        dto.setRuntime(100);
        dto.setActorIds(Collections.emptyList());
        dto.setDirectorIds(Collections.emptyList());

        when(actorRepository.findAllById(any())).thenReturn(Collections.emptyList());
        when(directorRepository.findAllById(any())).thenReturn(Collections.emptyList());
        when(movieRepository.save(any())).thenReturn(movie);

        MovieResponseDto result = movieService.createMovie(dto);
        assertEquals("Test", result.getTitle());
    }

    @Test
    void updateMovie_found_updatesFields() {
        MovieRequestDto dto = new MovieRequestDto();
        dto.setTitle("Updated");
        dto.setReleaseDate(LocalDate.of(2025,1,1));
        dto.setRuntime(150);
        dto.setActorIds(List.of("a1"));
        dto.setDirectorIds(List.of("d1"));

        when(movieRepository.findById("1")).thenReturn(Optional.of(movie));
        when(actorRepository.findAllById(any())).thenReturn(List.of(new Actor()));
        when(directorRepository.findAllById(any())).thenReturn(List.of(new Director()));
        when(movieRepository.save(any())).thenReturn(movie);

        MovieResponseDto res = movieService.updateMovie("1", dto);
        assertNotNull(res);
    }

    @Test
    void updateMovie_notFound_throws() {
        when(movieRepository.findById("bad")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> movieService.updateMovie("bad", new MovieRequestDto()));
    }

    @Test
    void deleteMovie_deletes() {
        movieService.deleteMovie("1");
        verify(movieRepository).deleteById("1");
    }

    @Test
    void searchByTitle_returnsList() {
        when(movieRepository.findByTitleContainingIgnoreCase("test")).thenReturn(List.of(movie));
        assertEquals(1, movieService.searchByTitle("test").size());
    }

    @Test
    void searchByReleaseDate_returnsList() {
        when(movieRepository.findByReleaseDate(any())).thenReturn(List.of(movie));
        assertEquals(1, movieService.searchByReleaseDate(LocalDate.now()).size());
    }
}