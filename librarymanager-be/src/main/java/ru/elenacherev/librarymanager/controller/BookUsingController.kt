package ru.elenacherev.librarymanager.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.elenacherev.librarymanager.api.BookUsingApi
import ru.elenacherev.librarymanager.api.dto.BookUsing
import ru.elenacherev.librarymanager.services.BookUsingService
import javax.validation.Valid

@RequestMapping("/bookUsings")
@RestController
class BookUsingController(
    val bookUsingService: BookUsingService
) : BookUsingApi {

    @GetMapping("/{bookusingid}")
    override fun getBookUsing(
        @PathVariable("bookusingid") bookusingid: Long
    ) = bookUsingService.findBookUsingById(bookusingid)

    @PostMapping("/search")
    override fun searchBookUsings(
        pageable: Pageable
    ) = bookUsingService.findAllByPage(pageable)

    @PostMapping
    override fun createBookUsing(
        @RequestBody bookUsing: @Valid BookUsing
    ) = bookUsingService.create(bookUsing)

    @PutMapping("/{bookusingid}")
    override fun saveBookUsing(
        @PathVariable("bookusingid") bookUsingId: Long,
        @RequestBody bookUsing: @Valid BookUsing
    ) = bookUsingService.save(bookUsingId, bookUsing)
}