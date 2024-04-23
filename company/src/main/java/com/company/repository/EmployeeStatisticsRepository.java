package com.company.repository;

import com.company.entity.EmployeeManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeStatisticsRepository extends JpaRepository<EmployeeManagement, Long> {

    @Query("select count(e) from EmployeeManagement e")
    Long countAllByCompany();

    @Query("select count(e) from EmployeeManagement e where e.companyDepartment.departmentName = :departmentName")
    Long countAllByCompanyDepartment(@Param("departmentName") String departmentName);

    List<EmployeeManagement> findAllByAge(Long age);

    @Query("select sum(e.salary) from EmployeeManagement e")
    Long findSumSalaryEmployees();

//    =======================================================================

    @Query("select e from EmployeeManagement e where e.companyDepartment.departmentName = 'Customer Service'")
    List<EmployeeManagement> findEmployeesByCustomerServiceDepartment();

    @Query("select e from EmployeeManagement  e where e.id = :id")
    EmployeeManagement findEmployeeById(@Param("id") Long id);

    @Query("select e from EmployeeManagement e where e.companyDepartment.departmentName = 'Sales'")
    List<EmployeeManagement> findEmployeesBySalesDepartment();
}
