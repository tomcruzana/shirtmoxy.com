package com.shirtmoxy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {
    // Add custom query methods if needed
}
