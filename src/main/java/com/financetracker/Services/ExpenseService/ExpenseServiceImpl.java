package com.financetracker.Services.ExpenseService;


import com.financetracker.Entity.Expense;
import com.financetracker.Repository.ExpenseRepository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
