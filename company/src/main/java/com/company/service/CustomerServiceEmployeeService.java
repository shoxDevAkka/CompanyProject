package com.company.service;

import com.company.entity.CustomerService;
import com.company.entity.EmployeeManagement;
import com.company.repository.CustomerServiceEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceEmployeeService {

    private final CustomerServiceEmployeeRepository customerServiceEmployeeRepository;

    public CustomerServiceEmployeeService(CustomerServiceEmployeeRepository customerServiceEmployeeRepository) {
        this.customerServiceEmployeeRepository = customerServiceEmployeeRepository;
    }


    public EmployeeManagement save(EmployeeManagement employeeManagement) {
        return customerServiceEmployeeRepository.save(employeeManagement);
    }

    public List<CustomerService> findAllCustomers(Long id) {
        Optional<EmployeeManagement> employee = customerServiceEmployeeRepository.findById(id);
        if (employee.isPresent()){
            EmployeeManagement employeeData = employee.get();
            return new ArrayList<>(employeeData.getCustomerService());
        } else {
            return null;
        }
    }
}
