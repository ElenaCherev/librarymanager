package ru.elenacherev.librarymanager.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.elenacherev.librarymanager.api.dto.Author;
import ru.elenacherev.librarymanager.api.dto.Edition;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface AuthorApi {
    Author getAuthor(
    		@NotNull Long id
    );

    Author createAuthor(
    		@Valid Author author
    );

    Author saveAuthor(
    		@NotNull Long id, 
    		@Valid Author author
    );

    Page<Author> searchAuthors(
    		Pageable pageable
    );

    Page<Edition> searchEditions(
    		@NotNull Long id,  
    		Pageable pageable 
    );
    
    List<Edition> updateEditionsByAuthorId(
    		@NotNull Long authorid,
    		@NotNull List<Long> editionIds
    );
}
