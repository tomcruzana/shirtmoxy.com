package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.Barcode;

@Repository
public interface BarcodeRepository extends CrudRepository<Barcode, Integer>{

}
