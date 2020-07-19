package ru.elenacherev.librarymanager.api.dto

import ru.elenacherev.librarymanager.api.enum.OrderState
import java.util.*

data class Order(
    var orderId: UUID? = null,
    val completeDate: Date?,
    val orderDate: Date,
    val orderState: OrderState,
    val readerFio: String,
    val editionTitle: String,
    val readerId: UUID,
    val editionId: UUID
)