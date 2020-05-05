package ru.elenacherev.librarymanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.elenacherev.librarymanager.api.dto.Book;
import ru.elenacherev.librarymanager.api.dto.Edition;
import ru.elenacherev.librarymanager.api.dto.Order;
import ru.elenacherev.librarymanager.domain.entity.EditionEntity;
import ru.elenacherev.librarymanager.domain.entity.GenreEntity;
import ru.elenacherev.librarymanager.domain.entity.LangEntity;
import ru.elenacherev.librarymanager.domain.entity.PublishingHouseEntity;
import ru.elenacherev.librarymanager.domain.entity.SectionEntity;
import ru.elenacherev.librarymanager.domain.repository.BookRepository;
import ru.elenacherev.librarymanager.domain.repository.EditionRepository;
import ru.elenacherev.librarymanager.domain.repository.GenreRepository;
import ru.elenacherev.librarymanager.domain.repository.LangRepository;
import ru.elenacherev.librarymanager.domain.repository.OrderRepository;
import ru.elenacherev.librarymanager.domain.repository.PublishingHouseRepository;
import ru.elenacherev.librarymanager.domain.repository.SectionRepository;
import ru.elenacherev.librarymanager.mapper.BookMapper;
import ru.elenacherev.librarymanager.mapper.EditionMapper;
import ru.elenacherev.librarymanager.mapper.OrderMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditionService {
    private final EditionRepository editionRepository;
    
    private final GenreRepository genreRepository;
    private final LangRepository langRepository;
    private final SectionRepository sectionRepository;
    private final PublishingHouseRepository publishingHouseRepository;
    
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Edition> findAll() {
        return editionRepository.findAll()
                .stream()
                .map(EditionMapper::map)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Page<Edition> findAllByPage(Pageable pageable) {
        return editionRepository.findAll(pageable)
                .map(EditionMapper::map);
    }

    @Transactional(readOnly = true)
    public Edition findById(Long id) {
        return editionRepository.findById(id)
                .map(EditionMapper::map)
                .orElse(null);
    }

    @Transactional
    public Edition save(Long editionId, Edition dto) {
    	
    	GenreEntity genreEntity =  genreRepository.findById(dto.getGenreId()).get();
    	LangEntity langEntity = langRepository.findById(dto.getLangId()).get();
    	LangEntity publLangEntity = langRepository.findById(dto.getPublLangId()).get();
    	SectionEntity sectionEntity = sectionRepository.findById(dto.getSectionId()).get();
    	PublishingHouseEntity publHouseEntity =  publishingHouseRepository
    			.findById(dto.getPublishingHouseId())
    			.orElse(null);
    	
    	return editionRepository.findById(editionId)
                .map(entity -> EditionMapper
                	.map(
                		dto, 
                		entity,
                		langEntity,
                		publLangEntity,
                		publHouseEntity,
                		genreEntity,
                		sectionEntity
                     )
                 )
                .map(editionRepository::saveAndFlush)
                .map(EditionMapper::map)
                .get();
    }

    @Transactional
    public Edition create(Edition dto) {
    	
    	GenreEntity genreEntity =  genreRepository.findById(dto.getGenreId()).get();
    	LangEntity langEntity = langRepository.findById(dto.getLangId()).get();
    	LangEntity publLangEntity = langRepository.findById(dto.getPublLangId()).get();
    	SectionEntity sectionEntity = sectionRepository.findById(dto.getSectionId()).get();
    	PublishingHouseEntity publHouseEntity =  publishingHouseRepository
    			.findById(dto.getPublishingHouseId())
    			.orElse(null); 
    	
    	return Optional.of(dto)
                .map(edition-> EditionMapper
                		.map(edition,
                			 langEntity,
                			 publLangEntity,
                			 publHouseEntity,
                			 genreEntity,
                			 sectionEntity
                			 )
                 )
                .map(editionRepository::saveAndFlush)
                .map(EditionMapper::map)
                .get();
    }
    
    @Transactional(readOnly = true)
    public Page<Book> findAllBooksByEditionId(Long editionId, Pageable pageable){
        return  editionRepository.findById(editionId)
        		.map(entity -> bookRepository.findAllByEdition(entity, pageable))
        		.orElse(Page.empty())
                .map(BookMapper::map);
    }

    @Transactional(readOnly = true)
	public Page<Order> findAllOrdersByEditionId(Long editionId, Pageable pageable) {
		return editionRepository.findById(editionId)
		.map(entity -> orderRepository.findAllByEdition(entity, pageable))
		.orElse(Page.empty())
        .map(OrderMapper::map);
	}

    @Transactional
	public List<Book> updateBooksByEditionId(Long editionId, List<Long> bookIds) {
    	return editionRepository
				.findById(editionId)
				.map(editionEntity-> {
					editionEntity.getBooks().clear();
					editionEntity.getBooks().addAll(
							bookIds.stream()
  							.map(bookRepository::getOne)
  							.collect(Collectors.toList())
  							);
					return editionEntity;
				 })
				.map(editionRepository::saveAndFlush)
				.map(EditionEntity :: getBooks)
				.orElse(Collections.emptySet())
				.stream()
				.map(BookMapper :: map)
				.collect(Collectors.toList());
	}

    @Transactional
	public List<Order> updateOrdersByEditionId(Long editionId, List<Long> orderIds) {
    	return editionRepository
				.findById(editionId)
				.map(editionEntity-> {
					editionEntity.getOrders().clear();
					editionEntity.getOrders().addAll(
							orderIds.stream()
  							.map(orderRepository::getOne)
  							.collect(Collectors.toList())
  							);
					return editionEntity;
				 })
				.map(editionRepository::saveAndFlush)
				.map(EditionEntity :: getOrders)
				.orElse(Collections.emptySet())
				.stream()
				.map(OrderMapper :: map)
				.collect(Collectors.toList());
	}
}
