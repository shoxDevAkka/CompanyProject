package com.company.service;

import com.company.entity.CompanyDepartment;
import com.company.repository.CompanyDepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDepartmentService {

    private final CompanyDepartmentRepository companyDepartmentRepository;

    public CompanyDepartmentService(CompanyDepartmentRepository companyDepartmentRepository) {
        this.companyDepartmentRepository = companyDepartmentRepository;
    }

    public CompanyDepartment save(CompanyDepartment companyDepartment) {
        return companyDepartmentRepository.save(companyDepartment);
    }

    public List<CompanyDepartment> findAll() {
        return companyDepartmentRepository.findAll();
    }

    public void deleteById(Long id) {
        companyDepartmentRepository.deleteById(id);
    }
}
