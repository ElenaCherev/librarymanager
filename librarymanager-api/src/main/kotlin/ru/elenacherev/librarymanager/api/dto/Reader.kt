package ru.elenacherev.librarymanager.api.dto

import java.util.Date

data class Reader
(
    var readrId: Long? = null,
    val firstName: String,
    val surname : String? = null,
    val patronymic : String? = null,
    val birthdate: Date,
    val regDate: Date,
    val telephone: String?,
    val email : String,
    val bookUsingIds: List<Long?>,
    val orderIds: List<Long?>
)