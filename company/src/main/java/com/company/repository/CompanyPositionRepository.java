package com.company.repository;

import com.company.entity.CompanyPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPositionRepository extends JpaRepository<CompanyPosition, Long> {

    void deleteByCompanyPosition(String name);

}
