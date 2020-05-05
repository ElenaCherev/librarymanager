package ru.elenacherev.librarymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.elenacherev.librarymanager.api.BookApi;
import ru.elenacherev.librarymanager.api.dto.Book;
import ru.elenacherev.librarymanager.api.dto.BookUsing;
import ru.elenacherev.librarymanager.services.BookService;

import java.util.List;

import javax.validation.Valid;

@RequestMapping(value = "librarymanager/books", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController implements BookApi {
    BookService bookService;

    @Override
    @GetMapping(value = "/{bookid}")
    public Book getBook(
            @PathVariable("bookid") Long bookId
    ) {
        return bookService.findBookById(bookId);
    }

    @Override
    @PostMapping("/search")
    public Page<Book> searchBooks(
            Pageable pageable
    ) {
        log.info("Searching books");
        return bookService.findAllByPage(pageable);
    }

    @Override
    @PostMapping
    public Book createBook(
            @Valid @RequestBody Book book
    ) {
        log.info("Creating book");
        return bookService.create(book);
    }

    @Override
    @PutMapping(value = "/{bookid}")
    public Book saveBook(
            @PathVariable("bookid") Long bookId,
            @Valid @RequestBody Book book
    ) {
        log.info("Updating book");
        return bookService.save(bookId, book);
    }

    @Override
    @PostMapping("/{bookid}/bookusings/search")
    public Page<BookUsing> searchBookUsings(
            @PathVariable("bookid") Long bookId,
            Pageable pageable
    ) {
        log.info("Listing BookUsing by Book");
        return bookService.findBookUsingsByBookId(bookId, pageable);
    }
    
    @Override
    @PutMapping("/{bookid}/bookusings/")
    public List<BookUsing> updateBookUsings(
    		@PathVariable("bookid") Long bookId,
    		@RequestParam("list") List<Long> bookUsingIds
    		)
    {
    	log.info("");
    	return bookService.updateBookUsings(bookId, bookUsingIds);
    }
}
