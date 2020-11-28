package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Formula
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.persistence.Transient
import javax.persistence.Version
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/*
 * Класс для  хранения инфы о читателях библиотеки
 */
@Entity
@Table(name = "READERS")
data class ReaderEntity(

    @Column(name = "READER_ID", nullable = false, updatable = false)
    @GeneratedValue
    @Id
    @get:NotNull
    var readerId: UUID? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION", nullable = false)
    var version: Int = 0,

    //имя
    @Column(name = "FIRST_NAME")
    @NotEmpty
    @Size(min = 3, max = 60)
    var firstName: String,

    //фамилия
    @Column(name = "SURNAME")
    @NotEmpty
    @Size(min = 1, max = 60)
    var surname: String? = null,

    //отчество
    @Column(name = "PATRONYMIC")
    @Size(min = 0, max = 60)
    var patronymic: String? = null,

    //дата рождения, чтобы камасутру мелким не выдать
    @Column(name = "BIRTHDATE")
    @NotNull
    @Temporal(TemporalType.DATE)
    var birthdate: Date,

    @Column(name = "REG_DATE")
    @Temporal(TemporalType.DATE)
    var regDate: Date,

    //телефон, чтобы присылать смс о готовых заказах и задолжностях
    @Column(name = "TELEPHONE")
    @Size(min = 0, max = 12)
    var telephone: String? = null,

    //электронная почта. Уникальное поле, используется для авторизации
    @Column(name = "EMAIL")
    @NotEmpty
    @Size(min = 4, max = 200)
    var email: String,

    @OneToMany(mappedBy = "reader", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orders: MutableSet<OrderEntity> = mutableSetOf(),

    @OneToMany(mappedBy = "reader", cascade = [CascadeType.ALL], orphanRemoval = true)
    val bookUsings: MutableSet<BookUsingEntity> = mutableSetOf()
) {
    @Formula("(SURNAME || ' ' || FIRST_NAME || ' ' || PATRONYMIC)")
    @Transient
    lateinit var fio: String
}
