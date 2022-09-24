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
@Table(name = "type_event")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"dinsert", "dupdate"},
        allowGetters = true)
public class TypeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String type;

    private int isExpense;

    private int isIncome;

    private int isEvent;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIsExpense() {
        return isExpense;
    }

    public void setIsExpense(int isExpense) {
        this.isExpense = isExpense;
    }

    public int getIsIncome() {
        return isIncome;
    }

    public void setIsIncome(int isIncome) {
        this.isIncome = isIncome;
    }

    public int getIsEvent() {
        return isEvent;
    }

    public void setIsEvent(int isEvent) {
        this.isEvent = isEvent;
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
		return "TypeEvent [id=" + id + ", type=" + type + ", isExpense=" + isExpense + ", isIncome=" + isIncome
				+ ", isEvent=" + isEvent + ", dinsert=" + dinsert + ", dupdate=" + dupdate + "]";
	}
    
}
