package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.PublishingHouse
import ru.elenacherev.librarymanager.domain.entity.PublishingHouseEntity

fun PublishingHouseEntity.map() = PublishingHouse(
    publishingHouseId = publishingHouseId,
    title = title,
    logo = logo
)

fun PublishingHouse.map() = PublishingHouseEntity(
    title = title,
    logo = logo
)

fun PublishingHouse.map(
    entity: PublishingHouseEntity
) = entity.also {
    it.title = title
    it.logo = logo
}