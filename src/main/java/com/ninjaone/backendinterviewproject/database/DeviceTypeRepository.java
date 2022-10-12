package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.DeviceType;

@Repository
public interface DeviceTypeRepository extends CrudRepository<DeviceType, Long> {
	
	Optional<DeviceType> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<DeviceType> findByIsActive(Boolean isActive);
	
}

	
