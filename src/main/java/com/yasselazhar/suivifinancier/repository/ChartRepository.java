package com.yasselazhar.suivifinancier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yasselazhar.suivifinancier.model.ChartIn;
import com.yasselazhar.suivifinancier.model.ChartInOut;
import com.yasselazhar.suivifinancier.model.ChartOut;
import com.yasselazhar.suivifinancier.model.Income;

/**
 * Created by Yassine EL-AZHAR
 */
@Repository
public interface ChartRepository extends JpaRepository<Income, Integer> {

	//Pour les requête JPQL query nous utilisons les projections (en passant par des interfaces)
	@Query(value = "SELECT to_char(income.date_income, 'yyyy-mm') as yearMonth, "
			+ "SUM(income.montant) as sommeMontant, "
			+ "to_char(income.date_income, 'yyyy') as year "
			+ "FROM income "
			+ "group by to_char(income.date_income, 'yyyy-mm'), to_char(income.date_income, 'yyyy') "
			+ "HAVING to_char(income.date_income, 'yyyy') = to_char(CURRENT_DATE,'yyyy') "
			+ "ORDER BY to_char(income.date_income, 'yyyy-mm') ASC;",
			  nativeQuery = true)
	List<ChartInOut> getChartInOutIncomesCurrentYear();
	


	//Pour les requête JPQL query nous utilisons les projections (en passant par des interfaces)
	@Query(value = "SELECT to_char(expense.date_expense, 'yyyy-mm') as yearMonth, "
			+ "SUM(expense.montant) as sommeMontant, "
			+ "to_char(expense.date_expense, 'yyyy') as year "
			+ "FROM expense "
			+ "group by to_char(expense.date_expense, 'yyyy-mm'), to_char(expense.date_expense, 'yyyy') "
			+ "HAVING to_char(expense.date_expense, 'yyyy') = to_char(CURRENT_DATE,'yyyy') "
			+ "ORDER BY to_char(expense.date_expense, 'yyyy-mm') ASC;",
				nativeQuery = true)
	List<ChartInOut> getChartInOutExpensesCurrentYear();
	
	
	
	
	@Query(value = "SELECT type_event.type as typeEvent, SUM(expense.montant) as sommeMontant, to_char(expense.date_expense, 'yyyy-mm') yearMonth "
			+ "FROM expense "
			+ "INNER JOIN type_event ON type_event.id = expense.type "
			+ "GROUP BY type_event.type,to_char(expense.date_expense, 'yyyy-mm') "
			+ "HAVING to_char(expense.date_expense, 'yyyy-mm') = to_char(CURRENT_DATE, 'yyyy-mm')",
			nativeQuery = true)
	List<ChartOut> getChartOutCurrentMonth();
	
	
	
	
	@Query(value = "SELECT type_event.type as typeEvent, SUM(income.montant) as sommeMontant, to_char(income.date_income, 'yyyy-mm') yearMonth "
			+ "FROM income "
			+ "INNER JOIN type_event ON type_event.id = income.type "
			+ "GROUP BY type_event.type,to_char(income.date_income, 'yyyy-mm') "
			+ "HAVING to_char(income.date_income, 'yyyy-mm') = to_char(CURRENT_DATE, 'yyyy-mm')",
			nativeQuery = true)
	List<ChartIn> getChartInCurrentMonth();

	
}
