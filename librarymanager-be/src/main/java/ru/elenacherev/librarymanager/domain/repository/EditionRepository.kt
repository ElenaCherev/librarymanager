package ru.elenacherev.librarymanager.domain.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.domain.entity.AuthorEntity
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.GenreEntity
import ru.elenacherev.librarymanager.domain.entity.LangEntity
import ru.elenacherev.librarymanager.domain.entity.PublishingHouseEntity
import ru.elenacherev.librarymanager.domain.entity.SectionEntity

@Repository
@Transactional(propagation = Propagation.MANDATORY)
interface EditionRepository : JpaRepository<EditionEntity, Long> {
    fun findAllBySection(section: SectionEntity, pageable: Pageable): Page<EditionEntity>

    //Переписать!!! Работает неправильно!!! Переделать все запросы Много-Много
    fun findAllByAuthor(author: AuthorEntity, pageable: Pageable): Page<EditionEntity>
    fun findAllByGenre(genre: GenreEntity, pageable: Pageable): Page<EditionEntity>
    fun findAllByLang(lang: LangEntity, pageable: Pageable): Page<EditionEntity>
    fun findAllByPublLang(publLang: LangEntity, pageable: Pageable): Page<EditionEntity>
    fun findAllByPublishingHouse(publishingHouse: PublishingHouseEntity, pageable: Pageable): Page<EditionEntity>
}