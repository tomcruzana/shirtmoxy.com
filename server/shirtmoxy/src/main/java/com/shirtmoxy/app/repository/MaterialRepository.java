package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Material;

@Repository
public interface MaterialRepository extends CrudRepository<Material, Integer> {
    // Add custom query methods if needed
}
