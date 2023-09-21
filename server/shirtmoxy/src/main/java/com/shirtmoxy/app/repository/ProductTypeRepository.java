package com.shirtmoxy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    // Add custom query methods if needed
}
