package com.shirtmoxy.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	@Query(value = "SELECT * FROM product WHERE MATCH(SKU, name, description) AGAINST(?1)", nativeQuery = true)
	public Page<Product> search(String keyword, Pageable pageable);

	public Page<Product> findByCategoryId(@Param("id") int id, Pageable pageable);

	public Page<Product> findByManufacturerId(@Param("id") int id, Pageable pageable);

	public Page<Product> findByGenderId(@Param("id") int id, Pageable pageable);

	public Optional<Product> findBySku(String sku);

	@Query("SELECT p FROM Product p WHERE p.name = :productName AND p.color.id = :colorId GROUP BY p.name")
	public Optional<Product> findProductsByNameAndColorGrouped(@Param("productName") String productName,
			@Param("colorId") int colorId);

}
