package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.Order
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.OrderEntity
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity

fun OrderEntity.map() = Order(
    orderId = orderId,
    completeDate = completeDate,
    orderDate = orderDate,
    orderState = orderState,
    readerFio = reader.fio,
    editionTitle = edition.title,
    readerId = reader.readerId ?: 0,
    editionId = edition.editionId ?: 0
)

fun Order.map(readerEntity: ReaderEntity,
              editionEntity: EditionEntity
) = OrderEntity(
    completeDate = completeDate,
    orderDate = orderDate,
    orderState = orderState,
    reader = readerEntity,
    edition = editionEntity
)

fun Order.map(
    entity:OrderEntity,
    readerEntity: ReaderEntity,
    editionEntity: EditionEntity
) = entity.also {
    it.completeDate = completeDate
    it.orderDate = orderDate
    it.orderState = orderState
    it.reader = readerEntity
    it.edition = editionEntity
}