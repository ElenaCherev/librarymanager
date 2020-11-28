package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.Author
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.domain.entity.AuthorEntity
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.repository.AuthorRepository
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*

@Service
class AuthorService(
    val authorRepository: AuthorRepository,
    val editionRepository: EditionRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): List<Author> {
        return authorRepository
            .findAll()
            .map(AuthorEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAllByPage(pageable: Pageable): Page<Author> {
        return authorRepository
            .findAll(pageable)
            .map(AuthorEntity::map)

    }

    @Transactional(readOnly = true)
    fun findAuthorById(authorId: UUID): Author? {
        return authorRepository.findById(authorId)
            .map(AuthorEntity::map)
            .orElse(null)
    }

    @Transactional
    fun save(authorId: UUID, dto: Author): Author {
        return authorRepository
            .findById(authorId)
            .map(dto::map)
            .map { author: AuthorEntity -> authorRepository.saveAndFlush(author) }
            .map(AuthorEntity::map)
            .get()
    }

    @Transactional
    fun create(dto: Author): Author {
        return Optional.of(dto)
            .map(Author::map)
            .map { author: AuthorEntity -> authorRepository.saveAndFlush(author) }
            .map(AuthorEntity::map)
            .get()
    }

    @Transactional(readOnly = true)
    fun findEditionsByAuthorId(authorId: UUID, pageable: Pageable): Page<Edition> {
        return authorRepository.findById(authorId)
            .map { entity -> editionRepository.findAllByAuthor(entity, pageable) }
            .orElse(Page.empty())
            .map(EditionEntity::map)
    }

    @Transactional
    fun updateEditionsByAuthorId(authorId: UUID, editionIds: List<UUID>): List<Edition> {
        return authorRepository
            .findById(authorId)
            .map { authorEntity ->
                authorEntity.editions.clear()
                authorEntity.editions += editionIds.map(editionRepository::getOne)
                return@map authorEntity
            }
            .map { author: AuthorEntity -> authorRepository.saveAndFlush(author) }
            .map { obj: AuthorEntity -> obj.editions }
            .orElse(mutableSetOf())
            .map(EditionEntity::map)
            .toList()
    }
}