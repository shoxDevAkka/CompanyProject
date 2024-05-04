package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.service.SalesDepartmentStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SalesDepartmentStatisticsResource {

    private final SalesDepartmentStatisticsService salesDepartmentStatisticsService;

    private final Logger logger = LoggerFactory.getLogger(SalesDepartmentStatisticsResource.class);

    public SalesDepartmentStatisticsResource(SalesDepartmentStatisticsService salesDepartmentStatisticsService) {
        this.salesDepartmentStatisticsService = salesDepartmentStatisticsService;
    }

    @GetMapping("/sales-statistics/expense-type")
    public ResponseEntity getTypeOfAdByExpense(){

        logger.debug("'/sales-statistics/expense-type' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi qabul qilindi");
        logger.debug("'getTypeOfAdByExpense' method-i ishga tushdi");

        try {
            String result = salesDepartmentStatisticsService.findTypeOfAdByExpense();
            logger.info("Eng kop reklama xarajatlari sarflangan reklama turi 'SalesDepartment' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Eng kop reklama xarajatlari sarflangan reklama turi 'SalesDepartment' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales-statistics/sales-registers")
    public ResponseEntity getTopSaleRegister(){

        logger.debug("'/sales-statistics/sales-registers' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi  qabul qilindi");
        logger.debug("'getTopSaleRegister' method-i ishga tushdi");

        try {
            List<EmployeeManagement> result = salesDepartmentStatisticsService.findTopSaleRegister();
            logger.info("Eng kop reklama xarajatlarini kiritgan xodim(lar) 'EmployeeManagement' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Eng kop reklama xarajatlarini kiritgan xodim(lar) 'EmployeeManagement' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales-statistics/sales-month-start")
    public ResponseEntity getNewSalesInMonth(@RequestParam Long year,
                                             @RequestParam Long month,
                                             @RequestParam Long day){

        logger.debug("'/sales-statistics/sales-month-start' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get' sorovi   qabul qilindi");
        logger.debug("'getNewSalesInMonth' method-i ishga tushdi");

        try {
            if (!(year>0 && (month>0 && month <=12) && (day > 0 && day <32))){
                return ResponseEntity.badRequest().build();
            }

            String result = salesDepartmentStatisticsService.findNewSalesInMonth(year, month, day);
            logger.info(year + "/" + month + "/" + day + " sanadan boshlab so'nggi 1 oy davomida yolga qoyilgan umumiy reklamalar/elonlar soni 'SalesDepartment' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(year + "/" + month + "/" + day + " sanadan boshlab so'nggi 1 oy davomida yolga qoyilgan umumiy reklamalar/elonlar soni 'SalesDepartment' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales-statistics/sales-month-end")
    public ResponseEntity getFinishedSalesInMonth(@RequestParam Long year,
                                                  @RequestParam Long month,
                                                  @RequestParam Long day){

        logger.debug("'/sales-statistics/sales-month-end' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get'  sorovi   qabul qilindi");
        logger.debug("'getFinishedSalesInMonth' method-i ishga tushdi");

        try {
            if (!(year>0 && (month>0 && month <=12) && (day > 0 && day <32))){
                return ResponseEntity.badRequest().build();
            }

            String result = salesDepartmentStatisticsService.findFinishedSalesInMonth(year, month, day);
            logger.info(year + "/" + month + "/" + day + " sanadan boshlab so'nggi 1 oy davomida toxtagan umumiy reklamalar/elonlar soni 'SalesDepartment' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error(year + "/" + month + "/" + day + " sanadan boshlab so'nggi 1 oy davomida toxtagan umumiy reklamalar/elonlar soni 'SalesDepartment' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sales-statistics/each-type-expense")
    public ResponseEntity getEachTypeExpense(){

        logger.debug("'/sales-statistics/each-type-expense' endpoint - dan so'rov qabul qilindi");
        logger.debug("'Get'  sorovi  qabul qilindi");
        logger.debug("'getEachTypeExpense' method-i ishga tushdi");

        try {
            Map<String, Long> result = salesDepartmentStatisticsService.findEachTypeExpense();
            logger.info("Har bir reklama turiga mos tushadigan umumiy xarajatlar 'SalesDepartment' jadvalidan qabul qilindi");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Har bir reklama turiga mos tushadigan umumiy xarajatlar 'SalesDepartment' jadvalidan qabul qilinmadi");
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
