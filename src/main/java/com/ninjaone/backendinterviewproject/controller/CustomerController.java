package com.ninjaone.backendinterviewproject.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ninjaone.backendinterviewproject.model.businessClasses.Customer;
import com.ninjaone.backendinterviewproject.model.dto.CustomerDTO;
import com.ninjaone.backendinterviewproject.service.crud.CustomerCRUDService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerCRUDService customerService;

    public CustomerController(CustomerCRUDService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private Customer postCustomerEntity(@RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomerEntity(customerDTO.getCustomerForCreation());
    }

    @GetMapping("/{id}")
    private Customer getCustomerEntity(@PathVariable Long id){
        return customerService.getCustomerEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteCustomerEntity(@PathVariable Long id){
        customerService.deleteCustomerEntity(id);
    }
    
    @GetMapping("/all")
    private List<CustomerDTO> getAllCustomerDTO(){
        return customerService.getAllCustomerActiveDTO();
    }
}
