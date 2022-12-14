package com.yasselazhar.suivifinancier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yasselazhar.suivifinancier.model.Income;

/**
 * Created by Yassine EL-AZHAR
 */
@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
	
}
