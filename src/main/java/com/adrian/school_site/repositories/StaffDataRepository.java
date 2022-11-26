package com.adrian.school_site.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.adrian.school_site.entity.StaffData;

public interface StaffDataRepository extends CrudRepository<StaffData, Integer> {
	Optional<StaffData> findByFirstnameAndLastname(final String firstname, final String lastname);

	Integer deleteByFirstnameAndLastname(final String firstname, final String lastname);
}
