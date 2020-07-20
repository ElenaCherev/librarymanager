package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Version
import javax.validation.constraints.NotNull

@Entity
@Table(name = "LIBRARIANS")
data class LibrarianEntity(

    @Column(name = "LIBRARIAN_ID", nullable = false, updatable = false)
    @GeneratedValue
    @Id
    @get:NotNull
    var librarianId: UUID? = null,

    //версия, инкрементится при редактировании записи
    @Column(name = "VERSION", nullable = false)
    @Version
    var version: Int = 0
)