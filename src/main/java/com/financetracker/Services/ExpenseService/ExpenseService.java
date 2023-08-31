package com.financetracker.Services.ExpenseService;

import com.financetracker.Entity.Expense;

import java.util.List;

public interface ExpenseService{

    List<Expense> getAll();


    Expense createExpense(Expense expense);

    Expense updateExpense(String id, Expense expense);

    void deleteExpenseById(String id);
}
