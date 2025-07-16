package com.finsplit.finsplit.repository;

import com.finsplit.finsplit.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByPaidBy(String paidBy);

    @Query("SELECT e.category, SUM(e.amount) FROM Expense e WHERE MONTH(e.date) = MONTH(CURRENT_DATE) AND YEAR(e.date) = YEAR(CURRENT_DATE) GROUP BY e.category")
    List<Object[]> getMonthlySummaryByCategory();

}

