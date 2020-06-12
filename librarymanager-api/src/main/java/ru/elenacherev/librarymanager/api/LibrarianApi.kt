package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Librarian
import javax.validation.Valid
import javax.validation.constraints.NotNull

interface LibrarianApi {
    fun getLibrarian(
        librarianId:  Long
    ): Librarian?

    fun searchLibrarians(
        pageable: Pageable
    ): Page<Librarian>

    fun createLibrarian(
        librarian: @Valid Librarian
    ): Librarian

    fun saveLibrarian(
        librarianId:  Long,
        librarian: @Valid Librarian
    ): Librarian
}