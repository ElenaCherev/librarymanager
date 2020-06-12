package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.Lang
import ru.elenacherev.librarymanager.domain.entity.LangEntity

fun LangEntity.map() = Lang(
    langId = langId,
    title = title
)

fun Lang.map() = LangEntity(title = title)

fun Lang.map(entity: LangEntity) = entity.also {
    it.title = title
}