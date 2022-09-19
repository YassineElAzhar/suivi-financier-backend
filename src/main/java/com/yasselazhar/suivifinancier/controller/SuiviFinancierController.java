package com.yasselazhar.suivifinancier.controller;

import com.yasselazhar.suivifinancier.handler.SuiviFinancierHandler;
import com.yasselazhar.suivifinancier.model.Expense;
import com.yasselazhar.suivifinancier.model.Income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

/**
 * Created by Yassine EL-AZHAR
 */
@RestController
@RequestMapping("suivi-financier")
public class SuiviFinancierController {

    @Autowired
    SuiviFinancierHandler suiviFinancierHandler;


    /**
     * getAllIncomes
     * 
     * @return List of all incomes
     */
    @GetMapping("/getAllIncomes")
    public List<HashMap<String,String>> allIncomes() {
        return suiviFinancierHandler.getAllIncomes();
    }

    /**
     * getIncomeById
     * 
     * @return List of all incomes
     */
    @GetMapping("/getIncomeById/{id}")
    public HashMap<String,String> incomeById(@PathVariable(value = "id") int incomeId) {
        return suiviFinancierHandler.getIncomeById(incomeId);
    }
    

    /**
     * addIncome
     * @param Income object
     * @return new income inserted
     */
    @PostMapping("/addIncome")
    public Income addIncome(@Valid @RequestBody Income income) {
        return suiviFinancierHandler.addIncome(income);
    }
    
    

    /**
     * 
     * @param incomeId
     * @param incomeDetails
     * @return income udpated
     */
    @PutMapping("/incomes/{id}")
    public Income updateIncome(@PathVariable(value = "id") int incomeId,
                                           @Valid @RequestBody Income incomeDetails) {
    	return suiviFinancierHandler.updateIncome(incomeId,incomeDetails);
    }
    
    


    @DeleteMapping("/incomes/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable(value = "id") int incomeId) {
    	return suiviFinancierHandler.deleteIncome(incomeId);
    }
    
    
    /**
     * Expense
     */
    
    
    /**
     * getAllExpenses
     * 
     * @return List of all expenses
     */
    @GetMapping("/getAllExpenses")
    public List<HashMap<String,String>> allExpenses() {
        return suiviFinancierHandler.getAllExpenses();
    }

    /**
     * getExpenseById
     * 
     * @return List of all expenses
     */
    @GetMapping("/getExpenseById/{id}")
    public HashMap<String,String> expenseById(@PathVariable(value = "id") int expenseId) {
        return suiviFinancierHandler.getExpenseById(expenseId);
    }
    

    /**
     * addExpense
     * @param Expense object
     * @return new expense inserted
     */
    @PostMapping("/addExpense")
    public Expense addExpense(@Valid @RequestBody Expense expense) {
        return suiviFinancierHandler.addExpense(expense);
    }
    
    

    /**
     * 
     * @param expenseId
     * @param expenseDetails
     * @return expense udpated
     */
    @PutMapping("/expenses/{id}")
    public Expense updateExpense(@PathVariable(value = "id") int expenseId,
                                           @Valid @RequestBody Expense expenseDetails) {
    	return suiviFinancierHandler.updateExpense(expenseId,expenseDetails);
    }
    
    


    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable(value = "id") int expenseId) {
    	return suiviFinancierHandler.deleteExpense(expenseId);
    }
}
