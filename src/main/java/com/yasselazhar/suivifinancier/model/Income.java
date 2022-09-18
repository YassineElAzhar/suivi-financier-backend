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
@Table(name = "income")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"dinsert", "dupdate"},
        allowGetters = true)
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type", referencedColumnName = "id")
    private TypeIncome type;

    @NotBlank
    private String provenance;

    @NotBlank
    private String titre;

    private int montant;

    private Date dateIncome;


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

	public TypeIncome getType() {
		return type;
	}

	public void setType(TypeIncome type) {
		this.type = type;
	}


	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
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

	public Date getDateIncome() {
		return dateIncome;
	}

	public void setDateIncome(Date dateIncome) {
		this.dateIncome = dateIncome;
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
		return "Income [id=" + id + ", type=" + type + ", provenance=" + provenance + ", titre=" + titre + ", montant="
				+ montant + ", dateIncome=" + dateIncome + ", dinsert=" + dinsert + ", dupdate=" + dupdate + "]";
	}
    
    

    

}
