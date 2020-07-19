package ru.elenacherev.librarymanager.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Version
import javax.validation.constraints.NotNull

@Entity
@Table(name = "LIBRARIANS")
data class LibrarianEntity(

    @Id
    @GeneratedValue
    @Column(name = "LIBRARIAN_ID")
    @get:NotNull
    var librarianId: Long? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int = 0
)