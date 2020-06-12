package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Lang
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.LangEntity
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.domain.repository.LangRepository
import ru.elenacherev.librarymanager.mapper.map

@Service
class LangService (
    val langRepository: LangRepository,
    val editionRepository: EditionRepository
){
    @Transactional
    fun save(langId:Long, dto: Lang): Lang {
        return langRepository
            .findById(langId)
            .map (dto:: map)
            .map{ obj: LangEntity -> langRepository.saveAndFlush(obj) }
            .map(LangEntity::map)
            .orElse(null)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Lang> {
        return langRepository
            .findAll()
            .map(LangEntity::map)
    }

    @Transactional(readOnly = true)
    fun findLangById(langId: Long): Lang {
        return langRepository
            .findById(langId)
            .map(LangEntity::map)
            .orElse(null)
    }

    @Transactional(readOnly = true)
    fun findEditionsByLangId(langId: Long, pageable: Pageable): Page<Edition> {
        return langRepository
            .findById(langId)
            .map { entity -> editionRepository.findAllByLang(entity, pageable) }
            .orElse(Page.empty())
            .map(EditionEntity::map)
    }

    @Transactional(readOnly = true)
    fun findEditionsByPublLangId(langId: Long, pageable: Pageable): Page<Edition> {
        return langRepository.findById(langId)
            .map { entity -> editionRepository.findAllByPublLang(entity, pageable) }
            .orElse(Page.empty())
            .map(EditionEntity::map)
    }

    fun updateEditionsByLangId(langId: Long, editionIds: List<Long>): List<Edition> {
        return langRepository
            .findById(langId)
            .map { langEntity ->
                langEntity.editions.clear()
                langEntity.editions +=
                    editionIds.map(editionRepository:: getOne)
                return@map langEntity
            }
            .map{ obj: LangEntity -> langRepository.saveAndFlush(obj) }
            .map{ obj: LangEntity -> obj.editions }
            .orElse(mutableSetOf())
            .map(EditionEntity::map)
    }

    fun updateEditionsByPubllangId(publlangId: Long, editionIds: List<Long>): List<Edition> {
        return langRepository
            .findById(publlangId)
            .map { langEntity ->
                langEntity.publLangEditions.clear()
                langEntity.publLangEditions += editionIds.map(editionRepository::getOne)
                return@map langEntity
            }
            .map{ obj: LangEntity -> langRepository.saveAndFlush(obj) }
            .map{ obj: LangEntity -> obj.publLangEditions }
            .orElse(mutableSetOf())
            .map(EditionEntity::map)
    }
}