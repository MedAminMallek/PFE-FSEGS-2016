package org.fsegs.modele;

import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.MatiereEnseignement;
import org.fsegs.entitees.Seance;

public class MatiereParResp {
	
	private Enseignant responsable;
	private MatiereEnseignement matiere;
	private Seance seance;
	public Enseignant getResponsable() {
		return responsable;
	}
	public void setResponsable(Enseignant responsable) {
		this.responsable = responsable;
	}
	public MatiereEnseignement getMatiere() {
		return matiere;
	}
	public void setMatiere(MatiereEnseignement matiere) {
		this.matiere = matiere;
	}
	public Seance getSeance() {
		return seance;
	}
	public void setSeance(Seance seance) {
		this.seance = seance;
	}
	
	public String getJASON(){
		String tabD[] = seance.getJourExamen().getDate().toString().split(" ");
		String tabH[] = seance.getHeureDebut().toString().split(":");
		return "{\"date\":\""+tabD[0]+"\",\"heure\":\""+tabH[0]+":"+tabH[1]+"\",\"ab\":\""+matiere.getMatiere().getAbreviation()+" "+matiere.getFiliere().getId()+"\"}";
		
	}

}
