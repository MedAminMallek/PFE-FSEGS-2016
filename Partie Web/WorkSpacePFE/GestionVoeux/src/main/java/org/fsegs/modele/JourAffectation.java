package org.fsegs.modele;

import java.util.Collection;
import java.util.Date;

import org.fsegs.entitees.JourExamen;

public class JourAffectation {
	
	private Date date;
	private Collection<SeanceAffectation> seances;
	private boolean jourEnsMast;
	
	
	
	public boolean isJourEnsMast() {
		return jourEnsMast;
	}
	public void setJourEnsMast(boolean jourEnsMast) {
		this.jourEnsMast = jourEnsMast;
	}
	
	public Date getDate() {
		return date;
	}
	public String getDate2() {
		String[] d = date.toString().split(" ");
		JourExamen jj = new JourExamen();
		jj.setDate(date);
		return jj.dateJour();
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Collection<SeanceAffectation> getSeances() {
		return seances;
	}
	public void setSeances(Collection<SeanceAffectation> seances) {
		this.seances = seances;
	}
	public JourAffectation() {
		super();
	}
	
	

}
