package com.yasselazhar.suivifinancier.handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.yasselazhar.suivifinancier.exception.ResourceNotFoundException;
import com.yasselazhar.suivifinancier.model.Event;
import com.yasselazhar.suivifinancier.model.Expense;
import com.yasselazhar.suivifinancier.model.Income;
import com.yasselazhar.suivifinancier.model.TypeEvent;
import com.yasselazhar.suivifinancier.repository.ChartRepository;
import com.yasselazhar.suivifinancier.repository.EventRepository;
import com.yasselazhar.suivifinancier.repository.ExpenseRepository;
import com.yasselazhar.suivifinancier.repository.IncomeRepository;
import com.yasselazhar.suivifinancier.repository.TypeEventRepository;
import com.yasselazhar.suivifinancier.repository.TypeExpenseRepository;
import com.yasselazhar.suivifinancier.repository.TypeIncomeRepository;

@Configuration
public class SuiviFinancierHandler {
	
    @Autowired
    IncomeRepository incomeRepository;
    
    @Autowired
    TypeIncomeRepository typeIncomeRepository;
	
    @Autowired
    ExpenseRepository expenseRepository;
    
    @Autowired
    TypeExpenseRepository typeExpenseRepository;
	
    @Autowired
    EventRepository eventRepository;
    
    @Autowired
    TypeEventRepository typeEventRepository;

    @Autowired
    ChartRepository chartRepository;
    
    
    
    
	
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

			TypeEvent typeEvent = typeEventRepository.findByIsIncomeAndId(1,income.getType());
			
			tempIncome.clear();
			tempIncome.put("id", String.valueOf(income.getId()));
			//tempIncome.put("type", String.valueOf(income.getType()));
			tempIncome.put("type", String.valueOf(typeEvent.getType()));
			tempIncome.put("provenance", income.getProvenance());
			tempIncome.put("titre", income.getTitre());
			tempIncome.put("montant", String.valueOf(income.getMontant()));
			tempIncome.put("dateIncome", income.getDateIncome().toString().substring(0, 10));
			
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
			finalIncome.put("dateIncome", income.getDateIncome().toString().substring(0, 10));
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
		Event eventToAdd = new Event();
		try {
			TypeEvent typeEvent = typeEventRepository.findByIsIncomeAndId(1,income.getType());
			
			income.setType(typeEvent.getId());
			newIncome = incomeRepository.save(income);
			
			//Nous ajoutons un nouvel event
			eventToAdd.setType(newIncome.getType());
			eventToAdd.setTitre(newIncome.getTitre());
			eventToAdd.setDateEvent(newIncome.getDateIncome());
			eventToAdd.setIncomeId(newIncome.getId());
			eventToAdd.setExpenseId(0);
			eventToAdd.setStartTime("00:00:00");
			eventToAdd.setEndTime("00:00:00");
			eventRepository.save(eventToAdd);
		} catch (Exception e) {
			System.out.println("addIncome error" + e);
		}
		
		return newIncome;
	}

    /**
     * updateIncome
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
			
			TypeEvent typeEvent = typeEventRepository.findByIsIncomeAndId(1,incomeDetails.getType());
			
			
	    	income.setDateIncome(incomeDetails.getDateIncome());
	    	income.setTitre(incomeDetails.getTitre());
	    	income.setProvenance(incomeDetails.getProvenance());
	    	income.setMontant(incomeDetails.getMontant());
	    	income.setType(typeEvent.getId());

	    	updatedIncome = incomeRepository.save(income);
	    	
	    	//Nous mettons ?? jour l'event
	    	Event event = eventRepository.findByIncomeId(incomeId);
	    	event.setTitre(income.getTitre());
	    	event.setType(typeEvent.getId());
	    	event.setDateEvent(income.getDateIncome());
	    	eventRepository.save(event);
	    	
		} catch (Exception e) {
			System.out.println("updateIncome error" + e);
		}

        return updatedIncome;
    }
    

    
    /**
     * deleteIncome
     * 
     * @param incomeId
     * @return http status
     */
    public ResponseEntity<?> deleteIncome(int incomeId) {
    	ResponseEntity<?> re = ResponseEntity.notFound().build();
    	try {
        	Income income = incomeRepository.findById(incomeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Income", "id", incomeId));
        	
        	incomeRepository.delete(income);
        	
        	//Nous supprimons l'event
        	Event event = eventRepository.findByIncomeId(incomeId);
        	eventRepository.delete(event);
        	
        	re = ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println("deleteIncome error" + e);
		}

        return re;
    }
    
    

	/**
	 * Expenses
	 * 
	 */
    
    
    

	
	/**
	 * getAllExpenses
	 * 
	 * @return list of all expenses
	 */
	public List<HashMap<String,String>> getAllExpenses() {
		List<Expense> listExpenses = expenseRepository.findAll();
		
		List<HashMap<String,String>> allExpenses = new ArrayList<>();
		
		for (Expense expense : listExpenses) {
			HashMap<String, String> tempExpense = new HashMap<>();
			
			TypeEvent typeEvent = typeEventRepository.findByIsExpenseAndId(1,expense.getType());
			
			tempExpense.clear();
			tempExpense.put("id", String.valueOf(expense.getId()));
			tempExpense.put("type", String.valueOf(typeEvent.getType()));
			tempExpense.put("destinataire", expense.getDestinataire());
			tempExpense.put("titre", expense.getTitre());
			tempExpense.put("montant", String.valueOf(expense.getMontant()));
			tempExpense.put("dateExpense", expense.getDateExpense().toString().substring(0, 10));
			
			allExpenses.add(tempExpense);
			
		}
		return allExpenses;
	}
	
	/**
	 * getExpenseById
	 * 
	 * @return list of all expenses
	 */
	public HashMap<String,String> getExpenseById(int expenseId) {
		HashMap<String, String> finalExpense = new HashMap<>();
		try {
			Expense expense = expenseRepository.findById(expenseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", expenseId));
			
			finalExpense.put("id", String.valueOf(expense.getId()));
			finalExpense.put("type", String.valueOf(expense.getType()));
			finalExpense.put("destinataire", expense.getDestinataire());
			finalExpense.put("titre", expense.getTitre());
			finalExpense.put("montant", String.valueOf(expense.getMontant()));
			finalExpense.put("dateExpense", expense.getDateExpense().toString().substring(0, 10));
		} catch (Exception e) {
			System.out.println("getExpenseById error" + e);
		}
			
		return finalExpense;
	}
	
	/**
	 * 
	 * @param expense - Expense object
	 * @return new Expense
	 */
	public Expense addExpense(Expense expense) {
		Expense newExpense = new Expense();
		Event eventToAdd = new Event();
		try {
			TypeEvent typeEvent = typeEventRepository.findByIsExpenseAndId(1,expense.getType());
			
			expense.setType(typeEvent.getId());
			newExpense = expenseRepository.save(expense);
			
			//Nous ajoutons un nouvel event
			eventToAdd.setType(newExpense.getType());
			eventToAdd.setTitre(newExpense.getTitre());
			eventToAdd.setDateEvent(newExpense.getDateExpense());
			eventToAdd.setExpenseId(newExpense.getId());
			eventToAdd.setIncomeId(0);
			eventToAdd.setStartTime("00:00:00");
			eventToAdd.setEndTime("00:00:00");
			eventRepository.save(eventToAdd);
			
		} catch (Exception e) {
			System.out.println("addExpense error" + e);
		}
		
		return newExpense;
	}

    /**
     * updateExpense
     * 
     * @param expenseId
     * @param expenseDetails
     * @return expense udpated
     */
    public Expense updateExpense(int expenseId, Expense expenseDetails) {
    	Expense updatedExpense = new Expense();
		try {
	    	Expense expense = expenseRepository.findById(expenseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", expenseId));

			TypeEvent typeEvent = typeEventRepository.findByIsExpenseAndId(1,expenseDetails.getType());
			
			
	    	expense.setDateExpense(expenseDetails.getDateExpense());
	    	expense.setTitre(expenseDetails.getTitre());
	    	expense.setDestinataire(expenseDetails.getDestinataire());
	    	expense.setMontant(expenseDetails.getMontant());
	    	expense.setType(typeEvent.getId());
	    

	    	updatedExpense = expenseRepository.save(expense);
	    	
	    	//Nous mettons ?? jour l'event
	    	Event event = eventRepository.findByExpenseId(expenseId);
	    	event.setTitre(expense.getTitre());
	    	event.setType(typeEvent.getId());
	    	event.setDateEvent(expense.getDateExpense());
	    	eventRepository.save(event);
	    	
		} catch (Exception e) {
			System.out.println("updateExpense error" + e);
		}

        return updatedExpense;
    }
    

    
    /**
     * deleteExpense
     * 
     * @param expenseId
     * @return http status
     */
    public ResponseEntity<?> deleteExpense(int expenseId) {
    	ResponseEntity<?> re = ResponseEntity.notFound().build();
    	try {
        	Expense expense = expenseRepository.findById(expenseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", expenseId));
        	
        	expenseRepository.delete(expense);
        	
        	//Nous supprimons l'event
        	Event event = eventRepository.findByExpenseId(expenseId);
        	eventRepository.delete(event);
        	
        	re = ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println("deleteExpense error" + e);
		}

        return re;
    }
    
    
    
    /**
     * getAllEvents
     * 
     * @return list of all events
     */
    public List<Event> getAllEvents() {
        List<Event> listEvents = eventRepository.findAll();
        
        
        return listEvents;
    }
    
    /**
     * getEventById
     * 
     * @return list of all events
     */
    public Event getEventById(int eventId) {
        Event event = new Event();
        try {
            event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));
        } catch (Exception e) {
            System.out.println("getEventById error" + e);
        }
            
        return event;
    }
    
    /**
     * 
     * @param event - Event object
     * @return new Event
     */
    public Event addEvent(Event event) {
        Event newEvent = new Event();
        try {

            TypeEvent typeEvent = typeEventRepository.findById(event.getType())
                    .orElseThrow(() -> new ResourceNotFoundException("TypeEvent", "id", event.getType()));
            
            event.setType(typeEvent.getId());
            
            //On ajoute une expense
            if((typeEvent.getIsExpense() == 1) && (typeEvent.getIsIncome() == 0)) {
                Expense expense = new Expense();
                expense.setTitre(event.getTitre());
                expense.setType(event.getType());
                expense.setDateExpense(event.getDateEvent());
                expense.setDestinataire("Ajout automatique");
                expense.setMontant(0);
                Expense newExpense = expenseRepository.save(expense);
                event.setExpenseId(newExpense.getId());
            } else if((typeEvent.getIsExpense() == 0) && (typeEvent.getIsIncome() == 1)) {
                Income income = new Income();
                income.setTitre(event.getTitre());
                income.setType(event.getType());
                income.setDateIncome(event.getDateEvent());
                income.setProvenance("Ajout automatique");
                income.setMontant(0);
                incomeRepository.save(income);
                Income newIncome = incomeRepository.save(income);
                event.setIncomeId(newIncome.getId());
            }
            
            newEvent = eventRepository.save(event);
        } catch (Exception e) {
            System.out.println("addEvent error" + e);
        }
        
        return newEvent;
    }

    /**
     * updateEvent
     * 
     * @param eventId
     * @param eventDetails
     * @return event udpated
     */
    public Event updateEvent(int eventId, Event eventDetails) {
        Event updatedEvent = new Event();
        try {

            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));
            TypeEvent typeEvent = typeEventRepository.findById(eventDetails.getType())
                    .orElseThrow(() -> new ResourceNotFoundException("TypeEvent", "id", eventDetails.getType()));
            
            event.setDateEvent(eventDetails.getDateEvent());
            event.setTitre(eventDetails.getTitre());
            event.setStartTime(eventDetails.getStartTime());
            event.setEndTime(eventDetails.getEndTime());
            event.setType(typeEvent.getId());
            
            if(event.getIncomeId() != 0) {
            	Income income = incomeRepository.findById(event.getIncomeId())
                .orElseThrow(() -> new ResourceNotFoundException("Income", "id", event.getIncomeId()));
            	
            	income.setDateIncome(eventDetails.getDateEvent());
            	income.setTitre(eventDetails.getTitre());
            	income.setType(event.getType());
                incomeRepository.save(income);
            } else if(event.getExpenseId() != 0)  {
            	Expense expense = expenseRepository.findById(event.getExpenseId())
                        .orElseThrow(() -> new ResourceNotFoundException("Expense", "id", event.getExpenseId()));
            	
            	expense.setDateExpense(eventDetails.getDateEvent());
            	expense.setTitre(eventDetails.getTitre());
            	expense.setType(event.getType());
            	expenseRepository.save(expense);
            }
        
			
            updatedEvent = eventRepository.save(event);
        } catch (Exception e) {
            System.out.println("updateEvent error" + e);
        }

        return updatedEvent;
    }
    

    
    /**
     * deleteEvent
     * 
     * @param eventId
     * @return http status
     */
    public ResponseEntity<?> deleteEvent(int eventId) {
        ResponseEntity<?> re = ResponseEntity.notFound().build();
        
        try {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new ResourceNotFoundException("Event", "id", eventId));
                        
            if(event.getIncomeId() != 0) {
                incomeRepository.deleteById(event.getIncomeId());
            } else if(event.getExpenseId() != 0)  {
            	expenseRepository.deleteById(event.getExpenseId());
            }
            
            eventRepository.deleteById(eventId);
            re = ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("deleteEvent error" + e);
        }

        return re;
    }
    
    
    
    public Map<String, List<Map<String,String>>> getEventsByMonth(String calendarMonth, String calendarYear) {

    	Map<String, List<Map<String,String>>> returnedEvents = new HashMap<>();
    	List<Event> eventList = new ArrayList<Event>();
    	
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String startDate = "01-"+calendarMonth+"-"+calendarYear;
        String endDate = "31-"+calendarMonth+"-"+calendarYear;

        try {

            Date startDateFormatted = formatter.parse(startDate);
            Date endDateFormatted = formatter.parse(endDate);

            eventList = eventRepository.findByDateEventBetween(startDateFormatted,endDateFormatted);


            eventList.forEach((Event event) -> {
        		Map<String, String> valuesToReturn = new HashMap<>();

    			TypeEvent typeEvent = typeEventRepository.findById(event.getType())
    					.orElseThrow(() -> new ResourceNotFoundException("TypeEvent", "id", event.getType()));
    			
    			String dateString = event.getDateEvent().toString().substring(0, 10);
    			
            	valuesToReturn.clear();
            	valuesToReturn.put("id", String.valueOf(event.getId()));
            	valuesToReturn.put("type", typeEvent.getType());
            	valuesToReturn.put("titre", event.getTitre());
            	valuesToReturn.put("start_time", event.getStartTime().substring(0, 5));
            	valuesToReturn.put("end_time", event.getEndTime().substring(0, 5));
            	
            	if(returnedEvents.get(dateString)!= null) {
            		returnedEvents.get(dateString).add(valuesToReturn);
            	}else {
            		Map<Integer,Map<String,String>> newValtemp = new HashMap<>();
            		newValtemp.put(event.getId(), valuesToReturn);
            		List<Map<String,String>> tempList = new ArrayList<>();
            		tempList.add(valuesToReturn);
            		returnedEvents.put(dateString, tempList);
            	}
            	

            });

            

        } catch (ParseException e) {
            e.printStackTrace();
        }

    	return returnedEvents;
    }
    
    
    public Map<String, Object> getChartInOutCurrentYear() {
    	Map<String, Object> chartDataSet = new HashMap<>();
    	List<String> listChartLabels = new ArrayList<String>();
    	List<Map<String,Object>> dataset = new ArrayList<>();
    	Map<String, Object> datasetIncome = new HashMap<>();
    	Map<String, Object> datasetExpense = new HashMap<>();
    	
    	Collections.addAll(listChartLabels, "Janvier", "F??vrier", "Mars", "Avril", "Mai", "Juin", 
    			"Juillet", "Aout", "Septembre", "Octobre", "Novembre", "D??cembre");
    	
    	
    	/**
    	 * Incomes
    	 */
    	//Nous r??cup??rons les incomes depuis la base de donn??es
    	Map<Integer, Integer> mapIncomesToAdd = new HashMap<>();
    	chartRepository.getChartInOutIncomesCurrentYear().forEach(getChartInOutMap -> {
    	    try {
    	    	//Nous traitons la date pour r??cup??rer le mois
				Date dateToConvert=new SimpleDateFormat("yyyy-MM").parse(getChartInOutMap.getYearMonth());
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateToConvert);
				int month = cal.get(Calendar.MONTH);
				//On feed une map temporaire
				mapIncomesToAdd.put(month+1, getChartInOutMap.getSommeMontant());
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	});
    	//Nous passons les mois vide ?? 0
    	for (int cpt = 1; cpt <= 12; cpt++) {
    		if (!mapIncomesToAdd.containsKey(cpt)) {
        		mapIncomesToAdd.put(cpt, 0);
        	}
		}
    	//Nous transformons la map en list
    	List<Integer> listIncomeDataSet = new ArrayList<Integer>(mapIncomesToAdd.values());


    	/**
    	 * Expenses
    	 */
    	//Nous r??cup??rons les expenses depuis la base de donn??es
    	Map<Integer, Integer> mapExpensesToAdd = new HashMap<>();
    	chartRepository.getChartInOutExpensesCurrentYear().forEach(getChartInOutMap -> {
    	    try {
    	    	//Nous traitons la date pour r??cup??rer le mois
				Date dateToConvert=new SimpleDateFormat("yyyy-MM").parse(getChartInOutMap.getYearMonth());
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateToConvert);
				int month = cal.get(Calendar.MONTH);
				//On feed une map temporaire
				mapExpensesToAdd.put(month+1, getChartInOutMap.getSommeMontant());
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	});
    	//Nous passons les mois vide ?? 0
    	for (int cpt = 1; cpt <= 12; cpt++) {
    		if (!mapExpensesToAdd.containsKey(cpt)) {
    			mapExpensesToAdd.put(cpt, 0);
        	}
		}
    	//Nous transformons la map en list
    	List<Integer> listExpenseDataSet = new ArrayList<Integer>(mapExpensesToAdd.values());

	    
	    
    	datasetIncome.put("label", "Revenus");
    	datasetIncome.put("backgroundColor", "red");
    	datasetIncome.put("data", listIncomeDataSet);
    	
    	datasetExpense.put("label", "D??penses");
    	datasetExpense.put("backgroundColor", "blue");
    	datasetExpense.put("data", listExpenseDataSet);
    	
    	//Nous alimentons notre dataset avec les incomes et expenses
    	Collections.addAll(dataset, datasetIncome, datasetExpense);
    	

    	chartDataSet.put("chartLabels", listChartLabels);
    	chartDataSet.put("chartTypeInit", "line");
    	chartDataSet.put("dataset", dataset);
    	
    	
    	return chartDataSet;
    }
    
    
    
	
}