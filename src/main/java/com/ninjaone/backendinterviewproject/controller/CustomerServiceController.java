package com.ninjaone.backendinterviewproject.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.businessClasses.CustomerService;
import com.ninjaone.backendinterviewproject.model.dto.CustomerServiceDTO;
import com.ninjaone.backendinterviewproject.service.crud.CustomerServiceCRUDService;

@RestController
@RequestMapping("/customerService")
public class CustomerServiceController {
    private final CustomerServiceCRUDService customerServiceService;

    public CustomerServiceController(CustomerServiceCRUDService customerServiceService) {
        this.customerServiceService = customerServiceService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private CustomerService postCustomerServiceEntity(@RequestBody CustomerServiceDTO customerServiceDTO){
        return customerServiceService.saveCustomerServiceEntity(customerServiceDTO.getCustomerService());
    }

    @GetMapping("/{id}")
    private CustomerService getCustomerServiceEntity(@PathVariable Long id){
        return customerServiceService.getCustomerServiceEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteCustomerServiceEntity(@PathVariable Long id){
    	customerServiceService.deleteCustomerServiceEntity(id);
    }
    
    @GetMapping("/all")
    private List<CustomerServiceDTO> getAllCustomerServiceDTO(){
        return customerServiceService.getAllCustomerServiceActiveDTO();
    }
}
