package org.fsegs.entitees;

import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Entity
@Table(name="Seance")
public class Seance implements Serializable{
	
	
	
	@Id
	private Time heureDebut;
	@Id
	@ManyToOne
	@JoinColumn(name="date",referencedColumnName="date")
	private JourExamen jourExamen;
	
	private int nombreSurveillant;
	private int nombreMembreC;
	
	
	public Time getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
	public int getNombreSurveillant() {
		return nombreSurveillant;
	}
	public void setNombreSurveillant(int nombreSurveillant) {
		this.nombreSurveillant = nombreSurveillant;
	}
	public int getNombreMembreC() {
		return nombreMembreC;
	}
	public void setNombreMembreC(int nombreMembreC) {
		this.nombreMembreC = nombreMembreC;
	}
	
	
	public JourExamen getJourExamen() {
		return jourExamen;
	}
	public void setJourExamen(JourExamen jourExamen) {
		this.jourExamen = jourExamen;
	}
	
	public Seance() {
		super();
	}
	public Seance( JourExamen jourExamen,Time heureDebut,
			int nombreSurveillant, int nombreMembreC) {
		super();
		this.heureDebut = heureDebut;
		this.jourExamen = jourExamen;
		this.nombreSurveillant = nombreSurveillant;
		this.nombreMembreC = nombreMembreC;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((heureDebut == null) ? 0 : heureDebut.hashCode());
		result = prime * result
				+ ((jourExamen == null) ? 0 : jourExamen.hashCode());
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
		Seance other = (Seance) obj;
		if (heureDebut == null) {
			if (other.heureDebut != null)
				return false;
		} else if (!heureDebut.equals(other.heureDebut))
			return false;
		if (jourExamen == null) {
			if (other.jourExamen != null)
				return false;
		} else if (!jourExamen.equals(other.jourExamen))
			return false;
		return true;
	}
	
	public String getJSON()
	{
		String tabH[] = heureDebut.toString().split(":");
		String tabD[] = jourExamen.getDate().toString().split(" ");
		return "{\"date\":\""+tabD[0]+"\",\"heure\":\""+tabH[0]+":"+tabH[1]+"\"}";
	}
	
	
	
}
