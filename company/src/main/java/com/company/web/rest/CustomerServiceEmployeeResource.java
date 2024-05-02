package com.company.web.rest;

import com.company.entity.CustomerService;
import com.company.entity.EmployeeManagement;
import com.company.service.CustomerServiceEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerServiceEmployeeResource {

    private final CustomerServiceEmployeeService customerServiceEmployeeService;

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceEmployeeResource.class);

    public CustomerServiceEmployeeResource(CustomerServiceEmployeeService customerServiceEmployeeService) {
        this.customerServiceEmployeeService = customerServiceEmployeeService;
    }

    @PutMapping("/customer-employees")
    public ResponseEntity saveCustomer(@RequestBody EmployeeManagement employeeManagement){

        logger.debug("'/customer-employees' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Put' sorovi qabul qilindi");
        logger.debug("'saveCustomer' method-i ishga tushdi");

        try {
            if (employeeManagement.getId() == null && employeeManagement.getSalesDepartments() != null){
                return ResponseEntity.badRequest().build();
            }

            EmployeeManagement result = customerServiceEmployeeService.save(employeeManagement);
            List<Long> ids = employeeManagement.getCustomerService().stream().map(CustomerService::getId).collect(Collectors.toList());
            logger.info(ids + " id-dagi mijozlar 'EmployeeManagement' jadvalidagi " + employeeManagement.getId() + " -idagi 'CustomerService' xodim akkauntiga qoshildi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            List<Long> ids1 = employeeManagement.getCustomerService().stream().map(CustomerService::getId).collect(Collectors.toList());
            logger.error(ids1 + " id-dagi mijozlar " + employeeManagement.getId() + " -idagi 'CustomerService' xodim akkauntiga qoshishda muvavaqqiyatsizlikga uchradi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer-employees/{id}")
    public ResponseEntity getEmployeeCustomers(@PathVariable Long id){

        logger.debug("'/customer-employees/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getEmployeeCustomers' method-i ishga tushdi");

        try {
            List<CustomerService> result = customerServiceEmployeeService.findAllCustomers(id);
            logger.info(id + "-id-li 'CustomerService' xodimining mijozlari 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(id + "-id-li 'CustomerService' xodimining mijozlari 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
