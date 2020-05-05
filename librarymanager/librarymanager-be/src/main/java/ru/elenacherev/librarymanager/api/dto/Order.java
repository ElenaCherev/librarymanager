package ru.elenacherev.librarymanager.api.dto;

import lombok.Builder;
import lombok.Getter;
import ru.elenacherev.librarymanager.util.OrderState;

import java.util.Date;

@Builder
@Getter
public class Order {
    private Long orderId;
    private Date completeDate; //дата выполнения заказа (когда его выдали)
    private Date orderDate; //дата создания заказа
    private OrderState orderState; // состояние заказа
    private String readerFio;
    private String editionTitle;
    private Long readerId; //кто книгу заказал
    private Long editionId; //какую книгу заказал
}
