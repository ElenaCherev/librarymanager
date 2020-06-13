package ru.elenacherev.librarymanager.domain.entity

import javax.persistence.Basic
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Version
import javax.validation.constraints.NotNull

@Entity
@Table(name = "PUBLISHING_HOUSES")
class PublishingHouseEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "PUBLISHING_HOUSE_ID")
    @get: NotNull
    var publishingHouseId: Long? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int  = 0,

    @Column(name = "TITLE")
    var title: String,

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "LOGO")
    var logo: Byte? = 0,

    @OneToMany(mappedBy = "publishingHouse", cascade = [CascadeType.ALL], orphanRemoval = true)
    val editions: MutableSet<EditionEntity> = mutableSetOf()
)