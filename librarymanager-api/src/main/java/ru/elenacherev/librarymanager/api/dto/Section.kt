package ru.elenacherev.librarymanager.api.dto

data class Section
(
     var sectionId: Long? = null,
     val title: String,
     val parentSectionId: Long? = null
)