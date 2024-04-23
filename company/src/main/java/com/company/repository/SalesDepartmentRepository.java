package com.company.repository;

import com.company.entity.SalesDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDepartmentRepository extends JpaRepository<SalesDepartment, Long> {



}
