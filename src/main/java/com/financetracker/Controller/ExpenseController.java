package com.financetracker.Controller;

import com.financetracker.Entity.Expense;
import com.financetracker.Repository.ExpenseRepository.ExpenseRepository;
import com.financetracker.Services.ExpenseService.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/expense/")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("getAllExpenses")
    public ResponseEntity<?> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAll());
    }

    @PostMapping("addExpense")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense){
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateExpense(@RequestBody Expense expense, @PathVariable String id){
        Expense updatedExpense = expenseService.updateExpense(id, expense);
        if(updatedExpense == null){
            return ResponseEntity.notFound().build();
        }return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExpense(@PathVariable("id") String id) {
        expenseService.deleteExpenseById(id);
    }

    @GetMapping("sortByWeek")
    public Map<Integer, List<Expense>> getExpensesSortedByWeek() {
        return expenseService.getExpensesSortedByWeek();
    }

    @GetMapping("/groupBy/{numOfDates}")
    public Map<LocalDate, List<Expense>> groupExpensesByDate(
            @PathVariable("numOfDates") int numOfDates) {
        return expenseService.groupExpensesByDate(numOfDates);
    }

//    @GetMapping("/groupByMonth")
//    public Map<Integer, List<Expense>> groupExpensesByMonth(
//            @RequestParam(name = "month", required = true) int month) {
//        return expenseService.groupExpensesByMonth(month);
//    }

    @GetMapping("/last-week")
    public long getPurchasesInWeek() {
        return expenseService.getPurchasesInWeek();
    }


    @GetMapping("/grouped-by-week")
    public Map<LocalDateTime, Long> getExpensesGroupedByWeek() {
        return expenseService.groupExpensesByWeek();
    }

    @GetMapping("/groupByWeek")
    public long getExpenseCountInPreviousWeek() {
        return expenseService.countExpensesInPreviousWeek();
    }

    @GetMapping("/groupByMonth")
    public long getExpenseCountInPreviousMonth(){
        return expenseService.countExpenseInPreviousMonth();
    }

}
