package ru.elenacherev.librarymanager.controller

import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.elenacherev.librarymanager.api.ReaderApi
import ru.elenacherev.librarymanager.api.dto.Reader
import ru.elenacherev.librarymanager.services.ReaderService

@RequestMapping(value = ["librarymanager/readers"], produces = [MediaType.APPLICATION_JSON_VALUE])
@RestController
class ReaderController(
    val readerService: ReaderService
) : ReaderApi {

    @GetMapping("/{readerid}")
    override fun getReader(
        @PathVariable("readerid") readerId: Long
    ) = readerService.findById(readerId)

    @PostMapping("/search")
    override fun searchReaders(
        pageable: Pageable
    ) = readerService.findAllByPage(pageable)

    @PostMapping
    override fun createReader(
        @RequestBody reader: Reader
    ) = readerService.create(reader)

    @PutMapping("/{readerid}")
    override fun saveReader(
        @PathVariable("readerid") readerId: Long,
        @RequestBody reader: Reader
    ) = readerService.save(readerId, reader)

    @PostMapping("/{readerid}/orders/search")
    override fun searchOrders(
        @PathVariable("readerid") readerId: Long,
        pageable: Pageable
    ) = readerService.findOrdersByReaderId(readerId, pageable)

    @PutMapping("/{readerid}/orders/")
    override fun updateOrders(
        @PathVariable("readerid") readerId: Long,
        @RequestParam("list") orderIds: List<Long>
    ) = readerService.updateOrders(readerId, orderIds)

    @PostMapping("/{readerid}/bookusings/search")
    override fun searchBookUsings(
        @PathVariable("readerid") readerId: Long,
        pageable: Pageable
    ) = readerService.findBookUsingsByReaderId(readerId, pageable)

    @PutMapping("/{readerid}/bookusings/")
    override fun updateBookUsings(
        @PathVariable("readerid") readerId: Long,
        @RequestParam("list") bookUsingIds: List<Long>
    ) = readerService.updateBookUsings(readerId, bookUsingIds)
}