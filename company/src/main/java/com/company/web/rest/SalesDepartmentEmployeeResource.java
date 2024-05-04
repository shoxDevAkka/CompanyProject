package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.entity.SalesDepartment;
import com.company.service.SalesDepartmentEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalesDepartmentEmployeeResource {

    private final SalesDepartmentEmployeeService salesDepartmentEmployeeService;

    private final Logger logger = LoggerFactory.getLogger(SalesDepartmentEmployeeResource.class);

    public SalesDepartmentEmployeeResource(SalesDepartmentEmployeeService salesDepartmentEmployeeService) {
        this.salesDepartmentEmployeeService = salesDepartmentEmployeeService;
    }

    @PutMapping("/sales-employee")
    public ResponseEntity saveAccount(@RequestBody EmployeeManagement employeeManagement){

        logger.debug("'/sales-employee' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Put' sorovi qabul qilindi");
        logger.debug("'saveAccount' method-i ishga tushdi");

        try {
            if (employeeManagement.getId() == null && employeeManagement.getCustomerService() != null){
                return ResponseEntity.badRequest().build();
            }

            EmployeeManagement result = salesDepartmentEmployeeService.save(employeeManagement);
            List<Long> ids = employeeManagement.getSalesDepartments().stream().map(SalesDepartment::getId).collect(Collectors.toList());
            logger.info(ids + " id-dagi elonlar 'EmployeeManagement' jadvalidagi " + employeeManagement.getId() + " -idagi 'SalesDepartment' xodim akkauntiga qoshildi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            List<Long> ids1 = employeeManagement.getSalesDepartments().stream().map(SalesDepartment::getId).collect(Collectors.toList());
            logger.error(ids1 + " id-dagi elonlar 'EmployeeManagement' jadvalidagi " + employeeManagement.getId() + " -idagi 'SalesDepartment' xodim akkauntiga qoshildi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales-employee/{id}")
    public ResponseEntity getEmployeeAdverts(@PathVariable Long id){

        logger.debug("'/sales-employee/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getEmployeeAdverts' method-i ishga tushdi");

        try {
            List<SalesDepartment> result = salesDepartmentEmployeeService.findEmployeeAdverts(id);
            logger.info(id + "-id-li 'SalesDepartment' xodimining barcha elonlari 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(id + "-id-li 'SalesDepartment' xodimining barcha elonlari 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
