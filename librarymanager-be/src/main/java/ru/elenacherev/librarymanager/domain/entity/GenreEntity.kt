package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Version
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "GENRES")
data class GenreEntity(

    @Column(name = "GENRE_ID", nullable = false, updatable = false)
    @GeneratedValue
    @Id
    @get:NotNull
    var genreId: UUID? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION", nullable = false)
    var version: Int = 0,

    @Column(name = "TITLE")
    @Size(min = 1, max = 255)
    var title: String,

    @OneToMany(mappedBy = "genre", cascade = [CascadeType.ALL], orphanRemoval = true)
    val editions: MutableSet<EditionEntity> = mutableSetOf()
)