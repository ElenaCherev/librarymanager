package ru.spring5.elena.librarymanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring5.elena.librarymanager.api.dto.Order;
import ru.spring5.elena.librarymanager.domain.entity.EditionEntity;
import ru.spring5.elena.librarymanager.domain.entity.ReaderEntity;
import ru.spring5.elena.librarymanager.domain.repository.EditionRepository;
import ru.spring5.elena.librarymanager.domain.repository.OrderRepository;
import ru.spring5.elena.librarymanager.domain.repository.ReaderRepository;
import ru.spring5.elena.librarymanager.mapper.OrderMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class OrderService {
    OrderRepository orderRepository;
    ReaderRepository readerRepository;
    EditionRepository editionRepository;

    @Transactional(readOnly = true)
    public Page<Order> findAllByPage(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(OrderMapper::map);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(OrderMapper::map)
                .orElse(null);
    }

    @Transactional
    public Order save(Long orderId,  Order dto) {
        
    	ReaderEntity readerEntity = readerRepository.findById(dto.getReaderId()).get();
        EditionEntity editionEntity = editionRepository.findById(dto.getEditionId()).get();
        
    	return orderRepository.findById(orderId)
                .map(entity -> OrderMapper.map(dto, entity, readerEntity, editionEntity))
                .map(orderRepository::saveAndFlush)
                .map(OrderMapper::map)
                .orElse(null);

    }

    @Transactional
    public Order create(Order dto) {
    	
    	ReaderEntity readerEntity = readerRepository.findById(dto.getReaderId()).get();
        EditionEntity editionEntity = editionRepository.findById(dto.getEditionId()).get();
    	
        return   Optional.of(dto)
                .map(order -> OrderMapper.map(order, readerEntity, editionEntity))
                .map(orderRepository::saveAndFlush)
                .map(OrderMapper::map)
                .orElse(null);

    }

}
