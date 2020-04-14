package ru.spring5.elena.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PublishingHouse {
    private Long publishingHouseId;
    private String title;
    private byte logo;
}
