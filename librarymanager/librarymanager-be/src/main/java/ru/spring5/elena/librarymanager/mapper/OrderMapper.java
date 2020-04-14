package ru.spring5.elena.librarymanager.mapper;

import ru.spring5.elena.librarymanager.api.dto.Order;
import ru.spring5.elena.librarymanager.domain.entity.EditionEntity;
import ru.spring5.elena.librarymanager.domain.entity.OrderEntity;
import ru.spring5.elena.librarymanager.domain.entity.ReaderEntity;

public class OrderMapper {
    public static Order map(OrderEntity entity) {
        return Order.builder()
                .orderId(entity.getOrderId())
                .completeDate(entity.getCompleteDate())
                .orderDate(entity.getCompleteDate())
                .orderState(entity.getOrderState())
                .readerFio(entity.getReader()!=null
                		?entity.getReader().getFio()
                		:""
                )
                .editionTitle(entity.getEdition()!=null
                		?entity.getEdition().getTitle()
                		:""
                )
                .readerId(entity.getReader()!=null
                		?entity.getReader().getReaderId()
                		:0
                )
                .editionId(entity.getEdition()!=null
                		?entity.getEdition().getEditionId()
                		:0
                )
                .build();
    }

    public static OrderEntity map(
    		Order dto, 
    		ReaderEntity readerEntity,
    		EditionEntity editionEntity
    ) {
        return map(dto, new OrderEntity(), readerEntity, editionEntity);
    }

    public static OrderEntity map(
    		Order dto, 
    		OrderEntity entity,
    		ReaderEntity readerEntity,
    		EditionEntity editionEntity
    ) {
        entity.setCompleteDate(dto.getCompleteDate());
        entity.setOrderDate(dto.getOrderDate());
        entity.setOrderState(dto.getOrderState());
        entity.setReader(readerEntity);
        entity.setEdition(editionEntity);
        return entity;
    }
}
