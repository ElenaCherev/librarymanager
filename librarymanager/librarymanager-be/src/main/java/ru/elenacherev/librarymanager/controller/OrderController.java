package ru.elenacherev.librarymanager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.elenacherev.librarymanager.api.OrderApi;
import ru.elenacherev.librarymanager.api.dto.Order;
import ru.elenacherev.librarymanager.services.OrderService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "librarymanager/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController implements OrderApi {
	private final OrderService orderService;
	
	@Override
	@GetMapping("/{orderid}")
    public Order getOrder(
            @PathVariable("orderid") Long orderId
    ) {
        return orderService.findOrderById(orderId);
    }

    @Override
	@PostMapping("/search")
    public Page<Order> searchOrders(
            Pageable pageable
    ) {
        log.info("Listing orders");
        return orderService.findAllByPage(pageable);
    }


    @Override
	@PostMapping
    public Order createOrder(
            @RequestBody Order order
    ) {
        log.info("Creating order");
        return orderService.create(order);
    }

    @Override
	@PutMapping("/{orderid}")
    public Order saveOrder(
            @PathVariable("orderid") Long orderId,
            @RequestBody Order order
    ) {
        log.info("Updating author");
        return orderService.save(orderId, order);
    }

}
