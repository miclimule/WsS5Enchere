package com.example.demo.model;

import java.time.LocalDate;

public class Histo_enchere {

	int idenchere;
	
	int idgagnant;
	
	int montantpayer;
	
	LocalDate dateajout;

	public int getIdenchere() {
		return idenchere;
	}

	public void setIdenchere(int idenchere) {
		this.idenchere = idenchere;
	}

	public int getIdgagnant() {
		return idgagnant;
	}

	public void setIdgagnant(int idgagnant) {
		this.idgagnant = idgagnant;
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

	public Histo_enchere() {
		super();
	}
	
	
	
}
