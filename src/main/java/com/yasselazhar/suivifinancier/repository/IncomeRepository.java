package com.yasselazhar.suivifinancier.repository;

import com.yasselazhar.suivifinancier.model.Income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Yassine EL-AZHAR
 */

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

}
