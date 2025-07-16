package com.finsplit.finsplit.controller;

import com.finsplit.finsplit.model.Expense;
import com.finsplit.finsplit.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;


import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // Add new expense
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    // Get all expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // Get expenses by user
    @GetMapping("/user/{paidBy}")
    public List<Expense> getExpensesByUser(@PathVariable String paidBy) {
        return expenseService.getExpensesByUser(paidBy);
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Double>> getMonthlySummary() {
        return ResponseEntity.ok(expenseService.getMonthlySummary());
    }

    @GetMapping("/split")
    public ResponseEntity<Map<String, Double>> getSplitResult() {
        return ResponseEntity.ok(expenseService.calculateSplit());
    }


}
