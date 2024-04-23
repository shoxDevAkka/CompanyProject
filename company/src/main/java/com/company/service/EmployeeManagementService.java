package com.company.service;

import com.company.entity.EmployeeManagement;
import com.company.repository.EmployeeManagementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeManagementService {

    private final EmployeeManagementRepository employeeManagementRepository;

    public EmployeeManagementService(EmployeeManagementRepository employeeManagementRepository) {
        this.employeeManagementRepository = employeeManagementRepository;
    }


    public EmployeeManagement save(EmployeeManagement employeeManagement) {
        return employeeManagementRepository.save(employeeManagement);
    }


    public EmployeeManagement findById(Long id) {
        Optional<EmployeeManagement> employee = employeeManagementRepository.findById(id);
        return employee.orElse(null);
    }


    public List<EmployeeManagement> findAllByParam(String firstName, String lastName, String familyName) {
        return employeeManagementRepository.findAllByParam(firstName, lastName, familyName);
    }

    public List<EmployeeManagement> findAll() {
        return employeeManagementRepository.findAll();
    }

    public void deleteById(Long id) {
        employeeManagementRepository.deleteById(id);
    }
}
