package org.fsegs.entitees;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CycleAnnee")
public class CycleAnnee implements Serializable {
	
	@Id
	private int id;
	private String annee;
	private String cycle;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAnnee() {
		return annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	
	public CycleAnnee() {
		super();
	}
	public CycleAnnee(int id, String annee, String cycle) {
		super();
		this.id = id;
		this.annee = annee;
		this.cycle = cycle;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		CycleAnnee other = (CycleAnnee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	

}
