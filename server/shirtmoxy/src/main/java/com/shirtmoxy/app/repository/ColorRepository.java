package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Color;

@Repository
public interface ColorRepository extends CrudRepository<Color, Integer> {
    // Add custom query methods if needed
}
