package ru.elenacherev.librarymanager.api.dto

import java.util.*

data class Book(
    var bookId: UUID? = null,
    val isReserved: Boolean = false,
    val isAvailable: Boolean = false,
    val shelfNumber: Int = 0,
    val title: String,
    val editionId: UUID
)