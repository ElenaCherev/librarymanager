package ru.elenacherev.librarymanager.domain.entity

import org.hibernate.annotations.Formula
import java.util.Date
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
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
class ReaderEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "READER_ID")
    @get: NotNull
    var readerId: Long? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int = 0,

    //имя
    @Column(name = "FIRST_NAME")
    @NotEmpty(message = "{validation.firstname.notempty.message}")
    @Size(min = 3, max = 60, message = "{validation.firstname.size.message}")
    var firstName:  String,

    //фамилия
    @Column(name = "SURNAME")
    @NotEmpty(message = "{validation.surname.notempty.message}")
    @Size(min = 1, max = 60, message = "{validation.surname.size.message}")
    var surname:  String? = null,

    //отчество
    @Column(name = "PATRONYMIC")
    @Size(min = 0, max = 60, message = "{validation.patronymic.Size.message}")
    var patronymic: String? = null,

    //дата рождения, чтобы камасутру мелким не выдать
    @Column(name = "BIRTHDATE")
    @NotNull(message = "{validation.birthday.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    var birthdate: Date,

    @Column(name = "REG_DATE")
    @Temporal(TemporalType.DATE)
    var regDate: Date,

    //телефон, чтобы присылать смс о готовых заказах и задолжностях
    @Column(name = "TELEPHONE")
    @Size(min = 0, max = 12, message = "{validation.telephone.Size.message}")
    var telephone:  String? = null,

    //электронная почта. Уникальное поле, используется для авторизации
    @Column(name = "EMAIL")
    @NotEmpty(message = "{validation.email.NotEmpty.message}")
    @Size(min = 4, max = 200, message = "{validation.email.Size.message}")
    var email: String,

    @OneToMany(mappedBy = "reader", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orders: MutableSet<OrderEntity> = mutableSetOf(),

    @OneToMany(mappedBy = "reader", cascade = [CascadeType.ALL], orphanRemoval = true)
    val bookUsings: MutableSet<BookUsingEntity> = mutableSetOf()
)
{
        @Formula("(SURNAME || ' ' || FIRST_NAME || ' ' || PATRONYMIC)")
        @Transient
        lateinit var fio: String
}
