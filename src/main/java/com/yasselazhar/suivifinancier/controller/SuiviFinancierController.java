package com.yasselazhar.suivifinancier.controller;

import com.yasselazhar.suivifinancier.handler.SuiviFinancierHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Yassine EL-AZHAR
 */
@RestController
@RequestMapping("suivi-financier")
public class SuiviFinancierController {

    @Autowired
    SuiviFinancierHandler suiviFinancierHandler;
    

    @GetMapping("/allIncomes")
    public List<HashMap<String,String>> getAllTypeIncomes() {
        return suiviFinancierHandler.getAllIncomes();
    }
    
}
