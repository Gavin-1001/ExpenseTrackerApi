package com.financetracker.Services.ExpenseService;


import com.financetracker.Entity.Expense;
import com.financetracker.Repository.ExpenseRepository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense createExpense(Expense expense) {
        expense.setTimestampExpenseCreated(LocalDateTime.now());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(String id, Expense expense) {

        Expense updatedExpense = expenseRepository.findById(id).get();
        //expense name
        if (Objects.nonNull(expense.getExpenseTitle()) && !"".equalsIgnoreCase(expense.getExpenseTitle())) {
            updatedExpense.setExpenseTitle(expense.getExpenseTitle());
        }

        //expense description
        if (Objects.nonNull(expense.getExpenseDescription()) && !"".equalsIgnoreCase(expense.getExpenseDescription())) {
            updatedExpense.setExpenseDescription(expense.getExpenseDescription());
        }

        //expense price
        updatedExpense.setExpensePrice(expense.getExpensePrice());

        //expense date
        if (Objects.nonNull(expense.getExpenseDate())) {
            updatedExpense.setExpenseDate(expense.getExpenseDate());
        }


        return expenseRepository.save(updatedExpense);
    }

    @Override
    public void deleteExpenseById(String id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Map<Integer, List<Expense>> getExpensesSortedByWeek() {
        List<Expense> allExpenses = expenseRepository.findAll();
        Map<Integer, List<Expense>> expensesByWeek = new TreeMap<>();

        for (Expense expense : allExpenses) {
            LocalDate expenseDate = expense.getExpenseDate();
            int weekNumber = expenseDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

            if (!expensesByWeek.containsKey(weekNumber)) {
                expensesByWeek.put(weekNumber, new ArrayList<>());
            }
            expensesByWeek.get(weekNumber).add(expense);
        }

        return expensesByWeek;
    }

    @Override
    public Map<LocalDate, List<Expense>> groupExpensesByDate(int numOfDates) {
        List<Expense> allExpenses = expenseRepository.findAll();
        Map<LocalDate, List<Expense>> expensesByDate = new LinkedHashMap<>();

        for (Expense expense : allExpenses) {
            LocalDate expenseDate = expense.getExpenseDate();

            if (!expensesByDate.containsKey(expenseDate)) {
                expensesByDate.put(expenseDate, new ArrayList<>());
            }
            expensesByDate.get(expenseDate).add(expense);
        }

        // Limit the result to the specified number of dates
        Map<LocalDate, List<Expense>> limitedResult = new LinkedHashMap<>();
        int count = 0;

        for (Map.Entry<LocalDate, List<Expense>> entry : expensesByDate.entrySet()) {
            if (count >= numOfDates) {
                break;
            }
            limitedResult.put(entry.getKey(), entry.getValue());
            count++;
        }

        return limitedResult;
    }



    public Map<Integer, List<Expense>> groupExpensesByMonth(int month) {
        List<Expense> allExpenses = expenseRepository.findAll();
        Map<Integer, List<Expense>> expensesByMonth = new LinkedHashMap<>();

        for (Expense expense : allExpenses) {
            LocalDate expenseDate = expense.getExpenseDate();
            if (expenseDate.getMonthValue() == month) {
                int year = expenseDate.getYear();
                int monthYearKey = year * 100 + month; // Combine year and month into a single key
                expensesByMonth
                        .computeIfAbsent(monthYearKey, k -> new ArrayList<>())
                        .add(expense);
            }
        }

        return expensesByMonth;
    }

    //getting the wrong date, gets the date of when the timestamp was added not when the expense was
    // made, see POSTMAN
}

