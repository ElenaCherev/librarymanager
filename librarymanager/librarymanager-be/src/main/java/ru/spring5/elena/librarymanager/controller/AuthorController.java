package ru.spring5.elena.librarymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.spring5.elena.librarymanager.api.AuthorApi;
import ru.spring5.elena.librarymanager.api.dto.Author;
import ru.spring5.elena.librarymanager.api.dto.Edition;
import ru.spring5.elena.librarymanager.services.AuthorService;

import java.util.List;


@RequestMapping(value = "librarymanager/authors", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthorController implements AuthorApi {

    private final AuthorService authorService;

    @Override
    @GetMapping("/{authorid}")
    public Author getAuthor(
            @PathVariable("authorid") Long authorId
    ) {
        return authorService.findAuthorById(authorId);
    }

    @Override
    @PostMapping("/search")
    public Page<Author> searchAuthors(
            Pageable pageable
    ) {
        log.info("Listing authors");
        return authorService.findAllByPage(pageable);
    }


    @Override
    @PostMapping
    public Author createAuthor(
            @RequestBody Author author
    ) {
        log.info("Creating author");
        return authorService.create(author);
    }

    @Override
    @PutMapping("/{authorid}")
    public Author saveAuthor(
            @PathVariable("authorid") Long authorId,
            @RequestBody Author author
    ) {
        log.info("Updating author");
        return authorService.save(authorId, author);
    }

    @Override
    @PostMapping("/{authorid}/editions/search")
    public Page<Edition> searchEditions(
            @PathVariable("authorid") Long authorId,
            Pageable pageable
    ) {
        log.info("Listing editions by author");
        return authorService.findEditionsByAuthorId(authorId,pageable);
    }
    
    @Override
    @PutMapping("/{authorid}/editions/")
    public List<Edition> updateEditionsByAuthorId(
    		@PathVariable("authorid") Long authorId,
    		@RequestParam("list") List<Long> editionIds
    		)
    {
    	log.info("Updating list of editions for author {0}", authorId );
    	return authorService.updateEditionsByAuthorId(authorId, editionIds);
    }
    
}