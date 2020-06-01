package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Edition
import ru.elenacherev.librarymanager.api.dto.Genre
import ru.elenacherev.librarymanager.api.dto.Lang

interface DictionaryApi {
    fun getGenre(
        genreId:  Long
    ): Genre?

    fun searchGenres(): List<Genre>

    fun searchEditionsByGenreId(
        genreId:  Long,
        pageable: Pageable
    ): Page<Edition>

    fun getLang(
        langId:  Long
    ): Lang?

    fun searchLangs(): List<Lang>

    fun searchEditionsByLangId(
        langId:  Long,
        pageable: Pageable
    ): Page<Edition>

    fun searchEditionsByPublLangId(
        publLangId:  Long,
        pageable: Pageable
    ): Page<Edition>

    fun updateEditionsByGenreId(
        genreId:  Long,
        editionIds:  List<Long>
    ): List<Edition>

    fun updateEditionsByLangId(
        langId:  Long,
        editionIds:  List<Long>
    ): List<Edition>

    fun updateEditionsByPubllangId(
        publLangId:  Long,
        editionIds:  List<Long>
    ): List<Edition>
}