package org.fsegs.entitees;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JourEnseignement")
public class JourEnseignement implements Serializable{
	
	@Id
	private int id;
	private String jour;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJour() {
		return jour;
	}
	public void setJour(String jour) {
		this.jour = jour;
	}
	public JourEnseignement(int id, String jour) {
		super();
		this.id = id;
		this.jour = jour;
	}
	public JourEnseignement() {
		super();
	}
	
	
}
