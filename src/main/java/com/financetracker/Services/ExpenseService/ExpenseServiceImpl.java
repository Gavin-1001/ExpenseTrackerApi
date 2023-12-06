package com.financetracker.Services.ExpenseService;


import com.financetracker.Entity.Expense;
import com.financetracker.Repository.ExpenseRepository.ExpenseRepository;
import com.financetracker.Utils.DateUtils;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

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

        if (Objects.nonNull(expense.getExpenseCategory())) {
            updatedExpense.setExpenseCategory(expense.getExpenseCategory());
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

    @Override
    public long getPurchasesInWeek() {
        LocalDate today = LocalDate.now();
        LocalDate oneWeekAgo = today.minus(2, ChronoUnit.DAYS);
        //List<Expense> expensesInWeek = expenseRepository.findByExpenseDateBetween(oneWeekAgo, today);
        //return expensesInWeek.size();
        return 0;
    }


    public Map<LocalDateTime, Long> groupExpensesByWeek() {
        List<Expense> allExpenses = expenseRepository.findAll();

        // Group expenses by week using Java Streams and count them
        Map<LocalDateTime, Long> expensesByWeek = allExpenses.stream()
                .collect(Collectors.groupingBy(expense -> {
                    LocalDateTime timestampExpenseCreated = expense.getExpenseDate().atStartOfDay();
                    return DateUtils.getStartOfWeek(timestampExpenseCreated);
                }, Collectors.counting()));

        return expensesByWeek;
    }

//    @Override
//    public Map<String, Long> getExpenseByCategory() {
//
//        List<Expense> allExpenses = expenseRepository.findAll();
//
//        Map<String, Long> expensesByCategory = allExpenses.stream()
//                .collect(Collectors.groupingBy(expense -> {
//                    String expensesCategory = expense.getExpenseCategory();
//                    return expensesCategory;
//                }, Collectors.counting()));
//        return expensesByCategory;
//    }

    public long countExpensesInPreviousWeek() {
        LocalDate currentDate = LocalDate.now();

        // Calculate the start and end dates of the previous week (assuming Monday as the start of the week)
        LocalDate endOfPreviousWeek = currentDate.with(java.time.temporal.TemporalAdjusters.previous(DayOfWeek.MONDAY));
        LocalDate startOfPreviousWeek = endOfPreviousWeek.minusDays(7);

        // Fetch expenses that fall within the previous week
        List<Expense> expensesInPreviousWeek = expenseRepository.findByExpenseDateBetween(startOfPreviousWeek, endOfPreviousWeek);

        return expensesInPreviousWeek.size();
    }


    @Override
    public long countExpenseInPreviousMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endOfPreviousMonth = currentDate.with(java.time.temporal.TemporalAdjusters.previous(DayOfWeek.MONDAY));
        LocalDate startOfPreviousMonth = endOfPreviousMonth.minusDays(30);

        List<Expense> expensesInPreviousMonth = expenseRepository.findByExpenseDateBetween(startOfPreviousMonth, endOfPreviousMonth);
        return expensesInPreviousMonth.size();
    }

    @Override
    public long getTotalAmountOfPurchasesForCurrentWeek() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDateCurrentWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDateCurrentWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<Expense> expensesInPreviousWeek = expenseRepository.findByExpenseDateBetween(startDateCurrentWeek, endDateCurrentWeek);
        return expensesInPreviousWeek.size();
    }

    @Override
    public Optional<Expense> getTopPurchaseForCurrentWeek() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDate = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return expenseRepository.findAllByExpenseDateBetween(startDate, endDate);
    }


    @Override
    public Map<String, Long> getCategoryCounts() {
        List<Object[]> categoryCounts = expenseRepository.getCategoryCounts();
        Map<String, Long> counts = new HashMap<>();

        for (Object[] categoryCount : categoryCounts) {
            String categoryName = (String) categoryCount[0];
            Long count = (Long) categoryCount[1];
            counts.put(categoryName, count);
        }

        return counts;
    }

    @Override
    public String getHighestCategorySumForPreviousWeek() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusDays(7);
        LocalDate endDate = currentDate.minusDays(1);

        List<Object[]> result = expenseRepository.findByExpenseDateBetweenOrderByExpenseCategoryAsc(startDate, endDate);

        if (result.isEmpty()) {
            return null; // Handle the case where there are no purchases for the previous week
        }

        // Get the highest category
        String highestCategory = (String) result.get(result.size() - 1)[4];

        return highestCategory;
    }


}

