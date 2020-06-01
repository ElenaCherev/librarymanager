package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.Librarian
import ru.elenacherev.librarymanager.domain.entity.LibrarianEntity

fun LibrarianEntity.map() = Librarian(
    librarianId = librarianId
)

fun Librarian.map() = LibrarianEntity()

fun Librarian.map(entity: LibrarianEntity) = entity.also {
}