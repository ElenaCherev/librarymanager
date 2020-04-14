package ru.spring5.elena.librarymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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

import ru.spring5.elena.librarymanager.api.SectionApi;
import ru.spring5.elena.librarymanager.api.dto.Edition;
import ru.spring5.elena.librarymanager.api.dto.Section;
import ru.spring5.elena.librarymanager.services.SectionService;


@RequestMapping(value = "librarymanager/sections", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class SectionController implements SectionApi {
    private final SectionService sectionService;

    @Override
	@GetMapping("/{sectionid}")
    public Section getSection(
            @PathVariable("sectionid") Long sectionId
    ) {
        return sectionService.findSectionById(sectionId);
    }
    
    @Override
	@PostMapping
    public Page<Section> searchRootSections(
    		Pageable pageable
    ) {
        log.info("Listing rootsections");
        return sectionService.findAllRootSections(pageable);
    }
    
    @Override
	@PostMapping("/{sectionid}/sections/search")
    public Page<Section> searchSectionsByParentSectionId(
    		@PathVariable("sectionid") Long sectionId,
    		Pageable pageable
    ) {
        log.info("Listing subsections by Section {0}", sectionId);
        return sectionService.findAllByParentSectionId(sectionId, pageable);
    }
    
    @Override
	@PostMapping("/{sectionid}/editions/search")
    public Page<Edition> searchEditionsBySectionId(
            @PathVariable("sectionid") Long sectionId,
            Pageable pageable
    ) {
        log.info("Listing editions by sectionid {0}", sectionId);
        return sectionService.findeAllEditionsBySectionId(sectionId, pageable);
    }
    
    @Override
    @PutMapping("/{sectionid}/editions/")
    public List<Edition> updateEditionsBySectionId(
    		@PathVariable("sectionid") Long sectionId,
    		@RequestParam("list") List<Long> editionIds
    		)
    {
    	log.info("Updating list of editions for section {0}", sectionId );
    	return sectionService.updateEditionsByAuthorId(sectionId, editionIds);
    }
 }
