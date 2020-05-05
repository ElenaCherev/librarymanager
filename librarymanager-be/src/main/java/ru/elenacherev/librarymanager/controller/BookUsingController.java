package ru.elenacherev.librarymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ru.elenacherev.librarymanager.api.BookUsingApi;
import ru.elenacherev.librarymanager.api.dto.BookUsing;
import ru.elenacherev.librarymanager.services.BookUsingService;

import javax.validation.Valid;

@RequestMapping(value = "librarymanager/bookUsings", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class BookUsingController implements BookUsingApi {

    BookUsingService bookUsingService;

    @Override
	@GetMapping("/{bookusingid}")
    public BookUsing getBookUsing(
            @PathVariable("bookusingid") Long bookusingid
    ) {
    	log.info("Searching BookUsing by Id {0}", bookusingid );
    	return bookUsingService.findBookUsingById(bookusingid);
    }

    @Override
	@PostMapping("/search")
    public Page<BookUsing> searchBookUsings(
            Pageable pageable
    ) {
        log.info("Searching BookUsings");
        return bookUsingService.findAllByPage(pageable);
    }

    @Override
	@PostMapping
    public BookUsing createBookUsing(
            @Valid @RequestBody BookUsing bookUsing
    ) {
        log.info("Creating BookUsing");
        return bookUsingService.create(bookUsing);
    }

    @Override
	@PutMapping("/{bookusingid}")
    public BookUsing saveBookUsing(
            @PathVariable("bookusingid") Long bookUsingId,
            @Valid @RequestBody BookUsing bookUsing
    ) {
        log.info("Updating bookUsing");
        return bookUsingService.save(bookUsingId, bookUsing);
    }
}
