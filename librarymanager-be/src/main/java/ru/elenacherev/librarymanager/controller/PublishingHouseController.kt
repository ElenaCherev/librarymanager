package ru.elenacherev.librarymanager.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.elenacherev.librarymanager.api.PublishingHouseApi
import ru.elenacherev.librarymanager.api.dto.PublishingHouse
import ru.elenacherev.librarymanager.services.PublishingHouseService
import java.util.*

@RequestMapping("/publishinghouse")
@RestController
class PublishingHouseController(
    val publishingHouseService: PublishingHouseService
) : PublishingHouseApi {

    @GetMapping("/{publishinghouseid}")
    override fun getPublishingHouse(
        @PathVariable("publishinghouseid") publishingHouseId: UUID
    ): PublishingHouse {
        return publishingHouseService.findPublishingHouseById(publishingHouseId)
    }

    @PostMapping("/search")
    override fun searchPublishingHouses(
        pageable: Pageable
    ) = publishingHouseService.findAllByPage(pageable)

    @PostMapping
    override fun createPublishingHouse(
        @RequestBody publishingHouse: PublishingHouse
    ) = publishingHouseService.create(publishingHouse)

    @PutMapping("/{publishinghouseid}")
    override fun savePublishingHouse(
        @PathVariable("publishinghouseid") publishingHouseId: UUID,
        @RequestBody publishingHouse: PublishingHouse
    ) = publishingHouseService.save(publishingHouseId, publishingHouse)

    @PostMapping("/{publishinghouseid}/editions/search")
    override fun searchEditions(
        @PathVariable("publishinghouseid") publishingHouseId: UUID,
        pageable: Pageable
    ) = publishingHouseService.findEditionsByPublishingHouseId(publishingHouseId, pageable)

    @PutMapping("/{publishinghouseid}/editions/")
    override fun updateEditionsByPublishingHouseId(
        @PathVariable("publishinghouseid") publishingHouseId: UUID,
        @RequestParam("list") editionIds: List<UUID>
    ) = publishingHouseService.updateEditionsByPublishingHouseId(publishingHouseId, editionIds)
}