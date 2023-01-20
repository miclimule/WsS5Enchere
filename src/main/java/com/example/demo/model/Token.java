package com.example.demo.model;

import java.time.LocalDate;

public class Token {

	int idclient;
	
	String value;
	
	LocalDate dateajout;

	public Token() {
		super();
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDate getDateajout() {
		return dateajout;
	}

	public void setDateajout(LocalDate dateajout) {
		this.dateajout = dateajout;
	}
	
	
	
}
