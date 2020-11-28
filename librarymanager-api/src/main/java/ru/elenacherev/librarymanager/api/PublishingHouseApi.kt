package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.PublishingHouse
import java.util.*
import javax.validation.Valid

interface PublishingHouseApi {

    fun getPublishingHouse(
        publishingHouseId: UUID
    ): PublishingHouse?

    fun searchPublishingHouses(
        pageable: Pageable
    ): Page<PublishingHouse>

    fun createPublishingHouse(
        publishingHouse: @Valid PublishingHouse
    ): PublishingHouse

    fun savePublishingHouse(
        publishingHouseId: UUID,
        publishingHouse: @Valid PublishingHouse
    ): PublishingHouse

    fun searchEditions(
        publishingHouseId: UUID,
        pageable: Pageable
    ): Page<Edition>

    fun updateEditionsByPublishingHouseId(
        publishingHouseId: UUID,
        editionIds: List<UUID>
    ): List<Edition>
}