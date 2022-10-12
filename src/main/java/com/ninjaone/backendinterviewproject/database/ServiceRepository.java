package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.Service_;

@Repository
public interface ServiceRepository extends CrudRepository<Service_, Long> {
	
	Optional<Service_> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<Service_> findByIsActive(Boolean isActive);
	
}

	
