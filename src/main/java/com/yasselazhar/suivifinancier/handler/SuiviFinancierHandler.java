package com.yasselazhar.suivifinancier.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.yasselazhar.suivifinancier.exception.ResourceNotFoundException;
import com.yasselazhar.suivifinancier.model.Income;
import com.yasselazhar.suivifinancier.model.TypeIncome;
import com.yasselazhar.suivifinancier.repository.IncomeRepository;
import com.yasselazhar.suivifinancier.repository.TypeIncomeRepository;

@Configuration
public class SuiviFinancierHandler {
	

    @Autowired
    IncomeRepository incomeRepository;
    
    @Autowired
    TypeIncomeRepository typeIncomeRepository;
	
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
	
}