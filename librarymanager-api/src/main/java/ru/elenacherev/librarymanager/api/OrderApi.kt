package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Order
import javax.validation.Valid

interface OrderApi {
    fun getOrder(
        orderId:  Long
    ): Order?

    fun searchOrders(
        pageable: Pageable
    ): Page<Order>

    fun createOrder(
        order: @Valid Order
    ): Order

    fun saveOrder(
        orderId:  Long,
        order: @Valid Order
    ): Order
}