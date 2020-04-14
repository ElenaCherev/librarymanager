package ru.spring5.elena.librarymanager.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.spring5.elena.librarymanager.api.dto.BookUsing;

public interface BookUsingApi {

	BookUsing getBookUsing(
			@NotNull Long bookusingid
	);

	Page<BookUsing> searchBookUsings(
			Pageable pageable
	);

	BookUsing createBookUsing(
			@Valid BookUsing bookUsing
	);

	BookUsing saveBookUsing(
			@NotNull Long bookUsingId, 
			@Valid BookUsing bookUsing
	);

}