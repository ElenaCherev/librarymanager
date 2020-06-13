package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.BookUsing
import ru.elenacherev.librarymanager.api.dto.Order
import ru.elenacherev.librarymanager.api.dto.Reader
import javax.validation.Valid

interface ReaderApi {
    fun getReader(
        readerId:  Long
    ): Reader?

    fun searchReaders(
        pageable: Pageable
    ): Page<Reader>

    fun createReader(
        reader: @Valid Reader
    ): Reader

    fun saveReader(
        readerId:  Long,
        reader: @Valid Reader
    ): Reader

    fun searchOrders(
        readerId: Long,
        pageable: Pageable
    ): Page<Order>

    fun updateOrders(
        readerId:  Long,
        orderIds:  List<Long>
    ): List<Order>

    fun searchBookUsings(
        readerId:  Long,
        pageable: Pageable
    ): Page<BookUsing>

    fun updateBookUsings(
        readerId:  Long,
        bookUsingIds:  List<Long>
    ): List<BookUsing>
}