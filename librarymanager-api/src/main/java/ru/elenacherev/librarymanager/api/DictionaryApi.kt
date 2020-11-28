package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Genre
import ru.elenacherev.librarymanager.api.dto.Lang
import java.util.*

interface DictionaryApi {
    fun getGenre(
        genreId: UUID
    ): Genre?

    fun searchGenres(): List<Genre>

    fun searchEditionsByGenreId(
        genreId: UUID,
        pageable: Pageable
    ): Page<Edition>

    fun getLang(
        langId: UUID
    ): Lang?

    fun searchLangs(): List<Lang>

    fun searchEditionsByLangId(
        langId: UUID,
        pageable: Pageable
    ): Page<Edition>

    fun searchEditionsByPublLangId(
        publLangId: UUID,
        pageable: Pageable
    ): Page<Edition>

    fun updateEditionsByGenreId(
        genreId: UUID,
        editionIds: List<UUID>
    ): List<Edition>

    fun updateEditionsByLangId(
        langId: UUID,
        editionIds: List<UUID>
    ): List<Edition>

    fun updateEditionsByPubllangId(
        publLangId: UUID,
        editionIds: List<UUID>
    ): List<Edition>
}