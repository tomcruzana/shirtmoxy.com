package com.shirtmoxy.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shirtmoxy.app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByEmail(String email);
}

