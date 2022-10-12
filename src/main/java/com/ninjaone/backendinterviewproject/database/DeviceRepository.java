package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
	
	Optional<Device> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<Device> findByIsActive(Boolean isActive);
	
}

	
