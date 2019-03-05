package org.fsegs.modele;

import org.fsegs.entitees.JourExamen;

public class JourModele {
	
	private JourExamen jour;
	private int nbAbsence;
	public JourExamen getJour() {
		return jour;
	}
	public void setJour(JourExamen jour) {
		this.jour = jour;
	}
	public int getNbAbsence() {
		return nbAbsence;
	}
	public void setNbAbsence(int nbAbsence) {
		this.nbAbsence = nbAbsence;
	}
	public JourModele(JourExamen jour, int nbAbsence) {
		super();
		this.jour = jour;
		this.nbAbsence = nbAbsence;
	}
	public JourModele() {
		super();
	}
	
	

}
