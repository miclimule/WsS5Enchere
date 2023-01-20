package com.example.demo.model;

import java.time.LocalDate;

public class Enchere {

	int id;
	
	String description;
	
	int prixdepart;
	
	LocalDate datedepartenchere ;
	
	int dureeenchere;
	
	int prixmax;
	
	int prixmin;
	
	int dureemin;
	
	int idmaterielle;
	
	int isfinish;
	
	int idGagnant;
	
	int montantpayer;
	
	LocalDate dateajout;

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

	public int getPrixdepart() {
		return prixdepart;
	}

	public void setPrixdepart(int prixdepart) {
		this.prixdepart = prixdepart;
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

	public int getPrixmax() {
		return prixmax;
	}

	public void setPrixmax(int prixmax) {
		this.prixmax = prixmax;
	}

	public int getPrixmin() {
		return prixmin;
	}

	public void setPrixmin(int prixmin) {
		this.prixmin = prixmin;
	}

	public int getDureemin() {
		return dureemin;
	}

	public void setDureemin(int dureemin) {
		this.dureemin = dureemin;
	}

	

	public int getIdmaterielle() {
		return idmaterielle;
	}

	public void setIdmaterielle(int idmaterielle) {
		this.idmaterielle = idmaterielle;
	}
	
	
	
}
