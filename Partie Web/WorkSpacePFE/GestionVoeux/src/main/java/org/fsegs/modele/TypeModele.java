package org.fsegs.modele;

import org.fsegs.entitees.TypeAlerte;

public class TypeModele {
	
	private String libelle ;
	private int nbAlerte;
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public int getNbAlerte() {
		return nbAlerte;
	}
	public void setNbAlerte(int nbAlerte) {
		this.nbAlerte = nbAlerte;
	}
	public TypeModele(String libelle, int nbAlerte) {
		super();
		this.libelle = libelle;
		this.nbAlerte = nbAlerte;
	}
	public TypeModele() {
		super();
	}
	
	

}
