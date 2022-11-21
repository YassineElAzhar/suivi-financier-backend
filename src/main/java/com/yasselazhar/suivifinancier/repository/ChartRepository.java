package com.yasselazhar.suivifinancier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	@Query(value = "SELECT DATE_FORMAT(income.date_income, '%Y-%m') as yearMonth, SUM(income.montant) as sommeMontant FROM income WHERE user_id = :userId group by DATE_FORMAT(income.date_income, '%Y-%m') ASC HAVING yearMonth = year(curdate())",
			  nativeQuery = true)
	List<ChartInOut> getChartInOutIncomesCurrentYearByUserId(@Param("userId") int userId);
	

	

	//Pour les requête JPQL query nous utilisons les projections (en passant par des interfaces)
	@Query(value = "SELECT DATE_FORMAT(expense.date_expense, '%Y-%m') as yearMonth, SUM(expense.montant) as sommeMontant FROM expense WHERE user_id = :userId group by DATE_FORMAT(expense.date_expense, '%Y-%m') ASC HAVING yearMonth = year(curdate())",
			  nativeQuery = true)
	List<ChartInOut> getChartInOutExpensesCurrentYearByUserId(@Param("userId") int userId);
	
	
	@Query(value = "SELECT DATE_FORMAT(expense.date_expense, '%Y-%m') yearMonth, type_event.type as typeEvent, SUM(expense.montant) as sommeMontant FROM expense INNER JOIN type_event ON type_event.id = expense.type WHERE user_id = :userId GROUP BY DATE_FORMAT(expense.date_expense, '%Y-%m'), type_event.type HAVING yearMonth = DATE_FORMAT(CURRENT_DATE, '%Y-%m')",
			nativeQuery = true)
	List<ChartOut> getChartOutCurrentMonthByUserId(@Param("userId") int userId);
	
	
	@Query(value = "SELECT DATE_FORMAT(income.date_income, '%Y-%m') yearMonth, type_event.type as typeEvent, SUM(income.montant) as sommeMontant FROM income INNER JOIN type_event ON type_event.id = income.type WHERE user_id = :userId GROUP BY DATE_FORMAT(income.date_income, '%Y-%m'), type_event.type HAVING yearMonth = DATE_FORMAT(CURRENT_DATE, '%Y-%m')",
			nativeQuery = true)
	List<ChartIn> getChartInCurrentMonthByUserId(@Param("userId") int userId);
}
