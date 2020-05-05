package ru.elenacherev.librarymanager.api;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.elenacherev.librarymanager.api.dto.Order;

public interface OrderApi {

	Order getOrder(
			@NotNull Long orderId
	);

	Page<Order> searchOrders(
			Pageable pageable
	);

	Order createOrder(
			@Valid Order order
	);

	Order saveOrder(
			@NotNull Long orderId, 
			@Valid Order order
	);

}