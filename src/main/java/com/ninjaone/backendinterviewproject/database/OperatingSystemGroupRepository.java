package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.OperatingSystemGroup;

@Repository
public interface OperatingSystemGroupRepository extends CrudRepository<OperatingSystemGroup, Long> {
	
	Optional<OperatingSystemGroup> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<OperatingSystemGroup> findByIsActive(Boolean isActive);
	
}

	
