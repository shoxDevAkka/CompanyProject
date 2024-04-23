package com.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

@Entity
public class SalesDepartment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Advert_Type")
    private String advertType;

    @Column(name = "Expense_$")
    private Long expense;

    @Column(name = "Advert_Deadline_in_Days")
    private Long advertDeadlineDays;

    @Column(name = "Advert_Start")
    private Instant advertStart = Instant.now();

    @NotNull
    private String responsiblePerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvertType() {
        return advertType;
    }

    public void setAdvertType(String advertType) {
        this.advertType = advertType;
    }

    public Long getExpense() {
        return expense;
    }

    public void setExpense(Long expense) {
        this.expense = expense;
    }

    public Long getAdvertDeadlineDays() {
        return advertDeadlineDays;
    }

    public void setAdvertDeadlineDays(Long advertDeadlineDays) {
        this.advertDeadlineDays = advertDeadlineDays;
    }

    public Instant getAdvertStart() {
        return advertStart;
    }

    public void setAdvertStart(Instant advertStart) {
        this.advertStart = advertStart;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }
}
