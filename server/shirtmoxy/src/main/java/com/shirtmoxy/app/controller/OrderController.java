package com.shirtmoxy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shirtmoxy.app.dto.OrderDto;
import com.shirtmoxy.app.service.OrderService;

@RestController
@RequestMapping("/user")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/orders/all")
	public ResponseEntity<Page<OrderDto>> getAllOrders(@RequestParam("email") String email,
			@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {

		Page<OrderDto> orderPage = orderService.readAllOrdersByCustomerEmail(email, pageNum, pageSize);
		return new ResponseEntity<>(orderPage, HttpStatus.OK);
	}
}
