package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.Book
import ru.elenacherev.librarymanager.api.dto.BookUsing
import ru.elenacherev.librarymanager.domain.entity.BookEntity
import ru.elenacherev.librarymanager.domain.entity.BookUsingEntity
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.repository.BookRepository
import ru.elenacherev.librarymanager.domain.repository.BookUsingRepository
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*

@Service
class BookService(
    val bookRepository: BookRepository,
    val bookUsingRepository: BookUsingRepository,
    val editionRepository: EditionRepository
) {
    @Transactional(readOnly = true)
    fun findAll(): List<Book> {
        return bookRepository
            .findAll()
            .map(BookEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAllByPage(pageable: Pageable): Page<Book> {
        return bookRepository
            .findAll(pageable)
            .map(BookEntity::map)
    }

    @Transactional(readOnly = true)
    fun findBookById(id: UUID): Book {
        return bookRepository
            .findById(id)
            .map(BookEntity::map)
            .orElse(null)
    }

    fun save(bookId: UUID, dto: Book): Book {
        val edition: EditionEntity = editionRepository.findById(dto.editionId).get()
        return bookRepository
            .findById(bookId)
            .map { dto.map(edition) }
            .map { book: BookEntity -> bookRepository.saveAndFlush(book) }
            .map(BookEntity::map)
            .orElse(null)
    }

    fun create(dto: Book): Book {
        val edition: EditionEntity = editionRepository.findById(dto.editionId).get()
        return Optional.of(dto)
            .map { dto.map(edition) }
            .map { book: BookEntity -> bookRepository.saveAndFlush(book) }
            .map(BookEntity::map)
            .get()
    }

    @Transactional(readOnly = true)
    fun findBookUsingsByBookId(bookId: UUID, pageable: Pageable): Page<BookUsing> {
        return bookRepository.findById(bookId)
            .map { entity -> bookUsingRepository.findAllByBook(entity, pageable) }
            .orElse(Page.empty())
            .map(BookUsingEntity::map)
    }

    @Transactional
    fun updateBookUsings(bookId: UUID, bookUsingIds: List<UUID>): List<BookUsing> {
        return bookRepository.findById(bookId)
            .map { bookEntity ->
                bookEntity.bookUsings.clear()
                bookEntity.bookUsings += bookUsingIds.map(bookUsingRepository::getOne)
                return@map bookEntity
            }
            .map { book: BookEntity -> bookRepository.saveAndFlush(book) }
            .map(BookEntity::bookUsings)
            .orElse(Collections.emptySet())
            .map(BookUsingEntity::map)
    }
}