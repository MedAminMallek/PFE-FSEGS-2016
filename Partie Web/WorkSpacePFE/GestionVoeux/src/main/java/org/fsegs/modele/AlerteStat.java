package org.fsegs.modele;

import org.fsegs.entitees.Alerte;
import org.fsegs.entitees.TypeAlerte;

public class AlerteStat {
	
	private TypeAlerte typeAlerte;
	private int nb;
	
	
	public AlerteStat(TypeAlerte typeAlerte, int nb) {
		super();
		this.typeAlerte = typeAlerte;
		this.nb = nb;
	}
	public TypeAlerte getTypeAlerte() {
		return typeAlerte;
	}
	public void setTypeAlerte(TypeAlerte typeAlerte) {
		this.typeAlerte = typeAlerte;
	}
	public int getNb() {
		return nb;
	}
	public void setNb(int nb) {
		this.nb = nb;
	}
	
	public AlerteStat() {
		super();
	}
	
	

}
