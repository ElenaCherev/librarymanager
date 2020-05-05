package ru.elenacherev.librarymanager.mapper;

import ru.elenacherev.librarymanager.api.dto.BookUsing;
import ru.elenacherev.librarymanager.domain.entity.BookEntity;
import ru.elenacherev.librarymanager.domain.entity.BookUsingEntity;
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity;

public class BookUsingMapper {
    public static BookUsing map(BookUsingEntity entity) {
        return BookUsing.builder()
                .bookUsingId(entity.getBookUsingId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .bookId(entity.getBook()!=null
                			?entity.getBook().getBookId()
                			:0
                		)
                .readerId(entity.getReader().getReaderId())
                .build();
    }

    public static BookUsingEntity map(
    		BookUsing dto,
    		ReaderEntity reader,
    		BookEntity book
    ) {
        return map(new BookUsingEntity(), dto, reader, book);
    }

    public static BookUsingEntity map(
    		BookUsingEntity entity,
    		BookUsing dto, 
    		ReaderEntity reader,
    		BookEntity book
    ) {
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setBook(book);
        entity.setReader(reader);
        return entity;
    }
}