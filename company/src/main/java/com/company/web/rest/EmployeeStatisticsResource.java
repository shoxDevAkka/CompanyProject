package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.service.EmployeeStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    private final Logger logger = LoggerFactory.getLogger(EmployeeStatisticsResource.class);

    public EmployeeStatisticsResource(EmployeeStatisticsService employeeStatisticsService) {
        this.employeeStatisticsService = employeeStatisticsService;
    }

    @GetMapping("/employee-statistics/count")
    public ResponseEntity getStatisticsEmployeesByDepartment(@RequestParam String departmentName){

        logger.debug("'/employee-statistics/count' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getStatisticsEmployeesByDepartment' method-i ishga tushdi");

        try {
            String result = employeeStatisticsService.findStatisticsEmployeesByDepartment(departmentName);
            logger.info(departmentName + " bo'limidagi xodimlar soni va umumiy xodimalrning nechi foizini tashkil qiluvchi natija 'EmployeeManagement' jadavalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee-statistics/countAllDepartments")
    public ResponseEntity getStatisticsEmployeesByAllDepartment(){

        logger.debug("'/employee-statistics/countAllDepartments' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getStatisticsEmployeesByAllDepartment' method-i ishga tushdi");

        try {
            String result = employeeStatisticsService.findStatisticsEmployeesByAllDepartment();
            logger.info("Barcha bo'limdagi xodimlar soni va umumiy xodimlarning nechi foizini tashkil qiluvchi natijalar 'EmployeeManagement' jadavalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Barcha bo'limdagi xodimlar soni va umumiy xodimlarning nechi foizini tashkil qiluvchi natijalar 'EmployeeManagement' jadavalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee-statistics/age")
    public ResponseEntity getStatisticsEmployeesByAge(@RequestParam Long age){

        logger.debug("'/employee-statistics/age' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getStatisticsEmployeesByAge' method-i ishga tushdi");

        try {
            List<EmployeeManagement> result = employeeStatisticsService.findStatisticsEmployeesByAge(age);
            logger.info(age + "-yosh bo'yicha xodimlar natijalari 'EmployeeManagement' jadavalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(age + "-yosh bo'yicha xodimlar natijalari 'EmployeeManagement' jadavalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee-statistics/paging")
    public ResponseEntity getEmployeesByPaging(Pageable pageable){

        logger.debug("'/employee-statistics/paging' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getEmployeesByPaging' method-i ishga tushdi");

        try {
            Page<EmployeeManagement> result = employeeStatisticsService.findEmployeesByPaging(pageable);
            logger.info("Barcha xodimlar malumotlari 'Pagination' sifatida 'EmployeeManagement' jadavalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Barcha xodimlar malumotlari 'Pagination' sifatida 'EmployeeManagement' jadavalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee-statistics/salary")
    public ResponseEntity getSumSalaryEmployees(){

        logger.debug("'/employee-statistics/paging' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getEmployeesByPaging' method-i ishga tushdi");

        try {
            Long result = employeeStatisticsService.findSumSalaryEmployees();
            logger.info("Barcha xodimlarning umumiy maoshlarining summasi 'EmployeeManagement' jadavalidan qabul qilindi");
            return ResponseEntity.ok("Ishchilar umumiy oyligi summasi : " + result);
        } catch (Exception e) {
            logger.error("Barcha xodimlarning umumiy maoshlarining summasi 'EmployeeManagement' jadavalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
