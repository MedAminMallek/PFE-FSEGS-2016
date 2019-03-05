package org.fsegs.entitees;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Semestre")
public class Semestre implements Serializable{
	@Id
	private int id;
	private String semestre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	
	public Semestre(int id, String semestre) {
		super();
		this.id = id;
		this.semestre = semestre;
	
	}
	public Semestre() {
		super();
	}
	
	
}
