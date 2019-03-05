package org.fsegs.gestionvoeux;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fsegs.entitees.CycleAnnee;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.Filiére;
import org.fsegs.entitees.Matiere;
import org.fsegs.entitees.MatiereEnseignement;
import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlleurConsulterSalle {
	
	@Autowired
	private ISurveillanceMetier metier;
	@RequestMapping(value="/listeEpreuves")
	@ResponseBody
	public String listeEpreuve(String id,HttpServletRequest request,HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    Collection<MatiereEnseignement> matieres = metier.findMatiereEnseigner(Long.parseLong(id));
	    Collection<MatiereEnseignement> matiereEnseignements = new ArrayList<MatiereEnseignement>();
	    
	    for(MatiereEnseignement m : matieres)
	    {
	    	if(metier.existeExamenPourMatiere(m))
	    		matiereEnseignements.add(m);
	    }
	    
	    String JASON ="";
	    int i=1;
	    for(MatiereEnseignement m1 : matiereEnseignements)
	    {
	    	JASON+="{\"idMat\":\""+m1.getMatiere().getId()+"\",\"abrev\":\""+m1.getMatiere().getAbreviation()+"\",\"idCycle\":\""+m1.getCycleAnnee().getId()+"\",\"idFiliere\":\""+m1.getFiliere().getId()+"\"}";
	    	if(i<matiereEnseignements.size())
	    		JASON+=",";
	    	i++;
	    }
	    
	    
		return "["+JASON+"]";
	}
	@RequestMapping(value="/listeSallePourMat")
	@ResponseBody
	public String listeSallePourMat(String idMat,String idF,String idC,HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
		CycleAnnee cycle = metier.findCycleAnnee(Integer.parseInt(idC));
		Matiere mat = metier.findMatiere(Long.parseLong(idMat));
		Filiére filiere = metier.findFiliére(idF);
		MatiereEnseignement maEnseignement = new MatiereEnseignement(mat, filiere, cycle, null);
		Collection<Examen> examens = metier.findExamensByMatiere(maEnseignement);
		String JASON="";
		int i=1;
		for(Examen e : examens)
		{
			JASON += "{\"salle\":\""+e.getSalle().getId()+"\",\"date\":\""+e.getSeance().getJourExamen().getDate().toString().split(" ")[0]+"\",\"heure\":\""+e.getSeance().getHeureDebut().toString().split(":")[0]+":"+e.getSeance().getHeureDebut().toString().split(":")[1]+"\"}";
			if(i<examens.size())
	    		JASON+=",";
	    	i++;
		}
		return "["+JASON+"]";
	}

}
