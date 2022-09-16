package com.yasselazhar.suivifinancier.controller;

import com.yasselazhar.suivifinancier.exception.ResourceNotFoundException;
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

    @GetMapping("/typeIncomes")
    public List<TypeIncome> getAllTypeIncomes() {
        return typeIncomeRepository.findAll();
    }

    @PostMapping("/typeIncomes")
    public TypeIncome createTypeIncome(@Valid @RequestBody TypeIncome typeIncome) {
        return typeIncomeRepository.save(typeIncome);
    }

    @GetMapping("/typeIncomes/{id}")
    public TypeIncome getTypeIncomeById(@PathVariable(value = "id") int typeIncomeId) {
        return typeIncomeRepository.findById(typeIncomeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeIncome", "id", typeIncomeId));
    }

    @PutMapping("/typeIncomes/{id}")
    public TypeIncome updateTypeIncome(@PathVariable(value = "id") int typeIncomeId,
                                           @Valid @RequestBody TypeIncome typeIncomeDetails) {

    	TypeIncome typeIncome = typeIncomeRepository.findById(typeIncomeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeIncome", "id", typeIncomeId));

    	typeIncome.setType(typeIncomeDetails.getType());

    	TypeIncome updatedTypeIncome = typeIncomeRepository.save(typeIncome);
        return updatedTypeIncome;
    }

    @DeleteMapping("/typeIncomes/{id}")
    public ResponseEntity<?> deleteTypeIncome(@PathVariable(value = "id") int typeIncomeId) {
    	TypeIncome typeIncome = typeIncomeRepository.findById(typeIncomeId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeIncome", "id", typeIncomeId));

    	typeIncomeRepository.delete(typeIncome);

        return ResponseEntity.ok().build();
    }
}
