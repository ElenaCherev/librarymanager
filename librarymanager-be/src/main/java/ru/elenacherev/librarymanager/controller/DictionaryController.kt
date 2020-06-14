package ru.elenacherev.librarymanager.controller

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.elenacherev.librarymanager.api.DictionaryApi
import ru.elenacherev.librarymanager.services.GenreService
import ru.elenacherev.librarymanager.services.LangService

@RequestMapping("/dictionary")
@RestController
class DictionaryController(
    val genresService: GenreService,
    val langService: LangService
) : DictionaryApi {

    @GetMapping("/genres/{genreid}")
    override fun getGenre(
        @PathVariable("genreid") genreId: Long
    ) = genresService.findById(genreId)

    @PostMapping("/genres/search")
    override fun searchGenres() = genresService.findAll()

    @PostMapping("/genres/{genreid}/editions/search")
    override fun searchEditionsByGenreId(
        @PathVariable("genreid") genreId: Long,
        pageable: Pageable
    ) = genresService.findEditionsByGenreId(genreId, pageable)

    @PutMapping("/genres/{genreid}/editions/")
    override fun updateEditionsByGenreId(
        @PathVariable("genreid") genreId: Long,
        @RequestParam("list") editionIds: List<Long>
    ) = genresService.updateEditionsByGenreId(genreId, editionIds)

    @GetMapping("/langs/{langid}")
    override fun getLang(
        @PathVariable("langid") langId: Long
    ) = langService.findLangById(langId)

    @PostMapping("/langs/search")
    override fun searchLangs() = langService.findAll()

    @PostMapping("/langs/lang/{langid}/editions/search")
    override fun searchEditionsByLangId(
        @PathVariable("langid") langId: Long,
        pageable: Pageable
    ) = langService.findEditionsByLangId(langId, pageable)

    @PutMapping("/langs/lang/{langid}/editions/")
    override fun updateEditionsByLangId(
        @PathVariable("langid") langId: Long,
        @RequestParam("list") editionIds: List<Long>
    ) = langService.updateEditionsByLangId(langId, editionIds)

    @PostMapping("/langs/publlang/{publlangid}/editions/search")
    override fun searchEditionsByPublLangId(
        @PathVariable("publlangid") publLangId: Long,
        pageable: Pageable
    ) = langService.findEditionsByPublLangId(publLangId, pageable)

    @PutMapping("/langs/publlang/{publlangid}/editions/")
    override fun updateEditionsByPubllangId(
        @PathVariable("publlangid") publLangId: Long,
        @RequestParam("list") editionIds: List<Long>
    ) = langService.updateEditionsByPubllangId(publLangId, editionIds)
}