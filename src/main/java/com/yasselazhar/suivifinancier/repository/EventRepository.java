package com.yasselazhar.suivifinancier.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yasselazhar.suivifinancier.model.Event;

/**
 * Created by Yassine EL-AZHAR
 */

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	List<Event> findByDateEventBetween(Date start, Date end);
}
