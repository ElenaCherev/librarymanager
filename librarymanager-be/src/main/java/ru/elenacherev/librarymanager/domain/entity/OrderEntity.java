package ru.elenacherev.librarymanager.domain.entity;

import lombok.*;
import ru.elenacherev.librarymanager.util.OrderState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ORDERS")
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY) //уникальный ID, генерируется сам в БД
    @Column(name = "ORDER_ID")
    @NotNull
    @Setter(AccessLevel.NONE)
    private Long orderId;

    @Version
    @Column(name = "VERSION")
    private int version; //версия, инкрементится при редактировании записи

    @Temporal(TemporalType.DATE)
    @Column(name = "COMPLETE_DATE")
    private Date completeDate; //дата выполнения заказа (когда его выдали)

    @NotNull(message = "{validation.orderDate.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE")
    private Date orderDate; //дата создания заказа

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATE")
    private OrderState orderState; // состояние заказа

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private ReaderEntity reader; //Заказчик

    @ManyToOne
    @JoinColumn(name = "EDITION_ID")
    private EditionEntity edition; // Заказанная кннига
}
