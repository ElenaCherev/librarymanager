package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Librarian
import java.util.*
import javax.validation.Valid

interface LibrarianApi {

    fun getLibrarian(
        librarianId: UUID
    ): Librarian?

    fun searchLibrarians(
        pageable: Pageable
    ): Page<Librarian>

    fun createLibrarian(
        librarian: @Valid Librarian
    ): Librarian

    fun saveLibrarian(
        librarianId: UUID,
        librarian: @Valid Librarian
    ): Librarian
}