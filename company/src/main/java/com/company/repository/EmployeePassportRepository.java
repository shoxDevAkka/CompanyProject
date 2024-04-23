package com.company.repository;

import com.company.entity.EmployeePassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePassportRepository extends JpaRepository<EmployeePassport, Long> {
}
