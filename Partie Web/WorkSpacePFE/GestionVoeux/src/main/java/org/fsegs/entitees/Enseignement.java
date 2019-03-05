package org.fsegs.entitees;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Enseignement")
public class Enseignement implements Serializable {
	
	
	@Id
	private int au;
	@Id
	@ManyToOne
	private Semestre semestre;
	@Id
	private String ne;
	@Id
	private String heureDebut;
	@Id
	@ManyToOne
	@JoinColumn(name="jour_id")
	private JourEnseignement jour;
	@Id
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="matiere",referencedColumnName="matiere_id"),
		@JoinColumn(name="filiere",referencedColumnName="filiere_id"),
		@JoinColumn(name="cycleAnnee",referencedColumnName="cycleAnnee_id")
		})
	private MatiereEnseignement matiereEnseignement;
	@Id
	private int groupe;
	@Id
	private int groupeTp;
	private int auditoire;
	@ManyToOne
	private Enseignant enseignant;
	
	
	
	public int getAu() {
		return au;
	}
	public void setAu(int au) {
		this.au = au;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	
	
	public int getGroupe() {
		return groupe;
	}
	public void setGroupe(int groupe) {
		this.groupe = groupe;
	}
	public int getGroupeTp() {
		return groupeTp;
	}
	public void setGroupeTp(int groupeTp) {
		this.groupeTp = groupeTp;
	}
	
	public int getAuditoire() {
		return auditoire;
	}
	public void setAuditoire(int auditoire) {
		this.auditoire = auditoire;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public JourEnseignement getJour() {
		return jour;
	}
	public void setJour(JourEnseignement jour) {
		this.jour = jour;
	}
	public Enseignement() {
		super();
	}
	public String getNe() {
		return ne;
	}
	public void setNe(String ne) {
		this.ne = ne;
	}
	public String getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}
	public MatiereEnseignement getMatiereEnseignement() {
		return matiereEnseignement;
	}
	public void setMatiereEnseignement(MatiereEnseignement matiereEnseignement) {
		this.matiereEnseignement = matiereEnseignement;
	}
	public Enseignement(int au, Semestre semestre, String ne,
			String heureDebut, JourEnseignement jour,
			MatiereEnseignement matiereEnseignement, int groupe, int groupeTp,
			int auditoire, Enseignant enseignant) {
		super();
		this.au = au;
		this.semestre = semestre;
		this.ne = ne;
		this.heureDebut = heureDebut;
		this.jour = jour;
		this.matiereEnseignement = matiereEnseignement;
		this.groupe = groupe;
		this.groupeTp = groupeTp;
		this.auditoire = auditoire;
		this.enseignant = enseignant;
	}
	
	
	
	
	

}
