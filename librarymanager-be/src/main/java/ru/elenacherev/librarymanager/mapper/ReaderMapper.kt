package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.Reader
import ru.elenacherev.librarymanager.domain.entity.BookUsingEntity
import ru.elenacherev.librarymanager.domain.entity.OrderEntity
import ru.elenacherev.librarymanager.domain.entity.ReaderEntity

fun ReaderEntity.map() = Reader(
    readrId = readerId,
    firstName = firstName,
    surname = surname,
    patronymic = patronymic,
    birthdate = birthdate,
    regDate = regDate,
    telephone = telephone,
    email = email,
    bookUsingIds = bookUsings.mapNotNull(BookUsingEntity::bookUsingId),
    orderIds = orders.mapNotNull(OrderEntity::orderId)
)

fun Reader.map() = ReaderEntity(
    firstName = firstName,
    surname = surname,
    patronymic = patronymic,
    birthdate = birthdate,
    regDate = regDate,
    telephone = telephone,
    email = email
)

fun Reader.map(entity: ReaderEntity) = entity.also {
    it.firstName = firstName
    it.surname = surname
    it.patronymic = patronymic
    it.birthdate = birthdate
    it.regDate = regDate
    it.telephone = telephone
    it.email = email
}