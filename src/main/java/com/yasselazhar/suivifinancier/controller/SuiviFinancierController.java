package com.yasselazhar.suivifinancier.controller;

import com.yasselazhar.suivifinancier.exception.ResourceNotFoundException;
import com.yasselazhar.suivifinancier.handler.TypeIncomeHandler;
import com.yasselazhar.suivifinancier.model.TypeIncome;
import com.yasselazhar.suivifinancier.repository.TypeIncomeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Yassine EL-AZHAR
 */
@RestController
@RequestMapping("suivi-financier")
public class SuiviFinancierController {

    @Autowired
    TypeIncomeRepository typeIncomeRepository;

    @Autowired
    TypeIncomeHandler typeIncomeHandler;

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
}
