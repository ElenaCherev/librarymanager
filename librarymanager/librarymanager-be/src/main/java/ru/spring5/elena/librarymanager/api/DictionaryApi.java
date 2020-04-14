package ru.spring5.elena.librarymanager.api;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.spring5.elena.librarymanager.api.dto.Edition;
import ru.spring5.elena.librarymanager.api.dto.Genre;
import ru.spring5.elena.librarymanager.api.dto.Lang;

public interface DictionaryApi {

	Genre getGenre(
			@NotNull  Long genreId
	);

	List<Genre> searchGenres();

	Page<Edition> searchEditionsByGenreId(
			@NotNull  Long genreId, 
			Pageable pageable
	);

	Lang getLang(
			@NotNull Long langId
	);

	List<Lang> searchLangs();

	Page<Edition> searchEditionsByLangId(
			@NotNull Long langId, 
			Pageable pageable
	);

	Page<Edition> searchEditionsByPublLangId(
			@NotNull Long langId,
            Pageable pageable
    );

	List<Edition> updateEditionsByGenreId(
			@NotNull Long genreId, 
			@NotNull List<Long> editionIds
	);

	List<Edition> updateEditionsByLangId(
			@NotNull Long langId, 
			@NotNull List<Long> editionIds
	);

	List<Edition> updateEditionsByPubllangId(
			@NotNull Long publlangId, 
			@NotNull List<Long> editionIds
	);
}