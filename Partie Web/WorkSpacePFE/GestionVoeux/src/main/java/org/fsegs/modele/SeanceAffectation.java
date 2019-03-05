package org.fsegs.modele;

import java.sql.Time;

public class SeanceAffectation {
	
	private Time heureDebut;
	private boolean affecter;
	private boolean responsable;
	private boolean saturer;
	private boolean ens;
	private boolean emp;
	private boolean empM;
	
	
	
	public boolean isEmpM() {
		return empM;
	}
	public void setEmpM(boolean empM) {
		this.empM = empM;
	}
	public boolean isEmp() {
		return emp;
	}
	public void setEmp(boolean emp) {
		this.emp = emp;
	}
	public boolean isEns() {
		return ens;
	}
	public void setEns(boolean ens) {
		this.ens = ens;
	}
	public Time getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
	public boolean isAffecter() {
		return affecter;
	}
	public void setAffecter(boolean affecter) {
		this.affecter = affecter;
	}
	public boolean isResponsable() {
		return responsable;
	}
	public void setResponsable(boolean responsable) {
		this.responsable = responsable;
	}
	public boolean isSaturer() {
		return saturer;
	}
	public void setSaturer(boolean saturer) {
		this.saturer = saturer;
	}
	public SeanceAffectation() {
		super();
	}
	
	

}
