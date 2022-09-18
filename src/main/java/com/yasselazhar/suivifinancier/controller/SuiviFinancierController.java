package com.yasselazhar.suivifinancier.controller;

import com.yasselazhar.suivifinancier.handler.SuiviFinancierHandler;
import com.yasselazhar.suivifinancier.model.Income;

import org.springframework.beans.factory.annotation.Autowired;
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
    
}
