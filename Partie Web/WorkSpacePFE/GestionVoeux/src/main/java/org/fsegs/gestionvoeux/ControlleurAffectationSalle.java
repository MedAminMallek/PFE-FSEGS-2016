package org.fsegs.gestionvoeux;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fsegs.entitees.AffectationExamen;
import org.fsegs.entitees.AffectationSeance;
import org.fsegs.entitees.CycleAnnee;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.Filiére;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.Matiere;
import org.fsegs.entitees.MatiereEnseignement;
import org.fsegs.entitees.Remplacement;
import org.fsegs.entitees.Salle;
import org.fsegs.entitees.Seance;
import org.fsegs.metier.ISurveillanceMetier;
import org.fsegs.modele.AffectationModele;
import org.fsegs.modele.ExamenSalle;
import org.fsegs.modele.ModiferAbsence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlleurAffectationSalle {
	
	@Autowired
	private ISurveillanceMetier metier;
	
	
	@RequestMapping(value="/ajouterAffectation", method=RequestMethod.POST)
	//@ResponseBody
	public String ajouterAffectation(HttpServletRequest request,Model model,String date)
	{
		DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();
		d.set(Integer.parseInt(date.split(" ")[0].split("-")[0]),Integer.parseInt(date.split(" ")[0].split("-")[1])-1,Integer.parseInt(date.split(" ")[0].split("-")[2]),0,0,0);
		Date dateJour=d.getTime();
		Time heureDebut = new Time(Integer.parseInt(date.split(" ")[1].split(":")[0]), Integer.parseInt(date.split(" ")[1].split(":")[1]), 00);
		
		String xx="";
		String MSG="";
		List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
		for(String s : parameterNames){
			
			if(s.split("X").length>1 && request.getParameter(s)!=""){
				AffectationExamen af = new AffectationExamen();
				
				Salle salle = metier.findSalle(s.split("X")[1]);
				Filiére filiére = metier.findFiliére(s.split("X")[2]);
				CycleAnnee cycleAnnee = metier.findCycleAnnee(Integer.parseInt(s.split("X")[3]));
				Matiere matiere = metier.findMatiere(Long.parseLong(s.split("X")[4]));
				Enseignant enseignant=null ;
				if(metier.findEnseignant(request.getParameter(s))!=null)
					enseignant = metier.findEnseignant(request.getParameter(s));
				Calendar cal = Calendar.getInstance();
				Date timeAffectation= (Date) cal.getTime();
				Time heureAff = new Time(Integer.parseInt(timeAffectation.toLocaleString().split(" ")[3].split(":")[0]), Integer.parseInt(timeAffectation.toLocaleString().split(" ")[3].split(":")[1]), 00);
				
				af.setCycleAnnee(cycleAnnee);
				af.setDateJour(dateJour);
				af.setFiliere(filiére);
				af.setHeureReelAff(heureAff);
				af.setHeureDebut(heureDebut);
				af.setMatiere(matiere);
				af.setSalleExamen(salle);
				af.setSurveillant(enseignant);
				
				boolean enseignantExiste = true;
				if(enseignant==null){
					enseignantExiste=false;
					MSG+="<br>Salle "+af.getSalleExamen().getId()+": L'enseignant est introuvable";
				}
				if(enseignantExiste && !metier.enseignantAffectéSalle(enseignant, date.split(" ")[0], date.split(" ")[1])){
					metier.addAffectationSalle(af);
					//return af.getDateJour().toLocaleString();
					metier.updateAbsence(metier.findAffectation(String.valueOf(enseignant.getId()), date.split(" ")[0], date.split(" ")[1]), true);
				}
					
								
			}
			
			if(s.split("M").length>1 && !s.split("M")[0].equals("R"))
			{
				Enseignant remplacent =  metier.findEnseignant(request.getParameter("R"+s));
				String m = s.replace('M', 'X');
				Enseignant ense = metier.findEnseignant(request.getParameter("N"+m));
				if(ense!= null){
				if(remplacent==null){
					MSG+="<br>Salle "+m.split("X")[1]+": Le remplaçent est introuvable";
					metier.supprimerAffectationExamen(date.split(" ")[0], date.split(" ")[1], ense.getId());
				}else
				{
					Remplacement remp = new Remplacement(dateJour, heureDebut, ense, remplacent);
					metier.addRemplacement(remp);
				}
				}
				//xx+=remplacent.getEnseignant();
				
				//xx+=" "+request.getParameter("N"+m);
			}
		}
		
		model.addAttribute("MSG", MSG);
		model.addAttribute("Date", date);
		JourExamen jj = new JourExamen();
		jj.setDate(new Date(Integer.parseInt(date.split(" ")[0].split("-")[0])-1900, Integer.parseInt(date.split(" ")[0].split("-")[1])-1, Integer.parseInt(date.split(" ")[0].split("-")[2])));
		model.addAttribute("Date2", jj.dateJour()+" à "+date.split(" ")[1].split(":")[0]+":"+date.split(" ")[1].split(":")[1]);
		model.addAttribute("listeS", chargerSalles(date.split(" ")[0], date.split(" ")[1]));
		Collection<AffectationExamen> affeE = metier.findAffectationExamen(date.split(" ")[0], date.split(" ")[1]);
		model.addAttribute("listeA", findAffecation(date.split(" ")[0], date.split(" ")[1]));
		model.addAttribute("MSGA", getMSGA(affeE));
		model.addAttribute("listeE", listeEnseignant(date.split(" ")[0], date.split(" ")[1]));
		model.addAttribute("listeR",listeRemp(metier.findSurveillantsParSéance(metier.findSeanceByHeurDebut(date.split(" ")[0], date.split(" ")[1]))));
		//return xx;
		return "affectationSalle";
	}
	@RequestMapping(value="/imprimerAffectation")
	//@ResponseBody
	public String imprimerAffectation(Model model,String date)
	{
		DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();		
		Date dateJour=metier.getDateActuelle();
		int m =dateJour.getMonth()+1;
		int a =Integer.parseInt(dateJour.toString().split(" ")[5]);
		String AU = "" ;
		if(9<=m && m<=12)
			AU = a+"/"+a+1;
		if(1<=m && m<=6)
			AU = a-1+"/"+a;
		String nature = metier.typeExamen();
		if(nature.equals("EX1")||nature.equals("EX2"))
			nature="Principale";
		if(nature.equals("CC1")||nature.equals("CC2"))
			nature="Control Continu";
		if(nature.equals("RR"))
			nature="Rattrapage";
		model.addAttribute("session", nature);
		model.addAttribute("AU", AU);
		
		//DateFormat for= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(date.split(" ")[0].split("-")[0]),Integer.parseInt(date.split(" ")[0].split("-")[1])-1,Integer.parseInt(date.split(" ")[0].split("-")[2]),0,0,0);
		Date dat1=cal.getTime();
		JourExamen jjj = new JourExamen();
		jjj.setDate(dat1);
		String datejour = jjj.dateJour()+" à "+date.split(" ")[1].split(":")[0]+":"+date.split(" ")[1].split(":")[1];
		
		model.addAttribute("date", datejour);
		model.addAttribute("listeA", findAffecation(date.split(" ")[0], date.split(" ")[1]));
		return "imprimerAffectaionSalle";
	}
	
	
	@RequestMapping(value="/supprimerAffectation")
	//@ResponseBody
	public String supprimerAffectation(Model model,String date,String id,String dd)
	{
		metier.updateAbsence(metier.findAffectation(id, date.split(" ")[0], date.split(" ")[1]), false);
		metier.supprimerAffectationExamen(date.split(" ")[0], date.split(" ")[1], Long.parseLong(id));
		metier.supprimerRemplacement(date.split(" ")[0], date.split(" ")[1], metier.findEnseignant(Long.parseLong(id)));
		
		
		model.addAttribute("Date", dd);
		JourExamen jj = new JourExamen();
		jj.setDate(new Date(Integer.parseInt(dd.split(" ")[0].split("-")[0])-1900, Integer.parseInt(dd.split(" ")[0].split("-")[1])-1, Integer.parseInt(dd.split(" ")[0].split("-")[2])));
		model.addAttribute("Date2", jj.dateJour()+" à "+dd.split(" ")[1].split(":")[0]+":"+dd.split(" ")[1].split(":")[1]);
		model.addAttribute("listeS", chargerSalles(dd.split(" ")[0],dd.split(" ")[1]));
		Collection<AffectationExamen> affeE = metier.findAffectationExamen(date.split(" ")[0], date.split(" ")[1]);
		model.addAttribute("listeA", findAffecation(date.split(" ")[0], date.split(" ")[1]));
		model.addAttribute("MSGA", getMSGA(affeE));
		model.addAttribute("listeE", listeEnseignant(date.split(" ")[0], date.split(" ")[1]));
		model.addAttribute("listeR",listeRemp(metier.findSurveillantsParSéance(metier.findSeanceByHeurDebut(date.split(" ")[0], date.split(" ")[1]))));
		return "affectationSalle";
	}
	
	@RequestMapping(value="/listeSeanceParJour")
	public String listeSeanceParJour(Model model)
	{
		
		model.addAttribute("listeJ", metier.findAllJourExamen());
		
		return "listeJourSeance";
	}
	
	@RequestMapping(value="/listeSeanceParJourAB")
	public String listeSeanceParJourAB(Model model)
	{
		
		model.addAttribute("listeJ", metier.findAllJourExamen());
		
		return "ChoisirSeanceAbsence";
	}
	
	@RequestMapping(value="/chargerSeanceParJourAbsence", method=RequestMethod.POST)
	public String chargerSeanceParJourAbsence(Model model,HttpServletRequest request)
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
		return "ChoisirSeanceAbsence";
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
			
			return "presence";
		}
	}
	
	@RequestMapping(value="/updateP",method=RequestMethod.POST)
	//@ResponseBody
	public String updateP(Model model,HttpServletRequest request,HttpServletResponse response)
	{
		String jour = request.getParameter("jour");
		String seance = request.getParameter("seance");
		Collection<AffectationSeance> listeAff = metier.findAffectationSeance(jour, seance);
		for(AffectationSeance afa : listeAff){
			if(!metier.enseignantAffectéSalle(afa.getEnseignant(), afa.getDateJour().toString().split(" ")[0], afa.getHeureDebut().toString()))
			metier.updateAbsence(afa, false);
		}
		
		List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
		
		for(String x : parameterNames)
			{
				if(!x.equals("jour")&&!x.equals("seance")){
				AffectationSeance afE = metier.findAffectation(x,jour,seance);
				metier.updateAbsence(afE, true);
				}
			}
		try {
			response.sendRedirect("consulterAbsence?date="+jour+"&heure="+seance+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "home";
	}
	
	
	@RequestMapping(value="/chargerSeanceParJour", method=RequestMethod.POST)
	public String chargerSeanceParJour(Model model,HttpServletRequest request)
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
		return "listeJourSeance";
		}else
		{
			String seance = request.getParameter("seance");
			model.addAttribute("Date", jour+" "+seance);
			JourExamen jj = new JourExamen();
			jj.setDate(new Date(Integer.parseInt(jour.split("-")[0])-1900, Integer.parseInt(jour.split("-")[1])-1, Integer.parseInt(jour.split("-")[2])));
			model.addAttribute("Date2", jj.dateJour()+" à "+seance.split(":")[0]+":"+seance.split(":")[1]);
			
			model.addAttribute("listeS", chargerSalles(jour,seance));
			Collection<AffectationExamen> affeE = metier.findAffectationExamen(jour, seance);
			model.addAttribute("listeA", findAffecation(jour, seance));
			model.addAttribute("MSGA", getMSGA(affeE));
			model.addAttribute("listeE", listeEnseignant(jour,seance));
			model.addAttribute("listeR",listeRemp(metier.findSurveillantsParSéance(metier.findSeanceByHeurDebut(jour,seance ))));
			return "affectationSalle";
		}
		
	}
	
	public Collection<Enseignant> listeEnseignant(String j , String s)
	{
		Collection<Enseignant> enseignants = metier.findSurveillantsParSéance(metier.findSeanceByHeurDebut(j, s));
		Collection<Enseignant> enseignant = new ArrayList<Enseignant>();
		for(Enseignant Ens : enseignants)
		{
			if(!metier.enseignantAffectéSalle(Ens,j,s))
				enseignant.add(Ens);
		}
		return enseignant;
	}
	
	
	public Collection<ExamenSalle> chargerSalles(String j , String s)
	{
		Collection<Examen> examens = metier.findExamenBySeance(metier.findSeanceByHeurDebut(j, s));
		Collection<ExamenSalle> examenSalle = new ArrayList<ExamenSalle>();
		
		
		Long nbAS = metier.nbAffectationParSeanceAuxSalle(j, s) ;
		int nbS = 0;//metier.findSeanceByHeurDebut(j, s).getNombreSurveillant();
		
		for(Examen exa : examens)
		{
			nbS+=exa.getSalle().getNbSurveillant();
		}
		
		//nbAS = nbAS + Math.round(nbAS*0.1) ;
		for(Examen e : examens)
		{
			ExamenSalle ex = new ExamenSalle();
			ex.setNbAff(metier.nbAffectationSalle(j, s, e.getSalle()));
			ex.setExamen(e);
			//if(ex.getNbAff()<ex.getExamen().getSalle().getNbSurveillant()||nbAS-nbS>=0)
			if(ex.getNbAff()<ex.getExamen().getSalle().getNbSurveillant()|| ex.getNbAff()<metier.nbExamenSalle(j, s, ex.getExamen().getSalle()) ||nbAS-nbS>=0)
				examenSalle.add(ex);
		}
		
		return examenSalle;
	}
	public Collection<AffectationModele> findAffecation(String date,String heure)
	{
		Collection<AffectationExamen> affeE = metier.findAffectationExamen(date, heure);
		Collection<AffectationModele> affectation = new ArrayList<AffectationModele>();
		for(AffectationExamen a : affeE)
		{
			AffectationModele af= new  AffectationModele() ;
			af.setAffectation(a);
			if(metier.existeRemp(date, heure, a.getSurveillant())){
			Remplacement r = metier.findRemplacement(date, heure, a.getSurveillant());
			if(r!=null)
				af.setRemp(r);
			}else
				af.setRemp(null);
			affectation.add(af);
		}
		return affectation;
	}
	public Collection<Enseignant> listeRemp(Collection<Enseignant> listeE)
	{
		Collection<Enseignant> tous = metier.findAllEnseignantActif();
		Collection<Enseignant> remp = new ArrayList<Enseignant>();
		for(Enseignant a : tous)
		{
			if(!listeE.contains(a))
				remp.add(a);
		}
		return remp;
	}
	public String getMSGA(Collection<AffectationExamen> affeE)
	{
		String MSGA="";
		for(AffectationExamen af : affeE)
		{	
			MatiereEnseignement ME = new MatiereEnseignement(af.getMatiere(), af.getFiliere(), af.getCycleAnnee(), null);
			Collection<MatiereEnseignement> MES = metier.findMatiereEnseigner(af.getSurveillant().getId());
			
			Remplacement r=null;
			 
			if(metier.existeRemp(af.getDateJour().toString().split(" ")[0], af.getHeureDebut().toString(), af.getSurveillant()))
			{
				r = metier.findRemplacement(af.getDateJour().toString().split(" ")[0], af.getHeureDebut().toString(), af.getSurveillant());
			}
			
			if(r==null){
			if(MES.contains(ME))
			{
				MSGA+="<br>Salle "+af.getSalleExamen().getId()+": "+af.getSurveillant().getEnseignant()+" enseigne l'examen à surveiller";
			}
			}else
			{
				MatiereEnseignement MER = new MatiereEnseignement(af.getMatiere(), af.getFiliere(), af.getCycleAnnee(), null);
				Collection<MatiereEnseignement> MESR = metier.findMatiereEnseigner(r.getRemplacant().getId());
				if(MESR.contains(MER))
				{
					MSGA+="<br>Salle "+af.getSalleExamen().getId()+": "+r.getRemplacant().getEnseignant()+" enseigne l'examen à surveiller";
				}
			}
		}
		return MSGA;
	}

}
