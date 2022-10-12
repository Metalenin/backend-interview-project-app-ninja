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

import com.ninjaone.backendinterviewproject.model.businessClasses.CustomerDevice;
import com.ninjaone.backendinterviewproject.model.dto.CustomerDeviceDTO;
import com.ninjaone.backendinterviewproject.service.crud.CustomerDeviceCRUDService;

@RestController
@RequestMapping("/customerDevice")
public class CustomerDeviceController {
    private final CustomerDeviceCRUDService customerDeviceService;

    public CustomerDeviceController(CustomerDeviceCRUDService customerDeviceService) {
        this.customerDeviceService = customerDeviceService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private CustomerDevice postCustomerDeviceEntity(@RequestBody CustomerDeviceDTO customerDeviceDTO){
        return customerDeviceService.saveCustomerDeviceEntity(customerDeviceDTO.getCustomerDevice());
    }

    @GetMapping("/{id}")
    private CustomerDevice getCustomerDeviceEntity(@PathVariable Long id){
        return customerDeviceService.getCustomerDeviceEntity(id)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteCustomerDeviceEntity(@PathVariable Long id){
    	customerDeviceService.deleteCustomerDeviceEntity(id);
    }
    
    @GetMapping("/all")
    private List<CustomerDeviceDTO> getAllCustomerDeviceDTO(){
        return customerDeviceService.getAllCustomerDeviceActiveDTO();
    }
}
