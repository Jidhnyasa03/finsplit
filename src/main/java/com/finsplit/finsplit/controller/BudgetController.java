package com.finsplit.finsplit.controller;

import com.finsplit.finsplit.model.Budget;
import com.finsplit.finsplit.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    // POST: Add or update budget
    @PostMapping
    public ResponseEntity<Budget> addOrUpdateBudget(@RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.saveOrUpdateBudget(budget));
    }

    // GET: Current month's budget for a user
    @GetMapping("/{user}")
    public ResponseEntity<Budget> getBudgetForUser(@PathVariable String user) {
        Optional<Budget> budget = budgetService.getCurrentBudgetForUser(user);
        return budget.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
