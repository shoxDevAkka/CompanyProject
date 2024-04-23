package com.company.service;

import com.company.entity.EmployeeManagement;
import com.company.entity.SalesDepartment;
import com.company.repository.SalesDepartmentEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesDepartmentEmployeeService {

    private final SalesDepartmentEmployeeRepository salesDepartmentEmployeeRepository;

    public SalesDepartmentEmployeeService(SalesDepartmentEmployeeRepository salesDepartmentEmployeeRepository) {
        this.salesDepartmentEmployeeRepository = salesDepartmentEmployeeRepository;
    }

    public EmployeeManagement save(EmployeeManagement employeeManagement) {
        return salesDepartmentEmployeeRepository.save(employeeManagement);
    }

    public List<SalesDepartment> findEmployeeAdverts(Long id) {
        Optional<EmployeeManagement> employee = salesDepartmentEmployeeRepository.findById(id);
        if (employee.isPresent()){
            EmployeeManagement employeeData = employee.get();
            return new ArrayList<>(employeeData.getSalesDepartments());
        } else {
            return null;
        }
    }
}
