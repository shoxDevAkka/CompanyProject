package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.entity.SalesDepartment;
import com.company.service.SalesDepartmentEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SalesDepartmentEmployeeResource {

    private final SalesDepartmentEmployeeService salesDepartmentEmployeeService;

    public SalesDepartmentEmployeeResource(SalesDepartmentEmployeeService salesDepartmentEmployeeService) {
        this.salesDepartmentEmployeeService = salesDepartmentEmployeeService;
    }

    @PutMapping("/sales-employee")
    public ResponseEntity saveAccount(@RequestBody EmployeeManagement employeeManagement){
        if (employeeManagement.getId() == null && employeeManagement.getCustomerService() != null){
            return ResponseEntity.badRequest().build();
        }

        EmployeeManagement result = salesDepartmentEmployeeService.save(employeeManagement);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales-employee/{id}")
    public ResponseEntity getEmployeeAdverts(@PathVariable Long id){
        List<SalesDepartment> result = salesDepartmentEmployeeService.findEmployeeAdverts(id);
        return ResponseEntity.ok(result);
    }
}
