package ru.elenacherev.librarymanager

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import ru.elenacherev.librarymanager.api.enum.AgeRating
import ru.elenacherev.librarymanager.api.enum.OrderState
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
import java.time.Instant
import java.util.*
import kotlin.random.Random

/*CONSTANTS */

val TEST_AUTHOR_ENTITY = AuthorEntity(
    birthdate = Instant.now(),
    deathdate = Instant.now(),
    firstName = RandomStringUtils.random(60),
    surname = RandomStringUtils.random(60),
    patronymic = RandomStringUtils.random(60),
    info = RandomStringUtils.random(1000)
)

val TEST_READER_ENTITY = ReaderEntity(
    firstName = RandomStringUtils.random(60),
    surname = RandomStringUtils.random(60),
    patronymic = RandomStringUtils.random(60),
    birthdate = Date.from(Instant.now()),
    email = RandomStringUtils.random(200),
    regDate = Date.from(Instant.now()),
    telephone = RandomStringUtils.random(12, false, true)
)

val TEST_GENRE_ENTITY = GenreEntity(
    title = RandomStringUtils.random(255)
)

val TEST_LANG_ENTITY = LangEntity(
    title = RandomStringUtils.random(255)
)

val TEST_PUBLISHING_HOUSE = PublishingHouseEntity(
    title = RandomStringUtils.random(255)
)

val TEST_SECTION_ENTITY = SectionEntity(
    parentSection = null,
    title = RandomStringUtils.random(60)
)

/* FACTORY */
fun testEditionEntity(
    genre: GenreEntity,
    lang: LangEntity,
    section: SectionEntity,
    publishingHouse: PublishingHouseEntity
) = EditionEntity(
    title = RandomStringUtils.random(255),
    workTitle = RandomStringUtils.random(255),
    year = RandomUtils.nextInt(1800, 2020),
    genre = genre,
    lang = lang,
    section = section,
    publishingYear = 2020,
    isbn = RandomStringUtils.random(255),
    isIllustrated = RandomUtils.nextBoolean(),
    downloadLink = RandomStringUtils.random(255),
    age = AgeRating._16,
    info = RandomStringUtils.random(255),
    publishingHouse = publishingHouse,
    publLang = lang
)

fun testBookEntity(
    edition: EditionEntity
) = BookEntity(
    edition = edition,
    isAvailable = RandomUtils.nextBoolean(),
    isReserved = RandomUtils.nextBoolean(),
    shelfNumber = Random.nextInt()
)

fun testBookUsingEntity(
    book: BookEntity,
    reader: ReaderEntity
) = BookUsingEntity(
    book = book,
    reader = reader,
    startDate = Instant.now(),
    endDate = Instant.now()
)

fun testOrderEntity(
    edition: EditionEntity,
    reader: ReaderEntity
) = OrderEntity(
    reader = reader,
    edition = edition,
    orderDate = Date.from(Instant.now()),
    completeDate = Date.from(Instant.now()),
    orderState = OrderState.NEW
)
