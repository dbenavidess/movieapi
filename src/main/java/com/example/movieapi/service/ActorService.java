package com.example.movieapi.service;

import com.example.movieapi.dto.ActorDto;
import com.example.movieapi.exception.ResourceNotFoundException;
import com.example.movieapi.mapper.ActorMapper;
import com.example.movieapi.model.Actor;
import com.example.movieapi.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public List<ActorDto> getAllActors() {
        return actorRepository.findAll()
                .stream()
                .map(ActorMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ActorDto> getActorById(String id) {
        return actorRepository.findById(id).map(ActorMapper::toDto);
    }

    public ActorDto createActor(ActorDto dto) {
        Actor actor = Actor.builder()
                .name(dto.getName())
                .dateOfBirth(dto.getDateOfBirth())
                .build();

        return ActorMapper.toDto(actorRepository.save(actor));
    }

    public ActorDto updateActor(String id, ActorDto dto) {
        return actorRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setDateOfBirth(dto.getDateOfBirth());
            return ActorMapper.toDto(actorRepository.save(existing));
        }).orElseThrow(() -> new ResourceNotFoundException("Actor not found with id " + id));
    }

    public void deleteActor(String id) {
        actorRepository.deleteById(id);
    }

    public List<ActorDto> searchByName(String name) {
        return actorRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ActorMapper::toDto)
                .collect(Collectors.toList());
    }
}