package org.fsegs.modele;

import java.util.Collection;

import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Examen;

public class ExamenSalle {
	
	private Examen examen;
	private Enseignant affecte;
	private Collection<Enseignant> enseignant;
	private Long nbAff;
	
	
	
	
	public Long getNbAff() {
		return nbAff;
	}
	public void setNbAff(Long nbAff) {
		this.nbAff = nbAff;
	}
	public Enseignant getAffecte() {
		return affecte;
	}
	public void setAffecte(Enseignant affecte) {
		this.affecte = affecte;
	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	public Collection<Enseignant> getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Collection<Enseignant> enseignant) {
		this.enseignant = enseignant;
	}
	
	

}
