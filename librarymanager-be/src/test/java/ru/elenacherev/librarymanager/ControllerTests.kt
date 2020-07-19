package ru.elenacherev.librarymanager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
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

@EnableAutoConfiguration
@WebMvcTest
class ControllerTests(
    @Autowired val mockMvc: MockMvc
) {
    @MockBean
    lateinit var authorRepository: AuthorRepository

    @MockBean
    lateinit var bookRepository: BookRepository

    @MockBean
    lateinit var bookUsingRepository: BookUsingRepository

    @MockBean
    lateinit var editionRepository: EditionRepository

    @MockBean
    lateinit var genreRepository: GenreRepository

    @MockBean
    lateinit var langRepository: LangRepository

    @MockBean
    lateinit var orderRepository: OrderRepository

    @MockBean
    lateinit var publishingHouseRepository: PublishingHouseRepository

    @MockBean
    lateinit var readerRepository: ReaderRepository

    @MockBean
    lateinit var sectionRepository: SectionRepository
}