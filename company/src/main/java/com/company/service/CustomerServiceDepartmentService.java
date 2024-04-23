package com.company.service;

import com.company.entity.CustomerService;
import com.company.repository.CustomerServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceDepartmentService {

    private final CustomerServiceRepository customerServiceRepository;

    public CustomerServiceDepartmentService(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }

    public CustomerService save(CustomerService customerService) {
        return customerServiceRepository.save(customerService);
    }

    public CustomerService findById(Long id) {
        Optional<CustomerService> result = customerServiceRepository.findById(id);
        return result.orElse(null);
    }

    public List<CustomerService> findAll() {
        return customerServiceRepository.findAll();
    }

    public List<CustomerService> findAllByParam(String firstName, String lastName) {
        return customerServiceRepository.findAllByParam(firstName, lastName);
    }

    public void deleteById(Long id) {
        customerServiceRepository.deleteById(id);
    }
}
