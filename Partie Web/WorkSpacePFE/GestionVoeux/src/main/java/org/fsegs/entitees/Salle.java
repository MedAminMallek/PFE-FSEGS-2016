package org.fsegs.entitees;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Salle")
public class Salle implements Serializable{
	
	@Id
	@Column(name="id_salle")
	private String id;
	private String nature;
	private String bloc;
	private int capacite;
	private int capaciteExamen;
	private int nbSurveillant;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getBloc() {
		return bloc;
	}
	public void setBloc(String bloc) {
		this.bloc = bloc;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public int getCapaciteExamen() {
		return capaciteExamen;
	}
	public void setCapaciteExamen(int capaciteExamen) {
		this.capaciteExamen = capaciteExamen;
	}
	public int getNbSurveillant() {
		return nbSurveillant;
	}
	public void setNbSurveillant(int nbSurveillant) {
		this.nbSurveillant = nbSurveillant;
	}
	public Salle() {
		super();
	}
	public Salle(String id, String nature, String bloc, int capacite,
			int capaciteExamen, int nbSurveillant) {
		super();
		this.id = id;
		this.nature = nature;
		this.bloc = bloc;
		this.capacite = capacite;
		this.capaciteExamen = capaciteExamen;
		this.nbSurveillant = nbSurveillant;
	}
	
	
	
	

}
