package ru.spring5.elena.librarymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ru.spring5.elena.librarymanager.api.ReaderApi;
import ru.spring5.elena.librarymanager.api.dto.BookUsing;
import ru.spring5.elena.librarymanager.api.dto.Order;
import ru.spring5.elena.librarymanager.api.dto.Reader;
import ru.spring5.elena.librarymanager.services.ReaderService;

import java.util.List;

@RequestMapping(value = "librarymanager/readers", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class ReaderController implements ReaderApi {
    private final ReaderService readerService;

    @Override
	@GetMapping("/{readerid}")
    public Reader getReader(@PathVariable("readerid") Long readerId) {
        return readerService.findById(readerId);
    }
    
    @Override
	@PostMapping("/search")
    public Page<Reader> searchReaders(
            Pageable pageable
    ) {
        log.info("Listing readers");
        return readerService.findAllByPage(pageable);
    }
    
    @Override
	@PostMapping
    public Reader createReader(
            @RequestBody Reader reader
    ) {
        log.info("Creating reader");
        return readerService.create(reader);
    }

    @Override
	@PutMapping("/{readerid}")
    public Reader saveReader(
            @PathVariable("readerid") Long readerId,
            @RequestBody Reader reader
    ) {
        log.info("Updating reader");
        return readerService.save(readerId, reader);
    }
    
    @Override
	@PostMapping("/{readerid}/orders/search")
    public Page<Order> searchOrders(
            @PathVariable("readerid") Long readerId,
            Pageable pageable
    ) {
        log.info("Listing orders by reader");
        return readerService.findOrdersByReaderId(readerId,pageable);
    }
    
    @Override
	@PutMapping("/{readerid}/orders/")
    public List<Order> updateOrders(
    		@PathVariable("readerid") Long readerId,
    		@RequestParam("list") List<Long> orderIds
    		)
    {
    	log.info("Update orders of reader");
    	return readerService.updateOrders(readerId, orderIds);
    }
    
    @Override
	@PostMapping("/{readerid}/bookusings/search")
    public Page<BookUsing> searchBookUsings(
            @PathVariable("readerid") Long readerId,
            Pageable pageable
    ) {
        log.info("Listing bookUsings by reader {0}", readerId);
        return readerService.findBookUsingsByReaderId(readerId,pageable);
    }
    
    @Override
	@PutMapping("/{readerid}/bookusings/")
    public List<BookUsing> updateBookUsings(
    		@PathVariable("readerid") Long readerId,
    		@RequestParam("list") List<Long> bookUsingIds
    		)
    {
    	log.info("Update bookUsings of reader {0}",readerId );
    	return readerService.updateBookUsings(readerId, bookUsingIds);
    }
}
