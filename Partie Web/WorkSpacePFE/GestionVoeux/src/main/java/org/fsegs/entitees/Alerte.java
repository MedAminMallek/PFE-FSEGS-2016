package org.fsegs.entitees;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Alerte")
public class Alerte implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private TypeAlerte type;
	@ManyToOne
	private Enseignant surveillant;
	private Date date;
	private Time heure;
	private String description;
	
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getHeure() {
		return heure;
	}
	public void setHeure(Time heure) {
		this.heure = heure;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TypeAlerte getType() {
		return type;
	}
	public void setType(TypeAlerte type) {
		this.type = type;
	}
	public Enseignant getSurveillant() {
		return surveillant;
	}
	public void setSurveillant(Enseignant surveillant) {
		this.surveillant = surveillant;
	}
	public Alerte(Long id, TypeAlerte type, Enseignant surveillant) {
		super();
		this.id = id;
		this.type = type;
		this.surveillant = surveillant;
	}
	public Alerte() {
		super();
	}
	
}
