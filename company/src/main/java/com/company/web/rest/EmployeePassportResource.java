package com.company.web.rest;

import com.company.entity.EmployeePassport;
import com.company.service.EmployeePassportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeePassportResource {

    private final EmployeePassportService employeePassportService;

    private final Logger logger = LoggerFactory.getLogger(EmployeePassportResource.class);

    public EmployeePassportResource(EmployeePassportService employeePassportService) {
        this.employeePassportService = employeePassportService;
    }

    @PostMapping("/passports")
    public ResponseEntity create(@RequestBody EmployeePassport employeePassport){

        logger.debug("'/customers' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Post' sorovi qabul qilindi");
        logger.debug("'create' method-i ishga tushdi");

        try {
            EmployeePassport result = employeePassportService.save(employeePassport);
            logger.info(result.getId() + "-id-li kiritilgan passport malumotlari 'EmployeePassport' jadvaliga qoshildi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Kiritilgan passport malumotlari 'EmployeePassport' jadvaliga qoshilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/passports")
    public ResponseEntity update(@RequestBody EmployeePassport employeePassport){

        logger.debug("'/passports' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Put' sorovi qabul qilindi");
        logger.debug("'update' method-i ishga tushdi");

        try {
            if (employeePassport.getId() == null){
                return ResponseEntity.badRequest().build();
            }

            EmployeePassport result = employeePassportService.save(employeePassport);
            logger.info(employeePassport.getId() + "-id-li passport malumotlari 'EmployeePassport' jadvalida yangilandi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(employeePassport.getId() + "-id-li passport malumotlari 'EmployeePassport' jadvalida yangilanmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/passports")
    public ResponseEntity getAll(){

        logger.debug("'/passports' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getAll' method-i ishga tushdi");

        try {
            List<EmployeePassport> result = employeePassportService.findAll();
            logger.info("List<EmployeePassport> turidagi malumot 'EmployeePassport' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("List<EmployeePassport> turidagi malumot 'EmployeePassport' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/passports/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        logger.debug("'/passports/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Delete' sorovi qabul qilindi");
        logger.debug("'delete' method-i ishga tushdi");

        try {
            employeePassportService.deleteById(id);
            logger.info(id + "-id-li passport malumotlari o'chirildi");
            return ResponseEntity.ok("Ma'lumot o'chirildi!");
        } catch (Exception e) {
            logger.error(id + "-id-li passport malumotlari o'chirilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
