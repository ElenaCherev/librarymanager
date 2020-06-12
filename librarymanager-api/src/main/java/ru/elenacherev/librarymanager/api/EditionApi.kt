package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Book
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Order
import javax.validation.Valid

interface EditionApi 
{
    fun getEdition(
        editionId:  Long
    ): Edition?

    fun searchEditions(
        pageable: Pageable
    ): Page<Edition>

    fun createEdition(
        edition: @Valid Edition
    ): Edition

    fun saveEdition(
        editionId:  Long,
        edition: @Valid Edition
    ): Edition

    fun searchBooksByEditionId(
        editionId:  Long,
        pageable: Pageable
    ): Page<Book>

    fun searchOrdersByEditionId(
        editionId:  Long,
        pageable: Pageable
    ): Page<Order>

    fun updateBooksByEditionId(
        editionId:  Long,
        bookIds:  List<Long>
    ): List<Book>

    fun updateOrdersByEditionId(
        editionId:  Long,
        orderIds:  List<Long>
    ): List<Order>
}