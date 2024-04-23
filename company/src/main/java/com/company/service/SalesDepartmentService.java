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

    public SalesDepartment saveAsPatch(SalesDepartment salesDepartment){
        Optional<SalesDepartment> sale = salesDepartmentRepository.findById(salesDepartment.getId());

        if (sale.isPresent()){
            SalesDepartment sale1 = sale.get();
            if (salesDepartment.getAdvertType() != null){
                sale1.setAdvertType(salesDepartment.getAdvertType());
            }

            if (salesDepartment.getAdvertDeadlineDays() != null){
                sale1.setAdvertDeadlineDays(salesDepartment.getAdvertDeadlineDays());
            }

            if (salesDepartment.getExpense() != null){
                sale1.setExpense(salesDepartment.getExpense());
            }

            return salesDepartmentRepository.save(sale1);
        }

        return null;
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
