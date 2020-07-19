package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Section
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.SectionEntity
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.domain.repository.SectionRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*

@Service
class SectionService(
    val sectionRepository: SectionRepository,
    val editionRepository: EditionRepository
) {
    @Transactional
    fun create(dto: Section): Section {

        var parentSection: SectionEntity? = null
        if (dto.parentSectionId != null)
            parentSection = sectionRepository.findById(dto.parentSectionId!!).get()

        return Optional.of(dto)
            .map { dto.map(parentSection) }
            .map { section: SectionEntity -> sectionRepository.saveAndFlush(section) }
            .map(SectionEntity::map)
            .orElse(null)
    }

    @Transactional(readOnly = true)
    fun findSectionById(id: UUID): Section {
        return sectionRepository
            .findById(id)
            .map(SectionEntity::map)
            .orElse(null)
    }

    @Transactional(readOnly = true)
    fun findAllByParentSectionId(
        parentSectionId: UUID,
        pageable: Pageable
    ): Page<Section> {
        return sectionRepository
            .findAllByParentSection(sectionRepository.findById(parentSectionId).get(), pageable)
            .map(SectionEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAllRootSections(pageable: Pageable): Page<Section> {
        return findAllByParentSectionId(UUID.fromString(""), pageable) //!!!
    }

    @Transactional(readOnly = true)
    fun findeAllEditionsBySectionId(
        sectionId: UUID,
        pageable: Pageable
    ): Page<Edition> {
        return editionRepository.findAllBySection(
            sectionRepository
                .findById(sectionId).orElse(null), pageable)
            .map(EditionEntity::map)
    }

    @Transactional
    fun updateEditionsByAuthorId(sectionId: UUID, editionIds: List<UUID>): List<Edition> {
        return sectionRepository
            .findById(sectionId)
            .map { sectionEntity ->
                sectionEntity.editions.clear()
                sectionEntity.editions +=
                    editionIds.map(editionRepository::getOne)
                return@map sectionEntity
            }
            .map { section: SectionEntity -> sectionRepository.saveAndFlush(section) }
            .map { obj: SectionEntity -> obj.editions }
            .orElse(mutableSetOf())
            .map(EditionEntity::map)
    }
}