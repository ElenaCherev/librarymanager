package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.Formula
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
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
class AuthorEntity (
    //уникальный ID, генерируется сам в БД
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHOR_ID")
    @get:NotNull
    var authorId:  Long? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION", nullable = false)
    var version: Int = 0,

    //имя
    @Column(name = "FIRST_NAME", nullable = false)
    @get:NotEmpty(message = "{validation.firstname.notempty.message}")
    @get:Size(min = 3, max = 60, message = "{validation.firstname.size.message}")
    var firstName:  String,

    //фамилия
    @Column(name = "SURNAME", nullable = false)
    @get:NotEmpty(message = "{validation.surname.notempty.message}")
    @get:Size(min = 1, max = 60, message = "{validation.surname.size.message}")
    var surname :   String,

    //отчество
    @Column(name = "PATRONYMIC")
    @get:Size(min = 0, max = 60, message = "{validation.patronymic.Size.message}")
    var patronymic :  String? = null,

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDATE")
    @get:NotNull(message = "{validation.birthday.NotEmpty.message}")
    var birthdate : Date? = null,

    @Temporal(TemporalType.DATE)
    @Column(name = "DEATHDATE")
    var deathdate  : Date? = null,

    //доп. информация
    @Column(name = "INFO")
    @get:Size(min = 0, max = 1000, message = "{validation.info.Size.message}")
    var info :  String? = null,

    //Все работы данного автора
    @ManyToMany(targetEntity = EditionEntity::class)
    @JoinTable(
        name = "AUTHORS_EDITIONS",
        joinColumns = [JoinColumn(name = "AUTHOR_ID")],
        inverseJoinColumns = [JoinColumn(name = "EDITION_ID")]
    )
    val editions: MutableSet<EditionEntity> = mutableSetOf()
)
{
    @Formula("(SURNAME || ' ' || FIRST_NAME || ' ' || PATRONYMIC)")
    @Transient
    lateinit var fio: String
}