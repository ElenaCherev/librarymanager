package ru.elenacherev.librarymanager.api.dto

data class PublishingHouse
(
     var publishingHouseId: Long? = null,
     val title: String,
     val logo: Byte? = null
)