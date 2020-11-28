package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Order
import java.util.*
import javax.validation.Valid

interface OrderApi {

    fun getOrder(
        orderId: UUID
    ): Order?

    fun searchOrders(
        pageable: Pageable
    ): Page<Order>

    fun createOrder(
        order: @Valid Order
    ): Order

    fun saveOrder(
        orderId: UUID,
        order: @Valid Order
    ): Order
}