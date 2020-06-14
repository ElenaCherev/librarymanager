package ru.elenacherev.librarymanager

import org.apache.commons.lang3.RandomStringUtils
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.domain.entity.AuthorEntity
import ru.elenacherev.librarymanager.domain.entity.BookEntity
import ru.elenacherev.librarymanager.domain.entity.BookUsingEntity
import ru.elenacherev.librarymanager.domain.entity.EditionEntity
import ru.elenacherev.librarymanager.domain.entity.GenreEntity
import ru.elenacherev.librarymanager.domain.entity.LangEntity
import ru.elenacherev.librarymanager.domain.entity.OrderEntity
import ru.elenacherev.librarymanager.domain.entity.PublishingHouseEntity
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity
import ru.elenacherev.librarymanager.domain.entity.SectionEntity
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

/** Testing nonempty repository */
@DataJpaTest
class RepositoriesTests(
    //val entityManager: TestEntityManager,
    val authorRepository: AuthorRepository,
    val bookRepository: BookRepository,
    val bookUsingRepository: BookUsingRepository,
    val editionRepository: EditionRepository,
    val genreRepository: GenreRepository,
    val langRepository: LangRepository,
    val orderRepository: OrderRepository,
    val publishingHouseRepository: PublishingHouseRepository,
    val readerRepository: ReaderRepository,
    val sectionRepository: SectionRepository
) {
    var genre: GenreEntity? = null
    var lang: LangEntity? = null
    var section: SectionEntity? = null
    var publishingHouse: PublishingHouseEntity? = null

    fun initTestData() {
        genre = genreRepository.saveAndFlush(TEST_GENRE_ENTITY)
        assertThat(genre!!)
            .extracting(GenreEntity::genreId)
            .isNotNull()

        lang = langRepository.saveAndFlush(TEST_LANG_ENTITY)
        assertThat(lang!!)
            .extracting(LangEntity::langId)
            .isNotNull()

        section = sectionRepository.saveAndFlush(TEST_SECTION_ENTITY)
        assertThat(section!!)
            .extracting(SectionEntity::sectionId)
            .isNotNull()

        publishingHouse = publishingHouseRepository.saveAndFlush(TEST_PUBLISHING_HOUSE)
        assertThat(publishingHouse!!)
            .extracting(PublishingHouseEntity::publishingHouseId)
            .isNotNull()
    }

    fun dropTestData(){
        genre?.let { genreRepository.delete(it) }
        lang?.let { langRepository.delete(it) }
        section?.let { sectionRepository.delete(it) }
        publishingHouse?.let { publishingHouseRepository.delete(it) }
    }

    /** BookRepository */
    @Test
    fun `Test findAllByEdition of BookRepository`() {
        initTestData()

        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val book = bookRepository.saveAndFlush(testBookEntity(
            edition = edition
        ))
        assertThat(book)
            .extracting(BookEntity::bookId)
            .isNotNull()

        val result = bookRepository.findAllByEdition(
            edition = edition,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(book)

        bookRepository.delete(book)
        editionRepository.delete(edition)
        dropTestData()
    }

    /** BookUsingRepository*/
    @Test
    fun `Test findAllByReader of BookUsingRepository`() {
        initTestData()

        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val book = bookRepository.saveAndFlush(testBookEntity(
            edition = edition
        ))
        assertThat(book)
            .extracting(BookEntity::bookId)
            .isNotNull()

        val reader = readerRepository.saveAndFlush(TEST_READER_ENTITY)
        assertThat(reader)
            .extracting(ReaderEntity::readerId)
            .isNotNull()

        val bookUsing = bookUsingRepository.saveAndFlush(testBookUsingEntity(
            reader = reader,
            book = book
        ))
        assertThat(bookUsing)
            .extracting(BookUsingEntity::bookUsingId)
            .isNotNull()

        val result = bookUsingRepository.findAllByReader(
            reader = reader,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(bookUsing)

        bookRepository.delete(book)
        editionRepository.delete(edition)
        readerRepository.delete(reader)
        bookUsingRepository.delete(bookUsing)
        dropTestData()
    }

    @Test
    fun `Test findAllByBook of BookUsingRepository`() {
        initTestData()

        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val book = bookRepository.saveAndFlush(testBookEntity(
            edition = edition
        ))
        assertThat(book)
            .extracting(BookEntity::bookId)
            .isNotNull()

        val reader = readerRepository.saveAndFlush(TEST_READER_ENTITY)
        assertThat(reader)
            .extracting(ReaderEntity::readerId)
            .isNotNull()

        val bookUsing = bookUsingRepository.saveAndFlush(testBookUsingEntity(
            reader = reader,
            book = book
        ))
        assertThat(bookUsing)
            .extracting(BookUsingEntity::bookUsingId)
            .isNotNull()

        val result = bookUsingRepository.findAllByBook(
            book = book,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(bookUsing)

        bookRepository.delete(book)
        editionRepository.delete(edition)
        readerRepository.delete(reader)
        bookUsingRepository.delete(bookUsing)
        dropTestData()
    }

    /** EditionRepository */
    @Test
    fun `Test findAllBySection of EditionRepository`() {
        initTestData()

        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val result = editionRepository.findAllBySection(
            section = section!!,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(edition)

        editionRepository.delete(edition)
        dropTestData()
    }

    @Test
    fun `Test findAllByAuthors of EditionRepository`() {
        initTestData()

        val author: AuthorEntity  = authorRepository.saveAndFlush(TEST_AUTHOR_ENTITY)
        assertThat(author)
            .extracting(AuthorEntity::authorId)
            .isNotNull()

        var edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        edition.authors.add(author)
        edition = editionRepository.saveAndFlush(edition)

        val result = editionRepository.findAllByAuthor(
            author = author,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(edition)

        authorRepository.delete(author)
        editionRepository.delete(edition)
        dropTestData()
    }

    @Test
    fun `Test findAllByGenre of EditionRepository`() {
        initTestData()

        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val result = editionRepository.findAllByGenre(
            genre = genre!!,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(edition)

        editionRepository.delete(edition)
        dropTestData()
    }

    @Test
    fun `Test findAllByLang of EditionRepository`() {
        initTestData()

        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val result = editionRepository.findAllByLang(
            lang = lang!!,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(edition)

        editionRepository.delete(edition)
        dropTestData()
    }

    @Test
    fun `Test findAllByPublLang of EditionRepository`() {
        initTestData()

        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val result = editionRepository.findAllByPublLang(
            publLang = lang!!,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(edition)

        editionRepository.delete(edition)
        dropTestData()
    }

    @Test
    fun `Test findAllByPublishingHouse of EditionRepository`() {
        initTestData()

        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val result = editionRepository.findAllByPublishingHouse(
            publishingHouse = publishingHouse!!,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(edition)

        editionRepository.delete(edition)
        dropTestData()
    }

    /** OrderRepository */
    @Test
    fun `Test findAllByEdition of OrderRepository`() {
        initTestData()
        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val reader = readerRepository.saveAndFlush(TEST_READER_ENTITY)
        assertThat(reader)
            .extracting(ReaderEntity::readerId)
            .isNotNull()

        val order: OrderEntity = orderRepository.saveAndFlush(testOrderEntity(
            edition = edition,
            reader = reader
        ))
        assertThat(order)
            .extracting(OrderEntity::orderId)
            .isNotNull()

        val result = orderRepository.findAllByEdition(
            edition = edition,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(order)

        orderRepository.delete(order)
        editionRepository.delete(edition)
        readerRepository.delete(reader)
        dropTestData()
    }

    @Test
    fun `Test findAllByReader of OrderRepository`() {
        initTestData()
        val edition = editionRepository.saveAndFlush(testEditionEntity(
            genre = genre!!,
            lang = lang!!,
            section = section!!,
            publishingHouse = publishingHouse!!
        ))
        assertThat(edition)
            .extracting(EditionEntity::editionId)
            .isNotNull()

        val reader = readerRepository.saveAndFlush(TEST_READER_ENTITY)
        assertThat(reader)
            .extracting(ReaderEntity::readerId)
            .isNotNull()

        val order: OrderEntity = orderRepository.saveAndFlush(testOrderEntity(
            edition = edition,
            reader = reader
        ))
        assertThat(order)
            .extracting(OrderEntity::orderId)
            .isNotNull()

        val result = orderRepository.findAllByReader(
            reader = reader,
            pageable = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(order)

        orderRepository.delete(order)
        editionRepository.delete(edition)
        readerRepository.delete(reader)
        dropTestData()
    }

    /** SectionRepository */
    @Test
    fun `Test findAllByParentSection of SectionRepository`() {
        initTestData()

        val subSection = sectionRepository.saveAndFlush(
            SectionEntity(
                parentSection = section,
                title = RandomStringUtils.random(60)
            )
        )
        assertThat(subSection)
            .extracting(SectionEntity::sectionId)
            .isNotNull()

        val result = sectionRepository.findAllByParentSection(
            parentSection =  section!!,
            pageble = Pageable.unpaged()
        )
        assertThat(result)
            .isNotEmpty()
            .hasSize(1)
            .contains(subSection)

        sectionRepository.delete(subSection)
        dropTestData()
    }
}