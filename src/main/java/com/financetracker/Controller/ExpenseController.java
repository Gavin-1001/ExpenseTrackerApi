package com.financetracker.Controller;

import com.financetracker.Entity.Expense;
import com.financetracker.Services.ExpenseService.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/expense/")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("getAllExpenses")
    public ResponseEntity<?> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAll());
    }

    @PostMapping("createExpense")
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
}
