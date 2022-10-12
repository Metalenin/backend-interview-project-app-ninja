package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.CustomerService;

@Repository
public interface CustomerServiceRepository extends CrudRepository<CustomerService, Long> {
	
	Optional<CustomerService> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<CustomerService> findByIsActive(Boolean isActive);
	
	@Query("FROM CustomerService cs "
			+" WHERE cs.isActive = :isActive"
			+" AND cs.customer.isActive = :isActive"
			+" AND cs.service_.isActive = :isActive"
			+" AND cs.customer.id = :idCustomer"
			)
	List<CustomerService> findByIdCustomerAndIsActive(@Param("idCustomer")Long idCustomer, @Param("isActive")Boolean isActive);
	
}

	
