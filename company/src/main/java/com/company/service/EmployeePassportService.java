package com.company.service;

import com.company.entity.EmployeePassport;
import com.company.repository.EmployeePassportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeePassportService {

    private final EmployeePassportRepository employeePassportRepository;

    public EmployeePassportService(EmployeePassportRepository employeePassportRepository) {
        this.employeePassportRepository = employeePassportRepository;
    }

    public EmployeePassport save(EmployeePassport employeePassport) {
        return employeePassportRepository.save(employeePassport);
    }

    public List<EmployeePassport> findAll() {
        return employeePassportRepository.findAll();
    }

    public void deleteById(Long id) {
        employeePassportRepository.deleteById(id);
    }
}
