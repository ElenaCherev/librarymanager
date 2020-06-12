package ru.elenacherev.librarymanager.mapper

import ru.elenacherev.librarymanager.api.dto.Genre
import ru.elenacherev.librarymanager.domain.entity.GenreEntity

fun GenreEntity.map() = Genre(
    genreId = genreId,
    title = title
)
fun Genre.map() = GenreEntity(title = title)

fun Genre.map(entity:GenreEntity) = entity.also {
    it.title = title
}
