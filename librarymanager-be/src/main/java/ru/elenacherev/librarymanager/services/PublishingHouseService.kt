package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.PublishingHouse
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.PublishingHouseEntity
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.domain.repository.PublishingHouseRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*

@Service
class PublishingHouseService (
    val publishingHouseRepository: PublishingHouseRepository,
    val editionRepository: EditionRepository
){
    @Transactional(readOnly = true)
    fun findAll(): List<PublishingHouse> {
        return publishingHouseRepository
            .findAll()
            .map(PublishingHouseEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAllByPage(pageable: Pageable): Page<PublishingHouse>
    {
        return publishingHouseRepository
            .findAll(pageable)
            .map(PublishingHouseEntity::map)
    }

    @Transactional(readOnly = true)
    fun findPublishingHouseById(publishingHouseId: Long): PublishingHouse {
        return publishingHouseRepository
            .findById(publishingHouseId)
            .map(PublishingHouseEntity::map)
            .orElse(null)
    }

    @Transactional
    fun save(publishingHouseId: Long, dto: PublishingHouse): PublishingHouse {
        return publishingHouseRepository
            .findById(publishingHouseId)
            .map(dto::map)
            .map{ publishingHouse: PublishingHouseEntity -> publishingHouseRepository.saveAndFlush(publishingHouse) }
            .map(PublishingHouseEntity::map)
            .orElse(null)
    }

    @Transactional
    fun create(dto: PublishingHouse): PublishingHouse {
        return Optional.of(dto)
            .map(PublishingHouse::map)
            .map{ publishingHouse: PublishingHouseEntity -> publishingHouseRepository.saveAndFlush(publishingHouse) }
            .map(PublishingHouseEntity::map)
            .orElse(null)
    }

    @Transactional(readOnly = true)
    fun findEditionsByPublishingHouseId(publishingHouseId: Long, pageable: Pageable): Page<Edition> {
        return publishingHouseRepository
            .findById(publishingHouseId)
            .map { publHouseEntity -> editionRepository.findAllByPublishingHouse(publHouseEntity, pageable) }
            .orElse(Page.empty())
            .map(EditionEntity::map)
    }

    fun updateEditionsByPublishingHouseId(
        publishingHouseId: Long,
        editionIds: List<Long>
    ): List<Edition> {
        return publishingHouseRepository
            .findById(publishingHouseId)
            .map { publishingHouseEntity ->
                publishingHouseEntity.editions.clear()
                publishingHouseEntity.editions += editionIds.map(editionRepository::getOne)
                return@map publishingHouseEntity
            }
            .map{ publishingHouse: PublishingHouseEntity -> publishingHouseRepository.saveAndFlush(publishingHouse) }
            .map{obj: PublishingHouseEntity -> obj.editions}
            .orElse(mutableSetOf())
            .map(EditionEntity::map)
    }
}