package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Book
import ru.elenacherev.librarymanager.api.dto.BookUsing
import java.util.*
import javax.validation.Valid

interface BookApi {
    fun getBook(
        bookId: UUID
    ): Book?

    fun searchBooks(
        pageable: Pageable
    ): Page<Book>

    fun createBook(
        book: @Valid Book
    ): Book

    fun saveBook(
        bookId: UUID,
        book: @Valid Book
    ): Book

    fun searchBookUsings(
        bookId: UUID,
        pageable: Pageable
    ): Page<BookUsing>

    fun updateBookUsings(
        bookId: UUID,
        bookUsingIds: List<UUID>
    ): List<BookUsing>
}