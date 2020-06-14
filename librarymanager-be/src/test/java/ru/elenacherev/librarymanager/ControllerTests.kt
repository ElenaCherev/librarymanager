package ru.elenacherev.librarymanager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import ru.elenacherev.librarymanager.domain.repository.AuthorRepository
import ru.elenacherev.librarymanager.domain.repository.BookRepository
import ru.elenacherev.librarymanager.domain.repository.BookUsingRepository
import ru.elenacherev.librarymanager.domain.repository.EditionRepository
import ru.elenacherev.librarymanager.domain.repository.GenreRepository
import ru.elenacherev.librarymanager.domain.repository.LangRepository
import ru.elenacherev.librarymanager.domain.repository.OrderRepository
import ru.elenacherev.librarymanager.domain.repository.PublishingHouseRepository
import ru.elenacherev.librarymanager.domain.repository.ReaderRepository
import ru.elenacherev.librarymanager.domain.repository.SectionRepository

@WebMvcTest
class ControllerTests(
    val mockMvc: MockMvc
) {
    @MockkBean
    lateinit var authorRepository: AuthorRepository

    @MockkBean
    lateinit var bookRepository: BookRepository

    @MockkBean
    lateinit var bookUsingRepository: BookUsingRepository

    @MockkBean
    lateinit var editionRepository: EditionRepository

    @MockkBean
    lateinit var genreRepository: GenreRepository

    @MockkBean
    lateinit var langRepository: LangRepository

    @MockkBean
    lateinit var orderRepository: OrderRepository

    @MockkBean
    lateinit var publishingHouseRepository: PublishingHouseRepository

    @MockkBean
    lateinit var readerRepository: ReaderRepository

    @MockkBean
    lateinit var sectionRepository: SectionRepository
}