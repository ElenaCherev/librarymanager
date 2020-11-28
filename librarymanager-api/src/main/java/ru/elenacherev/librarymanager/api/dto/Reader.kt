package ru.elenacherev.librarymanager.api.dto

import java.util.*

data class Reader(
    var readrId: UUID? = null,
    val firstName: String,
    val surname: String? = null,
    val patronymic: String? = null,
    val birthdate: Date,
    val regDate: Date,
    val telephone: String?,
    val email: String,
    val bookUsingIds: List<UUID>,
    val orderIds: List<UUID>
)