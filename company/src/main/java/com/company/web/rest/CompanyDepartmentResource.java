package com.company.web.rest;


import com.company.entity.CompanyDepartment;
import com.company.service.CompanyDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyDepartmentResource {

    private final CompanyDepartmentService companyDepartmentService;

    private final Logger logger = LoggerFactory.getLogger(CompanyDepartmentResource.class);

    public CompanyDepartmentResource(CompanyDepartmentService companyDepartmentService) {
        this.companyDepartmentService = companyDepartmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity create(@RequestBody CompanyDepartment companyDepartment){

        logger.debug("'/departments' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Post' sorovi qabul qilindi");
        logger.debug("'create' method-i ishga tushdi");

        try {
            CompanyDepartment result = companyDepartmentService.save(companyDepartment);
            logger.info(companyDepartment.getDepartmentName() + " department name 'CompanyDepartment' jadvaliga muvafaqqiyatli qo'shildi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(companyDepartment.getDepartmentName() + " department name 'CompanyDepartment' jadvaliga muvafaqqiyatsiz qo'shildi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departments")
    public ResponseEntity getAll(){

        logger.debug("'/departments' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getAll' method-i ishga tushdi");

        try {
            List<CompanyDepartment> result = companyDepartmentService.findAll();
            logger.info("List<CompanyDepartment> turidagi natija 'CompanyDepartment' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("List<CompanyDepartment> turidagi natija 'CompanyDepartment' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        logger.debug("'/departments/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Delete' sorovi qabul qilindi");
        logger.debug("'delete' method-i ishga tushdi");

        try {
            companyDepartmentService.deleteById(id);
            logger.info(id + "-id-dagi ma'lumot 'CompanyDepartment' jadvalidan o'chirildi");
            return ResponseEntity.ok("Department o'chirildi!");
        } catch (Exception e) {
            logger.error(id + "-id-dagi ma'lumot 'CompanyDepartment' jadvalidan o'chirilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
