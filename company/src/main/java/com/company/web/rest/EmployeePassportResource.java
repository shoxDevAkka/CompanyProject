package com.company.web.rest;

import com.company.entity.EmployeePassport;
import com.company.service.EmployeePassportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeePassportResource {

    private final EmployeePassportService employeePassportService;

    public EmployeePassportResource(EmployeePassportService employeePassportService) {
        this.employeePassportService = employeePassportService;
    }

    @PostMapping("/passports")
    public ResponseEntity create(@RequestBody EmployeePassport employeePassport){
        EmployeePassport result = employeePassportService.save(employeePassport);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/passports")
    public ResponseEntity update(@RequestBody EmployeePassport employeePassport){
        if (employeePassport.getId() == null){
            return ResponseEntity.badRequest().build();
        }

        EmployeePassport result = employeePassportService.save(employeePassport);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/passports")
    public ResponseEntity getAll(){
        List<EmployeePassport> result = employeePassportService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/passports/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        employeePassportService.deleteById(id);
        return ResponseEntity.ok("Ma'lumot o'chirildi!");
    }

}
