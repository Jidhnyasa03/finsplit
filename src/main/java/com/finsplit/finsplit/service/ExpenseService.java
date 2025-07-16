package com.finsplit.finsplit.service;

import com.finsplit.finsplit.model.Expense;
import com.finsplit.finsplit.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;


import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<Expense> getExpensesByUser(String paidBy) {
        return expenseRepository.findByPaidBy(paidBy);
    }

    public Map<String, Double> getMonthlySummary() {
        List<Object[]> summaryList = expenseRepository.getMonthlySummaryByCategory();
        Map<String, Double> summary = new HashMap<>();
        for (Object[] row : summaryList) {
            summary.put(row[0].toString(), (Double) row[1]);
        }
        return summary;
    }


    public Map<String, Double> calculateSplit() {
        List<Expense> allExpenses = expenseRepository.findAll();
        Map<String, Double> paidMap = new HashMap<>();

        double total = 0.0;
        for (Expense e : allExpenses) {
            paidMap.put(e.getPaidBy(), paidMap.getOrDefault(e.getPaidBy(), 0.0) + e.getAmount());
            total += e.getAmount();
        }

        int userCount = paidMap.size();
        double share = total / userCount;

        Map<String, Double> balanceMap = new HashMap<>();
        for (Map.Entry<String, Double> entry : paidMap.entrySet()) {
            balanceMap.put(entry.getKey(), Math.round((entry.getValue() - share) * 100.0) / 100.0);
        }

        return balanceMap;
    }


}
