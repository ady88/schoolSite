package com.adrian.school_site.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.adrian.school_site.entity.ResourcesData;

public interface ResourcesDataRepository extends CrudRepository<ResourcesData, Integer> {
	Optional<ResourcesData> findByName(final String name);

	Integer deleteByName(final String name);
}
