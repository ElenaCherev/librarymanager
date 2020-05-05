package ru.elenacherev.librarymanager.mapper;

import ru.elenacherev.librarymanager.api.dto.Genre;
import ru.elenacherev.librarymanager.domain.entity.GenreEntity;

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
