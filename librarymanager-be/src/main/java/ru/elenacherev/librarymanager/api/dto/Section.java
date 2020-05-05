package ru.elenacherev.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Section {
    private Long sectionId;
    private String title;
    private Long parentSectionId;
}