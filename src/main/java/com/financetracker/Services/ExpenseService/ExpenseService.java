package com.financetracker.Services.ExpenseService;

import com.financetracker.Entity.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ExpenseService{

    List<Expense> getAll();


    Expense createExpense(Expense expense);

    Expense updateExpense(String id, Expense expense);

    void deleteExpenseById(String id);


    Map<Integer, List<Expense>> getExpensesSortedByWeek();

    Map<LocalDate, List<Expense>> groupExpensesByDate(int numOfDates);

    Map<Integer, List<Expense>> groupExpensesByMonth(int month);
}

