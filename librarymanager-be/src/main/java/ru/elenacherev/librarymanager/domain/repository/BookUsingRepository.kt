package ru.elenacherev.librarymanager.domain.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.domain.entity.BookEntity
import ru.elenacherev.librarymanager.domain.entity.BookUsingEntity
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity

@Repository
@Transactional(propagation = Propagation.MANDATORY)
interface BookUsingRepository : JpaRepository<BookUsingEntity, Long> {
    fun findAllByReader(reader: ReaderEntity, pageable: Pageable): Page<BookUsingEntity>
    fun findAllByBook(book: BookEntity, pageable: Pageable): Page<BookUsingEntity>
}