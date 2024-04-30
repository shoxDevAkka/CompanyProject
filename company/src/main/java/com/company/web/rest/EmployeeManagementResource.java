package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.service.EmployeeManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeManagementResource {

    private final EmployeeManagementService employeeManagementService;

    public EmployeeManagementResource(EmployeeManagementService employeeManagementService) {
        this.employeeManagementService = employeeManagementService;
    }

    @PostMapping("/employees")
    public ResponseEntity create(@RequestBody EmployeeManagement employeeManagement){
        EmployeeManagement result = employeeManagementService.save(employeeManagement);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/employees/update")
    public ResponseEntity update(@RequestBody EmployeeManagement employeeManagement){
        if (employeeManagement.getId() == null){
            return ResponseEntity.badRequest().build();
        }

        EmployeeManagement result = employeeManagementService.save(employeeManagement);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getSingle(@PathVariable Long id){
        EmployeeManagement result = employeeManagementService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees/search")
    public ResponseEntity getAllByParam(@RequestBody String firstName,
                                        @RequestBody String lastName,
                                        @RequestBody String familyName){
        List<EmployeeManagement> result = employeeManagementService.findAllByParam(firstName, lastName, familyName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employees")
    public ResponseEntity getAll(){
        List<EmployeeManagement> result = employeeManagementService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/employees-delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        employeeManagementService.deleteById(id);
        return ResponseEntity.ok("Xodim muvaffaqiyatli o'chirildi!");
    }
}
