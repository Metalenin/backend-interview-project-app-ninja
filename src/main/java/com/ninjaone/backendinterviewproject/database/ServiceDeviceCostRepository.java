package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceDeviceCost;

@Repository
public interface ServiceDeviceCostRepository extends CrudRepository<ServiceDeviceCost, Long> {
	
	Optional<ServiceDeviceCost> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<ServiceDeviceCost> findByIsActive(Boolean isActive);
	
	@Query("FROM ServiceDeviceCost sdc "
			+" WHERE sdc.isActive = :isActive "
			+" AND sdc.deviceType is null "
			)
	Optional<ServiceDeviceCost> findByIsActiveDeviceTypeNull(@Param("isActive")Boolean isActive);
	
}

	
