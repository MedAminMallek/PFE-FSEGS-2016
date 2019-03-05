package org.fsegs.modele;

import org.fsegs.entitees.Enseignant;

public class NbAbsence implements Comparable<NbAbsence>{
	
	private Enseignant enseignant ;
	private int nbAbsence;
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public int getNbAbsence() {
		return nbAbsence;
	}
	public void setNbAbsence(int nbAbsence) {
		this.nbAbsence = nbAbsence;
	}
	public NbAbsence(Enseignant enseignant, int nbAbsence) {
		super();
		this.enseignant = enseignant;
		this.nbAbsence = nbAbsence;
	}
	
	@Override
	public int compareTo(NbAbsence NbAb) {
		// TODO Auto-generated method stub
		return -1*(this.nbAbsence - NbAb.getNbAbsence());
	}
	
	

}
