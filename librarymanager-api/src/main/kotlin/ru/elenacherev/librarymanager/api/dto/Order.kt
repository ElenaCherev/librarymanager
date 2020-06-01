package ru.elenacherev.librarymanager.api.dto

import ru.elenacherev.librarymanager.api.enum.OrderState
import java.util.Date

data class Order
(
    var orderId: Long? = null,
    val completeDate: Date?,
    val orderDate: Date,
    val orderState : OrderState,
    val readerFio: String,
    val editionTitle: String,
    val readerId : Long,
    val editionId : Long
)