package ru.spring5.elena.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Genre {
    private Long genreId;
    private String title; //
}
