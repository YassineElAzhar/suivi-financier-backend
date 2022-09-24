package com.yasselazhar.suivifinancier.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by Yassine EL-AZHAR
 */
@Entity
@Table(name = "event")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"dinsert", "dupdate"},
        allowGetters = true)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int type;

    @NotBlank
    private String titre;

    private Date dateEvent;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;
    
    private int expenseId;
    
    private int incomeId;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dinsert;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date dupdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}

	public Date getDinsert() {
		return dinsert;
	}

	public void setDinsert(Date dinsert) {
		this.dinsert = dinsert;
	}

	public Date getDupdate() {
		return dupdate;
	}

	public void setDupdate(Date dupdate) {
		this.dupdate = dupdate;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", titre=" + titre + ", dateEvent=" + dateEvent + ", startTime="
				+ startTime + ", endTime=" + endTime + ", expenseId=" + expenseId + ", incomeId=" + incomeId
				+ ", dinsert=" + dinsert + ", dupdate=" + dupdate + "]";
	}
	
	
}
