package ru.spring5.elena.librarymanager.services;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spring5.elena.librarymanager.api.dto.Section;
import ru.spring5.elena.librarymanager.api.dto.Edition;
import ru.spring5.elena.librarymanager.domain.entity.SectionEntity;
import ru.spring5.elena.librarymanager.domain.repository.EditionRepository;
import ru.spring5.elena.librarymanager.domain.repository.SectionRepository;
import ru.spring5.elena.librarymanager.mapper.EditionMapper;
import ru.spring5.elena.librarymanager.mapper.SectionMapper;


@RequiredArgsConstructor
@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final EditionRepository editionRepository;

    @Transactional
    public Section create(Section dto) 
    {
    	SectionEntity parentSection = sectionRepository.findById(dto.getParentSectionId()).get();
    	
        		return Optional.of(dto)
        		.map(section-> SectionMapper.map(section,parentSection))
        		.map(sectionRepository::saveAndFlush)
                .map(SectionMapper::map)
                .orElse(null);
    }
    
    @Transactional(readOnly = true)
    public Section findSectionById(Long id) 
    {
        return sectionRepository.findById(id)
                .map(SectionMapper::map)
                .orElse(null);
    }
    
    @Transactional(readOnly = true)
    public Page<Section> findAllByParentSectionId(
    		Long parentSectionId,
    		Pageable pageable
    ) {
    	return sectionRepository
    			.findAllByParentSection(    					
    						sectionRepository.findById(parentSectionId).get(),
    						pageable)
                .map(SectionMapper::map);
    }
    
    @Transactional(readOnly = true)
    public Page<Section> findAllRootSections(Pageable pageable) 
    {
    	return findAllByParentSectionId((long) 0,pageable);
    }

    @Transactional(readOnly = true)
    public Page<Edition> findeAllEditionsBySectionId(
    		Long sectionId, 
    		Pageable pageable
    ) {
        return editionRepository.findAllBySection(
                sectionRepository.findById(sectionId).orElse(null), pageable
                )
                .map(EditionMapper::map);

    }

    @Transactional
	public List<Edition> updateEditionsByAuthorId(Long sectionId, List<Long> editionIds) {
    	return sectionRepository
				.findById(sectionId)
				.map(sectionEntity-> {
					sectionEntity.getEditions().clear();
					sectionEntity.getEditions().addAll(
							editionIds.stream()
  							.map(editionRepository::getOne)
  							.collect(Collectors.toList())
  							);
					return sectionEntity;
				 })
				.map(sectionRepository::saveAndFlush)
				.map(SectionEntity :: getEditions)
				.orElse(Collections.emptySet())
				.stream()
				.map(EditionMapper :: map)
				.collect(Collectors.toList());
	}    
}
