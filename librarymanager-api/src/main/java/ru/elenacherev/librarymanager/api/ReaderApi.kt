package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.BookUsing
import ru.elenacherev.librarymanager.api.dto.Order
import ru.elenacherev.librarymanager.api.dto.Reader
import java.util.*
import javax.validation.Valid

interface ReaderApi {

    fun getReader(
        readerId: UUID
    ): Reader?

    fun searchReaders(
        pageable: Pageable
    ): Page<Reader>

    fun createReader(
        reader: @Valid Reader
    ): Reader

    fun saveReader(
        readerId: UUID,
        reader: @Valid Reader
    ): Reader

    fun searchOrders(
        readerId: UUID,
        pageable: Pageable
    ): Page<Order>

    fun updateOrders(
        readerId: UUID,
        orderIds: List<UUID>
    ): List<Order>

    fun searchBookUsings(
        readerId: UUID,
        pageable: Pageable
    ): Page<BookUsing>

    fun updateBookUsings(
        readerId: UUID,
        bookUsingIds: List<UUID>
    ): List<BookUsing>
}