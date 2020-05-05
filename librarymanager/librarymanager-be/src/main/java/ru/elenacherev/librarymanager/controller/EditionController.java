package ru.elenacherev.librarymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ru.elenacherev.librarymanager.api.EditionApi;
import ru.elenacherev.librarymanager.api.dto.Book;
import ru.elenacherev.librarymanager.api.dto.Edition;
import ru.elenacherev.librarymanager.api.dto.Order;
import ru.elenacherev.librarymanager.services.EditionService;


@RequestMapping(value = "librarymanager/books", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class EditionController 
		implements EditionApi {
    private final EditionService editionService;

    @Override
    @GetMapping("/{editionid}")
    public Edition getEdition(
    		@PathVariable("editionid") Long editionId
    ) {
        return editionService.findById(editionId);
    }

    @Override
    @PostMapping("/search")
    public Page<Edition> searchEditions(
            Pageable pageable
    ) {
        log.info("Searching Editions");
        return editionService.findAllByPage(pageable);
    }
    
    @Override
    @PostMapping
    public Edition createEdition(
            @RequestBody Edition edition
    ) {
        log.info("Creating Edition");
        return editionService.create(edition);
    }
    
    @Override
    @PutMapping("/{editionid}")
    public Edition saveEdition(
            @PathVariable("editionid") Long editionId,
            @RequestBody Edition edition
    ) {
        log.info("Updating Edition");
        return editionService.save(editionId, edition);
    }

    @Override
    @PostMapping("/{editionid}/books/search")
    public Page<Book> searchBooksByEditionId(
            @PathVariable("editionid") Long editionId,
            Pageable pageable
    ) {
        log.info("Listing books by editionid");
        return editionService.findAllBooksByEditionId(editionId, pageable);
    }
    
    @Override
    @PutMapping("/{editionid}/books/")
    public List<Book> updateBooksByEditionId(
    		@PathVariable("editionid") Long editionId,
    		@RequestParam("list") List<Long> bookIds
    		)
    {
    	log.info("Updating list of books for edition {0}", editionId );
    	return editionService.updateBooksByEditionId(editionId, bookIds);
    }
    
    @Override
    @PostMapping("/{editionid}/orders/search")
    public Page<Order> searchOrdersByEditionId(
            @PathVariable("editionid") Long editionId,
            Pageable pageable
    ) {
        log.info("Listing orders by editionid");
        return editionService.findAllOrdersByEditionId(editionId, pageable);
    }
    
    @Override
    @PutMapping("/{editionid}/orders/")
    public List<Order> updateOrdersByEditionId(
    		@PathVariable("editionid") Long editionId,
    		@RequestParam("list") List<Long> orderIds
    		)
    {
    	log.info("Updating list of orders for edition {0}", editionId );
    	return editionService.updateOrdersByEditionId(editionId, orderIds);
    }
}
