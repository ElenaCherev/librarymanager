package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Formula
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.persistence.Transient
import javax.persistence.Version
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "AUTHORS")
data class AuthorEntity(

    @Column(name = "AUTHOR_ID")
    @GeneratedValue
    @Id
    @get:NotNull
    var authorId: UUID? = null,

    //версия, инкрементится при редактировании записи
    @Column(name = "VERSION", nullable = false)
    @Version
    var version: Int = 0,

    //имя
    @Column(name = "FIRST_NAME", nullable = false)
    @NotEmpty
    @Size(min = 3, max = 60)
    var firstName: String,

    //фамилия
    @Column(name = "SURNAME", nullable = false)
    @NotEmpty
    @Size(min = 1, max = 60)
    var surname: String,

    //отчество
    @Column(name = "PATRONYMIC")
    @Size(min = 0, max = 60)
    var patronymic: String? = null,

    @Column(name = "BIRTHDATE")
    @NotNull
    var birthdate: Instant? = null,

    @Column(name = "DEATHDATE")
    var deathdate: Instant? = null,

    //доп. информация
    @Column(name = "INFO")
    @Size(min = 0, max = 1000)
    var info: String? = null,

    //Все работы данного автора
    @ManyToMany(targetEntity = EditionEntity::class)
    @JoinTable(
        name = "AUTHORS_EDITIONS",
        joinColumns = [JoinColumn(name = "AUTHOR_ID")],
        inverseJoinColumns = [JoinColumn(name = "EDITION_ID")]
    )
    val editions: MutableSet<EditionEntity> = mutableSetOf()
) {
    @Formula("(SURNAME || ' ' || FIRST_NAME || ' ' || PATRONYMIC)")
    @Transient
    lateinit var fio: String
}