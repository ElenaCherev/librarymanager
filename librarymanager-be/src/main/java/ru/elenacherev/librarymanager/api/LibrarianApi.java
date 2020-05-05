package ru.elenacherev.librarymanager.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.elenacherev.librarymanager.api.dto.Librarian;

public interface LibrarianApi {

	Librarian getLibrarian(
			@NotNull Long librarianId
	);

	Page<Librarian> searchLibrarians(
			Pageable pageable
	);

	Librarian createLibrarian(
			@Valid Librarian librarian
	);

	Librarian saveLibrarian(
			@NotNull Long librarianId, 
			@Valid Librarian librarian
	);

}