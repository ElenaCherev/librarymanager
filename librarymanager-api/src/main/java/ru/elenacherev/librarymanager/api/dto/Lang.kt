package ru.elenacherev.librarymanager.api.dto

import java.util.*

data class Lang(
    var langId: UUID? = null,
    val title: String
)