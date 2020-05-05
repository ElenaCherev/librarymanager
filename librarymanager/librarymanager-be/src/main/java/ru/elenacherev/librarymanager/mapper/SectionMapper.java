package ru.elenacherev.librarymanager.mapper;

import ru.elenacherev.librarymanager.api.dto.Section;
import ru.elenacherev.librarymanager.domain.entity.SectionEntity;

public class SectionMapper {
    public static Section map(SectionEntity entity) {
        return Section.builder()
                .sectionId(entity.getSectionId())
                .title(entity.getTitle())
                .parentSectionId(entity.getParentSection()!=null
                		?entity.getParentSection().getSectionId()
                		:0
                )
                .build();
    }

    public static SectionEntity map(
    		Section dto, 
    		SectionEntity parentSection
    ) {
        return map(dto, new SectionEntity(), parentSection);
    }

    public static SectionEntity map(
    		Section dto, 
    		SectionEntity entity,
    		SectionEntity parentSection
    ) {
        entity.setTitle(dto.getTitle());
        entity.setParentSection(parentSection);
        return entity;
    }
}
