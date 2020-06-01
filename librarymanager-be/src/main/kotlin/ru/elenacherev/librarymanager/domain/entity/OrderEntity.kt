package ru.elenacherev.librarymanager.domain.entity

import ru.elenacherev.librarymanager.api.enum.OrderState
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import javax.persistence.Version
import javax.validation.constraints.NotNull

@Entity
@Table(name = "ORDERS")
class OrderEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "ORDER_ID")
    @get: NotNull
    var orderId: Long? = null,

    //версия, инкрементится при редактировании записи
    @Version
    @Column(name = "VERSION")
    var version: Int  = 0,

    //дата выполнения заказа (когда его выдали)
    @Temporal(TemporalType.DATE)
    @Column(name = "COMPLETE_DATE")
    var completeDate: Date? = null,

    //дата создания заказа
    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE")
    @NotNull(message = "{validation.orderDate.NotEmpty.message}")
    var orderDate: Date,

    // состояние заказа
    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATE")
    var orderState: OrderState,

    //Заказчик
    @ManyToOne
    @JoinColumn(name = "READER_ID")
    var reader: ReaderEntity,

    // Заказанная кннига
    @ManyToOne
    @JoinColumn(name = "EDITION_ID")
    var edition: EditionEntity
)