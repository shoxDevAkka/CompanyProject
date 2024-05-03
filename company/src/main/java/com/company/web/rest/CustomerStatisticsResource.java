package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.service.CustomerStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerStatisticsResource {

    private final CustomerStatisticsService customerStatisticsService;

    private final Logger logger = LoggerFactory.getLogger(CustomerStatisticsResource.class);

    public CustomerStatisticsResource(CustomerStatisticsService customerStatisticsService) {
        this.customerStatisticsService = customerStatisticsService;
    }

    @GetMapping("/customer-statistics/customers")
    public ResponseEntity getCustomersCountDay(@RequestParam Long year,
                                               @RequestParam Long month,
                                               @RequestParam Long day){

        logger.debug("'/customer-statistics/customers' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getCustomersCountDay' method-i ishga tushdi");


        try {
            String result = customerStatisticsService.findCustomersCountDay(year, month, day);
            logger.info(year + "/" + month + "/" + day + " kundagi royhatdan o'tgan mijozlar soni 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(year + "/" + month + "/" + day + " kundagi royhatdan o'tgan mijozlar soni 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer-statistics/employee-registers")
    public ResponseEntity getTopRegistersByEmployees(){

        logger.debug("'/customer-statistics/employee-registers' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi  qabul qilindi");
        logger.debug("'getTopRegistersByEmployees' method-i ishga tushdi");

        try {
            List<EmployeeManagement> result = customerStatisticsService.findTopRegisterByEmployees();
            logger.info("Eng kop mijoz royhatdan o'tgazgan xodim(lar) List<EmployeeManagement> turida 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Eng kop mijoz royhatdan o'tgazgan xodim(lar) List<EmployeeManagement> turida 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer-statistics/employee-registers/top-three")
    public ResponseEntity getTopThreeRegistersByEmployees(){

        logger.debug("'/customer-statistics/employee-registers/top-three' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi  qabul qilindi");
        logger.debug("'getTopThreeRegistersByEmployees' method-i ishga tushdi");

        try {
            List<EmployeeManagement> result = customerStatisticsService.findTopThreeRegistersByEmployees();
            logger.info("Eng kop mijoz royhatdan o'tgazgan top 3-ta xodimlar List<EmployeeManagement> turida 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Eng kop mijoz royhatdan o'tgazgan top 3-ta xodimlar List<EmployeeManagement> turida 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer-statistics/employee-registers/customers-month")
    public ResponseEntity getCustomersInMonth(@RequestParam Long year,
                                              @RequestParam Long month,
                                              @RequestParam Long day){

        logger.debug("'/customer-statistics/employee-registers/customers-month' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi  qabul qilindi");
        logger.debug("'getCustomersInMonth' method-i ishga tushdi");

        try {
            if (!(year>0 && (month>0 && month <=12) && (day > 0 && day <32))){
                return ResponseEntity.badRequest().build();
            }

            String result = customerStatisticsService.findCustomersInMonth(year, month, day);
            logger.info(year + "/" + month + "/" + day + " sanadan boshlab so'nggi 1 oy davomida royhatdan otgan mijozlar soni 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(year + "/" + month + "/" + day + " sanadan boshlab so'nggi 1 oy davomida royhatdan otgan mijozlar soni 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer-statistics/employee-registers/customers-top-day")
    public ResponseEntity getTopDayRegistrationsInDay(@RequestParam Long year,
                                                      @RequestParam Long month,
                                                      @RequestParam Long day){

        logger.debug("'/customer-statistics/employee-registers/customers-top-day' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi  qabul qilindi");
        logger.debug("'getTopDayRegistrationsInDay' method-i ishga tushdi");

        try {
            if (!(year>0 && (month>0 && month <=12) && (day > 0 && day <32))){
                return ResponseEntity.badRequest().build();
            }

            String result = customerStatisticsService.findTopDayRegistrationsInDay(year,month,day);
            logger.info(year + "/" + month + "/" + day + " sanadan boshlab so'nggi 1 oy davomida eng kop mijoz royhatdan otgan kun 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(year + "/" + month + "/" + day + " sanadan boshlab so'nggi 1 oy davomida eng kop mijoz royhatdan otgan kun 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
