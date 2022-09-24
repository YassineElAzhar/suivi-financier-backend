package com.yasselazhar.suivifinancier.repository;

import com.yasselazhar.suivifinancier.model.TypeEvent;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Yassine EL-AZHAR
 */

@Repository
public interface TypeEventRepository extends JpaRepository<TypeEvent, Integer> {
	TypeEvent findByIsExpenseAndId(int isExpense,int id);
	TypeEvent findByIsIncomeAndId(int isIncome,int id);

}
