package ru.elenacherev.librarymanager.api.dto

import java.util.*

data class Genre(
    var genreId: UUID? = null,
    val title: String
)