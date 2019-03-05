package org.fsegs.modele;

import java.util.Collection;
import java.util.Date;

import org.fsegs.entitees.JourExamen;

public class CalendrierExamen {
	
	private Date date;
	private Collection<SeanceExamen> seanceExamens;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Collection<SeanceExamen> getSeanceExamens() {
		return seanceExamens;
	}
	public void setSeanceExamens(Collection<SeanceExamen> seanceExamens) {
		this.seanceExamens = seanceExamens;
	}
	public String affichageDate()
	{
		JourExamen j = new JourExamen();
		j.setDate(date);
		String dd= j.dateJour();
		String[] tab = date.toLocaleString().split(" ");
		return dd.split(" ")[0]+" "+tab[0]+" "+tab[1]+" "+tab[2];
	}
	

}
