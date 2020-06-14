package ru.elenacherev.librarymanager.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.elenacherev.librarymanager.api.OrderApi
import ru.elenacherev.librarymanager.api.dto.Order
import ru.elenacherev.librarymanager.services.OrderService

@RequestMapping("/orders")
@RestController
class OrderController(
    var orderService: OrderService
) : OrderApi {

    @GetMapping("/{orderid}")
    override fun getOrder(
        @PathVariable("orderid") orderId: Long
    ) = orderService.findOrderById(orderId)

    @PostMapping("/search")
    override fun searchOrders(
        pageable: Pageable
    ) = orderService.findAllByPage(pageable)

    @PostMapping
    override fun createOrder(
        @RequestBody order: Order
    ) = orderService.create(order)

    @PutMapping("/{orderid}")
    override fun saveOrder(
        @PathVariable("orderid") orderId: Long,
        @RequestBody order: Order
    ) = orderService.save(orderId, order)
}