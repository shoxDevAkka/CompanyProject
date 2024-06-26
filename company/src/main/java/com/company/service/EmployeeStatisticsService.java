package com.company.service;

import com.company.entity.CompanyDepartment;
import com.company.entity.EmployeeManagement;
import com.company.repository.CompanyDepartmentRepository;
import com.company.repository.EmployeeStatisticsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeStatisticsService {

    private final EmployeeStatisticsRepository employeeStatisticsRepository;

    private final CompanyDepartmentRepository companyDepartmentRepository;

    public EmployeeStatisticsService(EmployeeStatisticsRepository employeeStatisticsRepository, CompanyDepartmentRepository companyDepartmentRepository) {
        this.employeeStatisticsRepository = employeeStatisticsRepository;
        this.companyDepartmentRepository = companyDepartmentRepository;
    }


    public String findStatisticsEmployeesByDepartment(String departmentName) {
        Long countDepartment = employeeStatisticsRepository.countAllByCompanyDepartment(departmentName);
        Long countCompany = employeeStatisticsRepository.countAllByCompany();

        Double percentage = (double)countDepartment/countCompany *100;

        return departmentName + " bo'limda ishchilar soni : " + countDepartment + "\nBu bo'limning ishchilari soni umumiy kompaniya ishchilar sonining ulushi : " + percentage + " %";
    }

    public String findStatisticsEmployeesByAllDepartment() {

        List<CompanyDepartment> container = companyDepartmentRepository.findAll();
        String result = "";
        for (CompanyDepartment department : container){
            result += findStatisticsEmployeesByDepartment(department.getDepartmentName()) + "\n";
        }
        return result;
    }

    public List<EmployeeManagement> findStatisticsEmployeesByAge(Long age) {
        return employeeStatisticsRepository.findAllByAge(age);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeManagement> findEmployeesByPaging(Pageable pageable) {
        return employeeStatisticsRepository.findAll(pageable);
    }

    public Long findSumSalaryEmployees() {
        return employeeStatisticsRepository.findSumSalaryEmployees();
    }
}
