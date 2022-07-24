package com.webdev.expense;

import com.webdev.expense.model.Expense;
import com.webdev.expense.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ExpenseApplication.class, args);
	}

	@Autowired
	private ExpenseService expenseService;

	@Override
	public void run(String... args) throws Exception {
		expenseService.save(new Expense(
				null,
				"Sample",
				0.00,
				false
		));
//		expenseService.save(new Expense(
//				null,
//				"Transporation",
//				300.00,
//				false
//		));
//		expenseService.save(new Expense(
//				null,
//				"Lunch",
//				500.25,
//				false
//		));
	}
}

