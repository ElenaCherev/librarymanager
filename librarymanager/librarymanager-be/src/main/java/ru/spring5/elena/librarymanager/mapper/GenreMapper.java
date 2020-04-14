package ru.spring5.elena.librarymanager.mapper;

import ru.spring5.elena.librarymanager.api.dto.Genre;
import ru.spring5.elena.librarymanager.domain.entity.GenreEntity;

public class GenreMapper {
    public static Genre map(GenreEntity entity) {
        return Genre.builder()
                .genreId(entity.getGenreId())
                .title(entity.getTitle())
                .build();
    }

    public static GenreEntity map(Genre dto) {
        return map(dto, new GenreEntity());
    }

    public static GenreEntity map(Genre dto, GenreEntity entity) {
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
