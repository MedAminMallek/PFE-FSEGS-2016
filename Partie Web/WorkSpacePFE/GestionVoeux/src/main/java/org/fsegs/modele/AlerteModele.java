package org.fsegs.modele;

import java.util.Collection;

import org.fsegs.entitees.Alerte;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Examen;

public class AlerteModele {
	
	private Alerte alerte;
	private Examen examen ;
	private Collection<Enseignant> enseignants;
	
	public Collection<Enseignant> getEnseignants() {
		return enseignants;
	}
	public void setEnseignants(Collection<Enseignant> enseignants) {
		this.enseignants = enseignants;
	}
	public Alerte getAlerte() {
		return alerte;
	}
	public void setAlerte(Alerte alerte) {
		this.alerte = alerte;
	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	public AlerteModele(Alerte alerte, Examen examen) {
		super();
		this.alerte = alerte;
		this.examen = examen;
		//this.enseignants = enseignants;
	}
	

}
