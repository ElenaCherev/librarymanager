package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.Book
import ru.elenacherev.librarymanager.domain.entity.BookEntity
import ru.elenacherev.librarymanager.domain.entity.EditionEntity

fun BookEntity.map() = Book(
    bookId = bookId,
    isAvailable = isAvailable,
    isReserved = isReserved,
    shelfNumber = shelfNumber,
    editionId = edition.editionId!!,
    title = edition.title
)

fun Book.map(edition: EditionEntity) = BookEntity(
    isAvailable = isAvailable,
    isReserved = isReserved,
    shelfNumber = shelfNumber,
    edition = edition
)

fun Book.map(
    entity: BookEntity,
    edition: EditionEntity
) = entity.also {
    it.isAvailable = isAvailable
    it.isReserved = isReserved
    it.shelfNumber = shelfNumber
    it.edition = edition
}