package com.yasselazhar.suivifinancier.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.yasselazhar.suivifinancier.exception.ResourceNotFoundException;
import com.yasselazhar.suivifinancier.model.Expense;
import com.yasselazhar.suivifinancier.model.Income;
import com.yasselazhar.suivifinancier.model.TypeExpense;
import com.yasselazhar.suivifinancier.model.TypeIncome;
import com.yasselazhar.suivifinancier.repository.ExpenseRepository;
import com.yasselazhar.suivifinancier.repository.IncomeRepository;
import com.yasselazhar.suivifinancier.repository.TypeExpenseRepository;
import com.yasselazhar.suivifinancier.repository.TypeIncomeRepository;

@Configuration
public class SuiviFinancierHandler {
	
    @Autowired
    IncomeRepository incomeRepository;
    
    @Autowired
    TypeIncomeRepository typeIncomeRepository;
	
    @Autowired
    ExpenseRepository expenseRepository;
    
    @Autowired
    TypeExpenseRepository typeExpenseRepository;
    
    
	
	public SuiviFinancierHandler() {
		
	}
	
	/**
	 * getAllIncomes
	 * 
	 * @return list of all incomes
	 */
	public List<HashMap<String,String>> getAllIncomes() {
		List<Income> listIncomes = incomeRepository.findAll();
		
		List<HashMap<String,String>> allIncomes = new ArrayList<>();
		
		for (Income income : listIncomes) {
			HashMap<String, String> tempIncome = new HashMap<>();
			
			tempIncome.clear();
			tempIncome.put("id", String.valueOf(income.getId()));
			tempIncome.put("type", String.valueOf(income.getType()));
			tempIncome.put("provenance", income.getProvenance());
			tempIncome.put("titre", income.getTitre());
			tempIncome.put("montant", String.valueOf(income.getMontant()));
			tempIncome.put("dateIncome", income.getDateIncome().toString());
			
			allIncomes.add(tempIncome);
			
		}
		return allIncomes;
	}
	
	/**
	 * getIncomeById
	 * 
	 * @return list of all incomes
	 */
	public HashMap<String,String> getIncomeById(int incomeId) {
		HashMap<String, String> finalIncome = new HashMap<>();
		try {
			Income income = incomeRepository.findById(incomeId)
	                .orElseThrow(() -> new ResourceNotFoundException("Income", "id", incomeId));
			
			finalIncome.put("id", String.valueOf(income.getId()));
			finalIncome.put("type", String.valueOf(income.getType()));
			finalIncome.put("provenance", income.getProvenance());
			finalIncome.put("titre", income.getTitre());
			finalIncome.put("montant", String.valueOf(income.getMontant()));
			finalIncome.put("dateIncome", income.getDateIncome().toString());
		} catch (Exception e) {
			System.out.println("getIncomeById error" + e);
		}
			
		return finalIncome;
	}
	
	/**
	 * 
	 * @param income - Income object
	 * @return new Income
	 */
	public Income addIncome(Income income) {
		Income newIncome = new Income();
		try {

			TypeIncome typeIncome = typeIncomeRepository.findById(income.getType())
					.orElseThrow(() -> new ResourceNotFoundException("TypeIncome", "id", income.getType()));
			
			income.setType(typeIncome.getId());
			newIncome = incomeRepository.save(income);
		} catch (Exception e) {
			System.out.println("addIncome error" + e);
		}
		
		return newIncome;
	}

    /**
     * updateIncome
     * 
     * @param incomeId
     * @param incomeDetails
     * @return income udpated
     */
    public Income updateIncome(int incomeId, Income incomeDetails) {
    	Income updatedIncome = new Income();
		try {

	    	Income income = incomeRepository.findById(incomeId)
	                .orElseThrow(() -> new ResourceNotFoundException("Income", "id", incomeId));
			TypeIncome typeIncome = typeIncomeRepository.findById(incomeDetails.getType())
					.orElseThrow(() -> new ResourceNotFoundException("TypeIncome", "id", incomeDetails.getType()));
			
			
	    	income.setDupdate(incomeDetails.getDateIncome());
	    	income.setTitre(incomeDetails.getTitre());
	    	income.setProvenance(incomeDetails.getProvenance());
	    	income.setMontant(incomeDetails.getMontant());
	    	income.setType(typeIncome.getId());
	    

	    	updatedIncome = incomeRepository.save(income);
		} catch (Exception e) {
			System.out.println("updateIncome error" + e);
		}

        return updatedIncome;
    }
    

    
    /**
     * deleteIncome
     * 
     * @param incomeId
     * @return http status
     */
    public ResponseEntity<?> deleteIncome(int incomeId) {
    	ResponseEntity<?> re = ResponseEntity.notFound().build();
    	try {

        	Income income = incomeRepository.findById(incomeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Income", "id", incomeId));
        	
        	incomeRepository.delete(income);
        	re = ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println("deleteIncome error" + e);
		}

        return re;
    }
    
    

	/**
	 * Expenses
	 * 
	 */
    
    
    

	
	/**
	 * getAllExpenses
	 * 
	 * @return list of all expenses
	 */
	public List<HashMap<String,String>> getAllExpenses() {
		List<Expense> listExpenses = expenseRepository.findAll();
		
		List<HashMap<String,String>> allExpenses = new ArrayList<>();
		
		for (Expense expense : listExpenses) {
			HashMap<String, String> tempExpense = new HashMap<>();
			
			tempExpense.clear();
			tempExpense.put("id", String.valueOf(expense.getId()));
			tempExpense.put("type", String.valueOf(expense.getType()));
			tempExpense.put("provenance", expense.getDestinataire());
			tempExpense.put("titre", expense.getTitre());
			tempExpense.put("montant", String.valueOf(expense.getMontant()));
			tempExpense.put("dateExpense", expense.getDateExpense().toString());
			
			allExpenses.add(tempExpense);
			
		}
		return allExpenses;
	}
	
	/**
	 * getExpenseById
	 * 
	 * @return list of all expenses
	 */
	public HashMap<String,String> getExpenseById(int expenseId) {
		HashMap<String, String> finalExpense = new HashMap<>();
		try {
			Expense expense = expenseRepository.findById(expenseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", expenseId));
			
			finalExpense.put("id", String.valueOf(expense.getId()));
			finalExpense.put("type", String.valueOf(expense.getType()));
			finalExpense.put("provenance", expense.getDestinataire());
			finalExpense.put("titre", expense.getTitre());
			finalExpense.put("montant", String.valueOf(expense.getMontant()));
			finalExpense.put("dateExpense", expense.getDateExpense().toString());
		} catch (Exception e) {
			System.out.println("getExpenseById error" + e);
		}
			
		return finalExpense;
	}
	
	/**
	 * 
	 * @param expense - Expense object
	 * @return new Expense
	 */
	public Expense addExpense(Expense expense) {
		Expense newExpense = new Expense();
		try {

			TypeExpense typeExpense = typeExpenseRepository.findById(expense.getType())
					.orElseThrow(() -> new ResourceNotFoundException("TypeExpense", "id", expense.getType()));
			
			expense.setType(typeExpense.getId());
			newExpense = expenseRepository.save(expense);
		} catch (Exception e) {
			System.out.println("addExpense error" + e);
		}
		
		return newExpense;
	}

    /**
     * updateExpense
     * 
     * @param expenseId
     * @param expenseDetails
     * @return expense udpated
     */
    public Expense updateExpense(int expenseId, Expense expenseDetails) {
    	Expense updatedExpense = new Expense();
		try {

	    	Expense expense = expenseRepository.findById(expenseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", expenseId));
			TypeExpense typeExpense = typeExpenseRepository.findById(expenseDetails.getType())
					.orElseThrow(() -> new ResourceNotFoundException("TypeExpense", "id", expenseDetails.getType()));
			
			
	    	expense.setDupdate(expenseDetails.getDateExpense());
	    	expense.setTitre(expenseDetails.getTitre());
	    	expense.setDestinataire(expenseDetails.getDestinataire());
	    	expense.setMontant(expenseDetails.getMontant());
	    	expense.setType(typeExpense.getId());
	    

	    	updatedExpense = expenseRepository.save(expense);
		} catch (Exception e) {
			System.out.println("updateExpense error" + e);
		}

        return updatedExpense;
    }
    

    
    /**
     * deleteExpense
     * 
     * @param expenseId
     * @return http status
     */
    public ResponseEntity<?> deleteExpense(int expenseId) {
    	ResponseEntity<?> re = ResponseEntity.notFound().build();
    	try {

        	Expense expense = expenseRepository.findById(expenseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", expenseId));
        	
        	expenseRepository.delete(expense);
        	re = ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println("deleteExpense error" + e);
		}

        return re;
    }
	
}