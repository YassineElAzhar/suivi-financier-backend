package com.yasselazhar.suivifinancier.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yasselazhar.suivifinancier.model.Income;

/**
 * Created by Yassine EL-AZHAR
 */

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
	
	@Query(value = "SELECT DATE_FORMAT(income.date_income, '%Y-%m') as yearMonthIncome, SUM(income.montant) as sommeMontant FROM income group by DATE_FORMAT(income.date_income, '%Y-%m')",
			  nativeQuery = true)
	List<Map<String, Integer>> getChartInOut();
	
}
