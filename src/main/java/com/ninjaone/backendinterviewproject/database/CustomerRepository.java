package com.ninjaone.backendinterviewproject.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.businessClasses.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	Optional<Customer> findByIdAndIsActive(Long id, Boolean isActive);
	
	List<Customer> findByIsActive(Boolean isActive);
	
}

	
