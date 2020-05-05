package ru.elenacherev.librarymanager.mapper;

import java.util.stream.Collectors;

import ru.elenacherev.librarymanager.api.dto.Author;
import ru.elenacherev.librarymanager.domain.entity.AuthorEntity;

public class AuthorMapper {
    public static Author map(AuthorEntity entity) {
    	return Author.builder()
                     .authorId(entity.getAuthorId())
                     .firstName(entity.getFirstName())
                     .surname(entity.getSurname())
                     .patronymic(entity.getPatronymic())
                     .birthdate(entity.getBirthdate())
                     .deathdate(entity.getDeathdate())
                     .info(entity.getInfo())
                     .fio(entity.getFio())
                     .edidionIds(entity.getEditions()
                    		 .stream()
                    		 .map(edition -> edition.getEditionId())
                    		 .collect(Collectors.toSet()))
                     .build();
    }

    public static AuthorEntity map(Author dto) {
        return map(dto, new AuthorEntity());
    }

    public static AuthorEntity map(
    		Author dto, 
    		AuthorEntity entity 
    		
    ) {
        entity.setFirstName(entity.getFirstName());
        entity.setSurname(entity.getSurname());
        entity.setPatronymic(entity.getPatronymic());
        entity.setBirthdate(entity.getBirthdate());
        entity.setDeathdate(entity.getDeathdate());
        entity.setInfo(entity.getInfo());
        return entity;
    }
}
