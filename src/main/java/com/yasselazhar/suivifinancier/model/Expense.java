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
@Table(name = "expense")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"dinsert", "dupdate"},
        allowGetters = true)
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int type;
    
    private int userId;

    @NotBlank
    private String destinataire;

    @NotBlank
    private String titre;

    private int montant;

    private Date dateExpense;


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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Date getDateExpense() {
		return dateExpense;
	}

	public void setDateExpense(Date dateExpense) {
		this.dateExpense = dateExpense;
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
		return "Expense [id=" + id + ", userId=" + userId + ", type=" + type + ", destinataire=" + destinataire + ", titre=" + titre
				+ ", montant=" + montant + ", dateExpense=" + dateExpense + ", dinsert=" + dinsert + ", dupdate="
				+ dupdate + "]";
	} 

    

}
