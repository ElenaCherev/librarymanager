package ru.elenacherev.librarymanager.api.dto

import java.util.*

data class PublishingHouse(
    var publishingHouseId: UUID? = null,
    val title: String,
    val logo: Byte? = null
)