package org.fsegs.entitees;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TypeAlerte")
public class TypeAlerte implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	private boolean membreCommssion;
	private boolean enseignantResponsable;
	
	public boolean isMembreCommssion() {
		return membreCommssion;
	}
	public void setMembreCommssion(boolean membreCommssion) {
		this.membreCommssion = membreCommssion;
	}
	public boolean isEnseignantResponsable() {
		return enseignantResponsable;
	}
	public void setEnseignantResponsable(boolean enseignantResponsable) {
		this.enseignantResponsable = enseignantResponsable;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public TypeAlerte() {
		super();
	}
	public TypeAlerte(String libelle, boolean membreCommssion,
			boolean enseignantResponsable) {
		super();
		this.libelle = libelle;
		this.membreCommssion = membreCommssion;
		this.enseignantResponsable = enseignantResponsable;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TypeAlerte other = (TypeAlerte) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
