package com.example.movieapi.service;

import com.example.movieapi.dto.ActorDto;
import com.example.movieapi.exception.ResourceNotFoundException;
import com.example.movieapi.model.Actor;
import com.example.movieapi.repository.ActorRepository;
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

class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService actorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllActors_returnsList() {
        when(actorRepository.findAll()).thenReturn(Arrays.asList(
                new Actor("1", "Actor One", LocalDate.of(1990,1,1)),
                new Actor("2", "Actor Two", LocalDate.of(1985,5,5))
        ));

        List<ActorDto> actors = actorService.getAllActors();
        assertEquals(2, actors.size());
        assertEquals("Actor One", actors.get(0).getName());
    }

    @Test
    void getActorById_found_returnsDto() {
        Actor actor = new Actor("1", "Test Actor", LocalDate.of(1991, 1, 1));
        when(actorRepository.findById("1")).thenReturn(Optional.of(actor));

        Optional<ActorDto> result = actorService.getActorById("1");
        assertTrue(result.isPresent());
        assertEquals("Test Actor", result.get().getName());
    }

    @Test
    void getActorById_notFound_returnsEmpty() {
        when(actorRepository.findById("99")).thenReturn(Optional.empty());
        assertFalse(actorService.getActorById("99").isPresent());
    }

    @Test
    void createActor_savesAndReturnsDto() {
        ActorDto dto = new ActorDto();
        dto.setName("New Actor");
        dto.setDateOfBirth(LocalDate.of(2000, 1, 1));

        Actor saved = new Actor("1", "New Actor", LocalDate.of(2000, 1, 1));
        when(actorRepository.save(any(Actor.class))).thenReturn(saved);

        ActorDto result = actorService.createActor(dto);
        assertNotNull(result.getId());
        assertEquals("New Actor", result.getName());
    }

    @Test
    void updateActor_existingId_updates() {
        Actor existing = new Actor("1", "Old", LocalDate.of(1990, 1, 1));
        when(actorRepository.findById("1")).thenReturn(Optional.of(existing));

        Actor updated = new Actor("1", "Updated", LocalDate.of(1995, 5, 5));
        when(actorRepository.save(any(Actor.class))).thenReturn(updated);

        ActorDto dto = new ActorDto();
        dto.setName("Updated");
        dto.setDateOfBirth(LocalDate.of(1995, 5, 5));

        ActorDto result = actorService.updateActor("1", dto);
        assertEquals("Updated", result.getName());
    }

    @Test
    void updateActor_notFound_throws() {
        when(actorRepository.findById("99")).thenReturn(Optional.empty());
        ActorDto dto = new ActorDto();
        assertThrows(ResourceNotFoundException.class, () -> actorService.updateActor("99", dto));
    }

    @Test
    void deleteActor_callsRepository() {
        actorService.deleteActor("1");
        verify(actorRepository, times(1)).deleteById("1");
    }

    @Test
    void searchByName_returnsList() {
        when(actorRepository.findByNameContainingIgnoreCase("test"))
                .thenReturn(Arrays.asList(new Actor("1","Test Actor", LocalDate.now())));

        List<ActorDto> result = actorService.searchByName("test");
        assertEquals(1, result.size());
    }
}