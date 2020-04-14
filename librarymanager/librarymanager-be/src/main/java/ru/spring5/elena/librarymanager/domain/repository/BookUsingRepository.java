package ru.spring5.elena.librarymanager.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.spring5.elena.librarymanager.domain.entity.BookEntity;
import ru.spring5.elena.librarymanager.domain.entity.BookUsingEntity;
import ru.spring5.elena.librarymanager.domain.entity.ReaderEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface BookUsingRepository extends JpaRepository<BookUsingEntity, Long> {
    Page<BookUsingEntity> findAllByReader(ReaderEntity reader, Pageable pageable);
    Page<BookUsingEntity> findAllByBook(BookEntity book, Pageable pageable);
}
