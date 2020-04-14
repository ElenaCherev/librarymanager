package ru.spring5.elena.librarymanager.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.spring5.elena.librarymanager.domain.entity.AuthorEntity;
import ru.spring5.elena.librarymanager.domain.entity.EditionEntity;
import ru.spring5.elena.librarymanager.domain.entity.GenreEntity;
import ru.spring5.elena.librarymanager.domain.entity.LangEntity;
import ru.spring5.elena.librarymanager.domain.entity.PublishingHouseEntity;
import ru.spring5.elena.librarymanager.domain.entity.SectionEntity;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface EditionRepository extends JpaRepository<EditionEntity, Long> {

    Page<EditionEntity> findAllBySection(SectionEntity section, Pageable pageable);

    //Переписать!!! Работает неправильно!!! Переделать все запросы Много-Много
    Page<EditionEntity> findAllByAuthor(AuthorEntity author, Pageable pageable);
    
    Page<EditionEntity> findAllByGenre(GenreEntity genre, Pageable pageable);
    
    Page<EditionEntity> findAllByLang(LangEntity lang, Pageable pageable);
    
    Page<EditionEntity> findAllByPublLang(LangEntity publLang, Pageable pageable);
    
    Page<EditionEntity> findAllByPublishingHouse(PublishingHouseEntity publishingHouse, Pageable pageable);
}
