package com.shirtmoxy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
