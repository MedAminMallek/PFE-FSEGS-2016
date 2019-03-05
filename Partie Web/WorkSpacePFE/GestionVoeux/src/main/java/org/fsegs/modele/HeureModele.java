package org.fsegs.modele;

import java.sql.Time;

public class HeureModele {
	
	private Time heure;
	private int nbAbsence;
	public Time getHeure() {
		return heure;
	}
	public void setHeure(Time heure) {
		this.heure = heure;
	}
	public int getNbAbsence() {
		return nbAbsence;
	}
	public void setNbAbsence(int nbAbsence) {
		this.nbAbsence = nbAbsence;
	}
	public HeureModele(Time heure, int nbAbsence) {
		super();
		this.heure = heure;
		this.nbAbsence = nbAbsence;
	}
	public HeureModele() {
		super();
	}
	
	
	

}
