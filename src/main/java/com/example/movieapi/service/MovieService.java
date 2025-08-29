package com.example.movieapi.service;

import com.example.movieapi.dto.MovieRequestDto;
import com.example.movieapi.dto.MovieResponseDto;
import com.example.movieapi.exception.ResourceNotFoundException;
import com.example.movieapi.mapper.MovieMapper;
import com.example.movieapi.model.Actor;
import com.example.movieapi.model.Director;
import com.example.movieapi.model.Movie;
import com.example.movieapi.repository.ActorRepository;
import com.example.movieapi.repository.DirectorRepository;
import com.example.movieapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;

    public List<MovieResponseDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<MovieResponseDto> getMovieById(String id) {
        return movieRepository.findById(id).map(MovieMapper::toDto);
    }

    public MovieResponseDto createMovie(MovieRequestDto dto) {
        List<Actor> actors = actorRepository.findAllById(dto.getActorIds());
        List<Director> directors = directorRepository.findAllById(dto.getDirectorIds());

        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .releaseDate(dto.getReleaseDate())
                .runtime(dto.getRuntime())
                .actors(actors)
                .directors(directors)
                .build();

        return MovieMapper.toDto(movieRepository.save(movie));
    }

    public MovieResponseDto updateMovie(String id, MovieRequestDto dto) {
        return movieRepository.findById(id).map(existing -> {
            List<Actor> actors = actorRepository.findAllById(dto.getActorIds());
            List<Director> directors = directorRepository.findAllById(dto.getDirectorIds());

            existing.setTitle(dto.getTitle());
            existing.setReleaseDate(dto.getReleaseDate());
            existing.setRuntime(dto.getRuntime());
            existing.setActors(actors);
            existing.setDirectors(directors);

            return MovieMapper.toDto(movieRepository.save(existing));
        }).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + id));
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

    public List<MovieResponseDto> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<MovieResponseDto> searchByReleaseDate(LocalDate date) {
        return movieRepository.findByReleaseDate(date)
                .stream()
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
    }
}