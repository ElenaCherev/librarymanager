package ru.spring5.elena.librarymanager.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring5.elena.librarymanager.api.dto.Librarian;
import ru.spring5.elena.librarymanager.domain.repository.LibrarianRepository;
import ru.spring5.elena.librarymanager.mapper.LibrarianMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class LibrarianService {
    LibrarianRepository librarianRepository;

    @Transactional(readOnly = true)
    public Page<Librarian> findAllByPage(Pageable pageable) {
        return librarianRepository.findAll(pageable)
                .map(LibrarianMapper::map);
    }

    @Transactional(readOnly = true)
    public List<Librarian> findAll() {
        return librarianRepository.findAll()
                .stream()
                .map(LibrarianMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Librarian findLibrarianById(Long librarianId) {
        return librarianRepository.findById(librarianId)
                .map(LibrarianMapper::map)
                .orElse(null);
    }

    @Transactional
    public Librarian save(Long librarianId,  Librarian dto) {
        return librarianRepository.findById(librarianId)
                .map(entity -> LibrarianMapper.map(dto, entity))
                .map(librarianRepository::saveAndFlush)
                .map(LibrarianMapper::map)
                .orElse(null);

    }

    @Transactional
    public Librarian create(Librarian dto) {
        return   Optional.of(dto)
                .map(LibrarianMapper::map)
                .map(librarianRepository::saveAndFlush)
                .map(LibrarianMapper::map)
                .orElse(null);

    }
}
