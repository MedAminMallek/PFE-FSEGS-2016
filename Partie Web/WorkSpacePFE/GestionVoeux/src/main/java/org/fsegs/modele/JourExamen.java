package org.fsegs.modele;

import java.util.Collection;
import java.util.Date;

import org.fsegs.entitees.Seance;

public class JourExamen {
	
	private org.fsegs.entitees.JourExamen jour;
	private Collection<Seance> seances;
	private Date date ;
	public org.fsegs.entitees.JourExamen getJour() {
		return jour;
	}
	public void setJour(org.fsegs.entitees.JourExamen jour) {
		this.jour = jour;
	}
	public Collection<Seance> getSeances() {
		return seances;
	}
	public void setSeances(Collection<Seance> seances) {
		this.seances = seances;
	}
	public String getDate() {
		
		String[] d= date.toString().split(" ");
		return d[0];
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String dateJour()
	{
		return jour.dateJour();
	}
	
	
 
}
