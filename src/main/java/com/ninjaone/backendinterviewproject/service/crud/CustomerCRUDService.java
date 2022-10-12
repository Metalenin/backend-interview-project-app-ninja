package com.ninjaone.backendinterviewproject.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.model.businessClasses.Customer;
import com.ninjaone.backendinterviewproject.model.dto.CustomerDTO;
import com.ninjaone.backendinterviewproject.model.enumeration.ActiveEnum;

@Service
public class CustomerCRUDService {
	
    private final CustomerRepository customerRepository;
    private static final Boolean active = ActiveEnum.YES.getValue();

    public CustomerCRUDService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomerEntity(Customer customer){
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerEntity(Long id){
        return customerRepository.findByIdAndIsActive(id, active);
    }
    
    public void deleteCustomerEntity(Long id) {
    	Optional<Customer> customerOptional = customerRepository.findById(id);
    	
    	if(customerOptional.isPresent()) {
    		Customer customer = customerOptional.get();
    		customer.setDeleted();
    		customerRepository.save(customer);
    	}
    }
    
    public List<Customer> getAllCustomerActive (){
    	List<Customer> customers = customerRepository.findByIsActive(active);
    	if(customers == null) {
    		customers = new ArrayList<Customer>();
    	}
    	return customers;
    }
    
    public List<CustomerDTO> getAllCustomerActiveDTO(){
    	List<Customer> customers = getAllCustomerActive();
    	List<CustomerDTO> customersDTO = new ArrayList<CustomerDTO>();
    	if(!customers.isEmpty()) {
    		for(Customer customer: customers) {
    			customersDTO.add(customer.getCustomerDTO());
    		}
    	}
    	return customersDTO;
    }
    
}
