package com.company.repository;

import com.company.entity.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerStatisticsRepository extends JpaRepository<CustomerService, Long> {

}
