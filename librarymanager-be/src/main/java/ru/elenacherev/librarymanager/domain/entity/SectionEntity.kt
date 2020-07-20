package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Version
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "SECTIONS")
data class SectionEntity(

    @Column(name = "SECTION_ID")
    @GeneratedValue
    @Id
    @get:NotNull
    var sectionId: UUID? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int = 0,

    @Column(name = "TITLE")
    @NotEmpty
    @Size(min = 3, max = 60)
    var title: String,

    @ManyToOne
    @JoinColumn(name = "PARENT_SECTION_ID")
    var parentSection: SectionEntity? = null,

    @OneToMany(mappedBy = "parentSection", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sections: MutableSet<SectionEntity> = mutableSetOf(),

    @OneToMany(mappedBy = "section", cascade = [CascadeType.ALL], orphanRemoval = true)
    val editions: MutableSet<EditionEntity> = mutableSetOf()
)