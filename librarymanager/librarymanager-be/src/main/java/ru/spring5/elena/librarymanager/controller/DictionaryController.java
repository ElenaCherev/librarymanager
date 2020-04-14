package ru.spring5.elena.librarymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.spring5.elena.librarymanager.api.DictionaryApi;
import ru.spring5.elena.librarymanager.api.dto.Edition;
import ru.spring5.elena.librarymanager.api.dto.Genre;
import ru.spring5.elena.librarymanager.api.dto.Lang;
import ru.spring5.elena.librarymanager.services.GenreService;
import ru.spring5.elena.librarymanager.services.LangService;

import java.util.List;

@RequestMapping(value = "librarymanager/dictionary", 
				produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class DictionaryController implements DictionaryApi {
    private final GenreService genresService;
    private final LangService langService;

    @Override
	@GetMapping("/genres/{genreid}")
    public Genre getGenre(
            @PathVariable("genreid") Long genreId
    ) {
        return genresService.findById(genreId);
    }
    
    @Override
	@PostMapping("/genres/search")
    public List<Genre> searchGenres() {
        log.info("Searching genres");
        return genresService.findAll();
    }
    
    @Override
	@PostMapping("/genres/{genreid}/editions/search")
    public Page<Edition> searchEditionsByGenreId(
            @PathVariable("genreid") Long genreId,
            Pageable pageable
    ) {
        log.info("Listing editions by genreId {0}", genreId);
        return genresService.findEditionsByGenreId(genreId,pageable);
    }
    
    @Override
    @PutMapping("/genres/{genreid}/editions/")
    public List<Edition> updateEditionsByGenreId(
    		@PathVariable("genreid") Long genreId,
    		@RequestParam("list") List<Long> editionIds
    		)
    {
    	log.info("Updating list of editions for genre {0}", genreId );
    	return genresService.updateEditionsByGenreId(genreId, editionIds);
    }
    
    @Override
	@GetMapping("/langs/{langid}")
    public Lang getLang(
            @PathVariable("langid") Long langId
    ) {
        return langService.findLangById(langId);
    }
    
    @Override
	@PostMapping("/langs/search")
    public List<Lang> searchLangs() {
        log.info("Searching langs");
        return langService.findAll();
    }
    
    @Override
	@PostMapping("/langs/lang/{langid}/editions/search")
    public Page<Edition> searchEditionsByLangId(
            @PathVariable("langid") Long langId,
            Pageable pageable
    ) {
        log.info("Listing editions by langId {0}", langId);
        return langService.findEditionsByLangId(langId,pageable);
    }
    
    @Override
    @PutMapping("/langs/lang/{langid}/editions/")
    public List<Edition> updateEditionsByLangId(
    		@PathVariable("langid") Long langId,
    		@RequestParam("list") List<Long> editionIds
    		)
    {
    	log.info("Updating list of editions for lang {0}", langId );
    	return langService.updateEditionsByLangId(langId, editionIds);
    }
    
    @Override
	@PostMapping("/langs/publlang/{publlangid}/editions/search")
    public Page<Edition> searchEditionsByPublLangId(
            @PathVariable("publlangid") Long publLangId,
            Pageable pageable
    ) {
        log.info("Listing editions by publlangId {0}", publLangId);
        return langService.findEditionsByPublLangId(publLangId,pageable);
    }
    
    @Override
    @PutMapping("/langs/publlang/{publlangid}/editions/")
    public List<Edition> updateEditionsByPubllangId(
    		@PathVariable("publlangid") Long publLangId,
    		@RequestParam("list") List<Long> editionIds
    		)
    {
    	log.info("Updating list of editions for genre {0}", publLangId );
    	return langService.updateEditionsByPubllangId(publLangId, editionIds);
    }
}
