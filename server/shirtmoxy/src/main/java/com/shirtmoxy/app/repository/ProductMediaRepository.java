package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.ProductMedia;

@Repository
public interface ProductMediaRepository extends CrudRepository<ProductMedia, Integer> {
	// Add custom query methods if needed
}
