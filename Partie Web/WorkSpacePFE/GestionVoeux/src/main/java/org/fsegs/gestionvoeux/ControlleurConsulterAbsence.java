package org.fsegs.gestionvoeux;

import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fsegs.entitees.AffectationExamen;
import org.fsegs.entitees.AffectationSeance;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.Seance;
import org.fsegs.metier.ISurveillanceMetier;
import org.fsegs.modele.HeureModele;
import org.fsegs.modele.JourModele;
import org.fsegs.modele.ModiferAbsence;
import org.fsegs.modele.NbAbsence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlleurConsulterAbsence {
	
	@Autowired
	private ISurveillanceMetier metier;
	
	
	
	
	@RequestMapping(value="/choisirSession")
	//@ResponseBody
	public String choisirSession(Model model, HttpServletRequest request,HttpServletResponse response)
	{	
		String btn = request.getParameter("bt1");
		if(btn==null){
		Collection<String> au = metier.getAnnéeUniversitaire();
		model.addAttribute("au", au);
		return "choisirSession";
		}else
		{
			String an = request.getParameter("au");
			String sem = request.getParameter("semstre");
			String ses = request.getParameter("session");
			
			if(!ses.equals("RR"))
			{
				ses=ses+sem;
			}
			
			try {
				response.sendRedirect("ListeSeanceconsulterAbsence?type="+ses+"&au="+an+"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "choisirSession";
	}
	
	
	@RequestMapping(value="/consulterAbsence")
	//@ResponseBody
	public String consulterAbsence(Model model,String date , String heure)
	{
		Collection<Enseignant> enseignants = metier.findSurveillantsParSéance(metier.findSeanceByHeurDebut(date, heure));
		Collection<Enseignant> present = new ArrayList<Enseignant>();
		Collection<Enseignant> absent = new ArrayList<Enseignant>();
		for(Enseignant e : enseignants)
		{
			if(metier.findAffectation(String.valueOf(e.getId()), date, heure).isPresence())
				present.add(e);
			else
				absent.add(e);
					
		}
		model.addAttribute("date", date);
		model.addAttribute("heure", heure);
		
		date = date+" "+heure;
		DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();		
		Date dateJour=d.getTime();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(date.split(" ")[0].split("-")[0]),Integer.parseInt(date.split(" ")[0].split("-")[1])-1,Integer.parseInt(date.split(" ")[0].split("-")[2]),0,0,0);
		Date dat1=cal.getTime();
		
		JourExamen jjj = new JourExamen();
		jjj.setDate(dat1);
		String datejour = jjj.dateJour()+" à "+date.split(" ")[1].split(":")[0]+":"+date.split(" ")[1].split(":")[1];
		
		model.addAttribute("dateF", datejour);
		
		
		model.addAttribute("listeP", present);
		model.addAttribute("listeA", absent);
		
		return "ConsulterAbsences";
	}
	@RequestMapping(value="/ListeSeanceconsulterAbsence")
	public String ListeSeanceconsulterAbsence(Model model,String type,int au)
	{
		model.addAttribute("listeJ", metier.findAllJourExamen(type,au));
		
		return "ChoisirSeanceToAbsence";
	}
	@RequestMapping(value="/chargerconsulterAbsence", method=RequestMethod.POST)
	public String chargerconsulterAbsence(Model model,HttpServletRequest request,HttpServletResponse response)
	{
		
		String boutton = request.getParameter("bt1");
		String jour = request.getParameter("jour");
		if(boutton.equals("Continuer")){
		JourExamen j = new JourExamen();
		Collection<Seance> seances = metier.findSeanceByDateJour(jour);
		for(Seance s : seances)
		{
			j=s.getJourExamen();
		}
		Collection<JourExamen> jours = new ArrayList<JourExamen>();
		jours.add(j);
		model.addAttribute("listeJ",jours);
		model.addAttribute("listeS",seances);
		return "ChoisirSeanceToAbsence";
		}else
		{
			String seance = request.getParameter("seance");
			Collection<AffectationSeance> aff = metier.findSurveillantPourAbsence(jour, seance);
			Collection<ModiferAbsence> liste = new ArrayList<ModiferAbsence>();
			for(AffectationSeance m : aff)
			{
				ModiferAbsence ma = new ModiferAbsence();
				ma.setAffectation(m);
				ma.setAffectéSalle(metier.enseignantAffectéSalle(m.getEnseignant(), m.getDateJour().toString().split(" ")[0], m.getHeureDebut().toString()));
				liste.add(ma);
			}
			model.addAttribute("jour", jour);
			model.addAttribute("seance", seance);
			model.addAttribute("aff", liste);
			
			try {
				response.sendRedirect("consulterAbsence?date="+jour+"&heure="+seance+"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "home";
		}
	}
	@RequestMapping(value="/imprimerEtatP")
	//@ResponseBody
	public String imprimerEtatP(Model model,String date,String heure,String type)
	{
		Collection<Enseignant> enseignants = metier.findSurveillantsParSéance(metier.findSeanceByHeurDebut(date, heure));
		Collection<Enseignant> present = new ArrayList<Enseignant>();
		Collection<Enseignant> absent = new ArrayList<Enseignant>();
		for(Enseignant e : enseignants)
		{
			if(metier.findAffectation(String.valueOf(e.getId()), date, heure).isPresence())
				present.add(e);
			else
				absent.add(e);
					
		}
		if(type.equals("P"))
		{
			model.addAttribute("liste", present);
			model.addAttribute("titre", "Liste de présence");
		}else
		{
			model.addAttribute("liste", absent);
			model.addAttribute("titre", "Liste d'absence");
		}
		date = date+" "+heure;
		DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();		
		Date dateJour=d.getTime();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(date.split(" ")[0].split("-")[0]),Integer.parseInt(date.split(" ")[0].split("-")[1])-1,Integer.parseInt(date.split(" ")[0].split("-")[2]),0,0,0);
		Date dat1=cal.getTime();
		
		int m = Integer.parseInt(date.split(" ")[0].split("-")[1]);//dateJour.getMonth()+1;
		int a = dat1.getYear()+1900; //Integer.parseInt(date.split(" ")[0].split("-")[2]); //Integer.parseInt(dateJour.toString().split(" ")[5]);		
		String AU = "" ;
		if(9<=m && m<=12)
			AU = a+"/"+a+1;
		if(1<=m && m<=6)
			AU = a-1+"/"+a;
		String nature = metier.periodeExamen(dat1);//metier.typeExamen();
		if(nature.equals("EX1")||nature.equals("EX2"))
			nature="Principale";
		if(nature.equals("CC1")||nature.equals("CC2"))
			nature="Control Continu";
		if(nature.equals("RR"))
			nature="Rattrapage";
		model.addAttribute("session", nature);
		model.addAttribute("AU", AU);
		
		/*Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(date.split(" ")[0].split("-")[0]),Integer.parseInt(date.split(" ")[0].split("-")[1])-1,Integer.parseInt(date.split(" ")[0].split("-")[2]),0,0,0);
		Date dat1=cal.getTime();*/
		JourExamen jjj = new JourExamen();
		jjj.setDate(dat1);
		String datejour = jjj.dateJour()+" à "+date.split(" ")[1].split(":")[0]+":"+date.split(" ")[1].split(":")[1];
		
		model.addAttribute("date", datejour);
		
		
		return "imprimerEtatAb";
	}
	@RequestMapping(value="/choisirSession2")
	//@ResponseBody
	public String choisirSession2(Model model, HttpServletRequest request,HttpServletResponse response)
	{	
		String btn = request.getParameter("bt1");
		if(btn==null){
		Collection<String> au = metier.getAnnéeUniversitaire();
		model.addAttribute("au", au);
		return "choisirSession2";
		}else
		{
			String an = request.getParameter("au");
			String sem = request.getParameter("semstre");
			String ses = request.getParameter("session");
			
			if(!ses.equals("RR"))
			{
				ses=ses+sem;
			}
			
			try {
				response.sendRedirect("absenceParEnseignant?typ="+ses+"&au="+an+"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "choisirSession2";
	}
	@RequestMapping(value="/absenceParEnseignant")
	//@ResponseBody
	public String absenceParEnseignant(Model model,String typ,int au)
	{
		String x="";
		Collection<Enseignant> enseignants = metier.findAllSurveillants();
		//Enseignant e = metier.findEnseignant(16l);
		
		ArrayList<NbAbsence> liste = new ArrayList<NbAbsence>();
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
	
		
		
		//Date dateActuelle=dXX.getTime();
		
		Date dateActuelle = metier.getDateActuelle();
		
		String xX="";
		
		for(Enseignant e : enseignants)
		{
			Collection<AffectationSeance> affectations = metier.findAffectationById(e.getId(),typ,au);
			int nbAb=0;
			for(AffectationSeance af : affectations)
			{
				DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
				Calendar d = Calendar.getInstance();
				d.set(Integer.parseInt(af.getDateJour().toString().split(" ")[0].split("-")[0]),Integer.parseInt(af.getDateJour().toString().split(" ")[0].split("-")[1])-1,Integer.parseInt(af.getDateJour().toString().split(" ")[0].split("-")[2]),af.getHeureDebut().getHours(),af.getHeureDebut().getMinutes(),0);
				Date dateAffectation=d.getTime();
				
				
				
				if(!af.isPresence()&& dateActuelle.after(dateAffectation)){
					nbAb++;
				}
			}
			if(affectations.size()>0 && nbAb>0)
				liste.add(new NbAbsence(e,nbAb));
		}
		model.addAttribute("type", typ);
		model.addAttribute("au", au);
		model.addAttribute("liste",liste);
		Collections.sort(liste);
		return "AbsenceParEnseignant";
		
	}
	
	@RequestMapping(value="/listeAB")
	public String listeAB(Model model,String id,String type,int au)
	{
		
		
		ArrayList<NbAbsence> liste = new ArrayList<NbAbsence>();
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
	
		
		
		Date dateActuelle=dXX.getTime();
		
		
		dateActuelle = metier.getDateActuelle();
		
		
		Enseignant e = metier.findEnseignant(Long.parseLong(id));
		Collection<AffectationSeance> affectations = metier.findAffectationById(e.getId(),type,au);
		ArrayList<AffectationSeance> affs = new ArrayList<AffectationSeance>();
		for(AffectationSeance af : affectations)
		{
			DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(af.getDateJour().toString().split(" ")[0].split("-")[0]),Integer.parseInt(af.getDateJour().toString().split(" ")[0].split("-")[1])-1,Integer.parseInt(af.getDateJour().toString().split(" ")[0].split("-")[2]),af.getHeureDebut().getHours(),af.getHeureDebut().getMinutes(),0);
			Date dateAffectation=d.getTime();
			if(!af.isPresence()&& dateActuelle.after(dateAffectation))
				affs.add(af);
		}
		Collections.sort(affs);
		model.addAttribute("lien", "imprParEns?id="+id+"&type="+type+"&au="+au+"");
		model.addAttribute("ens", e.getEnseignant());
		model.addAttribute("liste", affs);
		return "listeAbsenceParEns";
	}
	@RequestMapping(value="/imprParEns")
	//@ResponseBody
	public String imprParEns(Model model,String id,String type,int au)
	{
		ArrayList<NbAbsence> liste = new ArrayList<NbAbsence>();
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		Date dateActuelle=dXX.getTime();
		dateActuelle = metier.getDateActuelle();
		Enseignant e = metier.findEnseignant(Long.parseLong(id));
		Collection<AffectationSeance> affectations = metier.findAffectationById(e.getId(),type,au);
		ArrayList<AffectationSeance> affs = new ArrayList<AffectationSeance>();
		for(AffectationSeance af : affectations)
		{
			DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(af.getDateJour().toString().split(" ")[0].split("-")[0]),Integer.parseInt(af.getDateJour().toString().split(" ")[0].split("-")[1])-1,Integer.parseInt(af.getDateJour().toString().split(" ")[0].split("-")[2]),af.getHeureDebut().getHours(),af.getHeureDebut().getMinutes(),0);
			Date dateAffectation=d.getTime();
			if(!af.isPresence()&& dateActuelle.after(dateAffectation))
				affs.add(af);
		}
		Collections.sort(affs);
		
		if(type.equals("EX1")||type.equals("EX2"))
			type="Principale";
		if(type.equals("CC1")||type.equals("CC2"))
			type="Control Continu";
		if(type.equals("RR"))
			type="Rattrapage";
		model.addAttribute("session", type);
		model.addAttribute("AU", au +" / " + (au+1));
		
		model.addAttribute("ens", e.getEnseignant());
		model.addAttribute("liste", affs);
		return "imprAbsParEns";
	}
	


}
