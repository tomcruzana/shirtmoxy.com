package com.shirtmoxy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shirtmoxy.app.entity.MediaFormat;

@Repository
public interface MediaFormatRepository extends CrudRepository<MediaFormat, Integer> {
    // Add custom query methods if needed
}
