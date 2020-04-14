package ru.spring5.elena.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Book {
    private Long bookId;
    private boolean isReserved;
    private boolean isAvailable;
    private int shelfNumber;
    private String title;
    private Long editionId;
}
