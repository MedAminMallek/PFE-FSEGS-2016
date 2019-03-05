package org.fsegs.gestionvoeux;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fsegs.entitees.AffectationSeance;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Enseignement;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.MatiereEnseignement;
import org.fsegs.entitees.Seance;
import org.fsegs.metier.ISurveillanceMetier;
import org.fsegs.modele.CalendrierExamen;
import org.fsegs.modele.SeanceExamen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlleurCalendrier {
	
	@Autowired
	private ISurveillanceMetier metier;
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("userC")!=null)
			return "home";
		else
		{

			if(session.getAttribute("nb")!=null)
			{
				String nb = session.getAttribute("nb").toString();
				if(Integer.parseInt(nb)>2)
					model.addAttribute("bloque", "Yes");
			}
			return "login";
		}
	}
	@RequestMapping(value="/teste")
	public String teste(Model model)
	{
		model.addAttribute("n", 5);
		return "teste";
	}
	@RequestMapping(value="/testeP")
	public String testeP()
	{
		
		return "testeP";
	}
	@RequestMapping(value="/testeP2")
	public String testeP2(Model model,String x)
	{
		model.addAttribute("x", x);
		return "testeP2";
	}
	@RequestMapping(value="/calEx")
	public String calEx(Model model)
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
		
		model.addAttribute("heures", metier.findHeureSeance());
		model.addAttribute("cal", findCalendrierEx());
		return "calEx";
	}
	@RequestMapping(value="/afficherMat")
	public String afficherMat(Model model,String date,String heure)
	{
		Seance s = metier.findSeanceByHeurDebut(date, heure);
		Set<String> liste = new HashSet<String>();
		for(MatiereEnseignement mm : metier.findMatiereParSeance(s))
		{
			liste.add(mm.getMatiere().getMatiere()+"99"+mm.getCycleAnnee().getAnnee()+"99"+mm.getFiliere().getLibelle());
		}
		/*Collection<MatiereEnseignement> matE = new ArrayList<MatiereEnseignement>();
		for(String x : liste)
		{
			MatiereEnseignement m = new MatiereEnseignement();
			String[] xx = x.toString().split("*");
			
		}*/
		JourExamen j = new JourExamen();
		j.setDate(new Date(Integer.parseInt(date.split("-")[0])-1900,Integer.parseInt(date.split("-")[1])-1, Integer.parseInt(date.split("-")[2])));
		model.addAttribute("date", j.dateJour()+" "+heure.split(":")[0]+":"+heure.split(":")[1]);
		
		model.addAttribute("mat", liste);
		return "listeMat";
	}
	
	@RequestMapping(value="/CalendrierSurveillance")
	public String calen(Model model)
	{

		model.addAttribute("calendrier", findCalendrier());
		model.addAttribute("heures", metier.findHeureSeance());
		//model.addAttribute("BesoinC", metier.besoinCalculer());
		return "calendrierSur";
	}
	
	@RequestMapping(value="/creerCalSurveillance")
	public String creerCalSurveillance(Model model)
	{
		if(metier.besoinCalcule()){
		Collection<CalendrierExamen> cal = new ArrayList<CalendrierExamen>();
		Collection<JourExamen> jours = metier.findAllJourExamen();
		for(JourExamen j : jours)
		{
			Collection<Seance> seances = metier.findSeanceByDateJour(j.getDate());
			CalendrierExamen c = new CalendrierExamen();
			c.setDate(j.getDate());
			Collection<SeanceExamen> ss = new ArrayList<SeanceExamen>();
			for(Seance s : seances)
			{
				SeanceExamen X = new SeanceExamen();
				X.setHeureDebut(s.getHeureDebut());
				X.setNombreSurveillant(s.getNombreSurveillant());
				X.setNombreCommission(s.getNombreMembreC());
				ss.add(X);
			}
			c.setSeanceExamens(ss);
			cal.add(c);
		}
		model.addAttribute("calendrier2", cal);
		}else
		model.addAttribute("calendrier2", findCalendrier());
		model.addAttribute("heures", metier.findHeureSeance());
		
		return "calendrierSurM";
	}
	@RequestMapping(value="/SurveillantCharge")
	public String SurveillantCharge(Model model)
	{
		model.addAttribute("charges", findEnsCharge());
		return "chargeEnseignant";
	}
	
	
	@RequestMapping(value="/validerParamSur", method=RequestMethod.POST)
	//@ResponseBody
	public String validerParamSur(HttpServletRequest  request,Model model)
	{ 	
		String date;
		String time;
		List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
		String xx="";
		for(String e : parameterNames)
		{	
			String[] tab = e.split(" P ");
			String[] tabM = e.split(" M ");
			if(tab.length==2){
			date=tab[0];
			String[] tab2=date.split(" "); 
			date=tab2[0];
			String[] tabdate = date.split("-");
			time=tab[1];
			metier.updateNbSurvaillantParSeance(tab2[0], tab[1], Integer.parseInt(request.getParameter(e)));
			}
			if(tabM.length==2){
				date=tabM[0];
				String[] tab2=date.split(" "); 
				date=tab2[0];
				String[] tabdate = date.split("-");
				time=tabM[1];
				metier.updateNbCommissionParSeance(tab2[0], tabM[1], Integer.parseInt(request.getParameter(e)));
				}
			
		}
		
		model.addAttribute("charges", findEnsCharge());
		
		return "chargeEnseignant";
	}
	
	@RequestMapping(value="/miseAjourCharge",method=RequestMethod.POST)
	//@ResponseBody
	public String ChargeEnseignant(HttpServletRequest  request,Model model)
	{
		
		List<String> parameterNames = new ArrayList<String>(request.getParameterMap().keySet());
		String xx="";
		for(String x : parameterNames){
			metier.updateChargeDeSurveillance(Long.parseLong(x), Integer.parseInt(request.getParameter(x)));
			int NbAffParS = metier.nbAffectationParSurveillant(Long.parseLong(x));
			int ChargeS = metier.findEnseignant(Long.parseLong(x)).getaSruveiller();
			if(NbAffParS>ChargeS)
			{
				Collection<AffectationSeance> afsE= metier.findAffectationById(Long.parseLong(x));
				int NBAFFS=ChargeS;
				for(AffectationSeance af : afsE)
				{
					if(NBAFFS<NbAffParS)
					{
						metier.supprimerAffectationSeance(x, af.getDateJour().toString().split(" ")[0], af.getHeureDebut().toString());
						NbAffParS--;
					}else
						break;
					
				}
			}
		}
		
		if(!metier.RespAffecté()){
		Collection<Examen> examens = metier.findAllExamen();
		Collection<AffectationSeance> affectations = metier.findAllAffectationSeances();
		for(Examen e : examens)
		{
			AffectationSeance aff = new AffectationSeance();
			aff.setDateJour(e.getSeance().getJourExamen().getDate());
			aff.setEnseignant(e.getMatiereEnseignement().getEnseignant());
			aff.setHeureDebut(e.getSeance().getHeureDebut());
			if(affectations.contains(aff)==false && e.getMatiereEnseignement().getEnseignant().isCommission()==false)
				affectations.add(aff);
		}
		
		for(AffectationSeance a : affectations)
		{
			try{
			if(!metier.findAffectation(a)){
			//if(metier.nbAffectationParSurveillant(a.getEnseignant().getId())==0){
			String tabDSA[] = a.getDateJour().toString().split(" ");
			Seance sA = metier.findSeanceByHeurDebut(tabDSA[0], a.getHeureDebut().toString());
			Set<String> emp = getClandrier(a.getEnseignant().getId());
			boolean MasterInCC = metier.isCC() && emp.contains(a.getDateJour().getDay()+"X"+a.getHeureDebut()+"M") ;
			if(a.getEnseignant().getaSruveiller()>metier.nbAffectationParSurveillant(a.getEnseignant().getId())&& ! MasterInCC)
				metier.addAffectationSeance(sA, a.getEnseignant());
			}
			}catch(Exception e)
			{
				return "home";
			}
		}
		}
		return "home";
	}
	
	public Collection<org.fsegs.modele.JourExamen> findCalendrierEx()
	{
		Collection<org.fsegs.modele.JourExamen> calendrier = new ArrayList<org.fsegs.modele.JourExamen>();
		
		Collection<JourExamen> jourE = metier.findAllJourExamen();
		for(JourExamen j : jourE)
		{
			org.fsegs.modele.JourExamen c = new org.fsegs.modele.JourExamen();
			c.setJour(j);
			c.setDate(j.getDate());
			Collection<Seance> seanceE= metier.findSeanceByDateJour(j.getDate());
			c.setSeances(seanceE);
			calendrier.add(c);
		}
		return calendrier;
	}
	
	
	
	public Collection<CalendrierExamen> findCalendrier()
	{
		if(!metier.besoinCalcule())
		{
			Collection<Enseignant> ens = metier.findAllEnseignantActif();
			for(Enseignant e : ens)
			metier.updateChargeDeSurveillance(e.getId(), 0);
		}
		
		
		Collection<CalendrierExamen> calendrier = new ArrayList<CalendrierExamen>();
		
		Collection<JourExamen> jourE = metier.findAllJourExamen();
		for(JourExamen j : jourE)
		{	
			CalendrierExamen c = new CalendrierExamen();
			c.setDate(j.getDate());
			Collection<Seance> seanceE= metier.findSeanceByDateJour(j.getDate());
			Collection<SeanceExamen> seances = new ArrayList<SeanceExamen>();
			for(Seance s : seanceE)
			{
				SeanceExamen se = new SeanceExamen();
				se.setHeureDebut(s.getHeureDebut());
				Set<String> liste = new HashSet<String>();
				for(MatiereEnseignement mm : metier.findMatiereParSeance(s))
				{
					liste.add(mm.getMatiere().getAbreviation()+" "+mm.getCycleAnnee().getAnnee()+" "+mm.getFiliere().getLibelle());
				}
				se.setMatieres(liste);
				String[] tabD =(s.getJourExamen().getDate()+"").split(" ");
				
				if(metier.findSeanceByHeurDebut(tabD[0],String.valueOf(s.getHeureDebut())).getNombreSurveillant()>0 )
				{
					int xx = metier.findSeanceByHeurDebut(tabD[0],String.valueOf(s.getHeureDebut())).getNombreSurveillant();
					se.setNombreSurveillant(xx);
				}else{
					se.setNombreSurveillant(metier.calculerNbSurveillantParSeance(s));
					metier.updateNbSurvaillantParSeance(s.getJourExamen().getDate().toString().split(" ")[0],s.getHeureDebut().toString(),metier.calculerNbSurveillantParSeance(s));
				}
				se.setNombreCommission(metier.calculerNbMembreCommissionParSeance(s));
				String[] date = s.getJourExamen().getDate().toString().split(" ");
				se.setNombreAffectationSurveillant(metier.nbAffectationParSeance(date[0], s.getHeureDebut().toString()));
				seances.add(se);
				
			}
			c.setSeanceExamens(seances);
			calendrier.add(c);
		}
		return calendrier;
	}
	public Collection<Enseignant> findEnsCharge()
	{
		
		Collection<Enseignant> enseignants = metier.findAllSurveillants();
		if(metier.chargeCalculé()==false){
		double globale = metier.calculeChargeHoraireGlobaleDesSurveillants();
		for(Enseignant e : enseignants)
		{
			double rapport =metier.calculeNbHeureSurveillant(e)/globale;
			double nbseance = rapport *metier.findAllSeance().size();
			int a = metier.chargeHoraireDeSurveillance(e);
			
			if(metier.findEnseignant(e.getId()).getaSruveiller()>0)
				e.setaSruveiller(e.getaSruveiller());
			else
			e.setaSruveiller(a);
		}
		}else
		{
			for(Enseignant e : enseignants)
			{
				e.setaSruveiller(e.getaSruveiller());
				
			}
		}
		Collection<Enseignant> ens = new ArrayList<Enseignant>();
		
		for(Enseignant e1 : enseignants)
		{
			if(e1.getaSruveiller()>0)
				ens.add(e1);
		}
		
		
		return ens;
	}
	public Set<String> getClandrier(Long id)
	{
		Set<String> emp = new HashSet<String>();
		Collection<Time> heure = metier.findHeureSeance();
		Collection<Enseignement> ens = metier.getCalendrierEnseignement(id);
		for(Enseignement e : ens)
		{
			int typeC = e.getMatiereEnseignement().getCycleAnnee().getId();
			String type="";
			if(typeC==51 || typeC ==53)
			{
				type="M";
			}else
				type="L";
			Time tt = new Time(Integer.parseInt(e.getHeureDebut().split(":")[0]),Integer.parseInt(e.getHeureDebut().split(":")[1]), 0);
			Boolean x=false;
			Time ttt=null;
			Time TX = null;
			for(Time t : heure)
			{
				if(t.before(tt)){
					TX = t;
					x=true;
				}
				ttt=t;
					
			}
			
			if(x==false){
				int i=0;
				for(Time XX : heure)
				{
					if(tt.before(XX)){
						//System.out.println(e.getJour().getJour()+" "+tt+" "+XX);
						emp.add(e.getJour().getId()+"X"+XX+type);
						i=1;
						break;
					}
				}
				if(i==0)
				emp.add(e.getJour().getId()+"X"+ttt+type);
			}
			else
				emp.add(e.getJour().getId()+"X"+TX+type);
		}
		return emp;
	}
}
