package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.Author
import ru.elenacherev.librarymanager.domain.entity.AuthorEntity
import ru.elenacherev.librarymanager.domain.entity.EditionEntity

fun AuthorEntity.map() = Author(
    authorId = authorId,
    firstName = firstName,
    surname = surname,
    patronymic = patronymic,
    birthdate = birthdate,
    deathdate = deathdate,
    info = info,
    fio = fio,
    edidionIds = editions.map(EditionEntity::editionId)
)

fun Author.map() = AuthorEntity(
    firstName = firstName,
    surname = surname,
    patronymic = patronymic,
    birthdate = birthdate,
    deathdate = deathdate,
    info = info
)

fun Author.map(entity: AuthorEntity) = entity.also {
    it.firstName = firstName
    it.surname = surname
    it.patronymic = patronymic
    it.birthdate = birthdate
    it.deathdate = deathdate
    it.info = info
}