package ru.elenacherev.librarymanager.domain.entity

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "BOOK_USINGS")
data class BookUsingEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "BOOK_USING_ID")
    @get: NotNull
    var bookUsingId:  Long? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int  = 0,

    //Книгу взяли
    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    @NotNull(message = "{validation.startDate.NotEmpty.message}")
    var startDate:  Date,

    //Книгу вернули
    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    var endDate: Date? = null,

    //кто взял книгу
    @ManyToOne
    @JoinColumn(name = "READER_ID")
    var reader: ReaderEntity? = null,

    //какую книгу взяли
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    var book: BookEntity
)