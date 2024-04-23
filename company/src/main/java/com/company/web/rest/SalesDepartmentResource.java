package com.company.web.rest;

import com.company.entity.SalesDepartment;
import com.company.service.SalesDepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SalesDepartmentResource {

    private final SalesDepartmentService salesDepartmentService;

    public SalesDepartmentResource(SalesDepartmentService salesDepartmentService) {
        this.salesDepartmentService = salesDepartmentService;
    }

    @PostMapping("/sales")
    public ResponseEntity create(@RequestBody SalesDepartment salesDepartment){
        SalesDepartment result = salesDepartmentService.save(salesDepartment);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/sales")             // "Put" method must be practiced again
    public ResponseEntity update(@RequestBody SalesDepartment salesDepartment){     // "NullRequest" errors on entity
        if (salesDepartment.getId() == null){
            return ResponseEntity.badRequest().build();
        }

        if (salesDepartment.getExpense() != null || salesDepartment.getResponsiblePerson() != null){
            return ResponseEntity.badRequest().build();
        }

        SalesDepartment result = salesDepartmentService.save(salesDepartment);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        SalesDepartment result = salesDepartmentService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales")
    public ResponseEntity getAll(){
        List<SalesDepartment> result = salesDepartmentService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales/paging")
    public ResponseEntity getSalesByPaging(Pageable pageable){
        Page<SalesDepartment> result = salesDepartmentService.findSalesByPaging(pageable);
        return ResponseEntity.ok(result);
    }

}
