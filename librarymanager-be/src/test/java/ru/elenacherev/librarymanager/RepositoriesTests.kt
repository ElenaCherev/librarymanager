package ru.elenacherev.librarymanager

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import ru.elenacherev.librarymanager.domain.repository.BookRepository
import ru.elenacherev.librarymanager.domain.repository.BookUsingRepository
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.domain.repository.OrderRepository
import ru.elenacherev.librarymanager.domain.repository.SectionRepository

/** Testing nonempty repository */
@DataJpaTest
class RepositoriesTests(
    val entityManager: TestEntityManager,
    val bookRepository: BookRepository,
    val bookUsingRepository: BookUsingRepository,
    val editionRepository: EditionRepository,
    val orderRepository: OrderRepository,
    val sectionRepository: SectionRepository
) {
}