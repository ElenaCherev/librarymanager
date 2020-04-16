package ru.spring5.elena.librarymanager.services;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.spring5.elena.librarymanager.api.dto.Edition;
import ru.spring5.elena.librarymanager.api.dto.Genre;
import ru.spring5.elena.librarymanager.domain.entity.AuthorEntity;
import ru.spring5.elena.librarymanager.domain.entity.GenreEntity;
import ru.spring5.elena.librarymanager.domain.repository.EditionRepository;
import ru.spring5.elena.librarymanager.domain.repository.GenreRepository;
import ru.spring5.elena.librarymanager.mapper.EditionMapper;
import ru.spring5.elena.librarymanager.mapper.GenreMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final EditionRepository editionRepository;

    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.findAll()
        		.stream()
        		.map(GenreMapper::map)
        		.collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .map(GenreMapper::map)
                .orElse(null);
    }

    @Transactional
    public Genre save(Genre dto) {
        return genreRepository.findById(dto.getGenreId())
                         .map(entity -> GenreMapper.map(dto,entity))
                         .map(genreRepository::saveAndFlush)
                         .map(GenreMapper::map)
                .orElse(null);
    }

    @Transactional(readOnly = true)
	public Page<Edition> findEditionsByGenreId(Long genreId, Pageable pageable) {
		return genreRepository.findById(genreId)
				.map(entity -> editionRepository.findAllByGenre(entity, pageable))
				.orElse(Page.empty())
				.map(EditionMapper::map);
	}

    @Transactional
	public List<Edition> updateEditionsByGenreId(Long genreId, List<Long> editionIds) {
    	return genreRepository
				.findById(genreId)
				.map(genreEntity-> {
					genreEntity.getEditions().clear();
					genreEntity.getEditions().addAll(
							editionIds.stream()
  							.map(editionRepository::getOne)
  							.collect(Collectors.toList())
  							);
					return genreEntity;
				 })
				.map(genreRepository::saveAndFlush)
				.map(GenreEntity :: getEditions)
				.orElse(Collections.emptySet())
				.stream()
				.map(EditionMapper :: map)
				.collect(Collectors.toList());
	}
}
