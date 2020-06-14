package ru.elenacherev.librarymanager.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.elenacherev.librarymanager.api.SectionApi
import ru.elenacherev.librarymanager.services.SectionService

@RequestMapping(value = ["sections"])
@RestController
class SectionController(
    val sectionService: SectionService
) : SectionApi {

    @GetMapping("/{sectionid}")
    override fun getSection(
        @PathVariable("sectionid") sectionId: Long
    ) = sectionService.findSectionById(sectionId)

    @PostMapping
    override fun searchRootSections(
        pageable: Pageable
    ) = sectionService.findAllRootSections(pageable)

    @PostMapping("/{sectionid}/sections/search")
    override fun searchSectionsByParentSectionId(
        @PathVariable("sectionid") sectionId: Long,
        pageable: Pageable
    ) = sectionService.findAllByParentSectionId(sectionId, pageable)

    @PostMapping("/{sectionid}/editions/search")
    override fun searchEditionsBySectionId(
        @PathVariable("sectionid") sectionId: Long,
        pageable: Pageable
    ) = sectionService.findeAllEditionsBySectionId(sectionId, pageable)

    @PutMapping("/{sectionid}/editions/")
    override fun updateEditionsBySectionId(
        @PathVariable("sectionid") sectionId: Long,
        @RequestParam("list") editionIds: List<Long>
    ) = sectionService.updateEditionsByAuthorId(sectionId, editionIds)
}