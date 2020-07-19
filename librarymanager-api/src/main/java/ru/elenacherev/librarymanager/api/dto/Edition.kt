package ru.elenacherev.librarymanager.api.dto

import ru.elenacherev.librarymanager.api.enum.AgeRating
import java.util.*

data class Edition(
    var editionId: UUID? = null,
    val title: String,
    val workTitle: String,
    val year: Int,
    val publishingYear: Int = 0,
    val isbn: String,
    val isIllustrated: Boolean = false,
    val downloadLink: String? = null,
    val age: AgeRating,
    val info: String? = null,
    val langString: String,
    val langId: UUID,
    val publLangString: String,
    val publLangId: UUID,
    val publishingHouseString: String,
    val publishingHouseId: UUID,
    val genreString: String,
    val genreId: UUID,
    val sectionTitle: String,
    val sectionId: UUID,
    val authorIds: List<UUID>,
    val authorFIOs: List<String>
)