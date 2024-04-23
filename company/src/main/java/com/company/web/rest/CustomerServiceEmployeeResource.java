package com.company.web.rest;

import com.company.entity.CustomerService;
import com.company.entity.EmployeeManagement;
import com.company.service.CustomerServiceEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerServiceEmployeeResource {

    private final CustomerServiceEmployeeService customerServiceEmployeeService;

    public CustomerServiceEmployeeResource(CustomerServiceEmployeeService customerServiceEmployeeService) {
        this.customerServiceEmployeeService = customerServiceEmployeeService;
    }

    @PutMapping("/customer-employees")
    public ResponseEntity saveCustomer(@RequestBody EmployeeManagement employeeManagement){
        if (employeeManagement.getId() == null && employeeManagement.getSalesDepartments() != null){
            return ResponseEntity.badRequest().build();
        }

        EmployeeManagement result = customerServiceEmployeeService.save(employeeManagement);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customer-employees/{id}")
    public ResponseEntity getEmployeeCustomers(@PathVariable Long id){
        List<CustomerService> result = customerServiceEmployeeService.findAllCustomers(id);
        return ResponseEntity.ok(result);
    }

}
