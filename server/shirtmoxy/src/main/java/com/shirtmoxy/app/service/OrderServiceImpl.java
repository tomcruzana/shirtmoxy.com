package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.OrderDto;
import com.shirtmoxy.app.entity.Order;
import com.shirtmoxy.app.exception.OrderException;
import com.shirtmoxy.app.repository.OrderRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	@Qualifier("OrderConverter")
	private ObjectConverter<OrderDto, Order> orderConverter;

	@Override
	public Page<OrderDto> readAllOrdersByCustomerEmail(String email, int pageNum, int pageSize) throws OrderException {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("dateCreated").descending());

		Page<Order> ordersPage = orderRepo.findByCustomerEmail(email, pageable);

		List<OrderDto> orderDtos = new ArrayList<>();

		for (Order product : ordersPage.getContent()) {
			OrderDto orderDto = orderConverter.convertToDTO(product);
			orderDtos.add(orderDto);
		}

		return new PageImpl<>(orderDtos, pageable, ordersPage.getTotalElements());
	}

}
