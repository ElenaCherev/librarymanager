package ru.elenacherev.librarymanager.api.dto

import java.util.*

data class Section(
    var sectionId: UUID? = null,
    val title: String,
    val parentSectionId: UUID? = null
)