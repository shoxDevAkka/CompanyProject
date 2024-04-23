package com.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Set;

@Entity
public class EmployeeManagement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String familyName;

    @NotNull
    private Long age;

    @OneToOne(optional = false)
    @JoinColumn(name = "employeePassport_id", unique = true, nullable = false)
    private EmployeePassport employeePassport;

    @NotNull
    @Column(name = "salary_in_$", nullable = false)
    private Long salary;

    private String address;

    @ManyToOne
    @JoinColumn(name = "Department", referencedColumnName = "departmentName")
    private CompanyDepartment companyDepartment;

    @ManyToOne
    @JoinColumn(name = "Position_name", nullable = false, referencedColumnName = "companyPosition")
    private CompanyPosition companyPosition;

    @OneToMany()
    @JoinTable(
            name = "customer_service_employee",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")}
    )
    private Set<CustomerService> customerService;

    @OneToMany
    @JoinTable(
            name = "sales_employee",
            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "sales_id", referencedColumnName = "id")}
    )
    private Set<SalesDepartment> salesDepartments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public EmployeePassport getEmployeePassport() {
        return employeePassport;
    }

    public void setEmployeePassport(EmployeePassport employeePassport) {
        this.employeePassport = employeePassport;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CompanyDepartment getCompanyDepartment() {
        return companyDepartment;
    }

    public void setCompanyDepartment(CompanyDepartment companyDepartment) {
        this.companyDepartment = companyDepartment;
    }

    public CompanyPosition getCompanyPosition() {
        return companyPosition;
    }

    public void setCompanyPosition(CompanyPosition companyPosition) {
        this.companyPosition = companyPosition;
    }

    public Set<CustomerService> getCustomerService() {
        return customerService;
    }

    public void setCustomerService(Set<CustomerService> customerService) {
        this.customerService = customerService;
    }

    public Set<SalesDepartment> getSalesDepartments() {
        return salesDepartments;
    }

    public void setSalesDepartments(Set<SalesDepartment> salesDepartments) {
        this.salesDepartments = salesDepartments;
    }
}
