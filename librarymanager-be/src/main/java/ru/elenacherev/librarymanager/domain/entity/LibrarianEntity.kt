package ru.elenacherev.librarymanager.domain.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "LIBRARIANS")
data class LibrarianEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "LIBRARIAN_ID")
    @get: NotNull
    var librarianId:  Long? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int  = 0
)