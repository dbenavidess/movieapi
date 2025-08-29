package com.example.movieapi.service;

import com.example.movieapi.dto.DirectorDto;
import com.example.movieapi.exception.ResourceNotFoundException;
import com.example.movieapi.mapper.DirectorMapper;
import com.example.movieapi.model.Director;
import com.example.movieapi.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public List<DirectorDto> getAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(DirectorMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DirectorDto> getDirectorById(String id) {
        return directorRepository.findById(id).map(DirectorMapper::toDto);
    }

    public DirectorDto createDirector(DirectorDto dto) {
        Director director = Director.builder()
                .name(dto.getName())
                .dateOfBirth(dto.getDateOfBirth())
                .build();

        return DirectorMapper.toDto(directorRepository.save(director));
    }

    public DirectorDto updateDirector(String id, DirectorDto dto) {
        return directorRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setDateOfBirth(dto.getDateOfBirth());
            return DirectorMapper.toDto(directorRepository.save(existing));
        }).orElseThrow(() -> new ResourceNotFoundException("Director not found with id " + id));
    }

    public void deleteDirector(String id) {
        directorRepository.deleteById(id);
    }

    public List<DirectorDto> searchByName(String name) {
        return directorRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(DirectorMapper::toDto)
                .collect(Collectors.toList());
    }
}