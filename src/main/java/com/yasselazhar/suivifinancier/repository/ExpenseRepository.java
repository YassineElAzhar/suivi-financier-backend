package com.yasselazhar.suivifinancier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yasselazhar.suivifinancier.model.Expense;

/**
 * Created by Yassine EL-AZHAR
 */

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
	List<Expense> findAllByUserId(int userId);
}
