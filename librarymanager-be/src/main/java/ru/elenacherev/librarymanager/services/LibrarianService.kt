package ru.elenacherev.librarymanager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.api.dto.Librarian
import ru.elenacherev.librarymanager.domain.entity.LibrarianEntity
import ru.elenacherev.librarymanager.domain.repository.LibrarianRepository
import ru.elenacherev.librarymanager.mapper.map
import java.util.*

@Service
class LibrarianService(
    var librarianRepository: LibrarianRepository
) {
    @Transactional(readOnly = true)
    fun findAllByPage(pageable: Pageable): Page<Librarian> {
        return librarianRepository
            .findAll(pageable)
            .map(LibrarianEntity::map)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Librarian> {
        return librarianRepository
            .findAll()
            .map(LibrarianEntity::map)
    }

    @Transactional(readOnly = true)
    fun findLibrarianById(librarianId: UUID): Librarian {
        return librarianRepository
            .findById(librarianId)
            .map(LibrarianEntity::map)
            .orElse(null)
    }

    @Transactional
    fun save(librarianId: UUID, dto: Librarian): Librarian {
        return librarianRepository
            .findById(librarianId)
            .map(dto::map)
            .map { librarian: LibrarianEntity -> librarianRepository.saveAndFlush(librarian) }
            .map(LibrarianEntity::map)
            .orElse(null)
    }

    @Transactional
    fun create(dto: Librarian): Librarian {
        return Optional.of(dto)
            .map(Librarian::map)
            .map { librarian: LibrarianEntity -> librarianRepository.saveAndFlush(librarian) }
            .map(LibrarianEntity::map)
            .orElse(null)
    }
}