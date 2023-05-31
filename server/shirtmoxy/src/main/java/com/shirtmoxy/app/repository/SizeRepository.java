package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Size;

@Repository
public interface SizeRepository extends CrudRepository<Size, Integer> {
	// Add custom query methods if needed
}
