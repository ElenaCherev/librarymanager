package ru.elenacherev.librarymanager.domain.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.OrderEntity
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity

@Repository
@Transactional(propagation = Propagation.MANDATORY)
interface OrderRepository : JpaRepository<OrderEntity, Long> {
    fun findAllByEdition(edition: EditionEntity, pageable: Pageable): Page<OrderEntity>
    fun findAllByReader(reader: ReaderEntity, pageable: Pageable): Page<OrderEntity>
}