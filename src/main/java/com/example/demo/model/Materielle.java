package com.example.demo.model;

import java.time.LocalDate;

public class Materielle {

	int id;
	
	String nom;
	
	int prixMinimal;
	
	LocalDate datedebut;
	
	int duree;
	
	int idcategory;
	
	int idClient;
	
	int qte;
	

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrixMinimal() {
		return prixMinimal;
	}

	public void setPrixMinimal(int prixMinimal) {
		this.prixMinimal = prixMinimal;
	}

	public LocalDate getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(LocalDate datedebut) {
		this.datedebut = datedebut;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getIdcategory() {
		return idcategory;
	}

	public void setIdcategory(int idcategory) {
		this.idcategory = idcategory;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Materielle() {
		super();
	}
	
	
}
