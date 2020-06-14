package ru.elenacherev.librarymanager.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.elenacherev.librarymanager.api.LibrarianApi
import ru.elenacherev.librarymanager.api.dto.Librarian
import ru.elenacherev.librarymanager.services.LibrarianService

@RequestMapping(value = ["librarians"])
@RestController
class LibrarianController(
    val librarianService: LibrarianService
) : LibrarianApi {

    @GetMapping("/{librarianid}")
    override fun getLibrarian(
        @PathVariable("librarianid") librarianId: Long
    ) = librarianService.findLibrarianById(librarianId)

    @PostMapping("/search")
    override fun searchLibrarians(
        pageable: Pageable
    ) = librarianService.findAllByPage(pageable)

    @PostMapping
    override fun createLibrarian(
        @RequestBody librarian: Librarian
    ) = librarianService.create(librarian)

    @PutMapping("/{librarianid}")
    override fun saveLibrarian(
        @PathVariable("librarianid") librarianId: Long,
        @RequestBody librarian: Librarian
    ) = librarianService.save(librarianId, librarian)
}