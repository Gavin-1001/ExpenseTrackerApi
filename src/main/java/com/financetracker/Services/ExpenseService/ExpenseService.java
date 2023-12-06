package com.financetracker.Services.ExpenseService;

import com.financetracker.Entity.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExpenseService {

    List<Expense> getAll();


    Expense createExpense(Expense expense);

    Expense updateExpense(String id, Expense expense);

    void deleteExpenseById(String id);


    Map<Integer, List<Expense>> getExpensesSortedByWeek();

    Map<LocalDate, List<Expense>> groupExpensesByDate(int numOfDates);

    Map<Integer, List<Expense>> groupExpensesByMonth(int month);

    long getPurchasesInWeek();

    Map<LocalDateTime, Long> groupExpensesByWeek();

    long countExpensesInPreviousWeek();


    long countExpenseInPreviousMonth();


    Map<String, Long> getCategoryCounts();



    long getTotalAmountOfPurchasesForCurrentWeek();


    Optional<Expense> getTopPurchaseForCurrentWeek();

    String getHighestCategorySumForPreviousWeek();
}

