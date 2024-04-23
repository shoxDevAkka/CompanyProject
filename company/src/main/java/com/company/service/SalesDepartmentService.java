package com.company.service;

import com.company.entity.SalesDepartment;
import com.company.repository.SalesDepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SalesDepartmentService {

    private final SalesDepartmentRepository salesDepartmentRepository;

    public SalesDepartmentService(SalesDepartmentRepository salesDepartmentRepository) {
        this.salesDepartmentRepository = salesDepartmentRepository;
    }

    public SalesDepartment save(SalesDepartment salesDepartment) {
        return salesDepartmentRepository.save(salesDepartment);
    }

    public SalesDepartment findById(Long id) {
        Optional<SalesDepartment> result = salesDepartmentRepository.findById(id);
        return result.orElse(null);
    }

    public List<SalesDepartment> findAll() {
        return salesDepartmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<SalesDepartment> findSalesByPaging(Pageable pageable) {
        return salesDepartmentRepository.findAll(pageable);
    }

}
