package com.company.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_info_id", unique = true, referencedColumnName = "id")
    private EmployeeManagement employeeManagement;

    @ManyToMany
    @JoinTable(
            name = "User_Authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
    )
    private Set<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeManagement getEmployeeManagement() {
        return employeeManagement;
    }

    public void setEmployeeManagement(EmployeeManagement employeeManagement) {
        this.employeeManagement = employeeManagement;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
