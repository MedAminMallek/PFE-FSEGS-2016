package org.fsegs.entitees;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="MatiereEnseignement")
public class MatiereEnseignement implements Serializable{
	
	@Id
	@ManyToOne
	private Matiere matiere;
	@Id
	@ManyToOne
	private Filiére filiere;
	@Id
	@ManyToOne
	private CycleAnnee cycleAnnee;
	@ManyToOne
	private Enseignant enseignant;
	
	
	
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public Filiére getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiére filiere) {
		this.filiere = filiere;
	}
	public CycleAnnee getCycleAnnee() {
		return cycleAnnee;
	}
	public void setCycleAnnee(CycleAnnee cycleAnnee) {
		this.cycleAnnee = cycleAnnee;
	}
	public MatiereEnseignement() {
		super();
	}
	public MatiereEnseignement(Matiere matiere, Filiére filiere,
			CycleAnnee cycleAnnee, Enseignant enseignant) {
		super();
		this.matiere = matiere;
		this.filiere = filiere;
		this.cycleAnnee = cycleAnnee;
		this.enseignant = enseignant;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cycleAnnee == null) ? 0 : cycleAnnee.hashCode());
		result = prime * result + ((filiere == null) ? 0 : filiere.hashCode());
		result = prime * result + ((matiere == null) ? 0 : matiere.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatiereEnseignement other = (MatiereEnseignement) obj;
		if (cycleAnnee == null) {
			if (other.cycleAnnee != null)
				return false;
		} else if (!cycleAnnee.equals(other.cycleAnnee))
			return false;
		if (filiere == null) {
			if (other.filiere != null)
				return false;
		} else if (!filiere.equals(other.filiere))
			return false;
		if (matiere == null) {
			if (other.matiere != null)
				return false;
		} else if (!matiere.equals(other.matiere))
			return false;
		return true;
	}
	
	

}
