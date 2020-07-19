package ru.elenacherev.librarymanager.api.dto

import java.time.Instant
import java.util.*

data class Author(
    var authorId: UUID?,
    var firstName: String,
    var surname: String,
    var patronymic: String?,
    var birthdate: Instant?,
    var deathdate: Instant?,
    var info: String?,
    var fio: String?,
    var edidionIds: List<UUID>
)