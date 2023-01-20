package com.example.demo.model;

import java.time.LocalDate;

public class Enchere {

	int id;
	
	String description;
	
	LocalDate datedepartenchere ;
	
	int dureeenchere;
		
	int idmaterielle;
	
	int isfinish;
	
	int idGagnant;
	
	int montantpayer;
	
	LocalDate dateajout;
	
	int commision;
	
	int qte;
	
	

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public int getCommision() {
		return commision;
	}

	public void setCommision(int commision) {
		this.commision = commision;
	}

	public Enchere(int id, String description, LocalDate datedepartenchere, int dureeenchere, int idmaterielle,
			int isfinish, int idGagnant, int montantpayer, LocalDate dateajout, int commision) {
		super();
		this.id = id;
		this.description = description;
		this.datedepartenchere = datedepartenchere;
		this.dureeenchere = dureeenchere;
		this.idmaterielle = idmaterielle;
		this.isfinish = isfinish;
		this.idGagnant = idGagnant;
		this.montantpayer = montantpayer;
		this.dateajout = dateajout;
		this.commision = commision;
	}

	public int getIdGagnant() {
		return idGagnant;
	}

	public void setIdGagnant(int idGagnant) {
		this.idGagnant = idGagnant;
	}

	public int getMontantpayer() {
		return montantpayer;
	}

	public void setMontantpayer(int montantpayer) {
		this.montantpayer = montantpayer;
	}

	public LocalDate getDateajout() {
		return dateajout;
	}

	public void setDateajout(LocalDate dateajout) {
		this.dateajout = dateajout;
	}

	public int getIsfinish() {
		return isfinish;
	}

	public void setIsfinish(int isfinish) {
		this.isfinish = isfinish;
	}

	public Enchere() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public LocalDate getDatedepartenchere() {
		return datedepartenchere;
	}

	public void setDatedepartenchere(LocalDate datedepartenchere) {
		this.datedepartenchere = datedepartenchere;
	}

	public int getDureeenchere() {
		return dureeenchere;
	}

	public void setDureeenchere(int dureeenchere) {
		this.dureeenchere = dureeenchere;
	}

	

	

	public int getIdmaterielle() {
		return idmaterielle;
	}

	public void setIdmaterielle(int idmaterielle) {
		this.idmaterielle = idmaterielle;
	}
	
	
	
}
