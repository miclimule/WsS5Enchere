package com.example.demo.model;

public class Admins {

	int id;
	
	String nom;
	
	String mdp;

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

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Admins(int id, String nom, String mdp) {
		super();
		this.id = id;
		this.nom = nom;
		this.mdp = mdp;
	}

	public Admins() {
		super();
	}
	
}
