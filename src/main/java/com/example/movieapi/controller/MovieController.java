package com.example.movieapi.controller;

import com.example.movieapi.dto.MovieRequestDto;
import com.example.movieapi.dto.MovieResponseDto;
import com.example.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MovieResponseDto createMovie(@RequestBody MovieRequestDto dto) {
        return movieService.createMovie(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable String id, @RequestBody MovieRequestDto dto) {
        try {
            return ResponseEntity.ok(movieService.updateMovie(id, dto));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public List<MovieResponseDto> searchByTitle(@RequestParam String title) {
        return movieService.searchByTitle(title);
    }

    @GetMapping("/search/releaseDate")
    public List<MovieResponseDto> searchByReleaseDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return movieService.searchByReleaseDate(date);
    }
}