package com.yasselazhar.suivifinancier.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.yasselazhar.suivifinancier.exception.ResourceNotFoundException;
import com.yasselazhar.suivifinancier.model.Income;
import com.yasselazhar.suivifinancier.repository.IncomeRepository;

@Configuration
public class IncomeHandler {

    @Autowired
    IncomeRepository incomeRepository;
	
	public IncomeHandler() {
		
	}
	
	
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }
	
    
    public Income createIncome(Income income) {
        return incomeRepository.save(income);
    }
    
    
    public Income getIncomeById(int incomeId) {
        return incomeRepository.findById(incomeId)
                .orElseThrow(() -> new ResourceNotFoundException("Income", "id", incomeId));
    }
    
    
    public Income updateIncome(int incomeId, Income incomeDetails) {

    	Income income = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new ResourceNotFoundException("Income", "id", incomeId));

    	income.setType(incomeDetails.getType());

    	Income updatedIncome = incomeRepository.save(income);
        return updatedIncome;
    }
    
    
    public ResponseEntity<?> deleteIncome(int incomeId) {
    	Income income = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new ResourceNotFoundException("Income", "id", incomeId));

    	incomeRepository.delete(income);

        return ResponseEntity.ok().build();
    }
	
    
}