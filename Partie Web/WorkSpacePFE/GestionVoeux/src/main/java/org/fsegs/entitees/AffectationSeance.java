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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="AffectationSeance")
public class AffectationSeance implements Serializable,Comparable<AffectationSeance>{
	
	
	@Id
	@ManyToOne
	private Enseignant enseignant;
	@Id
	private Date dateJour;
	@Id
	private Time heureDebut;
	private boolean presence;
	
	
	
	public boolean isPresence() {
		return presence;
	}
	public void setPresence(boolean presence) {
		this.presence = presence;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
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
	public AffectationSeance() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateJour == null) ? 0 : dateJour.hashCode());
		result = prime * result
				+ ((enseignant == null) ? 0 : enseignant.hashCode());
		result = prime * result
				+ ((heureDebut == null) ? 0 : heureDebut.hashCode());
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
		AffectationSeance other = (AffectationSeance) obj;
		if (dateJour == null) {
			if (other.dateJour != null)
				return false;
		} else if (!dateJour.equals(other.dateJour))
			return false;
		if (enseignant == null) {
			if (other.enseignant != null)
				return false;
		} else if (!enseignant.equals(other.enseignant))
			return false;
		if (heureDebut == null) {
			if (other.heureDebut != null)
				return false;
		} else if (!heureDebut.equals(other.heureDebut))
			return false;
		return true;
	}
	public AffectationSeance(Enseignant enseignant, Date dateJour,
			Time heureDebut) {
		super();
		this.enseignant = enseignant;
		this.dateJour = dateJour;
		this.heureDebut = heureDebut;
	}
	@Override
	public int compareTo(AffectationSeance o) {
		// TODO Auto-generated method stub
		if(this.dateJour.before(o.getDateJour()))
			return -1;
		if(!this.dateJour.before(o.getDateJour()))
			return 1;
		return 0;
		
	}
	
	public String date()
	{
		JourExamen j = new JourExamen();
		j.setDate(dateJour);
		
		return j.dateJour();
	}
	public String heure()
	{
		return heureDebut.toString().split(":")[0]+":"+heureDebut.toString().split(":")[1];
	}
	
	

}
