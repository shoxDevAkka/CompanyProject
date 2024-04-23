package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.service.SalesDepartmentStatisticsService;
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

    public SalesDepartmentStatisticsResource(SalesDepartmentStatisticsService salesDepartmentStatisticsService) {
        this.salesDepartmentStatisticsService = salesDepartmentStatisticsService;
    }

    @GetMapping("/sales-statistics/expense-type")
    public ResponseEntity getTypeOfAdByExpense(){
        String result = salesDepartmentStatisticsService.findTypeOfAdByExpense();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales-statistics/sales-registers")
    public ResponseEntity getTopSaleRegister(){
        List<EmployeeManagement> result = salesDepartmentStatisticsService.findTopSaleRegister();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales-statistics/sales-month-start")
    public ResponseEntity getNewSalesInMonth(@RequestParam Long year,
                                             @RequestParam Long month,
                                             @RequestParam Long day){
        if (!(year>0 && (month>0 && month <=12) && (day > 0 && day <32))){
            return ResponseEntity.badRequest().build();
        }

        String result = salesDepartmentStatisticsService.findNewSalesInMonth(year, month, day);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales-statistics/sales-month-end")
    public ResponseEntity getFinishedSalesInMonth(@RequestParam Long year,
                                                  @RequestParam Long month,
                                                  @RequestParam Long day){
        if (!(year>0 && (month>0 && month <=12) && (day > 0 && day <32))){
            return ResponseEntity.badRequest().build();
        }

        String result = salesDepartmentStatisticsService.findFinishedSalesInMonth(year, month, day);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/sales-statistics/each-type-expense")
    public ResponseEntity getEachTypeExpense(){
        Map<String, Long> result = salesDepartmentStatisticsService.findEachTypeExpense();
        return ResponseEntity.ok(result);
    }

}
