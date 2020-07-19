package ru.elenacherev.librarymanager.domain.entity

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
@Table(name = "LANGS")
data class LangEntity(

    @Id
    @GeneratedValue
    @Column(name = "LANG_ID")
    @get:NotNull
    var langId: Long? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int = 0,

    @Column(name = "TITLE")
    @Size(min = 1, max = 255)
    var title: String,

    @OneToMany(mappedBy = "lang", cascade = [CascadeType.ALL], orphanRemoval = true)
    val editions: MutableSet<EditionEntity> = mutableSetOf(),

    @OneToMany(mappedBy = "publLang", cascade = [CascadeType.ALL], orphanRemoval = true)
    val publLangEditions: MutableSet<EditionEntity> = mutableSetOf<EditionEntity>()
)