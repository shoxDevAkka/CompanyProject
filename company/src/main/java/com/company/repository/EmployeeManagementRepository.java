package com.company.repository;

import com.company.entity.EmployeeManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeManagementRepository extends JpaRepository<EmployeeManagement, Long> {

    @Query("select e from EmployeeManagement e where e.firstName = :firstName or e.lastName = :lastName or e.familyName = :familyName")
    List<EmployeeManagement> findAllByParam(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("familyName") String familyName);

}
