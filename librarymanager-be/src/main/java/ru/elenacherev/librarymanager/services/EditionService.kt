package ru.elenacherev.librarymanager.services


import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.Book
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Order
import ru.elenacherev.librarymanager.domain.entity.BookEntity
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.GenreEntity
import ru.elenacherev.librarymanager.domain.entity.LangEntity
import ru.elenacherev.librarymanager.domain.entity.OrderEntity
import ru.elenacherev.librarymanager.domain.entity.PublishingHouseEntity
import ru.elenacherev.librarymanager.domain.entity.SectionEntity
import ru.elenacherev.librarymanager.domain.repository.BookRepository
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.domain.repository.GenreRepository
import ru.elenacherev.librarymanager.domain.repository.LangRepository
import ru.elenacherev.librarymanager.domain.repository.OrderRepository
import ru.elenacherev.librarymanager.domain.repository.PublishingHouseRepository
import ru.elenacherev.librarymanager.domain.repository.SectionRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*

@Service
class EditionService(
    val editionRepository: EditionRepository,
    val genreRepository: GenreRepository,
    val langRepository: LangRepository,
    val sectionRepository: SectionRepository,
    val publishingHouseRepository: PublishingHouseRepository,
    val bookRepository: BookRepository,
    val orderRepository: OrderRepository
) {
    @Transactional(readOnly = true)
    fun findAll(): List<Edition> {
        return editionRepository
            .findAll()
            .map(EditionEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAllByPage(pageable: Pageable): Page<Edition> {
        return editionRepository
            .findAll(pageable)
            .map(EditionEntity::map)
    }

    @Transactional(readOnly = true)
    fun findById(id: UUID): Edition {
        return editionRepository.findById(id)
            .map(EditionEntity::map)
            .orElse(null)
    }

    @Transactional
    fun save(editionId: UUID, dto: Edition): Edition {
        val genreEntity: GenreEntity = genreRepository.findById(dto.genreId).get()
        val langEntity: LangEntity = langRepository.findById(dto.langId).get()
        val publLangEntity: LangEntity = langRepository.findById(dto.publLangId).get()
        val sectionEntity: SectionEntity = sectionRepository.findById(dto.sectionId).get()
        val publHouseEntity: PublishingHouseEntity = publishingHouseRepository
            .findById(dto.publishingHouseId)
            .orElse(null)
        return editionRepository.findById(editionId)
            .map {
                dto.map(
                    langEntity,
                    publLangEntity,
                    publHouseEntity,
                    genreEntity,
                    sectionEntity)
            }
            .map { edition: EditionEntity -> editionRepository.saveAndFlush(edition) }
            .map(EditionEntity::map)
            .get()
    }

    @Transactional
    fun create(dto: Edition): Edition {
        val genreEntity: GenreEntity = genreRepository.findById(dto.genreId).get()
        val langEntity: LangEntity = langRepository.findById(dto.langId).get()
        val publLangEntity: LangEntity = langRepository.findById(dto.publLangId).get()
        val sectionEntity: SectionEntity = sectionRepository.findById(dto.sectionId).get()
        val publHouseEntity: PublishingHouseEntity = publishingHouseRepository
            .findById(dto.publishingHouseId)
            .orElse(null)

        return Optional.of(dto)
            .map {
                dto.map(
                    langEntity,
                    publLangEntity,
                    publHouseEntity,
                    genreEntity,
                    sectionEntity
                )
            }
            .map { edition: EditionEntity -> editionRepository.saveAndFlush(edition) }
            .map(EditionEntity::map)
            .get()
    }

    @Transactional(readOnly = true)
    fun findAllBooksByEditionId(editionId: UUID, pageable: Pageable): Page<Book> {
        return editionRepository.findById(editionId)
            .map { entity -> bookRepository.findAllByEdition(entity, pageable) }
            .orElse(Page.empty())
            .map(BookEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAllOrdersByEditionId(editionId: UUID, pageable: Pageable): Page<Order> {
        return editionRepository.findById(editionId)
            .map { entity -> orderRepository.findAllByEdition(entity, pageable) }
            .orElse(Page.empty())
            .map(OrderEntity::map)
    }

    @Transactional
    fun updateBooksByEditionId(editionId: UUID, bookIds: List<UUID>): List<Book> {
        return editionRepository
            .findById(editionId)
            .map { editionEntity ->
                editionEntity.books.clear()
                editionEntity.books += bookIds.map(bookRepository::getOne)
                return@map editionEntity
            }
            .map { edition: EditionEntity -> editionRepository.saveAndFlush(edition) }
            .map { edition: EditionEntity -> edition.books }
            .orElse(mutableSetOf())
            .map(BookEntity::map)
    }

    @Transactional
    fun updateOrdersByEditionId(editionId: UUID, orderIds: List<UUID>): List<Order> {
        return editionRepository
            .findById(editionId)
            .map { editionEntity ->
                editionEntity.orders.clear()
                editionEntity.orders += orderIds.map(orderRepository::getOne)
                return@map editionEntity
            }
            .map { edition: EditionEntity -> editionRepository.saveAndFlush(edition) }
            .map { obj: EditionEntity -> obj.orders }
            .orElse(mutableSetOf())
            .map(OrderEntity::map)
    }
}