package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.ColumnDefault
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.persistence.Version
import javax.validation.constraints.NotNull

@Entity
@Table(name = "BOOK_USINGS")
data class BookUsingEntity(

    @Column(name = "BOOK_USING_ID")
    @GeneratedValue
    @Id
    @get:NotNull
    var bookUsingId: UUID? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int = 0,

    //Книгу взяли
    @Column(name = "START_DATE")
    @NotNull
    var startDate: Instant,

    //Книгу вернули
    @Column(name = "END_DATE")
    var endDate: Instant? = null,

    //кто взял книгу
    @ManyToOne
    @JoinColumn(name = "READER_ID")
    var reader: ReaderEntity,

    //какую книгу взяли
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    var book: BookEntity
)