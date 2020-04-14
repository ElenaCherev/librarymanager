package ru.spring5.elena.librarymanager.mapper;

import ru.spring5.elena.librarymanager.api.dto.Librarian;
import ru.spring5.elena.librarymanager.domain.entity.LibrarianEntity;

public class LibrarianMapper {
    public static Librarian map(LibrarianEntity entity) {
        return Librarian.builder()
                .librarianId(entity.getLibrarianId())
                .build();
    }

    public static LibrarianEntity map(Librarian dto) {
        return map(dto, new LibrarianEntity());
    }

    public static LibrarianEntity map(Librarian dto, LibrarianEntity entity) {

        return entity;
    }
}
