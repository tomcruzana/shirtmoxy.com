package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.OrderDto;
import com.shirtmoxy.app.entity.Order;

@Component
@Qualifier("OrderConverter")
public class OrderConverter implements ObjectConverter<OrderDto, Order> {

	@Override
	public OrderDto convertToDTO(Order entity) {
		OrderDto dto = new OrderDto();

		dto.setId(entity.getId());
		dto.setOrderTrackingNumber(entity.getOrderTrackingNumber());
//		dto.setInvoiceNumber(entity.getInvoiceNumber()); @TODO
		dto.setTotalQuantity(entity.getTotalQuantity());
		dto.setTotalPrice(entity.getTotalPrice());
		dto.setDateCreated(entity.getDateCreated());
		dto.setStatus(entity.getStatus());
		return dto;
	}

	@Override
	public Order convertToEntity(OrderDto dto) {
		return null;
	}

}
