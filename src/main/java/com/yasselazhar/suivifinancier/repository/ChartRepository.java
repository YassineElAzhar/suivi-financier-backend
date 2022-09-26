package com.yasselazhar.suivifinancier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yasselazhar.suivifinancier.model.ChartInOut;
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
	
}
