package com.yasselazhar.suivifinancier.handler;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.yasselazhar.suivifinancier.exception.ResourceNotFoundException;
import com.yasselazhar.suivifinancier.model.TypeIncome;
import com.yasselazhar.suivifinancier.repository.TypeIncomeRepository;

@Configuration
public class TypeIncomeHandler {

    @Autowired
    TypeIncomeRepository typeIncomeRepository;
	
	public TypeIncomeHandler() {
		
	}
	
	
    public List<TypeIncome> getAllTypeIncomes() {
        return typeIncomeRepository.findAll();
    }
	
    
    public TypeIncome createTypeIncome(TypeIncome typeIncome) {
        return typeIncomeRepository.save(typeIncome);
    }
    
    
    public TypeIncome getTypeIncomeById(int typeIncomeId) {
        return typeIncomeRepository.findById(typeIncomeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeIncome", "id", typeIncomeId));
    }
    
    
    public TypeIncome updateTypeIncome(int typeIncomeId, TypeIncome typeIncomeDetails) {

    	TypeIncome typeIncome = typeIncomeRepository.findById(typeIncomeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeIncome", "id", typeIncomeId));

    	typeIncome.setType(typeIncomeDetails.getType());

    	TypeIncome updatedTypeIncome = typeIncomeRepository.save(typeIncome);
        return updatedTypeIncome;
    }
    
    
    public ResponseEntity<?> deleteTypeIncome(int typeIncomeId) {
    	TypeIncome typeIncome = typeIncomeRepository.findById(typeIncomeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeIncome", "id", typeIncomeId));

    	typeIncomeRepository.delete(typeIncome);

        return ResponseEntity.ok().build();
    }
    
}