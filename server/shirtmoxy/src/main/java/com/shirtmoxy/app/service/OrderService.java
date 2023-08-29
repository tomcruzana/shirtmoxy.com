package com.shirtmoxy.app.service;

import org.springframework.data.domain.Page;

import com.shirtmoxy.app.dto.OrderDto;
import com.shirtmoxy.app.exception.OrderException;

public interface OrderService {
	public Page<OrderDto> readAllOrdersByCustomerEmail(String email, int pageNum, int pageSize) throws OrderException;
}
