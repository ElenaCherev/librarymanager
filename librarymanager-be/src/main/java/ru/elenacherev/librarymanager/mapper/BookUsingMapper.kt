package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.BookUsing
import ru.elenacherev.librarymanager.domain.entity.BookEntity
import ru.elenacherev.librarymanager.domain.entity.BookUsingEntity
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity

fun BookUsingEntity.map() = BookUsing(
    bookUsingId = bookUsingId,
    startDate = startDate,
    endDate = endDate,
    bookId = book.bookId!!,
    readerId = reader.readerId!!
)

fun BookUsing.map(
    book: BookEntity,
    reader: ReaderEntity
) = BookUsingEntity(
    startDate = startDate,
    endDate = endDate,
    book = book,
    reader = reader
)

fun BookUsing.map(
    entity: BookUsingEntity,
    book: BookEntity,
    reader: ReaderEntity
) = entity.also {
    it.startDate = startDate
    it.endDate = endDate
    it.book = book
    it.reader = reader
}
