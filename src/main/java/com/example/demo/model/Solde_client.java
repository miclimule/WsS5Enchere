package com.example.demo.model;

public class Solde_client {

	int idClient;
	
	int montant;
	
	int isvalidate;

	String nom;
	
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getIsvalidate() {
		return isvalidate;
	}

	public void setIsvalidate(int isvalidate) {
		this.isvalidate = isvalidate;
	}

	public Solde_client() {
		super();
	}
	
	
	
}
