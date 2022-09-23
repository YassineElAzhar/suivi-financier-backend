package com.yasselazhar.suivifinancier.controller;

import com.yasselazhar.suivifinancier.handler.SuiviFinancierHandler;
import com.yasselazhar.suivifinancier.model.Event;
import com.yasselazhar.suivifinancier.model.Expense;
import com.yasselazhar.suivifinancier.model.Income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

/**
 * Created by Yassine EL-AZHAR
 */
@RestController
@RequestMapping("/suivi-financier")
public class SuiviFinancierController {

    @Autowired
    SuiviFinancierHandler suiviFinancierHandler;


    /**
     * getAllIncomes
     * 
     * @return List of all incomes
     */
    @CrossOrigin
    @GetMapping("/getAllIncomes")
    public List<HashMap<String,String>> allIncomes() {
        return suiviFinancierHandler.getAllIncomes();
    }

    /**
     * getIncomeById
     * 
     * @return List of all incomes
     */
    @CrossOrigin
    @GetMapping("/getIncomeById/{id}")
    public HashMap<String,String> incomeById(@PathVariable(value = "id") int incomeId) {
        return suiviFinancierHandler.getIncomeById(incomeId);
    }
    

    /**
     * addIncome
     * @param Income object
     * @return new income inserted
     */
    @CrossOrigin
    @PostMapping("/addIncome")
    public Income addIncome(@Valid @RequestBody Income income) {
        return suiviFinancierHandler.addIncome(income);
    }
    
    
    /**
     * 
     * @param incomeId
     * @param incomeDetails
     * @return income udpated
     */
    @CrossOrigin
    @PutMapping("/incomes/{id}")
    public Income updateIncome(@PathVariable(value = "id") int incomeId,
                                           @Valid @RequestBody Income incomeDetails) {
    	return suiviFinancierHandler.updateIncome(incomeId,incomeDetails);
    }
    

    @CrossOrigin
    @DeleteMapping("/incomes/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable(value = "id") int incomeId) {
    	return suiviFinancierHandler.deleteIncome(incomeId);
    }
    
    
    /**
     * Expense
     */
    
    
    /**
     * getAllExpenses
     * 
     * @return List of all expenses
     */
    @CrossOrigin
    @GetMapping("/getAllExpenses")
    public List<HashMap<String,String>> allExpenses() {
        return suiviFinancierHandler.getAllExpenses();
    }

    /**
     * getExpenseById
     * 
     * @return List of all expenses
     */
    @CrossOrigin
    @GetMapping("/getExpenseById/{id}")
    public HashMap<String,String> expenseById(@PathVariable(value = "id") int expenseId) {
        return suiviFinancierHandler.getExpenseById(expenseId);
    }
    

    /**
     * addExpense
     * @param Expense object
     * @return new expense inserted
     */
    @CrossOrigin
    @PostMapping("/addExpense")
    public Expense addExpense(@Valid @RequestBody Expense expense) {
        return suiviFinancierHandler.addExpense(expense);
    }
    
    

    /**
     * 
     * @param expenseId
     * @param expenseDetails
     * @return expense udpated
     */
    @CrossOrigin
    @PutMapping("/expenses/{id}")
    public Expense updateExpense(@PathVariable(value = "id") int expenseId,
                                           @Valid @RequestBody Expense expenseDetails) {
    	return suiviFinancierHandler.updateExpense(expenseId,expenseDetails);
    }
    
    


    @CrossOrigin
    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable(value = "id") int expenseId) {
    	return suiviFinancierHandler.deleteExpense(expenseId);
    }
    
    /**
     * Event
     */
    
    
    /**
     * getAllEvents
     * 
     * @return List of all events
     */
    @CrossOrigin
    @GetMapping("/getAllEvents")
    public List<Event> allEvents() {
        return suiviFinancierHandler.getAllEvents();
    }

    /**
     * getEventById
     * 
     * @return List of all events
     */
    @CrossOrigin
    @GetMapping("/getEventById/{id}")
    public Event eventById(@PathVariable(value = "id") int eventId) {
        return suiviFinancierHandler.getEventById(eventId);
    }

    /**
     * getEventsByMonth
     * 
     * @return List of all events
     * @throws ParseException 
     */
    @CrossOrigin
    @GetMapping("/getEventsByMonth/{calendarMonth}/{calendarYear}")
	public Map<String, List<Map<String,String>>> eventsByMonth(
    		@PathVariable(value = "calendarMonth") String calendarMonth,
    		@PathVariable(value = "calendarYear") String calendarYear) {
        return suiviFinancierHandler.getEventsByMonth(calendarMonth,calendarYear);
    }
    

    /**
     * addEvent
     * @param Event object
     * @return new event inserted
     */
    @CrossOrigin
    @PostMapping("/addEvent")
    public Event addEvent(@Valid @RequestBody Event event) {
        return suiviFinancierHandler.addEvent(event);
    }
    
    

    /**
     * 
     * @param eventId
     * @param eventDetails
     * @return event udpated
     */
    @CrossOrigin
    @PutMapping("/events/{id}")
    public Event updateEvent(@PathVariable(value = "id") int eventId,
                                           @Valid @RequestBody Event eventDetails) {
        return suiviFinancierHandler.updateEvent(eventId,eventDetails);
    }
    
    

    /**
     * 
     * @param eventId
     * @return
     */
    @CrossOrigin
    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable(value = "id") int eventId) {
        return suiviFinancierHandler.deleteEvent(eventId);
    }
    
    
    
    /**
     * Charts
     */
    

    @CrossOrigin
    @GetMapping("/getChartInOutCurrentYear")
    public Map<String, Object> getChartInOutCurrentYear() {
        return suiviFinancierHandler.getChartInOutCurrentYear();
    }
    
}
