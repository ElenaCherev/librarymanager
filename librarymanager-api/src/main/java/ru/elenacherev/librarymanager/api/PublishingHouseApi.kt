package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.PublishingHouse
import javax.validation.Valid

interface PublishingHouseApi {
    fun getPublishingHouse(
        publishingHouseId:  Long
    ): PublishingHouse?

    fun searchPublishingHouses(
        pageable: Pageable
    ): Page<PublishingHouse>

    fun createPublishingHouse(
        publishingHouse: @Valid PublishingHouse
    ): PublishingHouse

    fun savePublishingHouse(
        publishingHouseId:  Long,
        publishingHouse: @Valid PublishingHouse
    ): PublishingHouse

    fun searchEditions(
        publishingHouseId:  Long,
        pageable: Pageable
    ): Page<Edition>

    fun updateEditionsByPublishingHouseId(
        publishingHouseId:  Long,
        editionIds:  List<Long>
    ): List<Edition>

}