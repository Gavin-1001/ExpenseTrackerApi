package com.financetracker.Repository.ExpenseRepository;


import com.financetracker.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {

    List<Expense> findByExpenseDateBetween(LocalDate oneWeekAgo, LocalDate today);

    }
