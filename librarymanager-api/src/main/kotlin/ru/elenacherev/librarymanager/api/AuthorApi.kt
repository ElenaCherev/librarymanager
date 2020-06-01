package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Author
import ru.elenacherev.librarymanager.api.dto.Edition
import javax.validation.Valid

interface AuthorApi {
    fun getAuthor(
        authorId: Long
    ): Author?

    fun createAuthor(
        author: @Valid Author
    ): Author

    fun saveAuthor(
        authorId: Long,
        author: @Valid Author
    ): Author

    fun searchAuthors(
        pageable: Pageable
    ): Page<Author>

    fun searchEditions(
        authorId: Long,
        pageable: Pageable
    ): Page<Edition>

    fun updateEditionsByAuthorId(
        authorId: Long,
        editionIds: List<Long>
    ): List<Edition>
}