package com.finsplit.finsplit.model;

import jakarta.persistence.*;
import java.time.YearMonth;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "budget_user")
    private String user;

    private double amount;

    private int month; // 1 - 12
    private int year;

    public Budget() {
    }

    public Budget(String user, double amount, int month, int year) {
        this.user = user;
        this.amount = amount;
        this.month = month;
        this.year = year;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}
