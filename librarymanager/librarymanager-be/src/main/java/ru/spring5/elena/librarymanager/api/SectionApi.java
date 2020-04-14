package ru.spring5.elena.librarymanager.api;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.spring5.elena.librarymanager.api.dto.Edition;
import ru.spring5.elena.librarymanager.api.dto.Section;

public interface SectionApi {

	Section getSection(
			@NotNull Long sectionId
	);

	Page<Section> searchRootSections(
			Pageable pageable
	);

	Page<Section> searchSectionsByParentSectionId(
			@NotNull Long sectionId, 
			Pageable pageable
	);

	Page<Edition> searchEditionsBySectionId(
			@NotNull Long sectionId, 
			Pageable pageable
	);

	List<Edition> updateEditionsBySectionId(
			@NotNull Long sectionId, 
			@NotNull List<Long> editionIds
	);

}