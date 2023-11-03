package com.financetracker.Repository.ExpenseRepository;


import com.financetracker.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {

    List<Expense> findByExpenseDateBetween(LocalDate oneWeekAgo, LocalDate today);


    @Query("SELECT e.expenseCategory, COUNT(e) FROM Expense e GROUP BY e.expenseCategory")
    List<Object[]> getCategoryCounts();

    Expense findTopByExpenseDateBetweenOrderByExpensePriceDesc(LocalDate startDateCurrentWeek, LocalDate endDateCurrentWeek);

    Optional<Expense> findAllByExpenseDateBetween(LocalDate startDate, LocalDate endDate);
}
