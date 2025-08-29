package com.example.movieapi.controller;

import com.example.movieapi.dto.DirectorDto;
import com.example.movieapi.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public List<DirectorDto> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> getDirectorById(@PathVariable String id) {
        return directorService.getDirectorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DirectorDto createDirector(@RequestBody DirectorDto dto) {
        return directorService.createDirector(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDto> updateDirector(@PathVariable String id, @RequestBody DirectorDto dto) {
        try {
            return ResponseEntity.ok(directorService.updateDirector(id, dto));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable String id) {
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<DirectorDto> searchByName(@RequestParam String name) {
        return directorService.searchByName(name);
    }
}