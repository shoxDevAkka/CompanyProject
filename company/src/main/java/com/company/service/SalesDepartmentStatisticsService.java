package com.company.service;

import com.company.entity.EmployeeManagement;
import com.company.entity.SalesDepartment;
import com.company.repository.EmployeeStatisticsRepository;
import com.company.repository.SalesDepartmentStatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalesDepartmentStatisticsService {

    private final SalesDepartmentStatisticsRepository salesDepartmentStatisticsRepository;

    private final EmployeeStatisticsRepository employeeStatisticsRepository;

    public SalesDepartmentStatisticsService(SalesDepartmentStatisticsRepository salesDepartmentStatisticsRepository, EmployeeStatisticsRepository employeeStatisticsRepository) {
        this.salesDepartmentStatisticsRepository = salesDepartmentStatisticsRepository;
        this.employeeStatisticsRepository = employeeStatisticsRepository;
    }

    public String findTypeOfAdByExpense() {
        List<SalesDepartment> container = salesDepartmentStatisticsRepository.findAll();

        Set<String> adType = new HashSet<>();

        for (SalesDepartment sale : container){
            adType.add(sale.getAdvertType());
        }

        List<Long> totalExpense = new ArrayList<>();
        List<String> adTypeList = new ArrayList<>(adType);

        for (String adTypeOne : adTypeList){
            totalExpense.add(expenseTotalByAdType(adTypeOne));
        }

        long max = 0;
        int indexMax = 0;

        for (int i = 0; i < totalExpense.size(); i++) {
            if(max < totalExpense.get(i)){
                max = totalExpense.get(i);
                indexMax = i;
            }
        }

        return adTypeList.get(indexMax) + " reklama turiga eng ko'p xarajat sarflangan : " + max;
    }

    public List<EmployeeManagement> findTopSaleRegister() {
        List<EmployeeManagement> employees = employeeStatisticsRepository.findEmployeesBySalesDepartment();

        TreeMap<Integer, Long> container = sortByRegistrationsBySalesDepartment(employees);

        Map.Entry entry = container.lastEntry();
        Integer count = (Integer) entry.getKey();

        List<Long> identicalRegistrations = getIdenticalRegistrationsBySalesDepartment(employees, count);

        List<EmployeeManagement> result = new ArrayList<>();
        for (Long id : identicalRegistrations){
            result.add(employeeStatisticsRepository.findEmployeeById(id));
        }

        return result;
    }

    public String findNewSalesInMonth(Long year, Long month, Long day) {

        Long lastMonth = month - 1;

        List<SalesDepartment> container = salesDepartmentStatisticsRepository.findAll();

        long count = 0;

        for (SalesDepartment sale : container){
            if ((Date.from(sale.getAdvertStart()).getYear() + 1900) == year &&
                    (((Date.from(sale.getAdvertStart()).getMonth() + 1) == lastMonth && (Date.from(sale.getAdvertStart()).getDate()) >= day) ||
                            ((Date.from(sale.getAdvertStart()).getMonth() + 1) == month && (Date.from(sale.getAdvertStart()).getDate()) <= day))){
                ++count;
            }
        }

        return "So'ngi 1 oy ichida yo'lga qo'yilgan reklamalar soni : " + count;
    }

    public String findFinishedSalesInMonth(Long year, Long month, Long day) {

        Long lastMonth = month - 1;

        List<SalesDepartment> container = salesDepartmentStatisticsRepository.findAll();

        long count = 0;

        long milliSeconds = 0;

        for (SalesDepartment sale : container){
            Date start = Date.from(sale.getAdvertStart());
            milliSeconds = sale.getAdvertDeadlineDays() * 86400000;
            Date saleEndDate = new Date(start.getTime() + milliSeconds);

            if ((saleEndDate.getYear() + 1900) == year &&
                    (((saleEndDate.getMonth() + 1) == lastMonth && (saleEndDate.getDate()) >= day) ||
                            ((saleEndDate.getMonth() + 1) == month && (saleEndDate.getDate()) <= day))){
                ++count;
            }
        }

        return "So'ngi 1 oy ichida to'xtagan reklamalar soni : " + count;
    }

    public Map<String, Long> findEachTypeExpense() {

        List<SalesDepartment> sales = salesDepartmentStatisticsRepository.findAll();

        Map<String, Long> container = new HashMap<>();

        for (SalesDepartment sale : sales){
            container.put(sale.getAdvertType(), null);
        }

        for (Map.Entry<String, Long> entry : container.entrySet()){
            container.put(entry.getKey(), expenseTotalByAdType(entry.getKey()));
        }

        return container;

    }

    private long expenseTotalByAdType(String type){
        List<SalesDepartment> container = salesDepartmentStatisticsRepository.findSalesByAdType(type);

        long sum = 0;

        for (SalesDepartment sale : container){
            sum += sale.getExpense();
        }

        return sum;
    }

    private List<Long> getIdenticalRegistrationsBySalesDepartment(List<EmployeeManagement> employees, Integer count) {
        List<Long> ids = new ArrayList<>();

        for (EmployeeManagement employee : employees){
            if (employee.getSalesDepartments().size() == count){
                ids.add(employee.getId());
            }
        }

        return ids;
    }

    private TreeMap<Integer, Long> sortByRegistrationsBySalesDepartment(List<EmployeeManagement> employees) {

        TreeMap<Integer, Long> container = new TreeMap<>();

        for (EmployeeManagement employee : employees){
            Integer size = employee.getSalesDepartments().size();
            Long id = employee.getId();
            container.put(size, id);
        }

        return container;
    }
}
