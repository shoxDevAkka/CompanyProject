package com.company.web.rest;

import com.company.entity.EmployeeManagement;
import com.company.service.CustomerStatisticsService;
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

    public CustomerStatisticsResource(CustomerStatisticsService customerStatisticsService) {
        this.customerStatisticsService = customerStatisticsService;
    }

    @GetMapping("/customer-statistics/customers")
    public ResponseEntity getCustomersCountDay(@RequestParam Long year,
                                               @RequestParam Long month,
                                               @RequestParam Long day){
        String result = customerStatisticsService.findCustomersCountDay(year, month, day);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customer-statistics/employee-registers")
    public ResponseEntity getTopRegistersByEmployees(){
        List<EmployeeManagement> result = customerStatisticsService.findTopRegisterByEmployees();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customer-statistics/employee-registers/top-three")
    public ResponseEntity getTopThreeRegistersByEmployees(){
        List<EmployeeManagement> result = customerStatisticsService.findTopThreeRegistersByEmployees();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customer-statistics/employee-registers/customers-month")
    public ResponseEntity getCustomersInMonth(@RequestParam Long year,
                                              @RequestParam Long month,
                                              @RequestParam Long day){
        if (!(year>0 && (month>0 && month <=12) && (day > 0 && day <32))){
            return ResponseEntity.badRequest().build();
        }

        String result = customerStatisticsService.findCustomersInMonth(year, month, day);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customer-statistics/employee-registers/customers-top-day")
    public ResponseEntity getTopDayRegistrationsInDay(@RequestParam Long year,
                                                      @RequestParam Long month,
                                                      @RequestParam Long day){
        if (!(year>0 && (month>0 && month <=12) && (day > 0 && day <32))){
            return ResponseEntity.badRequest().build();
        }

        String result = customerStatisticsService.findTopDayRegistrationsInDay(year,month,day);
        return ResponseEntity.ok(result);
    }

}
