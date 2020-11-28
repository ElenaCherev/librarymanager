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
import ru.elenacherev.librarymanager.api.BookApi
import ru.elenacherev.librarymanager.api.dto.Book
import ru.elenacherev.librarymanager.services.BookService
import java.util.*
import javax.validation.Valid

@RequestMapping("/books")
@RestController
class BookController(
    val bookService: BookService
) : BookApi {

    @GetMapping(value = ["/{bookid}"])
    override fun getBook(
        @PathVariable("bookid") bookId: UUID
    ) = bookService.findBookById(bookId)

    @PostMapping("/search")
    override fun searchBooks(
        pageable: Pageable
    ) = bookService.findAllByPage(pageable)

    @PostMapping
    override fun createBook(
        @RequestBody book: @Valid Book
    ) = bookService.create(book)

    @PutMapping(value = ["/{bookid}"])
    override fun saveBook(
        @PathVariable("bookid") bookId: UUID,
        @RequestBody book: @Valid Book
    ) = bookService.save(bookId, book)

    @PostMapping("/{bookid}/bookusings/search")
    override fun searchBookUsings(
        @PathVariable("bookid") bookId: UUID,
        pageable: Pageable
    ) = bookService.findBookUsingsByBookId(bookId, pageable)

    @PutMapping("/{bookid}/bookusings/")
    override fun updateBookUsings(
        @PathVariable("bookid") bookId: UUID,
        @RequestParam("list") bookUsingIds: List<UUID>
    ) = bookService.updateBookUsings(bookId, bookUsingIds)
}