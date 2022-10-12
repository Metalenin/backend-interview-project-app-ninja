package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceOperatingSystemGroupCost;

@Repository
public interface ServiceOperatingSystemGroupCostRepository extends CrudRepository<ServiceOperatingSystemGroupCost, Long> {
	
	Optional<ServiceOperatingSystemGroupCost> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<ServiceOperatingSystemGroupCost> findByIsActive(Boolean isActive);
	
	@Query("FROM ServiceOperatingSystemGroupCost sosc "
			+" WHERE sosc.isActive = :isActive"
			+" AND sosc.service_.isActive = :isActive"
			+" AND sosc.service_.id in :idServices"
			)
	List<ServiceOperatingSystemGroupCost> findByListIdServiceAndIsActive(@Param("idServices") List<Long> idServices, @Param("isActive")Boolean isActive);
	
}

	
