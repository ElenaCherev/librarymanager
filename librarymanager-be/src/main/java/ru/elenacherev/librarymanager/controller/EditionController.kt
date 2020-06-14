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
import ru.elenacherev.librarymanager.api.EditionApi
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.services.EditionService

@RequestMapping("/editions")
@RestController
class EditionController(
    var editionService: EditionService
) : EditionApi {

    @GetMapping("/{editionid}")
    override fun getEdition(
        @PathVariable("editionid") editionId: Long
    ) = editionService.findById(editionId)

    @PostMapping("/search")
    override fun searchEditions(
        pageable: Pageable
    ) = editionService.findAllByPage(pageable)

    @PostMapping
    override fun createEdition(
        @RequestBody edition: Edition
    ) = editionService.create(edition)

    @PutMapping("/{editionid}")
    override fun saveEdition(
        @PathVariable("editionid") editionId: Long,
        @RequestBody edition: Edition
    ) = editionService.save(editionId, edition)

    @PostMapping("/{editionid}/books/search")
    override fun searchBooksByEditionId(
        @PathVariable("editionid") editionId: Long,
        pageable: Pageable
    ) = editionService.findAllBooksByEditionId(editionId, pageable)

    @PutMapping("/{editionid}/books/")
    override fun updateBooksByEditionId(
        @PathVariable("editionid") editionId: Long,
        @RequestParam("list") bookIds: List<Long>
    ) = editionService.updateBooksByEditionId(editionId, bookIds)

    @PostMapping("/{editionid}/orders/search")
    override fun searchOrdersByEditionId(
        @PathVariable("editionid") editionId: Long,
        pageable: Pageable
    ) = editionService.findAllOrdersByEditionId(editionId, pageable)

    @PutMapping("/{editionid}/orders/")
    override fun updateOrdersByEditionId(
        @PathVariable("editionid") editionId: Long,
        @RequestParam("list") orderIds: List<Long>
    ) = editionService.updateOrdersByEditionId(editionId, orderIds)
}