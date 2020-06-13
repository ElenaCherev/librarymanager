package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Section

interface SectionApi {
    fun getSection(
        sectionId:  Long
    ): Section?

    fun searchRootSections(
        pageable: Pageable
    ): Page<Section>

    fun searchSectionsByParentSectionId(
        sectionId:  Long,
        pageable: Pageable
    ): Page<Section>

    fun searchEditionsBySectionId(
        sectionId:  Long,
        pageable: Pageable
    ): Page<Edition>

    fun updateEditionsBySectionId(
        sectionId:  Long,
        editionIds:  List<Long>
    ): List<Edition>
}