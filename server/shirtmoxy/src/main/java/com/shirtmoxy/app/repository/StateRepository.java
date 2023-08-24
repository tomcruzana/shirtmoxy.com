package com.shirtmoxy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
	List<State> findByCountryCode(@Param("code") String code);
}
