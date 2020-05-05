package ru.elenacherev.librarymanager.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.elenacherev.librarymanager.api.dto.Book;
import ru.elenacherev.librarymanager.api.dto.BookUsing;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface BookApi {
    Book getBook(
            @NotNull Long bookId
    );

    Page<Book> searchBooks(
            Pageable pageable
    );

    Book createBook(
            @Valid Book book
    );

    Book saveBook(
            @NotNull Long bookId,
            @Valid Book book
    );
    
    Page<BookUsing> searchBookUsings(
    		@NotNull Long bookId,
            Pageable pageable
    );
    
    List<BookUsing> updateBookUsings(
    		@NotNull Long bookId,
    		@NotNull List<Long> bookUsingIds
    );
}
