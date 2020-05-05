package ru.elenacherev.librarymanager.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.elenacherev.librarymanager.api.PublishingHouseApi;
import ru.elenacherev.librarymanager.api.dto.Edition;
import ru.elenacherev.librarymanager.api.dto.PublishingHouse;
import ru.elenacherev.librarymanager.services.PublishingHouseService;

@RequestMapping(value = "librarymanager/publishinghouse", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class PublishingHouseController implements PublishingHouseApi  {
    private final PublishingHouseService publishingHouseService;

    @Override
	@GetMapping("/{publishinghouseid}")
    public PublishingHouse getPublishingHouse(
            @PathVariable("publishinghouseid") Long publishingHouseId
    ) {
        return publishingHouseService.findPublishingHouseById(publishingHouseId);
    }

    @Override
	@PostMapping("/search")
    public Page<PublishingHouse> searchPublishingHouses(
            Pageable pageable
    ) {
        log.info("Listing PublishingHouses");
        return publishingHouseService.findAllByPage(pageable);
    }


    @Override
	@PostMapping
    public PublishingHouse createPublishingHouse(
            @RequestBody PublishingHouse publishingHouse
    ) {
        log.info("Creating PublishingHouse");
        return publishingHouseService.create(publishingHouse);
    }

    @Override
	@PutMapping("/{publishinghouseid}")
    public PublishingHouse savePublishingHouse(
            @PathVariable("publishinghouseid") Long publishingHouseId,
            @RequestBody PublishingHouse publishingHouse
    ) {
        log.info("Updating publishingHouse");
        return publishingHouseService.save(publishingHouseId, publishingHouse);
    }

    @Override
	@PostMapping("/{publishinghouseid}/editions/search")
    public Page<Edition> searchEditions(
            @PathVariable("publishinghouseid") Long publishingHouseId,
            Pageable pageable
    ) {
        log.info("Listing editions by publishinghouse");
        return publishingHouseService.findEditionsByPublishingHouseId(publishingHouseId, pageable);
    }
     
    @Override
    @PutMapping("/{publishinghouseid}/editions/")
    public List<Edition> updateEditionsByPublishingHouseId(
    		@PathVariable("publishinghouseid") Long publishingHouseId,
    		@RequestParam("list") List<Long> editionIds
    		)
    {
    	log.info("Updating list of editions for publishingHouse {0}", publishingHouseId );
    	return publishingHouseService.updateEditionsByPublishingHouseId(publishingHouseId, editionIds);
    }
}
