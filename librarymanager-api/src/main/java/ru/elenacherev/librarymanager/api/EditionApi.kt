package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Book
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Order
import java.util.*
import javax.validation.Valid

interface EditionApi {
    fun getEdition(
        editionId: UUID
    ): Edition?

    fun searchEditions(
        pageable: Pageable
    ): Page<Edition>

    fun createEdition(
        edition: @Valid Edition
    ): Edition

    fun saveEdition(
        editionId: UUID,
        edition: @Valid Edition
    ): Edition

    fun searchBooksByEditionId(
        editionId: UUID,
        pageable: Pageable
    ): Page<Book>

    fun searchOrdersByEditionId(
        editionId: UUID,
        pageable: Pageable
    ): Page<Order>

    fun updateBooksByEditionId(
        editionId: UUID,
        bookIds: List<UUID>
    ): List<Book>

    fun updateOrdersByEditionId(
        editionId: UUID,
        orderIds: List<UUID>
    ): List<Order>
}