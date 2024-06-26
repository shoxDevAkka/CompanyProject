package com.company.repository;

import com.company.entity.SalesDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesDepartmentStatisticsRepository extends JpaRepository<SalesDepartment, Long> {

    @Query("select e from SalesDepartment e where e.advertType = :type")
    List<SalesDepartment> findSalesByAdType(@Param("type") String type);

}
