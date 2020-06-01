package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Book
import ru.elenacherev.librarymanager.api.dto.BookUsing
import javax.validation.Valid

interface BookApi {
    fun getBook(
        bookId: Long
    ): Book?

    fun searchBooks(
        pageable: Pageable
    ): Page<Book>

    fun createBook(
        book: @Valid Book
    ): Book

    fun saveBook(
        bookId: Long,
        book: @Valid Book
    ): Book

    fun searchBookUsings(
        bookId: Long,
        pageable: Pageable
    ): Page<BookUsing>

    fun updateBookUsings(
        bookId: Long,
        bookUsingIds: List<Long>
    ): List<BookUsing>
}