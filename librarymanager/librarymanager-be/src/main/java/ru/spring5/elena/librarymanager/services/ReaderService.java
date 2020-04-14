package ru.spring5.elena.librarymanager.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spring5.elena.librarymanager.api.dto.BookUsing;
import ru.spring5.elena.librarymanager.api.dto.Order;
import ru.spring5.elena.librarymanager.api.dto.Reader;
import ru.spring5.elena.librarymanager.domain.entity.ReaderEntity;
import ru.spring5.elena.librarymanager.domain.repository.BookUsingRepository;
import ru.spring5.elena.librarymanager.domain.repository.OrderRepository;
import ru.spring5.elena.librarymanager.domain.repository.ReaderRepository;
import ru.spring5.elena.librarymanager.mapper.BookUsingMapper;
import ru.spring5.elena.librarymanager.mapper.OrderMapper;
import ru.spring5.elena.librarymanager.mapper.ReaderMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Реализация службы доступа к данным о читателях

@RequiredArgsConstructor
@Service
@Slf4j
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final OrderRepository orderRepository;
    private final BookUsingRepository bookUsingRepository;

    @Transactional(readOnly = true)  
    public List<Reader> findAll() {
        return readerRepository.findAll()
                .stream()
                .map(ReaderMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Reader> findAllByPage(Pageable pageable) {
        return readerRepository.findAll(pageable)
                .map(ReaderMapper::map);
    }
    
    @Transactional(readOnly = true)
    public Reader findById(Long id) {
        return readerRepository.findById(id)
                .map(ReaderMapper::map)
                .orElse(null);
    }

    @Transactional
    public Reader save(Long readerId, Reader dto) {
        return readerRepository.findById(readerId)
                .map(entity->ReaderMapper.map(dto,entity))
                .map(readerRepository::saveAndFlush)
                .map(ReaderMapper::map)
                .orElse(null);
    }
    
    @Transactional
    public Reader create(Reader dto) {
        return   Optional.of(dto)
                .map(ReaderMapper::map)
                .map(readerRepository::saveAndFlush)
                .map(ReaderMapper::map)
                .orElse(null);

    }
    
    @Transactional(readOnly = true)
    public Page<Order> findOrdersByReaderId(Long readerId, Pageable pageable) {
        return  readerRepository.findById(readerId)
        	   .map(entity -> orderRepository.findAllByReader(entity, pageable))
        	   .orElse(Page.empty())
        	   .map(OrderMapper::map);
    }
    
    @Transactional
	public List<Order> updateOrders(Long readerId, List<Long> orderIds) {
		return readerRepository.findById(readerId)
				.map(readerEntity-> {
					readerEntity.getOrders().clear();
					readerEntity.getOrders().addAll(
							orderIds.stream()
  							.map(orderRepository::getOne)
  							.collect(Collectors.toList())
  							);
					return readerEntity;
				 })
				.map(readerRepository::saveAndFlush)
				.map(ReaderEntity :: getOrders)
				.orElse(Collections.emptySet())
				.stream()
				.map(OrderMapper :: map)
				.collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly = true)
    public Page<BookUsing> findBookUsingsByReaderId(Long readerId, Pageable pageable) {
        return  readerRepository.findById(readerId)
        	   .map(entity -> bookUsingRepository.findAllByReader(entity, pageable))
               .orElse(Page.empty())
        	   .map(BookUsingMapper::map);
    }
    
	@Transactional
	public List<BookUsing> updateBookUsings(Long readerId, List<Long> bookUsingIds) {
		return readerRepository.findById(readerId)
				.map(readerEntity-> {
					readerEntity.getBookUsings().clear();
					readerEntity.getBookUsings().addAll(
							bookUsingIds.stream()
  							.map(bookUsingRepository::getOne)
  							.collect(Collectors.toList())
  							);
					return readerEntity;
				 })
				.map(readerRepository::saveAndFlush)
				.map(ReaderEntity :: getBookUsings)
				.orElse(Collections.emptySet())
				.stream()
				.map(BookUsingMapper :: map)
				.collect(Collectors.toList());
	}

}
