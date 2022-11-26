package com.adrian.school_site.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.adrian.school_site.entity.NewsData;

public interface NewsDataRepository extends CrudRepository<NewsData, Integer> {
	Optional<NewsData> findByTitle(final String title);

	Integer deleteByTitle(final String title);
}
