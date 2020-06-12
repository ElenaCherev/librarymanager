package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.Section
import ru.elenacherev.librarymanager.domain.entity.SectionEntity

fun SectionEntity.map() = Section(
    sectionId = sectionId,
    title = title,
    parentSectionId = parentSection?.sectionId
)

fun Section.map(parentSection: SectionEntity?) = SectionEntity(
    title = title,
    parentSection = parentSection
)

fun Section.map(
    entity: SectionEntity,
    parentSection: SectionEntity?
) = entity.also {
    it.title = title
    it.parentSection = parentSection
}