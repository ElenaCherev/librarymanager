package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.Order
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.OrderEntity
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.domain.repository.OrderRepository
import ru.elenacherev.librarymanager.domain.repository.ReaderRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val readerRepository: ReaderRepository,
    val editionRepository: EditionRepository
) {
    @Transactional(readOnly = true)
    fun findAllByPage(pageable: Pageable): Page<Order> {
        return orderRepository
            .findAll(pageable)
            .map(OrderEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Order> {
        return orderRepository
            .findAll()
            .map(OrderEntity::map)
    }

    @Transactional(readOnly = true)
    fun findOrderById(orderId: UUID): Order {
        return orderRepository
            .findById(orderId)
            .map(OrderEntity::map)
            .orElse(null)
    }

    @Transactional
    fun save(orderId: UUID, dto: Order): Order {
        val readerEntity: ReaderEntity = readerRepository.findById(dto.readerId).get()
        val editionEntity: EditionEntity = editionRepository.findById(dto.editionId).get()
        return orderRepository.findById(orderId)
            .map { entity -> dto.map(entity, readerEntity, editionEntity) }
            .map { order: OrderEntity -> orderRepository.saveAndFlush(order) }
            .map(OrderEntity::map)
            .orElse(null)
    }

    @Transactional
    fun create(dto: Order): Order {
        val readerEntity: ReaderEntity = readerRepository.findById(dto.readerId).get()
        val editionEntity: EditionEntity = editionRepository.findById(dto.editionId).get()
        return Optional.of(dto)
            .map { order -> order.map(readerEntity, editionEntity) }
            .map { order: OrderEntity -> orderRepository.saveAndFlush(order) }
            .map(OrderEntity::map)
            .orElse(null)
    }
}