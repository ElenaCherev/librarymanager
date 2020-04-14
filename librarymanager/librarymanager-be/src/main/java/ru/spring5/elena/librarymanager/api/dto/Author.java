package ru.spring5.elena.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Builder
@Getter
public class Author {
    private Long authorId;
    private String firstName; //имя
    private String surname; //фамилия
    private String patronymic; //отчество
    private Date birthdate; //
    private Date deathdate; //
    private String info; //доп. информация
    private String fio;
    private Set<Long> edidionIds;
}
