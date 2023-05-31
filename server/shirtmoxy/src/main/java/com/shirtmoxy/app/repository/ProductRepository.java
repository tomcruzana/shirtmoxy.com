package com.shirtmoxy.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{

}
