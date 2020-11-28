package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Genre
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.GenreEntity
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.domain.repository.GenreRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*


@Service
class GenreService(
    val genreRepository: GenreRepository,
    val editionRepository: EditionRepository
) {
    @Transactional(readOnly = true)
    fun findAll(): List<Genre> {
        return genreRepository
            .findAll()
            .map(GenreEntity::map)
    }

    @Transactional(readOnly = true)
    fun findById(id: UUID): Genre {
        return genreRepository.findById(id)
            .map(GenreEntity::map)
            .orElse(null)
    }

    @Transactional
    fun save(genreId: UUID, dto: Genre): Genre {
        return genreRepository
            .findById(genreId)
            .map(dto::map)
            .map { genre: GenreEntity -> genreRepository.saveAndFlush(genre) }
            .map(GenreEntity::map)
            .orElse(null)
    }

    @Transactional(readOnly = true)
    fun findEditionsByGenreId(genreId: UUID, pageable: Pageable): Page<Edition> {
        return genreRepository.findById(genreId)
            .map { entity -> editionRepository.findAllByGenre(entity, pageable) }
            .orElse(Page.empty())
            .map(EditionEntity::map)
    }

    @Transactional
    fun updateEditionsByGenreId(genreId: UUID, editionIds: List<UUID>): List<Edition> {
        return genreRepository
            .findById(genreId)
            .map { genreEntity ->
                genreEntity.editions.clear()
                genreEntity.editions +=
                    editionIds.map(editionRepository::getOne)
                return@map genreEntity
            }
            .map { genre: GenreEntity -> genreRepository.saveAndFlush(genre) }
            .map { genre: GenreEntity -> genre.editions }
            .orElse(mutableSetOf())
            .map(EditionEntity::map)
    }
}