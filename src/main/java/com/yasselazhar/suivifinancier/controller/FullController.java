package com.yasselazhar.suivifinancier.controller;

import com.yasselazhar.suivifinancier.handler.IncomeHandler;
import com.yasselazhar.suivifinancier.handler.TypeIncomeHandler;
import com.yasselazhar.suivifinancier.model.Income;
import com.yasselazhar.suivifinancier.model.TypeIncome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Yassine EL-AZHAR
 */
@RestController
@RequestMapping("full")
public class FullController {

    @Autowired
    TypeIncomeHandler typeIncomeHandler;
    
    @Autowired
    IncomeHandler incomeHandler;

    @GetMapping("/typeIncomes")
    public List<TypeIncome> getAllTypeIncomes() {
        return typeIncomeHandler.getAllTypeIncomes();
    }

    @PostMapping("/typeIncomes")
    public TypeIncome createTypeIncome(@Valid @RequestBody TypeIncome typeIncome) {
        return typeIncomeHandler.createTypeIncome(typeIncome);
    }

    @GetMapping("/typeIncomes/{id}")
    public TypeIncome getTypeIncomeById(@PathVariable(value = "id") int typeIncomeId) {
        return typeIncomeHandler.getTypeIncomeById(typeIncomeId);
    }

    @PutMapping("/typeIncomes/{id}")
    public TypeIncome updateTypeIncome(@PathVariable(value = "id") int typeIncomeId,
                                           @Valid @RequestBody TypeIncome typeIncomeDetails) {
    	return typeIncomeHandler.updateTypeIncome(typeIncomeId,typeIncomeDetails);
    }

    @DeleteMapping("/typeIncomes/{id}")
    public ResponseEntity<?> deleteTypeIncome(@PathVariable(value = "id") int typeIncomeId) {
    	return typeIncomeHandler.deleteTypeIncome(typeIncomeId);
    }
    
    


    @GetMapping("/incomes")
    public List<Income> getAllIncomes() {
        return incomeHandler.getAllIncomes();
    }
    
}
