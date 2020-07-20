package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.ColumnDefault
import ru.elenacherev.librarymanager.api.enum.AgeRating
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Version
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "EDITIONS")
data class EditionEntity(

    @Column(name = "EDITION_ID")
    @GeneratedValue
    @Id
    @get:NotNull
    var editionId: UUID? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int = 0,

    @Column(name = "TITLE")
    @Size(min = 1, max = 255)
    var title: String,

    @Column(name = "WORK_TITLE")
    @Size(min = 1, max = 255)
    var workTitle: String,

    @Column(name = "YEAR")
    var year: Int = 0,

    @ManyToOne
    @JoinColumn(name = "GENRE_ID")
    var genre: GenreEntity,

    @ManyToOne
    @JoinColumn(name = "LANG_ID")
    var lang: LangEntity,

    @ManyToOne
    @JoinColumn(name = "SECTION_ID")
    var section: SectionEntity,

    @Column(name = "PUBLISHING_YEAR")
    var publishingYear: Int = 0,

    @Column(name = "ISBN")
    @Size(min = 1, max = 255)
    var isbn: String,

    @Column(name = "IS_ILLUSTRATED")
    var isIllustrated: Boolean = false,

    @Column(name = "DOWNLOAD_LINK")
    @Size(min = 1, max = 255)
    var downloadLink: String? = null,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "AGE")
    var age: AgeRating,

    @Column(name = "INFO")
    @Size(min = 1, max = 255)
    var info: String? = null,

    @ManyToOne
    @JoinColumn(name = "PUBLISHING_HOUSE_ID")
    var publishingHouse: PublishingHouseEntity,

    @ManyToOne
    @JoinColumn(name = "PUBL_LANG_ID")
    var publLang: LangEntity,

    @ManyToMany(mappedBy = "editions", targetEntity = AuthorEntity::class) //  @JoinTable(name = "AUTHOR_EDITION", joinColumns = @JoinColumn(name = "EDITION_ID"), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    val authors: MutableSet<AuthorEntity> = mutableSetOf(),

    @OneToMany(mappedBy = "edition", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orders: MutableSet<OrderEntity> = mutableSetOf(),

    @OneToMany(mappedBy = "edition", cascade = [CascadeType.ALL], orphanRemoval = true)
    val books: MutableSet<BookEntity> = mutableSetOf()
)