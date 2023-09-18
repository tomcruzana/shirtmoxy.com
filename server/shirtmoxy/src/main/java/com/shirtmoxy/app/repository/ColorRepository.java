package com.shirtmoxy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
	@Query("SELECT c FROM Color c INNER JOIN c.productList p WHERE p.name = :productName")
    List<Color> findColorsByProductName(@Param("productName") String productName);
}
