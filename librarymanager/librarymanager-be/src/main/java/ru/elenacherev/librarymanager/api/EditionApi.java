package ru.elenacherev.librarymanager.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.elenacherev.librarymanager.api.dto.Book;
import ru.elenacherev.librarymanager.api.dto.Edition;
import ru.elenacherev.librarymanager.api.dto.Order;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


public interface EditionApi  {
    Edition getEdition(
    		@NotNull Long id
    );
    
    Page<Edition> searchEditions(
    		Pageable pageable
    );
    
    Edition createEdition(
    		@Valid Edition edition
    );
    
    Edition saveEdition(
    		@NotNull Long editionid,
    		@Valid Edition edition
    );
    
    Page<Book> searchBooksByEditionId(
    		@NotNull Long editionId,
            Pageable pageable
    );

	Page<Order> searchOrdersByEditionId(
			@NotNull Long editionId, 
			Pageable pageable
	);

	List<Book> updateBooksByEditionId(
			@NotNull Long editionId, 
			@NotNull List<Long> bookIds
	);

	List<Order> updateOrdersByEditionId(
			@NotNull Long editionId, 
			@NotNull List<Long> orderIds
	);
}
