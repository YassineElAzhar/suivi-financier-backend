package com.yasselazhar.suivifinancier.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.yasselazhar.suivifinancier.exception.ResourceNotFoundException;
import com.yasselazhar.suivifinancier.model.Income;
import com.yasselazhar.suivifinancier.repository.IncomeRepository;

@Configuration
public class SuiviFinancierHandler {
	

    @Autowired
    IncomeRepository incomeRepository;
	
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
		HashMap<String, String> tempIncome = new HashMap<>();
		
		for (Income income : listIncomes) {
			tempIncome.clear();
			tempIncome.put("id", String.valueOf(income.getId()));
			tempIncome.put("type", income.getType().getType());
			tempIncome.put("provenance", income.getProvenance());
			tempIncome.put("titre", income.getTitre());
			tempIncome.put("montant", String.valueOf(income.getMontant()));
			tempIncome.put("dateIncome", income.getDateIncome().toString());
			
			allIncomes.add(tempIncome);
			
		}
		return allIncomes;
	}
	
	/**
	 * getAllIncomes
	 * 
	 * @return list of all incomes
	 */
	public HashMap<String,String> getIncomeById(int incomeId) {
		Income income = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new ResourceNotFoundException("Income", "id", incomeId));
		
		HashMap<String, String> finalIncome = new HashMap<>();
		
		finalIncome.put("id", String.valueOf(income.getId()));
		finalIncome.put("type", income.getType().getType());
		finalIncome.put("provenance", income.getProvenance());
		finalIncome.put("titre", income.getTitre());
		finalIncome.put("montant", String.valueOf(income.getMontant()));
		finalIncome.put("dateIncome", income.getDateIncome().toString());
			
		return finalIncome;
	}
	
	/**
	 * 
	 * @param income - Income object
	 * @return new Income
	 */
	public Income addIncome(Income income) {
		return incomeRepository.save(income);
	}
	
}