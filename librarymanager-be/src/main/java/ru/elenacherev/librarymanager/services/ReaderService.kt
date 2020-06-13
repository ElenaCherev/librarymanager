package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.BookUsing
import ru.elenacherev.librarymanager.api.dto.Order
import ru.elenacherev.librarymanager.api.dto.Reader
import ru.elenacherev.librarymanager.domain.entity.BookUsingEntity
import ru.elenacherev.librarymanager.domain.entity.OrderEntity
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity
import ru.elenacherev.librarymanager.domain.repository.BookUsingRepository
import ru.elenacherev.librarymanager.domain.repository.OrderRepository
import ru.elenacherev.librarymanager.domain.repository.ReaderRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*

//Реализация службы доступа к данным о читателях
@Service
class ReaderService (
    val readerRepository: ReaderRepository,
    val orderRepository: OrderRepository,
    val bookUsingRepository: BookUsingRepository
){
    @Transactional(readOnly = true)
    fun findAll(): List<Reader> {
        return readerRepository
            .findAll()
            .map(ReaderEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAllByPage(pageable: Pageable): Page<Reader> {
        return readerRepository
            .findAll(pageable)
            .map(ReaderEntity::map)
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): Reader {
        return readerRepository
            .findById(id)
            .map(ReaderEntity::map)
            .orElse(null)
    }

    @Transactional
    fun save(readerId: Long, dto: Reader): Reader {
        return readerRepository
            .findById(readerId)
            .map(dto::map)
            .map{ reader: ReaderEntity -> readerRepository.saveAndFlush(reader) }
            .map(ReaderEntity::map)
            .orElse(null)
    }

    @Transactional
    fun create(dto: Reader): Reader {
        return Optional.of(dto)
            .map(Reader::map)
            .map{ reader: ReaderEntity -> readerRepository.saveAndFlush(reader) }
            .map(ReaderEntity::map)
            .orElse(null)
    }

    @Transactional(readOnly = true)
    fun findOrdersByReaderId(
        readerId: Long,
        pageable: Pageable
    ): Page<Order> {
        return readerRepository
            .findById(readerId)
            .map {entity -> orderRepository.findAllByReader(entity, pageable)}
            .orElse(Page.empty())
            .map(OrderEntity::map)
    }

    @Transactional
    fun updateOrders(readerId: Long, orderIds: List<Long>): List<Order> {
        return readerRepository
            .findById(readerId)
            .map { readerEntity ->
                readerEntity.orders.clear()
                readerEntity.orders += orderIds.map(orderRepository::getOne)
                return@map readerEntity
            }
            .map{ reader: ReaderEntity -> readerRepository.saveAndFlush(reader) }
            .map{obj: ReaderEntity -> obj.orders}
            .orElse(mutableSetOf())
            .map(OrderEntity::map)
    }

    @Transactional(readOnly = true)
    fun findBookUsingsByReaderId(readerId: Long, pageable: Pageable): Page<BookUsing> {
        return readerRepository
            .findById(readerId)
            .map { entity -> bookUsingRepository.findAllByReader(entity, pageable) }
            .orElse(Page.empty())
            .map(BookUsingEntity::map)
    }

    @Transactional
    fun updateBookUsings(readerId: Long, bookUsingIds: List<Long>): List<BookUsing> {
        return readerRepository
            .findById(readerId)
            .map { readerEntity ->
                readerEntity.bookUsings.clear()
                readerEntity.bookUsings += bookUsingIds.map(bookUsingRepository::getOne)
                return@map readerEntity
            }
            .map{ reader: ReaderEntity -> readerRepository.saveAndFlush(reader) }
            .map{ obj: ReaderEntity -> obj.bookUsings }
            .orElse(mutableSetOf())
            .map(BookUsingEntity::map)
    }
}