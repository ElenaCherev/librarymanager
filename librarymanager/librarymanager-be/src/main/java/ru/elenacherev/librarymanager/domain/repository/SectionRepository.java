package ru.elenacherev.librarymanager.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.elenacherev.librarymanager.domain.entity.SectionEntity;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface SectionRepository extends JpaRepository<SectionEntity, Long> {
    Page<SectionEntity> findAllByParentSection(SectionEntity parentSection, Pageable pageble);
}
