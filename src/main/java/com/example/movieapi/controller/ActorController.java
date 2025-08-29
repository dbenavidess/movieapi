package com.example.movieapi.controller;

import com.example.movieapi.dto.ActorDto;
import com.example.movieapi.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public List<ActorDto> getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable String id) {
        return actorService.getActorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ActorDto createActor(@RequestBody ActorDto dto) {
        return actorService.createActor(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDto> updateActor(@PathVariable String id, @RequestBody ActorDto dto) {
        try {
            return ResponseEntity.ok(actorService.updateActor(id, dto));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable String id) {
        actorService.deleteActor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<ActorDto> searchByName(@RequestParam String name) {
        return actorService.searchByName(name);
    }
}