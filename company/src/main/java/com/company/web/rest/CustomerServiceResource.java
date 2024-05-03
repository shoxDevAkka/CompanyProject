package com.company.web.rest;

import com.company.entity.CustomerService;
import com.company.service.CustomerServiceDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerServiceResource {

    private final CustomerServiceDepartmentService customerServiceDepartmentService;

    private final Logger logger = LoggerFactory.getLogger(CustomerServiceResource.class);

    public CustomerServiceResource(CustomerServiceDepartmentService customerServiceDepartmentService) {
        this.customerServiceDepartmentService = customerServiceDepartmentService;
    }

    @PostMapping("/customers")
    public ResponseEntity create(@RequestBody CustomerService customerService){

        logger.debug("'/customers' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Post' sorovi qabul qilindi");
        logger.debug("'create' method-i ishga tushdi");

        try {
            CustomerService result = customerServiceDepartmentService.save(customerService);
            logger.info(result.getId() + "-id-agi mijoz 'CustomerService' jadvalida registratsiyadan otdi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(customerService.getId() + "-id-agi mijoz 'CustomerService' jadvalida registratsiyadan otmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/update")
    public ResponseEntity update(@RequestBody CustomerService customerService){

        logger.debug("'/customers/update' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Put' sorovi qabul qilindi");
        logger.debug("'update' method-i ishga tushdi");

        try {
            if (customerService.getId() == null){
                return ResponseEntity.badRequest().build();
            }

            CustomerService result = customerServiceDepartmentService.save(customerService);
            logger.info(customerService.getId() + "-id-agi mijoz malumotlari 'CustomerService' jadvalida yangilandi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(customerService.getId() + "-id-agi mijoz malumotlari 'CustomerService' jadvalida yangilanmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity getOne(@PathVariable Long id){

        logger.debug("'/customers/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'get' sorovi qabul qilindi");
        logger.debug("'getOne' method-i ishga tushdi");

        try {
            CustomerService result = customerServiceDepartmentService.findById(id);
            logger.info(id + "-id-li mijoz malumotlari 'CustomerService' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(id + "-id-li mijoz malumotlari 'CustomerService' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity getAll(){

        logger.debug("'/customers' endpoint - dan so'rov qabul qilindi");
        logger.debug("'get' sorovi qabul qilindi");
        logger.debug("'getAll' method-i ishga tushdi");

        try {
            List<CustomerService> result = customerServiceDepartmentService.findAll();
            logger.info("List<CustomerService> tipidagi ma'lumot 'CustomerService' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("List<CustomerService> tipidagi ma'lumot 'CustomerService' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/search")
    public ResponseEntity getAllByParam(@RequestParam String firstName, @RequestParam String lastName){

        logger.debug("'/customers/search' endpoint - dan so'rov qabul qilindi");
        logger.debug("'get' sorovi qabul qilindi");
        logger.debug("'getAllByParam' method-i ishga tushdi");

        try {
            List<CustomerService> result = customerServiceDepartmentService.findAllByParam(firstName, lastName);
            logger.info(firstName + " va " + lastName + " parameterlariga mos mijoz malumotlari 'CustomerService' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(firstName + " va " + lastName + " parameterlariga mos mijoz malumotlari 'CustomerService' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers-delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        logger.debug("'/customers-delete/{id}' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Delete' sorovi qabul qilindi");
        logger.debug("'delete' method-i ishga tushdi");

        try {
            customerServiceDepartmentService.deleteById(id);
            logger.info(id + "-id-li mijoz malumotlari 'CustomerService' jadvalidan ochirildi");
            return ResponseEntity.ok("Obyekt o'chirildi!");
        } catch (Exception e) {
            logger.error(id + "-id-li mijoz malumotlari 'CustomerService' jadvalidan ochirilmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
