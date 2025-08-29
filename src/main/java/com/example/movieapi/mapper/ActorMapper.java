package com.example.movieapi.mapper;

import com.example.movieapi.dto.ActorDto;
import com.example.movieapi.model.Actor;

public class ActorMapper {
    public static ActorDto toDto(Actor actor) {
        if (actor == null) return null;
        ActorDto dto = new ActorDto();
        dto.setId(actor.getId());
        dto.setName(actor.getName());
        dto.setDateOfBirth(actor.getDateOfBirth());
        return dto;
    }
}