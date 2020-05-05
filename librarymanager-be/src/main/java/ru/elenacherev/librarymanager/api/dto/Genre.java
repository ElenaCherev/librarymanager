package ru.elenacherev.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Genre {
    private Long genreId;
    private String title; //
}
