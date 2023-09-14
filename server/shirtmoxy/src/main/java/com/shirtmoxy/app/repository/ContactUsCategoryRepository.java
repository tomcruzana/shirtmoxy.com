package com.shirtmoxy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.ContactUsCategory;

@Repository
public interface ContactUsCategoryRepository extends JpaRepository<ContactUsCategory, Integer> {

}
