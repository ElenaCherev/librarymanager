package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.BookUsing
import ru.elenacherev.librarymanager.domain.entity.BookEntity
import ru.elenacherev.librarymanager.domain.entity.BookUsingEntity
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity
import ru.elenacherev.librarymanager.domain.repository.BookRepository
import ru.elenacherev.librarymanager.domain.repository.BookUsingRepository
import ru.elenacherev.librarymanager.domain.repository.ReaderRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*

@Service
class BookUsingService(
    var bookUsingRepository: BookUsingRepository,
    var readerRepository: ReaderRepository,
    var bookRepository: BookRepository
) {
    @Transactional(readOnly = true)
    fun findAllByPage(pageable: Pageable): Page<BookUsing> {
        return bookUsingRepository
            .findAll(pageable)
            .map(BookUsingEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<BookUsing> {
        return bookUsingRepository
            .findAll()
            .map(BookUsingEntity::map)
    }

    @Transactional(readOnly = true)
    fun findBookUsingById(bookUsingId: UUID): BookUsing {
        return bookUsingRepository
            .findById(bookUsingId)
            .map(BookUsingEntity::map)
            .orElse(null)
    }

    @Transactional
    fun save(bookUsingId: UUID, dto: BookUsing): BookUsing {
        val reader: ReaderEntity = readerRepository.findById(dto.readerId).get()
        val book: BookEntity = bookRepository.findById(dto.bookId).get()

        return bookUsingRepository.findById(bookUsingId)
            .map { dto.map(book, reader) }
            .map { bookUsing: BookUsingEntity -> bookUsingRepository.saveAndFlush(bookUsing) }
            .map(BookUsingEntity::map)
            .orElse(null)
    }

    @Transactional
    fun create(dto: BookUsing): BookUsing {
        val readerEntity: ReaderEntity = readerRepository.findById(dto.readerId).get()
        val bookEntity: BookEntity = bookRepository.findById(dto.bookId).get()
        return Optional.of(dto)
            .map { dto.map(bookEntity, readerEntity) }
            .map { bookUsing: BookUsingEntity -> bookUsingRepository.saveAndFlush(bookUsing) }
            .map(BookUsingEntity::map)
            .orElse(null)
    }

    @Transactional(readOnly = true)
    fun findAllByReaderId(readerId: UUID, pageable: Pageable): Page<BookUsing> {
        return bookUsingRepository
            .findAllByReader(
                readerRepository.findById(readerId).get(),
                pageable).map(BookUsingEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAllByBookId(bookId: UUID, pageable: Pageable): Page<BookUsing> {
        return bookUsingRepository
            .findAllByBook(
                bookRepository.findById(bookId).get(),
                pageable)
            .map(BookUsingEntity::map)
    }
}