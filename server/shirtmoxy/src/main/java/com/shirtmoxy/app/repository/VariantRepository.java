package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Variant;

@Repository
public interface VariantRepository extends CrudRepository<Variant, Integer> {
	// Add custom query methods if needed
}
