package ru.spring5.elena.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class BookUsing {
    private Long bookUsingId;
    private Date startDate; //Книгу взяли
    private Date endDate; //Книгу вернули
    private Long readerId; //кто взял книгу
    private Long bookId; //какую книгу взял
}
