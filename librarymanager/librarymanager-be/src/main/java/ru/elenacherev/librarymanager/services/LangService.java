package ru.elenacherev.librarymanager.services;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.elenacherev.librarymanager.api.dto.Edition;
import ru.elenacherev.librarymanager.api.dto.Lang;
import ru.elenacherev.librarymanager.domain.entity.LangEntity;
import ru.elenacherev.librarymanager.domain.repository.EditionRepository;
import ru.elenacherev.librarymanager.domain.repository.LangRepository;
import ru.elenacherev.librarymanager.mapper.EditionMapper;
import ru.elenacherev.librarymanager.mapper.LangMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class LangService {

	private final LangRepository langRepository;
	private final EditionRepository editionRepository;

	@Transactional
	public Lang save(Lang dto) {
		return langRepository.findById(dto.getLangId())
				.map(entity -> LangMapper.map(dto, entity))
				.map(langRepository::saveAndFlush)
				.map(LangMapper::map)
				.orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Lang> findAll() {
		return langRepository.findAll()
				.stream()
				.map(LangMapper::map)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Lang findLangById(Long langId) {
		return langRepository.findById(langId)
				.map(LangMapper::map)
				.orElse(null);
	}

	@Transactional(readOnly = true)
	public Page<Edition> findEditionsByLangId(Long langId, Pageable pageable) {
		return langRepository.findById(langId)
				.map(entity -> editionRepository.findAllByLang(entity, pageable))
				.orElse(Page.empty())
				.map(EditionMapper :: map);
	}
	
	@Transactional(readOnly = true)
	public Page<Edition> findEditionsByPublLangId(Long langId, Pageable pageable) {
		return langRepository.findById(langId)
				.map(entity -> editionRepository.findAllByPublLang(entity, pageable))
				.orElse(Page.empty())
				.map(EditionMapper :: map);
	}

	public List<Edition> updateEditionsByLangId(Long langId, List<Long> editionIds) {
		return langRepository
				.findById(langId)
				.map(langEntity-> {
					langEntity.getEditions().clear();
					langEntity.getEditions().addAll(
							editionIds.stream()
  							.map(editionRepository::getOne)
  							.collect(Collectors.toList())
  							);
					return langEntity;
				 })
				.map(langRepository::saveAndFlush)
				.map(LangEntity :: getEditions)
				.orElse(Collections.emptySet())
				.stream()
				.map(EditionMapper :: map)
				.collect(Collectors.toList());
	}

	public List<Edition> updateEditionsByPubllangId(Long publlangId, List<Long> editionIds) {
		return langRepository
				.findById(publlangId)
				.map(langEntity-> {
					langEntity.getPublLangEditions().clear();
					langEntity.getPublLangEditions().addAll(
							editionIds.stream()
  							.map(editionRepository::getOne)
  							.collect(Collectors.toList())
  							);
					return langEntity;
				 })
				.map(langRepository::saveAndFlush)
				.map(LangEntity :: getPublLangEditions)
				.orElse(Collections.emptySet())
				.stream()
				.map(EditionMapper :: map)
				.collect(Collectors.toList());
	}
}
