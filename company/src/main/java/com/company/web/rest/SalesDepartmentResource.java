package com.company.web.rest;

import com.company.entity.SalesDepartment;
import com.company.service.SalesDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SalesDepartmentResource {

    private final SalesDepartmentService salesDepartmentService;

    private final Logger logger = LoggerFactory.getLogger(SalesDepartmentResource.class);

    public SalesDepartmentResource(SalesDepartmentService salesDepartmentService) {
        this.salesDepartmentService = salesDepartmentService;
    }

    @PostMapping("/sales")
    public ResponseEntity create(@RequestBody SalesDepartment salesDepartment){

        logger.debug("'/sales' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Post' sorovi qabul qilindi");
        logger.debug("'create' method-i ishga tushdi");

        try {
            SalesDepartment result = salesDepartmentService.save(salesDepartment);
            logger.info(result.getId() + "-id-li elon 'SalesDepartment' jadvaliga qoshildi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(salesDepartment.getId() + "-id-li elon 'SalesDepartment' jadvaliga qoshilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/sales/update")
    public ResponseEntity update(@RequestBody SalesDepartment salesDepartment){

        logger.debug("'/sales/update' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Patch' sorovi qabul qilindi");
        logger.debug("'update' method-i ishga tushdi");

        try {
            if (salesDepartment.getId() == null){
                return ResponseEntity.badRequest().build();
            }

            SalesDepartment result = salesDepartmentService.saveAsPatch(salesDepartment);
            logger.info(result.getId() + "-id-li elon malumotlari 'SalesDepartment' jadvalida yangilandi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(salesDepartment.getId() + "-id-li elon malumotlari 'SalesDepartment' jadvalida yangilanmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity getOne(@PathVariable Long id){

        logger.debug("'/sales/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'get' sorovi qabul qilindi");
        logger.debug("'getOne' method-i ishga tushdi");

        try {
            SalesDepartment result = salesDepartmentService.findById(id);
            logger.info(id + "-id-li elon malumotlari 'SalesDepartment' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(id + "-id-li elon malumotlari 'SalesDepartment' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales")
    public ResponseEntity getAll(){

        logger.debug("'/sales' endpoint - dan so'rov qabul qilindi");
        logger.debug("'get' sorovi qabul qilindi");
        logger.debug("'getAll' method-i ishga tushdi");

        try {
            List<SalesDepartment> result = salesDepartmentService.findAll();
            logger.info("List<SalesDepartment tipidagi ma'lumot 'SalesDepartment' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("List<SalesDepartment tipidagi ma'lumot 'SalesDepartment' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales/paging")
    public ResponseEntity getSalesByPaging(Pageable pageable){

        logger.debug("'/sales/paging' endpoint - dan so'rov qabul qilindi");
        logger.debug("'get' sorovi qabul qilindi");
        logger.debug("'getSalesByPaging' method-i ishga tushdi");

        try {
            Page<SalesDepartment> result = salesDepartmentService.findSalesByPaging(pageable);
            logger.info("Page<SalesDepartment> tipidagi malumot 'Pageable' sifatida 'SalesDepartment' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Page<SalesDepartment> tipidagi malumot 'Pageable' sifatida 'SalesDepartment' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sales-delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        logger.debug("'/sales-delete/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Delete' sorovi qabul qilindi");
        logger.debug("'delete' method-i ishga tushdi");

        try {
            salesDepartmentService.deleteById(id);
            logger.info(id + "-id-li elon malumotlari 'SalesDepartment' jadvalidan ochirildi");
            return ResponseEntity.ok("Ma'lumot o'chirildi!");
        } catch (Exception e) {
            logger.error(id + "-id-li elon malumotlari 'SalesDepartment' jadvalidan ochirilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
