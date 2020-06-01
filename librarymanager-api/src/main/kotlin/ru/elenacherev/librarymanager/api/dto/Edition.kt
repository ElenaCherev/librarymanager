package ru.elenacherev.librarymanager.api.dto

import ru.elenacherev.librarymanager.api.enum.AgeRating

data class Edition(
    var editionId: Long? = null,
    val title: String,
    val workTitle : String,
    val year: Int,
    val publishingYear: Int = 0,
    val isbn: String,
    val isIllustrated: Boolean = false,
    val downloadLink: String? = null,
    val age: AgeRating,
    val info: String? = null,
    val langString: String,
    val langId: Long,
    val publLangString: String,
    val publLangId: Long,
    val publishingHouseString: String,
    val publishingHouseId: Long,
    val genreString: String,
    val genreId: Long,
    val sectionTitle: String,
    val sectionId: Long,
    val authorIds: List<Long?>,
    val authorFIOs: List<String>
)