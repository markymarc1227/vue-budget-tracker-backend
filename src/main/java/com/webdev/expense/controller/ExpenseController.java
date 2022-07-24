package com.webdev.expense.controller;

import com.webdev.expense.model.Expense;
import com.webdev.expense.model.ErrorHandler;
import com.webdev.expense.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public ResponseEntity fetchAll() {
        List<Expense> contacts = expenseService.fetchAll();
        return ResponseEntity.ok().body(contacts);
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity find(@PathVariable Long id, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            return ResponseEntity.ok().body(expenseService.find(id));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/expense")
    public ResponseEntity save(@RequestBody Expense expense, HttpServletRequest request) {
        URI uri = URI.create(request.getRequestURI());
        try {
            expense.setId(null);
            return ResponseEntity.created(uri).body(expenseService.save(expense));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/expense/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Expense expense, HttpServletRequest request){
        URI uri = URI.create(request.getRequestURI());
        try {
            expense.setId(id);
            return ResponseEntity.ok().body(expenseService.update(expense));
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/expense/{id}")
    public ResponseEntity delete(@PathVariable Long id, HttpServletRequest request){
        URI uri = URI.create(request.getRequestURI());
        try {
            expenseService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            ErrorHandler error = new ErrorHandler(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(),
                    e.getLocalizedMessage(),
                    uri
            );
            return ResponseEntity.badRequest().body(error);
        }
    }
}
