package ru.elenacherev.librarymanager.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.elenacherev.librarymanager.domain.entity.PublishingHouseEntity

@Repository
@Transactional(propagation = Propagation.MANDATORY)
interface PublishingHouseRepository : JpaRepository<PublishingHouseEntity, Long> 