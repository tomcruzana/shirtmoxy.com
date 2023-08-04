package com.shirtmoxy.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	@Query(value = "SELECT * FROM product WHERE MATCH(SKU, name, description) AGAINST(?1)", nativeQuery = true)
	public Page<Product> search(String keyword, Pageable pageable);
	
	public Page<Product> findByCategoryId(@Param("id") int id, Pageable pageable);
	
	public Page<Product> findByManufacturerId(@Param("id") int id, Pageable pageable);
	
	@Query("SELECT p FROM Product p "
	        + "INNER JOIN p.variant v "
	        + "INNER JOIN v.gender g "
	        + "WHERE g.id = :id")
    public Page<Product> findByGenderId(@Param("id") int id, Pageable pageable);
}
