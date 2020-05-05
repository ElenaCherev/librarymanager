package ru.elenacherev.librarymanager.mapper;

import ru.elenacherev.librarymanager.api.dto.Edition;
import ru.elenacherev.librarymanager.domain.entity.AuthorEntity;
import ru.elenacherev.librarymanager.domain.entity.EditionEntity;
import ru.elenacherev.librarymanager.domain.entity.GenreEntity;
import ru.elenacherev.librarymanager.domain.entity.LangEntity;
import ru.elenacherev.librarymanager.domain.entity.PublishingHouseEntity;
import ru.elenacherev.librarymanager.domain.entity.SectionEntity;

import java.util.stream.Collectors;

public class EditionMapper {
    public static Edition map(EditionEntity entity) {
        return Edition.builder()
                .editionId(entity.getEditionId())
                
                .title(entity.getTitle())
                .workTitle(entity.getWorkTitle())
                .year(entity.getYear())
                .publishingYear(entity.getPublishingYear())
                .isbn(entity.getIsbn())
                .isIllustrated(entity.isIllustrated())
                .downloadLink(entity.getDownloadLink())
                .age(entity.getAge())
                .info(entity.getInfo())
                
                .langString(entity.getLang()!=null
                				?entity.getLang().getTitle()
                				:""
                 )
                .langId(entity.getLang()!=null
                		?entity.getLang().getLangId()
                		:0		
                 )
                .publLangString(entity.getPublLang()!=null
                		?entity.getPublLang().getTitle()
                		:""
                 )
                .publLangId(entity.getPublLang()!=null
                		?entity.getPublLang().getLangId()
                		:0
                 )
                .publishingHouseId(entity.getPublishingHouse()!=null
                		?entity.getPublishingHouse().getPublishingHouseId()
                		:0
                 )
                .publishingHouseString(entity.getPublishingHouse()!=null
                		?entity.getPublishingHouse().getTitle()
                		:""
                 )
                .genreId(entity.getGenre()!=null
                		?entity.getGenre().getGenreId()
                		:0)
                .genreString(entity.getGenre()!=null
                		?entity.getGenre().getTitle()
                		:""
                )
                .sectionId(entity.getSection()!=null
                		?entity.getSection().getSectionId()
                		:0
                )
                .sectionTitle(entity.getSection()!=null
                		?entity.getSection().getTitle()
                		:""
                )
                .authorFIOs(entity.getAuthors().stream()
                        .map(AuthorEntity::getFio)
                        .collect(Collectors.toList()))
                .authorIds(entity.getAuthors().stream()
                        .map(AuthorEntity::getAuthorId)
                        .collect(Collectors.toList()))
                .build();
    }

    public static EditionEntity map(
    		Edition dto,
    		LangEntity langEntity,
    		LangEntity publLangEntity,
    		PublishingHouseEntity publishingHouseEntity,
    		GenreEntity genreEntity,
    		SectionEntity sectionEntity
    ) {
        return map(
        		dto, 
        		new EditionEntity(), 
        		langEntity, 
        		publLangEntity, 
        		publishingHouseEntity, 
        		genreEntity,
        		sectionEntity
        		);
    }

    public static EditionEntity map(
    		Edition dto, 
    		EditionEntity entity,
    		LangEntity langEntity,
    		LangEntity publLangEntity,
    		PublishingHouseEntity publishingHouseEntity,
    		GenreEntity genreEntity,
    		SectionEntity sectionEntity
    		
    ) {
        entity.setTitle(dto.getTitle());
        entity.setWorkTitle(dto.getWorkTitle());
        entity.setYear(dto.getYear());
        entity.setPublishingYear(dto.getPublishingYear());
        entity.setIsbn(dto.getIsbn());
        entity.setIllustrated(dto.isIllustrated());
        entity.setDownloadLink(dto.getDownloadLink());
        entity.setAge(dto.getAge());
        entity.setInfo(dto.getInfo());
        
        entity.setLang(langEntity);
        entity.setPublLang(publLangEntity);
        entity.setPublishingHouse(publishingHouseEntity);
        entity.setGenre(genreEntity);
        entity.setSection(sectionEntity);
        return entity;
    }
}
