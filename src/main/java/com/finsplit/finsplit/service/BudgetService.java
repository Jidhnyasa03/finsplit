package com.finsplit.finsplit.service;

import com.finsplit.finsplit.model.Budget;
import com.finsplit.finsplit.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget saveOrUpdateBudget(Budget budget) {
        Optional<Budget> existing = budgetRepository.findByUserAndMonthAndYear(
                budget.getUser(), budget.getMonth(), budget.getYear());

        if (existing.isPresent()) {
            Budget existingBudget = existing.get();
            existingBudget.setAmount(budget.getAmount());
            return budgetRepository.save(existingBudget);
        } else {
            return budgetRepository.save(budget);
        }
    }

    public Optional<Budget> getCurrentBudgetForUser(String user) {
        LocalDate today = LocalDate.now();
        return budgetRepository.findByUserAndMonthAndYear(
                user, today.getMonthValue(), today.getYear());
    }
}
