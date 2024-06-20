package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.service.EmployeeManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeManagementResource {

    private final EmployeeManagementService employeeManagementService;

    private final Logger logger = LoggerFactory.getLogger(EmployeeManagementResource.class);

    public EmployeeManagementResource(EmployeeManagementService employeeManagementService) {
        this.employeeManagementService = employeeManagementService;
    }

    @PostMapping("/employees")
    public ResponseEntity create(@RequestBody EmployeeManagement employeeManagement){

        logger.debug("'/employees' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Post' sorovi qabul qilindi");
        logger.debug("'create' method-i ishga tushdi");

        try {
            EmployeeManagement result = employeeManagementService.save(employeeManagement);
            logger.info(result.getId() + "-id-li xodim 'EmployeeManagement' bazasiga muvaffaqiyatli qoshildi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Xodim 'EmployeeManagement' bazasiga qoshilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employees/update")
    public ResponseEntity update(@RequestBody EmployeeManagement employeeManagement){

        logger.debug("'/employees/update' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Put' sorovi qabul qilindi");
        logger.debug("'update' method-i ishga tushdi");

        try {
            if (employeeManagement.getId() == null){
                return ResponseEntity.badRequest().build();
            }

            EmployeeManagement result = employeeManagementService.save(employeeManagement);
            logger.info(employeeManagement.getId() + "-id-li xodim malumotlari 'EmployeeManagement' jadvalida yangilandi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(employeeManagement.getId() + "-id-li xodim malumotlari 'EmployeeManagement' jadvalida yangilanmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getSingle(@PathVariable Long id){

        logger.debug("'/employees/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getSingle' method-i ishga tushdi");

        try {
            EmployeeManagement result = employeeManagementService.findById(id);
            logger.info(id + "-id-li xodim malumotlari 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(id + "-id-li xodim malumotlari 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/search")
    public ResponseEntity getAllByParam(@RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam String familyName){

        logger.debug("'/employees/search' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getAllByParam' method-i ishga tushdi");

        try {
            List<EmployeeManagement> result = employeeManagementService.findAllByParam(firstName, lastName, familyName);
            logger.info(firstName + "; " + lastName + "; " + familyName + " parametrlarni qanoatlantiruvchi xodim malumtlari 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(firstName + "; " + lastName + "; " + familyName + " parametrlarni qanoatlantiruvchi xodim malumtlari 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity getAll(){

        logger.debug("'/employees' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getAll' method-i ishga tushdi");

        try {
            List<EmployeeManagement> result = employeeManagementService.findAll();
            logger.info("List<EmployeeManagement> turidagi malumot 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("List<EmployeeManagement> turidagi malumot 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/employees-delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        logger.debug("'/employees-delete/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Delete' sorovi qabul qilindi");
        logger.debug("'delete' method-i ishga tushdi");

        try {
            employeeManagementService.deleteById(id);
            logger.info(id + "-id-li xodim malumotlari 'EmployeeManagement' jadvalidan ochirildi");
            return ResponseEntity.ok("Xodim muvaffaqiyatli o'chirildi!");
        } catch (Exception e) {
            logger.error(id + "-id-li xodim malumotlari 'EmployeeManagement' jadvalidan ochirilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
