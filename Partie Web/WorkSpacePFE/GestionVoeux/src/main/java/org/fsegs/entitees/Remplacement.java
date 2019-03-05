package org.fsegs.entitees;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Remplacement")
public class Remplacement implements Serializable{
	
	
	@Id
	private Date dateJour;
	@Id
	private Time heureDebut;
	@Id
	@ManyToOne
	private Enseignant surveillant;	
	@Id
	@ManyToOne
	private Enseignant remplacant;
	
	public Enseignant getRemplacant() {
		return remplacant;
	}
	public void setRemplacant(Enseignant remplacant) {
		this.remplacant = remplacant;
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
	public Enseignant getSurveillant() {
		return surveillant;
	}
	public void setSurveillant(Enseignant surveillant) {
		this.surveillant = surveillant;
	}
	public Remplacement(Date dateJour,
			Time heureDebut, Enseignant surveillant, Enseignant remplacant) {
		super();
		this.dateJour = dateJour;
		this.heureDebut = heureDebut;
		this.surveillant = surveillant;
		this.remplacant = remplacant;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateJour == null) ? 0 : dateJour.hashCode());
		result = prime * result
				+ ((heureDebut == null) ? 0 : heureDebut.hashCode());
		result = prime * result
				+ ((remplacant == null) ? 0 : remplacant.hashCode());
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
		Remplacement other = (Remplacement) obj;
		if (dateJour == null) {
			if (other.dateJour != null)
				return false;
		} else if (!dateJour.equals(other.dateJour))
			return false;
		if (heureDebut == null) {
			if (other.heureDebut != null)
				return false;
		} else if (!heureDebut.equals(other.heureDebut))
			return false;
		if (remplacant == null) {
			if (other.remplacant != null)
				return false;
		} else if (!remplacant.equals(other.remplacant))
			return false;
		if (surveillant == null) {
			if (other.surveillant != null)
				return false;
		} else if (!surveillant.equals(other.surveillant))
			return false;
		return true;
	}
	public Remplacement() {
		super();
	}
	
	
	

}
