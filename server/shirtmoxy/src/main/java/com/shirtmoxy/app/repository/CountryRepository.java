package com.shirtmoxy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
