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
	@Query(value = "SELECT DATE_FORMAT(income.date_income, '%Y-%m') as yearMonth, SUM(income.montant) as sommeMontant FROM income group by DATE_FORMAT(income.date_income, '%Y-%m') ASC HAVING yearMonth = year(curdate())",
			  nativeQuery = true)
	List<ChartInOut> getChartInOutIncomesCurrentYear();
	


	//Pour les requête JPQL query nous utilisons les projections (en passant par des interfaces)
	@Query(value = "SELECT DATE_FORMAT(expense.date_expense, '%Y-%m') as yearMonth, SUM(expense.montant) as sommeMontant FROM expense group by DATE_FORMAT(expense.date_expense, '%Y-%m') ASC HAVING yearMonth = year(curdate())",
			  nativeQuery = true)
	List<ChartInOut> getChartInOutExpensesCurrentYear();
	
}
