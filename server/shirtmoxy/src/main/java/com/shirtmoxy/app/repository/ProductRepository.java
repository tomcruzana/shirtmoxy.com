package com.shirtmoxy.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Optional<Product> findBySku(String sku);

	@Query("SELECT p FROM Product p WHERE p.name = :productName AND p.color.id = :colorId GROUP BY p.name")
	public Optional<Product> findProductsByNameAndColorGrouped(@Param("productName") String productName,
			@Param("colorId") int colorId);

	@Query("SELECT p FROM Product p " +
		       "WHERE (:productType IS NULL OR p.productType.name = :productType) " +
		       "AND (:searchQuery IS NULL OR " +
		       "p.sku LIKE %:searchQuery% OR " +
		       "p.name LIKE %:searchQuery% OR " +
		       "p.manufacturer.name LIKE %:searchQuery% OR " +
		       "p.gender.type LIKE %:searchQuery% OR " +
		       "p.color.name LIKE %:searchQuery% OR " +
		       "p.size.type LIKE %:searchQuery% OR " +
		       "p.material.type LIKE %:searchQuery%)" +
		       "AND p.category.name IN :categories AND p.manufacturer.name IN :manufacturers AND p.gender.type IN :genders AND p.color.name IN :colors AND p.size.type IN :sizes AND p.material.type IN :materials " +
			   "GROUP BY p.name")
		Page<Product> findFilteredProducts(
				@Param("productType") String productTypeId,
		        @Param("searchQuery") String searchQuery, 
		        @Param("categories") List<String> categories,
		        @Param("manufacturers") List<String> manufacturers,
		        @Param("genders") List<String> genders,
		        @Param("colors") List<String> colors,
				@Param("sizes") List<String> sizes,
				@Param("materials") List<String> materials,
				Pageable pageable);

}
