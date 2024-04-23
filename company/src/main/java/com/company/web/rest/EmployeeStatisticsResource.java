package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.service.EmployeeStatisticsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeStatisticsResource {

    private final EmployeeStatisticsService employeeStatisticsService;

    public EmployeeStatisticsResource(EmployeeStatisticsService employeeStatisticsService) {
        this.employeeStatisticsService = employeeStatisticsService;
    }

    @GetMapping("/employee-statistics/count")
    public ResponseEntity getStatisticsEmployeesByDepartment(@RequestParam String departmentName){
        String result = employeeStatisticsService.findStatisticsEmployeesByDepartment(departmentName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employee-statistics/age")
    public ResponseEntity getStatisticsEmployeesByAge(@RequestParam Long age){
        List<EmployeeManagement> result = employeeStatisticsService.findStatisticsEmployeesByAge(age);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employee-statistics/paging")
    public ResponseEntity getEmployeesByPaging(Pageable pageable){
        Page<EmployeeManagement> result = employeeStatisticsService.findEmployeesByPaging(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employee-statistics/salary")
    public ResponseEntity getSumSalaryEmployees(){
        Long result = employeeStatisticsService.findSumSalaryEmployees();
        return ResponseEntity.ok("Ishchilar umumiy oyligi summasi : " + result);
    }
}
