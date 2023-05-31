package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Gender;

@Repository
public interface GenderRepository extends CrudRepository<Gender, Integer> {
    // Add custom query methods if needed
}
