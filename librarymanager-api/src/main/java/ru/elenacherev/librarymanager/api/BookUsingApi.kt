package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.BookUsing
import java.util.*
import javax.validation.Valid

interface BookUsingApi {
    fun getBookUsing(
        bookusingid: UUID
    ): BookUsing?

    fun searchBookUsings(
        pageable: Pageable
    ): Page<BookUsing>

    fun createBookUsing(
        bookUsing: @Valid BookUsing
    ): BookUsing

    fun saveBookUsing(
        bookUsingId: UUID,
        bookUsing: @Valid BookUsing
    ): BookUsing
}