package ru.spring5.elena.librarymanager.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring5.elena.librarymanager.api.dto.Book;
import ru.spring5.elena.librarymanager.api.dto.BookUsing;
import ru.spring5.elena.librarymanager.domain.entity.BookEntity;
import ru.spring5.elena.librarymanager.domain.entity.EditionEntity;
import ru.spring5.elena.librarymanager.domain.repository.BookRepository;
import ru.spring5.elena.librarymanager.domain.repository.BookUsingRepository;
import ru.spring5.elena.librarymanager.domain.repository.EditionRepository;
import ru.spring5.elena.librarymanager.mapper.BookMapper;
import ru.spring5.elena.librarymanager.mapper.BookUsingMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final BookUsingRepository bookUsingRepository;
    private final EditionRepository editionRepository;

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Book> findAllByPage(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(BookMapper::map);
    }

    @Transactional(readOnly = true)
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::map)
                .orElse(null);
    }

    public Book save(Long bookId, Book dto) {
    	EditionEntity edition = editionRepository.findById(dto.getEditionId()).get();
    	return bookRepository.findById(bookId)
                .map(bookEntity -> BookMapper.map(dto, bookEntity, edition))
                .map(bookRepository::saveAndFlush)
                .map(BookMapper::map)
                .orElse(null);
    }

    public Book create(Book dto) {
    	EditionEntity edition = editionRepository.findById(dto.getEditionId()).get();
    	return Optional.of(dto)
                .map(book -> BookMapper.map(book,edition))
                .map(bookRepository::saveAndFlush)
                .map(BookMapper::map)
                .get();
    }

    @Transactional(readOnly = true)
	public Page<BookUsing> findBookUsingsByBookId(Long bookId, Pageable pageable) {
		return bookRepository.findById(bookId)
                .map(entity -> bookUsingRepository.findAllByBook(entity, pageable))
                .orElse(Page.empty())
                .map(BookUsingMapper:: map);
	}

    @Transactional
	public List<BookUsing> updateBookUsings(Long bookId, List<Long> bookUsingIds) {
		return bookRepository.findById(bookId)
		.map(bookEntity-> {
			bookEntity.getBookUsings().clear();
			bookEntity.getBookUsings().addAll(
					bookUsingIds.stream()
						.map(bookUsingRepository::getOne)
						.collect(Collectors.toList())
						);
			return bookEntity;
		 })
		.map(bookRepository::saveAndFlush)
		.map(BookEntity :: getBookUsings)
		.orElse(Collections.emptySet())
		.stream()
		.map(BookUsingMapper :: map)
		.collect(Collectors.toList());
	}

}
