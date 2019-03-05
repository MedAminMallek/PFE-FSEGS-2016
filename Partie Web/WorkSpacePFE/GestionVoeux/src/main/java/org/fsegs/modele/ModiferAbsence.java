package org.fsegs.modele;

import org.fsegs.entitees.AffectationSeance;

public class ModiferAbsence {
	
	private AffectationSeance affectation;
	private boolean affectéSalle;
	public AffectationSeance getAffectation() {
		return affectation;
	}
	public void setAffectation(AffectationSeance affectation) {
		this.affectation = affectation;
	}
	public boolean isAffectéSalle() {
		return affectéSalle;
	}
	public void setAffectéSalle(boolean affectéSalle) {
		this.affectéSalle = affectéSalle;
	}
	public ModiferAbsence() {
		super();
	}
	
	
	

}
