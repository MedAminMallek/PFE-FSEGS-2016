package org.fsegs.entitees;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="AffectationExamen")
public class AffectationExamen implements Serializable{
	
	
	
	//@Id
	@ManyToOne
	private Salle salleExamen;
	//@Id
	@ManyToOne
	private Matiere matiere;
	//@Id
	@ManyToOne
	private Filiére filiere;
	//@Id
	@ManyToOne
	private CycleAnnee cycleAnnee;
	@Id
	private Date dateJour;
	@Id
	private Time heureDebut;
	@Id
	@ManyToOne
	private Enseignant surveillant;
	
	private Time heureReelAff;


	public Enseignant getSurveillant() {
		return surveillant;
	}

	public void setSurveillant(Enseignant surveillant) {
		this.surveillant = surveillant;
	}

	public Time getHeureReelAff() {
		return heureReelAff;
	}

	public void setHeureReelAff(Time heureReelAff) {
		this.heureReelAff = heureReelAff;
	}

	

	public AffectationExamen() {
		super();
	}

	public Salle getSalleExamen() {
		return salleExamen;
	}

	public void setSalleExamen(Salle salleExamen) {
		this.salleExamen = salleExamen;
	}

	public Date getDateJour() {
		return dateJour;
	}

	public void setDateJour(Date dateJour) {
		this.dateJour = dateJour;
	}

	public Time getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
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

	public AffectationExamen(Salle salleExamen, Matiere matiere,
			Filiére filiere, CycleAnnee cycleAnnee, Date dateJour,
			Time heureDebut, Enseignant surveillant, Time heureReelAff) {
		super();
		this.salleExamen = salleExamen;
		this.matiere = matiere;
		this.filiere = filiere;
		this.cycleAnnee = cycleAnnee;
		this.dateJour = dateJour;
		this.heureDebut = heureDebut;
		this.surveillant = surveillant;
		this.heureReelAff = heureReelAff;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cycleAnnee == null) ? 0 : cycleAnnee.hashCode());
		result = prime * result
				+ ((dateJour == null) ? 0 : dateJour.hashCode());
		result = prime * result + ((filiere == null) ? 0 : filiere.hashCode());
		result = prime * result
				+ ((heureDebut == null) ? 0 : heureDebut.hashCode());
		result = prime * result
				+ ((heureReelAff == null) ? 0 : heureReelAff.hashCode());
		result = prime * result + ((matiere == null) ? 0 : matiere.hashCode());
		result = prime * result
				+ ((salleExamen == null) ? 0 : salleExamen.hashCode());
		result = prime * result
				+ ((surveillant == null) ? 0 : surveillant.hashCode());
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
		AffectationExamen other = (AffectationExamen) obj;
		if (cycleAnnee == null) {
			if (other.cycleAnnee != null)
				return false;
		} else if (!cycleAnnee.equals(other.cycleAnnee))
			return false;
		if (dateJour == null) {
			if (other.dateJour != null)
				return false;
		} else if (!dateJour.equals(other.dateJour))
			return false;
		if (filiere == null) {
			if (other.filiere != null)
				return false;
		} else if (!filiere.equals(other.filiere))
			return false;
		if (heureDebut == null) {
			if (other.heureDebut != null)
				return false;
		} else if (!heureDebut.equals(other.heureDebut))
			return false;
		if (heureReelAff == null) {
			if (other.heureReelAff != null)
				return false;
		} else if (!heureReelAff.equals(other.heureReelAff))
			return false;
		if (matiere == null) {
			if (other.matiere != null)
				return false;
		} else if (!matiere.equals(other.matiere))
			return false;
		if (salleExamen == null) {
			if (other.salleExamen != null)
				return false;
		} else if (!salleExamen.equals(other.salleExamen))
			return false;
		if (surveillant == null) {
			if (other.surveillant != null)
				return false;
		} else if (!surveillant.equals(other.surveillant))
			return false;
		return true;
	}
	
	
	
	
	

}
