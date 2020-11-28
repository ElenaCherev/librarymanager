package ru.elenacherev.librarymanager.api

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import ru.elenacherev.librarymanager.api.dto.Author
import ru.elenacherev.librarymanager.api.dto.Edition
import java.util.*
import javax.validation.Valid

interface AuthorApi {
    fun getAuthor(
        authorId: UUID
    ): Author?

    fun createAuthor(
        author: @Valid Author
    ): Author

    fun saveAuthor(
        authorId: UUID,
        author: @Valid Author
    ): Author

    fun searchAuthors(
        pageable: Pageable
    ): Page<Author>

    fun searchEditions(
        authorId: UUID,
        pageable: Pageable
    ): Page<Edition>

    fun updateEditionsByAuthorId(
        authorId: UUID,
        editionIds: List<UUID>
    ): List<Edition>
}