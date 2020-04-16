package ru.spring5.elena.librarymanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring5.elena.librarymanager.api.dto.BookUsing;
import ru.spring5.elena.librarymanager.domain.entity.BookEntity;
import ru.spring5.elena.librarymanager.domain.entity.ReaderEntity;
import ru.spring5.elena.librarymanager.domain.repository.BookRepository;
import ru.spring5.elena.librarymanager.domain.repository.BookUsingRepository;
import ru.spring5.elena.librarymanager.domain.repository.ReaderRepository;
import ru.spring5.elena.librarymanager.mapper.BookUsingMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BookUsingService {
    BookUsingRepository bookUsingRepository;
    ReaderRepository readerRepository;
    BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Page<BookUsing> findAllByPage(Pageable pageable) {
        return bookUsingRepository.findAll(pageable)
                .map(BookUsingMapper::map);
    }

    @Transactional(readOnly = true)
    public List<BookUsing> findAll() {
        return bookUsingRepository.findAll()
                .stream()
                .map(BookUsingMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookUsing findBookUsingById(Long bookUsingId) {
        return bookUsingRepository.findById(bookUsingId)
                .map(BookUsingMapper::map)
                .orElse(null);
    }

    @Transactional
    public BookUsing save(Long bookUsingId,  BookUsing dto) {
        
    	ReaderEntity reader = readerRepository.findById(dto.getReaderId()).get();
        BookEntity book = bookRepository.findById(dto.getBookId()).get();
        
    	return bookUsingRepository.findById(bookUsingId)
                .map(entity -> BookUsingMapper.map(entity, dto, reader, book))
                .map(bookUsingRepository::saveAndFlush)
                .map(BookUsingMapper::map)
                .orElse(null);

    }

    @Transactional
    public BookUsing create(BookUsing dto) {
    	
    	ReaderEntity readerEntity = readerRepository.findById(dto.getReaderId()).get();
        BookEntity bookEntity = bookRepository.findById(dto.getBookId()).get();
    	return   Optional.of(dto)
                .map(bookUsing -> BookUsingMapper.map(
                		bookUsing,
                		readerEntity,
                		bookEntity)
                 )
                .map(bookUsingRepository::saveAndFlush)
                .map(BookUsingMapper::map)
                .orElse(null);

    }

    @Transactional(readOnly = true)
    public  Page<BookUsing> findAllByReaderId(Long readerId, Pageable pageable){
        return  bookUsingRepository.findAllByReader(
                    readerRepository.findById(readerId).get(),
                    pageable
                ).map(BookUsingMapper::map);
    }

    @Transactional(readOnly = true)
    public  Page<BookUsing> findAllByBookId(Long bookId, Pageable pageable){
        return  bookUsingRepository.findAllByBook(
                    bookRepository.findById(bookId).get(),
                    pageable
                ).map(BookUsingMapper::map);
    }
}
