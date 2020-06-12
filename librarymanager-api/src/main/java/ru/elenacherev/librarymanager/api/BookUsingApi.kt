package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.BookUsing
import javax.validation.Valid

interface BookUsingApi {
    fun getBookUsing(
        bookusingid:  Long
    ): BookUsing?

    fun searchBookUsings(
        pageable: Pageable
    ): Page<BookUsing>

    fun createBookUsing(
        bookUsing: @Valid BookUsing
    ): BookUsing

    fun saveBookUsing(
        bookUsingId: Long,
        bookUsing: @Valid BookUsing
    ): BookUsing
}