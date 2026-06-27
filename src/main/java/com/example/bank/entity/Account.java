package com.example.bank.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne
    @JsonBackReference
    private BankUser bankUser;

    // getters / setters

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setBankUser(BankUser bankUser) {
        this.bankUser = bankUser;
    }

}