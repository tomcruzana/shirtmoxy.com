package com.shirtmoxy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    // Add custom query methods if needed
}
