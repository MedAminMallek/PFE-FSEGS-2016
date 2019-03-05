package org.fsegs.modele;

import org.fsegs.entitees.AffectationExamen;
import org.fsegs.entitees.Remplacement;

public class AffectationModele {
	
	AffectationExamen affectation ;
	Remplacement remp;
	public AffectationExamen getAffectation() {
		return affectation;
	}
	public void setAffectation(AffectationExamen affectation) {
		this.affectation = affectation;
	}
	public Remplacement getRemp() {
		return remp;
	}
	public void setRemp(Remplacement remp) {
		this.remp = remp;
	}
	public AffectationModele() {
		super();
	}
	
	
	

}
