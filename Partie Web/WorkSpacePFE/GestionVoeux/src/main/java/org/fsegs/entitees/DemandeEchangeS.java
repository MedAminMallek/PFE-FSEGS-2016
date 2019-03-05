package org.fsegs.entitees;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.view.jasperreports.JasperReportsCsvView;

@Entity
@Table(name="DemandeEchangeS")
public class DemandeEchangeS implements Serializable{
	
	
	private String etat;
	@Id
	@ManyToOne
	private Enseignant emetteur;
	@Id
	@ManyToOne
	private Enseignant recepteur;
	
	@Id
	private Date dateJourP;
	@Id
	private Time heureDebutP;
	
	@Id
	private Date dateJourD;
	@Id
	private Time heureDebutD;
	
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Enseignant getEmetteur() {
		return emetteur;
	}
	public void setEmetteur(Enseignant emetteur) {
		this.emetteur = emetteur;
	}
	public Enseignant getRecepteur() {
		return recepteur;
	}
	public void setRecepteur(Enseignant recepteur) {
		this.recepteur = recepteur;
	}
	
	
	
	public Date getDateJourP() {
		return dateJourP;
	}
	public void setDateJourP(Date dateJourP) {
		this.dateJourP = dateJourP;
	}
	public Time getHeureDebutP() {
		return heureDebutP;
	}
	public void setHeureDebutP(Time heureDebutP) {
		this.heureDebutP = heureDebutP;
	}
	public Date getDateJourD() {
		return dateJourD;
	}
	public void setDateJourD(Date dateJourD) {
		this.dateJourD = dateJourD;
	}
	public Time getHeureDebutD() {
		return heureDebutD;
	}
	public void setHeureDebutD(Time heureDebutD) {
		this.heureDebutD = heureDebutD;
	}
	public DemandeEchangeS() {
		super();
	}
	public DemandeEchangeS(Enseignant emetteur, Enseignant recepteur,
			Date dateJourP, Time heureDebutP, Date dateJourD, Time heureDebutD,String etat) {
		super();
		this.emetteur = emetteur;
		this.recepteur = recepteur;
		this.dateJourP = dateJourP;
		this.heureDebutP = heureDebutP;
		this.dateJourD = dateJourD;
		this.heureDebutD = heureDebutD;
		this.etat=etat;
	}
	public String getJASON()
	{
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		ISurveillanceMetier metier =(ISurveillanceMetier) context.getBean("metier");
		String HD[] = heureDebutD.toString().split(":");
		String HeureD = HD[0]+":"+HD[1];
		String HP[] = heureDebutP.toString().split(":");
		String HeureP = HP[0]+":"+HP[1];
		String DD[] = dateJourD.toString().split(" ");
		String DP[] = dateJourP.toString().split(" ");
		String jason="{\"idE\":\""+emetteur.getId()+"\",\"idR\":\""+recepteur.getId()+"\",\"nomE\":\""+emetteur.getEnseignant()+"\",\"nomR\":\""+recepteur.getEnseignant()+"\",\"dateD\":\""+DD[0]+"\",\"dateP\":\""+DP[0]+"\",\"heureD\":\""+HeureD+"\",\"heureP\":\""+HeureP+"\",\"etat\":\""+etat+"\"}";
		return jason;
	}
	public String getDateJourP2()
	{
		String DP[] = dateJourP.toString().split(" ");
		return DP[0];
		
	}
	public String getDateJourD2()
	{
		String DD[] = dateJourD.toString().split(" ");
		return DD[0];
		
	}
	public String getheureDebutP2()
	{
		String HP[] = heureDebutP.toString().split(":");
		String HeureP = HP[0]+":"+HP[1];
		return HeureP;
	}
	public String getheureDebutD2()
	{
		String HD[] = heureDebutD.toString().split(":");
		String HeureD = HD[0]+":"+HD[1];
		return HeureD;
	}
	

}
