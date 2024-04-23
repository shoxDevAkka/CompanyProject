package com.company.service;

import com.company.entity.CustomerService;
import com.company.entity.EmployeeManagement;
import com.company.repository.CustomerStatisticsRepository;
import com.company.repository.EmployeeStatisticsRepository;
import com.sun.source.tree.Tree;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerStatisticsService {

    private final CustomerStatisticsRepository customerStatisticsRepository;

    private final EmployeeStatisticsRepository employeeStatisticsRepository;

    public CustomerStatisticsService(CustomerStatisticsRepository customerStatisticsRepository, EmployeeStatisticsRepository employeeStatisticsRepository) {
        this.customerStatisticsRepository = customerStatisticsRepository;
        this.employeeStatisticsRepository = employeeStatisticsRepository;
    }

    public String findCustomersCountDay(Long year, Long month, Long day) {

        List<CustomerService> customers = customerStatisticsRepository.findAll();

        int count = 0;

        for (CustomerService customer : customers){
            if ((Date.from(customer.getTimeDate()).getYear() + 1900) == year && (Date.from(customer.getTimeDate()).getMonth() + 1) == month && Date.from(customer.getTimeDate()).getDate() == day){
                ++count;
            }
        }

        return year + "|" + month + "|" + day + " sanadagi yoki Kundagi royxatdan o'tgan mijozlar soni : " + count;
    }

    public List<EmployeeManagement> findTopRegisterByEmployees() {
        List<EmployeeManagement> employees =  employeeStatisticsRepository.findEmployeesByCustomerServiceDepartment();

        TreeMap<Integer, Long> container = sortByRegistrationsCustomerService(employees);

        Map.Entry entry = container.lastEntry();
        Integer count = (Integer) entry.getKey();
        List<Long> identicalRegistrations = getIdenticalRegistrationsCustomerService(employees, count);

        List<EmployeeManagement> result = new ArrayList<>();
        for (Long id : identicalRegistrations){
            result.add(employeeStatisticsRepository.findEmployeeById(id));
        }
        return result;

    }

    public List<EmployeeManagement> findTopThreeRegistersByEmployees() {
        List<EmployeeManagement> employees = employeeStatisticsRepository.findEmployeesByCustomerServiceDepartment();

        TreeMap<Integer, Long> container = sortByRegistrationsCustomerService(employees);

        List<EmployeeManagement> result = new ArrayList<>();

        outer :
        for(Integer entry : container.descendingKeySet()){
            List<Long> identicalRegistrations = getIdenticalRegistrationsCustomerService(employees, entry);
            for (Long id : identicalRegistrations){
                if (result.size() <= 3){
                    result.add(employeeStatisticsRepository.findEmployeeById(id));
                } else {
                    break outer;
                }
            }
        }

        return result;
    }

    public String findCustomersInMonth(Long year, Long month, Long day) {
        Long monthLast = month - 1;
        List<CustomerService> customers = customerStatisticsRepository.findAll();

        long count = 0;

        for (CustomerService customer : customers){
            if ((Date.from(customer.getTimeDate()).getYear() + 1900) == year && (((Date.from(customer.getTimeDate()).getMonth() + 1) == monthLast && (Date.from(customer.getTimeDate()).getDate()) >= day) || ((Date.from(customer.getTimeDate()).getMonth() + 1) == month && (Date.from(customer.getTimeDate()).getDate()) <= day))){
                ++count;
            }
        }

        return "So'ngi 1 oyda ro'yhatdan o'tgan mijozlar soni : " + count;
    }

    public String findTopDayRegistrationsInDay(Long year, Long month, Long day) {

        Long monthLast = month - 1;
        List<CustomerService> customers = customerStatisticsRepository.findAll();

        long Month = 0;
        long Day = 0;

        long count = 0;

        String dayMonth = "";

        List<Long> countList = new ArrayList<>();
        List<String> dayMonthList = new ArrayList<>();

        for (CustomerService customer : customers){
            if ((Date.from(customer.getTimeDate()).getYear() + 1900) == year && (((Date.from(customer.getTimeDate()).getMonth() + 1) == monthLast && (Date.from(customer.getTimeDate()).getDate()) >= day) || ((Date.from(customer.getTimeDate()).getMonth() + 1) == month && (Date.from(customer.getTimeDate()).getDate()) <= day))){
                if (Month == (Date.from(customer.getTimeDate()).getMonth() + 1) && Day == (Date.from(customer.getTimeDate()).getDate())){
                    ++count;
                    dayMonth = "Month : " + Month + "; Day : " + Day;
                } else {
                    if (count != 0){
                        countList.add(count);
                    }
                    if (dayMonth != ""){
                        dayMonthList.add(dayMonth);
                    }
                    dayMonth = "";
                    Month = (Date.from(customer.getTimeDate()).getMonth() + 1);
                    Day = (Date.from(customer.getTimeDate()).getDate());
                    dayMonth = "Month : " + Month + "; Day : " + Day;
                    count = 1;
                }
            }
        }

        long countMax = 0;
        long index = 0;

        for (int i = 0; i < countList.size(); i++) {
            if (countMax < countList.get(i)){
                countMax = countList.get(i);
                index = i;
            }
        }


        return "So'nggi 1 oyning shu chislosida :  'Year :  " + year + "; " + dayMonthList.get((int)index) + "' eng ko'p mijoz ro'yhatdan o'tkazilgan soni : " + countMax;

    }

    private TreeMap<Integer, Long> sortByRegistrationsCustomerService(List<EmployeeManagement> employees){
        TreeMap<Integer, Long> container = new TreeMap<>();

        for (EmployeeManagement employee : employees){
            Integer size = employee.getCustomerService().size();
            Long id = employee.getId();
            container.put(size,id);
        }

        return container;
    }

    private List<Long> getIdenticalRegistrationsCustomerService(List<EmployeeManagement> container, Integer count){
        List<Long> ids = new ArrayList<>();

        for (EmployeeManagement employee : container){
            if (employee.getCustomerService().size() == count){
                ids.add(employee.getId());
            }
        }

        return ids;
    }

}
