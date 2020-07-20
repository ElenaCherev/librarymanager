package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.Basic
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Version
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "PUBLISHING_HOUSES")
data class PublishingHouseEntity(

    @Column(name = "PUBLISHING_HOUSE_ID")
    @GeneratedValue
    @Id
    @get:NotNull
    var publishingHouseId: UUID? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int = 0,

    @Column(name = "TITLE")
    @Size(min = 1, max = 255)
    var title: String,

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "LOGO")
    var logo: Byte? = 0,

    @OneToMany(mappedBy = "publishingHouse", cascade = [CascadeType.ALL], orphanRemoval = true)
    val editions: MutableSet<EditionEntity> = mutableSetOf()
)