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
import java.util.*

@RequestMapping("/dictionary")
@RestController
class DictionaryController(
    val genresService: GenreService,
    val langService: LangService
) : DictionaryApi {

    @GetMapping("/genres/{genreid}")
    override fun getGenre(
        @PathVariable("genreid") genreId: UUID
    ) = genresService.findById(genreId)

    @PostMapping("/genres/search")
    override fun searchGenres() = genresService.findAll()

    @PostMapping("/genres/{genreid}/editions/search")
    override fun searchEditionsByGenreId(
        @PathVariable("genreid") genreId: UUID,
        pageable: Pageable
    ) = genresService.findEditionsByGenreId(genreId, pageable)

    @PutMapping("/genres/{genreid}/editions/")
    override fun updateEditionsByGenreId(
        @PathVariable("genreid") genreId: UUID,
        @RequestParam("list") editionIds: List<UUID>
    ) = genresService.updateEditionsByGenreId(genreId, editionIds)

    @GetMapping("/langs/{langid}")
    override fun getLang(
        @PathVariable("langid") langId: UUID
    ) = langService.findLangById(langId)

    @PostMapping("/langs/search")
    override fun searchLangs() = langService.findAll()

    @PostMapping("/langs/lang/{langid}/editions/search")
    override fun searchEditionsByLangId(
        @PathVariable("langid") langId: UUID,
        pageable: Pageable
    ) = langService.findEditionsByLangId(langId, pageable)

    @PutMapping("/langs/lang/{langid}/editions/")
    override fun updateEditionsByLangId(
        @PathVariable("langid") langId: UUID,
        @RequestParam("list") editionIds: List<UUID>
    ) = langService.updateEditionsByLangId(langId, editionIds)

    @PostMapping("/langs/publlang/{publlangid}/editions/search")
    override fun searchEditionsByPublLangId(
        @PathVariable("publlangid") publLangId: UUID,
        pageable: Pageable
    ) = langService.findEditionsByPublLangId(publLangId, pageable)

    @PutMapping("/langs/publlang/{publlangid}/editions/")
    override fun updateEditionsByPubllangId(
        @PathVariable("publlangid") publLangId: UUID,
        @RequestParam("list") editionIds: List<UUID>
    ) = langService.updateEditionsByPubllangId(publLangId, editionIds)
}