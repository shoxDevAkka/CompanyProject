package com.company.web.rest;

import com.company.entity.CompanyPosition;
import com.company.service.CompanyPositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyPositionResource {

    private final CompanyPositionService companyPositionService;

    private final Logger logger = LoggerFactory.getLogger(CompanyPositionResource.class);

    public CompanyPositionResource(CompanyPositionService companyPositionService) {
        this.companyPositionService = companyPositionService;
    }

    @PostMapping("/positions")
    public ResponseEntity create(@RequestBody CompanyPosition companyPosition){

        logger.debug("'/positions' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Post' sorovi qabul qilindi");
        logger.debug("'create' method-i ishga tushdi");

        try {
            CompanyPosition result = companyPositionService.save(companyPosition);
            logger.info(companyPosition.getCompanyPosition() + " position 'CompanyPosition' jadvaliga muvafaqqiyatli qo'shildi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(companyPosition.getCompanyPosition() + " position 'CompanyPosition' jadvaliga muvafaqqiyatsiz qo'shildi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/positions")
    public ResponseEntity getAll(){

        logger.debug("'/positions' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getAll' method-i ishga tushdi");

        try {
            List<CompanyPosition> result = companyPositionService.findAll();
            logger.info("List<CompanyPosition> turidagi data 'CompanyPosition' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("List<CompanyPosition> turidagi data 'CompanyPosition' jadvalidan qabul qilinmadi");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/positions/{name}")
    public ResponseEntity delete(@PathVariable String name){

        logger.debug("'/positions/{name}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Delete' sorovi qabul qilindi");
        logger.debug("'delete' method-i ishga tushdi");

        try {
            companyPositionService.deleteByName(name);
            logger.info(name  + " boglangan data 'CompanyPosition' jadvalidan ochirildi");
            return ResponseEntity.ok("Kiritilgan lavozim o'chirildi!");
        } catch (Exception e) {
            logger.error(name  + " boglangan data 'CompanyPosition' jadvalidan ochirilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
