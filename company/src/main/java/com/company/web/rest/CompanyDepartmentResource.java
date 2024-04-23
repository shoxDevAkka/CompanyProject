package com.company.web.rest;


import com.company.entity.CompanyDepartment;
import com.company.service.CompanyDepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyDepartmentResource {

    private final CompanyDepartmentService companyDepartmentService;

    public CompanyDepartmentResource(CompanyDepartmentService companyDepartmentService) {
        this.companyDepartmentService = companyDepartmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity create(@RequestBody CompanyDepartment companyDepartment){
        CompanyDepartment result = companyDepartmentService.save(companyDepartment);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/departments")
    public ResponseEntity getAll(){
        List<CompanyDepartment> result = companyDepartmentService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        companyDepartmentService.deleteById(id);
        return ResponseEntity.ok("Department o'chirildi!");
    }


}
