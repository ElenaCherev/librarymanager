package ru.elenacherev.librarymanager.domain.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "LANGS")
data class LangEntity(
    @Id
    @Column(name = "LANG_ID")
    @get: NotNull
    var langId: Long? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int = 0,

    @Column(name = "TITLE")
    var title: String,

    @OneToMany(mappedBy = "lang", cascade = [CascadeType.ALL], orphanRemoval = true)
    val editions: MutableSet<EditionEntity> = mutableSetOf(),

    @OneToMany(mappedBy = "publLang", cascade = [CascadeType.ALL], orphanRemoval = true)
    val publLangEditions: MutableSet<EditionEntity> = mutableSetOf<EditionEntity>()
)