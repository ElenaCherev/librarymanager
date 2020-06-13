package ru.elenacherev.librarymanager.api.dto

data class Book(
    var bookId: Long? = null,
    val isReserved: Boolean = false,
    val isAvailable: Boolean = false,
    val shelfNumber: Int = 0,
    val title: String,
    val editionId: Long
)