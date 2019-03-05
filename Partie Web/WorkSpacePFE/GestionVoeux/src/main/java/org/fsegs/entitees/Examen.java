package org.fsegs.entitees;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Examen")
public class Examen implements Serializable{
	
	
	@Id
	@ManyToOne
	@JoinColumn(name="salle",referencedColumnName="id_salle")
	private Salle salle;
	@Id
	@ManyToOne
	@JoinColumns({
	@JoinColumn(name="date",referencedColumnName="date"),
	@JoinColumn(name="heureDebut",referencedColumnName="heureDebut")
	})
	private Seance seance;
	@Id
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="matiere",referencedColumnName="matiere_id"),
		@JoinColumn(name="filiere",referencedColumnName="filiere_id"),
		@JoinColumn(name="cycleAnnee",referencedColumnName="cycleAnnee_id")
		})
	private MatiereEnseignement matiereEnseignement;
	
	
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	public Seance getSeance() {
		return seance;
	}
	public void setSeance(Seance seance) {
		this.seance = seance;
	}
	public Examen() {
		super();
	}
	public MatiereEnseignement getMatiereEnseignement() {
		return matiereEnseignement;
	}
	public void setMatiereEnseignement(MatiereEnseignement matiereEnseignement) {
		this.matiereEnseignement = matiereEnseignement;
	}
	public Examen(Salle salle, Seance seance,
			MatiereEnseignement matiereEnseignement) {
		super();
		this.salle = salle;
		this.seance = seance;
		this.matiereEnseignement = matiereEnseignement;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((matiereEnseignement == null) ? 0 : matiereEnseignement
						.hashCode());
		result = prime * result + ((salle == null) ? 0 : salle.hashCode());
		result = prime * result + ((seance == null) ? 0 : seance.hashCode());
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
		Examen other = (Examen) obj;
		if (matiereEnseignement == null) {
			if (other.matiereEnseignement != null)
				return false;
		} else if (!matiereEnseignement.equals(other.matiereEnseignement))
			return false;
		if (salle == null) {
			if (other.salle != null)
				return false;
		} else if (!salle.equals(other.salle))
			return false;
		if (seance == null) {
			if (other.seance != null)
				return false;
		} else if (!seance.equals(other.seance))
			return false;
		return true;
	}
	
	
	
	

}
