package org.fsegs.entitees;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Entity
@Table(name="Enseignant")
public class Enseignant implements Serializable {
	@Id
	private Long id;
	private String enseignant;
	private String departement;
	private String statut;
	private String grade;
	private String sexe;
	private Long casier;
	private boolean actif;
	private int aSruveiller;
	private String cin;
	private String email;
	private String telFixe;
	private String telMobile;
	private boolean commission;
	private String nomUtilisateur;
	private String motDePasse;
	
	
	
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(String enseignant) {
		this.enseignant = enseignant;
	}
	
	
	
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public Long getCasier() {
		return casier;
	}
	public void setCasier(Long casier) {
		this.casier = casier;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public int getaSruveiller() {
		return aSruveiller;
	}
	public void setaSruveiller(int aSruveiller) {
		this.aSruveiller = aSruveiller;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelFixe() {
		return telFixe;
	}
	public void setTelFixe(String telFixe) {
		this.telFixe = telFixe;
	}
	public String getTelMobile() {
		return telMobile.replaceAll(" ", "");
	}
	public void setTelMobile(String telMobile) {
		this.telMobile = telMobile;
	}
	public boolean isCommission() {
		return commission;
	}
	public void setCommission(boolean commission) {
		this.commission = commission;
	}
	
	
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}

	public Enseignant() {
		super();
	}
	public Enseignant(Long id, String enseignant, String departement,
			String statut, String grade, String sexe, Long casier,
			boolean actif, int aSruveiller, String cin, String email,
			String telFixe, String telMobile, boolean commission) {
		super();
		this.id = id;
		this.enseignant = enseignant;
		this.departement = departement;
		this.statut = statut;
		this.grade = grade;
		this.sexe = sexe;
		this.casier = casier;
		this.actif = actif;
		this.aSruveiller = aSruveiller;
		this.cin = cin;
		this.email = email;
		this.telFixe = telFixe;
		this.telMobile = telMobile;
		this.commission = commission;
	}
	public int nbAff()
	{
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		ISurveillanceMetier metier =(ISurveillanceMetier) context.getBean("metier");
		return metier.nbAffectationParSurveillant(id);
	}
	public int isResponsable()
	{
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		ISurveillanceMetier metier =(ISurveillanceMetier) context.getBean("metier");
		if(metier.IsResponsable(id))
			return 1;
		else
			return 0;
		

	}
	
	public int AffectationAutoLancer()
	{
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		ISurveillanceMetier metier =(ISurveillanceMetier) context.getBean("metier");
		Collection<Seance> seances = metier.findAllSeance();
		int besoin = 0;
		for(Seance s : seances)
		{
			besoin+= s.getNombreSurveillant();
		}
		Collection<AffectationSeance> affectations = metier.findAllAffectationSeances();
		int aff = 0;
		for(AffectationSeance af : affectations)
		{
			aff+= 1;
		}
		boolean sature = besoin==aff?true:false;
		if(sature)
			return 1;
		else
			return 0;
		

	}
	
	public String getJSON()
	{
		
		//[{\"0\":\"157\",\"id\":\"157\",\"1\":\"heurefff\",\"nom\":\""+nom+"\",\"2\":\"heure\",\"prenom\":\"heure\"}]
		
		return "{\"id\":\""+id+"\",\"enseignant\":\""+enseignant+"\",\"commission\":\""+commission+"\",\"nbS\":\""+aSruveiller+"\",\"nbAff\":\""+nbAff()+"\",\"resp\":\""+isResponsable()+"\",\"auto\":\""+AffectationAutoLancer()+"\"}";
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
		Enseignant other = (Enseignant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
