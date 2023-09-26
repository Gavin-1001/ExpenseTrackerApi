package com.financetracker.Services.ExpenseService;


import com.financetracker.Entity.Expense;
import com.financetracker.Repository.ExpenseRepository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Override
    public Expense updateExpense(String id, Expense expense) {

        Expense updatedExpense = expenseRepository.findById(id).get();
        //expense name
        if(Objects.nonNull(expense.getExpenseName()) && !"".equalsIgnoreCase(expense.getExpenseName())){
            updatedExpense.setExpenseName(expense.getExpenseName());
        }

        //expense description
        if(Objects.nonNull(expense.getExpenseDescription()) && !"".equalsIgnoreCase(expense.getExpenseDescription())){
            updatedExpense.setExpenseDescription(expense.getExpenseDescription());
        }

        //expense price
        updatedExpense.setPrice(expense.getPrice());

        //expense date
        if(Objects.nonNull(expense.getDateCreated())){
            updatedExpense.setDateCreated(expense.getDateCreated());
        }


        return expenseRepository.save(updatedExpense);
    }

    @Override
    public void deleteExpenseById(String id) {
        expenseRepository.deleteById(id);
    }
}
