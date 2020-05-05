package ru.elenacherev.librarymanager.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.elenacherev.librarymanager.api.dto.BookUsing;
import ru.elenacherev.librarymanager.api.dto.Order;
import ru.elenacherev.librarymanager.api.dto.Reader;

public interface ReaderApi {

	Reader getReader(
			@NotNull Long readerId
	);

	Page<Reader> searchReaders(
			Pageable pageable
	);

	Reader createReader(
			@Valid Reader reader
	);

	Reader saveReader(
			@NotNull Long readerId, 
			@Valid Reader reader
	);

	Page<Order> searchOrders(
			Long readerId, 
			Pageable pageable
	);

	List<Order> updateOrders(
			@NotNull Long readerId, 
			@NotNull List<Long> orderIds
	);

	Page<BookUsing> searchBookUsings(
			@NotNull Long readerId, 
			Pageable pageable
	);

	List<BookUsing> updateBookUsings(
			@NotNull Long readerId, 
			@NotNull List<Long> bookUsingIds
	);

}