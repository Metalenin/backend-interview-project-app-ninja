package com.ninjaone.backendinterviewproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.dto.CustomerAccountCostsDTO;
import com.ninjaone.backendinterviewproject.service.cost.CustomerAccountCostsService;

@RestController
@RequestMapping("/customerAccount")
public class CustomerAccountController {
    private final CustomerAccountCostsService customerAccountCostsService;

    public CustomerAccountController(CustomerAccountCostsService customerAccountCostsService) {
        this.customerAccountCostsService = customerAccountCostsService;
    }

    @GetMapping("/getCustomerAccountCosts/{idCustomer}")
    @ResponseStatus(HttpStatus.OK)
    private CustomerAccountCostsDTO getCustomerAccountCosts(@PathVariable Long idCustomer){
        return customerAccountCostsService.getCustomerCosts(idCustomer);
    }

}
