package ru.spring5.elena.librarymanager.mapper;

import ru.spring5.elena.librarymanager.api.dto.Book;
import ru.spring5.elena.librarymanager.domain.entity.BookEntity;
import ru.spring5.elena.librarymanager.domain.entity.EditionEntity;

public class BookMapper {
    public static Book map(BookEntity entity) {
        return Book.builder()
                .bookId(entity.getBookId())
                .isAvailable(entity.isAvailable())
                .isReserved(entity.isReserved())
                .shelfNumber(entity.getShelfNumber())
                .title(entity.getEdition()!=null
                		?entity.getEdition().getTitle()
                		: ""
                      )
                .editionId(entity.getBookId())
                .build();
    }

    public static BookEntity map(Book dto, EditionEntity edition) {
        return map(dto, new BookEntity(), edition);
    }

    public static BookEntity map(
    		Book dto, 
    		BookEntity entity,
    		EditionEntity edition
    ) {
        entity.setAvailable(dto.isAvailable());
        entity.setReserved(dto.isReserved());
        entity.setShelfNumber(dto.getShelfNumber());
        entity.setEdition(edition);
        return entity;
    }
}
