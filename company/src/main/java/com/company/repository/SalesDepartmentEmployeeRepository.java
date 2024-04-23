package com.company.repository;

import com.company.entity.EmployeeManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDepartmentEmployeeRepository extends JpaRepository<EmployeeManagement, Long> {
}
