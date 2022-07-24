package com.webdev.expense.service;

import com.webdev.expense.model.Expense;
import com.webdev.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> fetchAll() {
        return  expenseRepository.findAll();
    }

    public Expense find(Long id) throws Exception {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense == null) throw new Exception("Contact id doesn't exists");
        return expense;
    }
    public Expense save(Expense expense) throws Exception {
        try {
            return expenseRepository.saveAndFlush(expense);
        } catch (Exception exception) {
            Throwable t = exception.getCause();
            while (t.getCause() != null) t = t.getCause();
            throw new Exception(t.getLocalizedMessage());
        }
    }

    public Expense update(Expense expense) throws Exception {
        try {
            return expenseRepository.save(expense);
        } catch (Exception exception) {
            Throwable t = exception.getCause();
            while (t.getCause() != null) t = t.getCause();
            throw new Exception(t.getLocalizedMessage());
        }
    }

    public void delete(Long id) throws Exception {
        try {
            Expense expense = this.find(id);
            expenseRepository.delete(expense);
        } catch (Exception exception) {
            Throwable t = exception.getCause();
            while (t.getCause() != null) t = t.getCause();
            throw new Exception(t.getLocalizedMessage());
        }
    }


}
