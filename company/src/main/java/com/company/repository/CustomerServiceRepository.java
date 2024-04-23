package com.company.repository;

import com.company.entity.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {

    @Query("select e from CustomerService e where e.firstName = :firstName or e.lastName = :lastName")
    List<CustomerService> findAllByParam(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
