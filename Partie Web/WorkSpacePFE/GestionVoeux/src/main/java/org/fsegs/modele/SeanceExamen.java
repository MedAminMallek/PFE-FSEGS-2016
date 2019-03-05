package org.fsegs.modele;

import java.sql.Time;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.fsegs.entitees.Matiere;
import org.fsegs.entitees.MatiereEnseignement;

public class SeanceExamen {
	
	private Time heureDebut;
	private Set<String> matieres;
	private int nombreSurveillant;
	private int nombreAffectationSurveillant;
	private int nombreCommission;
	private String url;
	
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getNombreAffectationSurveillant() {
		return nombreAffectationSurveillant;
	}
	public void setNombreAffectationSurveillant(int nombreAffectationSurveillant) {
		this.nombreAffectationSurveillant = nombreAffectationSurveillant;
	}
	public int getNombreCommission() {
		return nombreCommission;
	}
	public void setNombreCommission(int nombreCommission) {
		this.nombreCommission = nombreCommission;
	}
	public int getNombreSurveillant() {
		return nombreSurveillant;
	}
	public void setNombreSurveillant(int nombreSurveillant) {
		this.nombreSurveillant = nombreSurveillant;
	}
	public Time getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Set<String> getMatieres() {
		return matieres;
	}
	public void setMatieres(Set<String> matieres) {
		this.matieres = matieres;
	}
	
	
	

}
