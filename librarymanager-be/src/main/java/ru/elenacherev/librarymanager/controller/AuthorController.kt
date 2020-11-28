package ru.elenacherev.librarymanager.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.elenacherev.librarymanager.api.AuthorApi
import ru.elenacherev.librarymanager.api.dto.Author
import ru.elenacherev.librarymanager.services.AuthorService
import java.util.*

@RequestMapping("/authors")
@RestController
class AuthorController(
    val authorService: AuthorService
) : AuthorApi {

    @GetMapping("/{authorid}")
    override fun getAuthor(
        @PathVariable("authorid") authorId: UUID
    ) = authorService.findAuthorById(authorId)

    @PostMapping("/search")
    override fun searchAuthors(
        pageable: Pageable
    ) = authorService.findAllByPage(pageable)

    @PostMapping
    override fun createAuthor(
        @RequestBody author: Author
    ) = authorService.create(author)

    @PutMapping("/{authorid}")
    override fun saveAuthor(
        @PathVariable("authorid") authorId: UUID,
        @RequestBody author: Author
    ) = authorService.save(authorId, author)

    @PostMapping("/{authorid}/editions/search")
    override fun searchEditions(
        @PathVariable("authorid") authorId: UUID,
        pageable: Pageable
    ) = authorService.findEditionsByAuthorId(authorId, pageable)

    @PutMapping("/{authorid}/editions/")
    override fun updateEditionsByAuthorId(
        @PathVariable("authorid") authorId: UUID,
        @RequestParam("list") editionIds: List<UUID>
    ) = authorService.updateEditionsByAuthorId(authorId, editionIds)
}