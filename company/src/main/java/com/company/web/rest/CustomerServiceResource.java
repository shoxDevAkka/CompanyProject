package com.company.web.rest;

import com.company.entity.CustomerService;
import com.company.service.CustomerServiceDepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerServiceResource {

    private final CustomerServiceDepartmentService customerServiceDepartmentService;

    public CustomerServiceResource(CustomerServiceDepartmentService customerServiceDepartmentService) {
        this.customerServiceDepartmentService = customerServiceDepartmentService;
    }

    @PostMapping("/customers")
    public ResponseEntity create(@RequestBody CustomerService customerService){
        CustomerService result = customerServiceDepartmentService.save(customerService);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/customers/update")
    public ResponseEntity update(@RequestBody CustomerService customerService){
        if (customerService.getId() == null){
            return ResponseEntity.badRequest().build();
        }

        CustomerService result = customerServiceDepartmentService.save(customerService);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        CustomerService result = customerServiceDepartmentService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customers")
    public ResponseEntity getAll(){
        List<CustomerService> result = customerServiceDepartmentService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customers/search")
    public ResponseEntity getAllByParam(@RequestParam String firstName, @RequestParam String lastName){
        List<CustomerService> result = customerServiceDepartmentService.findAllByParam(firstName, lastName);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/customers-delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        customerServiceDepartmentService.deleteById(id);
        return ResponseEntity.ok("Obyekt o'chirildi!");
    }

}
