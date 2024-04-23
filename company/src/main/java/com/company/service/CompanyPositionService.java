package com.company.service;

import com.company.entity.CompanyPosition;
import com.company.repository.CompanyPositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyPositionService {

    private final CompanyPositionRepository companyPositionRepository;

    public CompanyPositionService(CompanyPositionRepository companyPositionRepository) {
        this.companyPositionRepository = companyPositionRepository;
    }

    public CompanyPosition save(CompanyPosition companyPosition) {
        return companyPositionRepository.save(companyPosition);
    }

    public List<CompanyPosition> findAll() {
        return companyPositionRepository.findAll();
    }

    public void deleteByName(String name) {
        companyPositionRepository.deleteByCompanyPosition(name);
    }
}
