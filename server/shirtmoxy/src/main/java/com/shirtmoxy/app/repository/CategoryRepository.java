package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    // Add custom query methods if needed
}
