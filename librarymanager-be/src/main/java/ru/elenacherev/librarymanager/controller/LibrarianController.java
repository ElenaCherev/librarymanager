package ru.elenacherev.librarymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.elenacherev.librarymanager.api.LibrarianApi;
import ru.elenacherev.librarymanager.api.dto.Librarian;
import ru.elenacherev.librarymanager.services.LibrarianService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "librarymanager/librarians", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class LibrarianController implements LibrarianApi {
	LibrarianService librarianService;
	
	@Override
	@GetMapping("/{librarianid}")
    public Librarian getLibrarian(
            @PathVariable("librarianid") Long librarianId
    ) {
        return librarianService.findLibrarianById(librarianId);
    }

    @Override
	@PostMapping("/search")
    public Page<Librarian> searchLibrarians(
            Pageable pageable
    ) {
        log.info("Listing Librarians");
        return librarianService.findAllByPage(pageable);
    }


    @Override
	@PostMapping
    public Librarian createLibrarian(
            @RequestBody Librarian librarian
    ) {
        log.info("Creating librarian");
        return librarianService.create(librarian);
    }

    @Override
	@PutMapping("/{librarianid}")
    public Librarian saveLibrarian(
            @PathVariable("librarianid") Long librarianId,
            @RequestBody Librarian librarian
    ) {
        log.info("Updating librarian {0}", librarianId);
        return librarianService.save(librarianId, librarian);
    }

}
