package org.fsegs.gestionvoeux;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fsegs.entitees.Alerte;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.Seance;
import org.fsegs.metier.ISurveillanceMetier;
import org.fsegs.modele.AlerteModele;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlleurConsulterAlerte {

	@Autowired
	private ISurveillanceMetier metier;
	
	@RequestMapping(value="/consulterAlertes")
	//@ResponseBody
	public String consulterAlertes(Model model)
	{
		DateFormat fx= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dx = Calendar.getInstance();
		Date datejour;
		
		datejour = metier.getDateActuelle();
		
		int moins = (datejour.getMonth()+1);
		int day = datejour.getDate();
		String dateActuelle = (datejour.getYear()+1900)+"-"+(moins < 10 ? "0" + moins : moins)+"-"+(day < 10 ? "0" + day : day);//"2016-01-06";
		int h = datejour.getHours();
		int m = datejour.getMinutes();
		String heureActuelle = ""+(h < 10 ? "0" + h : h)+":"+(m < 10 ? "0" + m : m)+"";
		
		//dateActuelle="2016-01-06";
		//heureActuelle="12:45";
		
		Collection<Seance> seances = metier.findSeanceByDateJour(dateActuelle);
		Collection<Time> heures = new ArrayList<Time>();
		for(Seance s : seances)
		{
			heures.add(s.getHeureDebut());
		}
		boolean séancetrouvée = false;
		Seance seanceActuelle = new Seance();
		for(Time t : heures)
		{
			DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(dateActuelle.split("-")[0]),Integer.parseInt(dateActuelle.split("-")[1])-1,Integer.parseInt(dateActuelle.split("-")[2]),t.getHours(),t.getMinutes(),0);
			Date dateD=d.getTime();
			if(!metier.typeExamen().equals("CC"))
			{
				d.add(Calendar.HOUR, 2);
				d.add(Calendar.MINUTE, 30);
			}else
			{
				d.add(Calendar.HOUR, 1);
				d.add(Calendar.MINUTE, 30);
					
			}
			Date dateF = d.getTime();
			
			DateFormat f1= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d1 = Calendar.getInstance();
			d1.set(Integer.parseInt(dateActuelle.split("-")[0]),Integer.parseInt(dateActuelle.split("-")[1])-1,Integer.parseInt(dateActuelle.split("-")[2]),Integer.parseInt(heureActuelle.split(":")[0]),Integer.parseInt(heureActuelle.split(":")[1]),0);
			Date dateAlerte=d1.getTime();
			
			if(dateAlerte.after(dateD)&&dateAlerte.before(dateF)){
				Time tt = new Time(dateD.getHours(), dateD.getMinutes(), 0);
				seanceActuelle = metier.findSeanceByHeurDebut(dateActuelle, tt.toString());
				séancetrouvée = true;
			}
				
			
		}
		if(séancetrouvée){
			Collection<AlerteModele> alertesModele = new ArrayList<AlerteModele>();			
			Collection<Alerte> alertes = metier.findAlertesBySeance(seanceActuelle);
			for(Alerte al : alertes)
			{
				Examen examen = metier.findExamenParAlerte(al.getSurveillant().getId(), al.getHeure().toString(), al.getDate().toString().split(" ")[0]);
				//Collection<Enseignant> enseignants = metier.findEnseignantsParMatiere(examen.getMatiereEnseignement());
				alertesModele.add(new AlerteModele(al, examen));
			}
			model.addAttribute("liste", alertesModele);
			JourExamen j = new JourExamen();
			j.setDate(seanceActuelle.getJourExamen().getDate());			
			model.addAttribute("seance", j.dateJour()+" à "+seanceActuelle.getHeureDebut().toString().split(":")[0]+":"+seanceActuelle.getHeureDebut().toString().split(":")[1]);
			
		}else
			model.addAttribute("erreur", "Aucune séance ne se déroule en ce moment");
		
		return "ConsulterAlerteEnCours"; 
	}
	@RequestMapping(value="/listeEnseignant")
	public String listeEnseignantParMatiere(Model model,Long id)
	{
		Alerte al = metier.findAlerte(id);
		Examen examen = metier.findExamenParAlerte(al.getSurveillant().getId(), al.getHeure().toString(), al.getDate().toString().split(" ")[0]);
		Collection<Enseignant> enseignants = metier.findEnseignantsParMatiere(examen.getMatiereEnseignement());
		model.addAttribute("liste", enseignants);
		return "listeEnseignantsParMatiere";
	}
	@RequestMapping(value="/getAlertePourResp")
	@ResponseBody
	public String getAlertePourRespOuCommission(HttpServletResponse response, String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
		String x="";
		Enseignant enseignantAuthentifié = metier.findEnseignant(Long.parseLong(id));
		Seance seanceActuelle = getSeanceActuelle();
		Set<AlerteModele> alerteAAffiché = new HashSet<AlerteModele>();
		if(seanceActuelle!=null)
		{
			Collection<Alerte> alertes = metier.findAlertesBySeance(seanceActuelle);
			for(Alerte al : alertes)
			{	
				Examen exmen = metier.findExamenParAlerte(al.getSurveillant().getId(), al.getHeure().toString(), al.getDate().toString().split(" ")[0]);
				if(exmen.getMatiereEnseignement().getEnseignant().equals(enseignantAuthentifié) && al.getType()!= null && al.getType().isEnseignantResponsable())
					alerteAAffiché.add(new AlerteModele(al, exmen));
				if(enseignantAuthentifié.isCommission() && al.getType() != null && al.getType().isMembreCommssion())
					alerteAAffiché.add(new AlerteModele(al, exmen));
			}
		
		
		int i=1;
		for(AlerteModele a : alerteAAffiché){
			String type = a.getAlerte().getType()!=null ? a.getAlerte().getType().getLibelle() : a.getAlerte().getDescription();
			String heure = a.getAlerte().getHeure().toString().split(":")[0]+":"+a.getAlerte().getHeure().toString().split(":")[1];
			String enseignant = a.getAlerte().getSurveillant().getEnseignant();
			String Tel = a.getAlerte().getSurveillant().getTelMobile().replaceAll(" ", "");
			String salle = a.getExamen().getSalle().getId();
			String examen = a.getExamen().getMatiereEnseignement().getMatiere().getAbreviation()+" "+a.getExamen().getMatiereEnseignement().getCycleAnnee().getId()+" "+a.getExamen().getMatiereEnseignement().getFiliere().getId();
			x+="{\"heure\":\""+heure+"\",\"enseignant\":\""+enseignant+"\",\"tel\":\""+Tel+"\",\"salle\":\""+salle+"\",\"type\":\""+type+"\",\"examen\":\""+examen+"\"}";
			if(i<alerteAAffiché.size())
				x+=",";
			i++;
		}
		}else
		{
			String err="Aucune séance ne se déroule en ce moment";
			x+="{\"erreur\":\""+err+"\"}";
		}
		return "["+x+"]";
	}
	
	
	
	
	
	
	public Seance getSeanceActuelle()
	{
		DateFormat fx= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dx = Calendar.getInstance();
		Date datejour;
		
		datejour = metier.getDateActuelle();
		
		int moins = (datejour.getMonth()+1);
		int day = datejour.getDate();
		String dateActuelle = datejour.getYear()+1900+"-"+(moins < 10 ? "0" + moins : moins)+"-"+(day < 10 ? "0" + day : day);//"2016-01-06";
		int h = datejour.getHours();
		int m = datejour.getMinutes();
		String heureActuelle = ""+(h < 10 ? "0" + h : h)+":"+(m < 10 ? "0" + m : m)+"";
		
		//dateActuelle="2016-01-06";
		//heureActuelle="12:45";
		
		Collection<Seance> seances = metier.findSeanceByDateJour(dateActuelle);
		Collection<Time> heures = new ArrayList<Time>();
		for(Seance s : seances)
		{
			heures.add(s.getHeureDebut());
		}
		boolean séancetrouvée = false;
		Seance seanceActuelle = new Seance();
		for(Time t : heures)
		{
			DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(dateActuelle.split("-")[0]),Integer.parseInt(dateActuelle.split("-")[1])-1,Integer.parseInt(dateActuelle.split("-")[2]),t.getHours(),t.getMinutes(),0);
			Date dateD=d.getTime();
			if(!metier.typeExamen().equals("CC"))
			{
				d.add(Calendar.HOUR, 2);
				d.add(Calendar.MINUTE, 30);
			}else
			{
				d.add(Calendar.HOUR, 1);
				d.add(Calendar.MINUTE, 30);
					
			}
			Date dateF = d.getTime();
			
			DateFormat f1= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d1 = Calendar.getInstance();
			d1.set(Integer.parseInt(dateActuelle.split("-")[0]),Integer.parseInt(dateActuelle.split("-")[1])-1,Integer.parseInt(dateActuelle.split("-")[2]),Integer.parseInt(heureActuelle.split(":")[0]),Integer.parseInt(heureActuelle.split(":")[1]),0);
			Date dateAlerte=d1.getTime();
			
			if(dateAlerte.after(dateD)&&dateAlerte.before(dateF)){
				Time tt = new Time(dateD.getHours(), dateD.getMinutes(), 0);
				seanceActuelle = metier.findSeanceByHeurDebut(dateActuelle, tt.toString());
				séancetrouvée = true;
			}
				
			
		}
		if(séancetrouvée){
			return seanceActuelle;
		}
		return null;
	}
	
}
