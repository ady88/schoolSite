package com.adrian.school_site.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.adrian.school_site.entity.ImagesData;

public interface ImagesDataRepository extends CrudRepository<ImagesData, Integer>  {
	Optional<ImagesData> findByTitle(final String title);

	Integer deleteByTitle(final String title);
}
