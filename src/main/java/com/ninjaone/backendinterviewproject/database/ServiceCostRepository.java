package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.ServiceCost;

@Repository
public interface ServiceCostRepository extends CrudRepository<ServiceCost, Long> {
	
	Optional<ServiceCost> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<ServiceCost> findByIsActive(Boolean isActive);
	
	@Query("FROM ServiceCost sc "
			+" WHERE sc.isActive = :isActive"
			+" AND sc.service_.isActive = :isActive"
			+" AND sc.service_.id in :idServices"
			)
	List<ServiceCost> findByListIdServiceAndIsActive(@Param("idServices") List<Long> idServices, @Param("isActive")Boolean isActive);
	
}

	
