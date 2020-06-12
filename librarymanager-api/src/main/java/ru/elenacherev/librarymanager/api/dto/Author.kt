package ru.elenacherev.librarymanager.api.dto

import java.util.Date

data class Author(
    var authorId: Long?,
    var firstName: String,
    var surname: String,
    var patronymic: String?,
    var birthdate: Date?,
    var deathdate: Date?,
    var info: String?,
    var fio: String?,
    var edidionIds: List<Long?>
)