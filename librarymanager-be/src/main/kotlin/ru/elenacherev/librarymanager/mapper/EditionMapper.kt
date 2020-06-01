package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.domain.entity.AuthorEntity
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.GenreEntity
import ru.elenacherev.librarymanager.domain.entity.LangEntity
import ru.elenacherev.librarymanager.domain.entity.PublishingHouseEntity
import ru.elenacherev.librarymanager.domain.entity.SectionEntity

fun EditionEntity.map() = Edition(
    editionId = editionId,
    title = title,
    workTitle = workTitle,
    year = year,
    publishingYear = publishingYear,
    isbn = isbn,
    isIllustrated = isIllustrated,
    downloadLink = downloadLink,
    age = age,
    info = info,
    langString = lang.title,
    langId = lang.langId ?: 0,
    publLangString = publLang.title,
    publLangId = publLang.langId ?: 0,
    publishingHouseId = publishingHouse.publishingHouseId ?: 0,
    publishingHouseString = publishingHouse.title,
    genreId = genre.genreId ?: 0,
    genreString = genre.title,
    sectionId = section.sectionId ?: 0,
    sectionTitle = section.title,
    authorFIOs = authors.map(AuthorEntity::fio),
    authorIds = authors.map(AuthorEntity::authorId)
)

fun Edition.map(
    langEntity: LangEntity ,
    publLangEntity: LangEntity ,
    publishingHouseEntity: PublishingHouseEntity,
    genreEntity: GenreEntity ,
    sectionEntity: SectionEntity
) = EditionEntity(
    title = title,
    workTitle = workTitle,
    year = year,
    publishingYear = publishingYear,
    isbn = isbn,
    isIllustrated = isIllustrated,
    downloadLink = downloadLink,
    age = age,
    info = info,
    lang = langEntity,
    publLang = publLangEntity,
    publishingHouse = publishingHouseEntity,
    genre = genreEntity,
    section = sectionEntity
)

fun Edition.map(
    entity: EditionEntity,
    langEntity: LangEntity ,
    publLangEntity: LangEntity ,
    publishingHouseEntity: PublishingHouseEntity,
    genreEntity: GenreEntity ,
    sectionEntity: SectionEntity
) = entity.also {
    it.title = title
    it.workTitle = workTitle
    it.year = year
    it.publishingYear = publishingYear
    it.isbn = isbn
    it.isIllustrated = isIllustrated
    it.downloadLink = downloadLink
    it.age = age
    it.info = info
    it.lang = langEntity
    it.publLang = publLangEntity
    it.publishingHouse = publishingHouseEntity
    it.genre = genreEntity
    it.section = sectionEntity
}