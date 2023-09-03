package com.adrian.school_site.repositories;

import org.springframework.data.repository.CrudRepository;

import com.adrian.school_site.entity.ShortNewsData;

public interface ShortNewsSiteDataRepository extends CrudRepository<ShortNewsData, Integer> {

}
