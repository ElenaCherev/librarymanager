package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Section
import java.util.*

interface SectionApi {
    fun getSection(
        sectionId: UUID
    ): Section?

    fun searchRootSections(
        pageable: Pageable
    ): Page<Section>

    fun searchSectionsByParentSectionId(
        sectionId: UUID,
        pageable: Pageable
    ): Page<Section>

    fun searchEditionsBySectionId(
        sectionId: UUID,
        pageable: Pageable
    ): Page<Edition>

    fun updateEditionsBySectionId(
        sectionId: UUID,
        editionIds: List<UUID>
    ): List<Edition>
}