package ru.spring5.elena.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Lang {
    private Long langId;
    private String title; //
}
