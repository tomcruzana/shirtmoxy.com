package com.shirtmoxy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
	@Query("SELECT s FROM Size s INNER JOIN s.productList p WHERE p.name = :productName GROUP BY s.type ORDER BY s.id")
	List<Size> findAllSizesByProductName(@Param("productName") String productName);

	@Query("SELECT s FROM Size s INNER JOIN s.productList p WHERE p.name = :productName AND p.color.id = :colorId AND p.unitsInStock > :stockThreshold GROUP BY s.type ORDER BY s.id")
	List<Size> findAllProductInStockSizes(@Param("productName") String productName, @Param("colorId") int colorId,
			@Param("stockThreshold") int stockThreshold);

	@Query("SELECT s FROM Size s INNER JOIN s.productList p WHERE p.name = :productName AND p.color.id = :colorId AND p.unitsInStock <= :stockThreshold GROUP BY s.type ORDER BY s.id")
	List<Size> findAllProductOutOfStockSizes(@Param("productName") String productName, @Param("colorId") int colorId,
			@Param("stockThreshold") int stockThreshold);

}
