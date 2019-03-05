package org.fsegs.gestionvoeux;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.fsegs.entitees.*;
import org.fsegs.metier.ISurveillanceMetier;
import org.fsegs.modele.CalendrierExamen;
import org.fsegs.modele.JourAffectation;
import org.fsegs.modele.SeanceAffectation;
import org.fsegs.modele.SeanceExamen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import teste.smtpTest;

@Controller
public class ControlleurAffectationSeances {
	
	@Autowired
	private ISurveillanceMetier metier;
	
	@RequestMapping(value="/avencementAff")
	public String avencement(Model model,HttpServletRequest request)
	{
		Collection<CalendrierExamen> cal = findCalendrier2();
		String xx="";
		int i=0;
		for(CalendrierExamen c : cal)
		{
			for(SeanceExamen s : c.getSeanceExamens())
			{
				i++;
				s.setUrl("<div id=\""+c.getDate().toString().split(" ")[0]+s.getHeureDebut()+"\" style=\"height: 85px; width: 100%;\"></div");
				
				String chart1 = "var A"+i+" = new CanvasJS.Chart(\""+c.getDate().toString().split(" ")[0]+s.getHeureDebut()+"\","+
						"{"+
					
			                "animationEnabled: true,"+
					"legend: {"+
						"verticalAlign: \"bottom\","+
						"horizontalAlign: \"center\""+
					"},"+
					"theme: \"theme3\","+
					"data: ["+
					"{"+        
						"type: \"pie\","+
						"indexLabelFontFamily: \"Garamond\", "+      
						"indexLabelFontSize: 20,"+
						"indexLabelFontWeight: \"bold\","+
						"startAngle:0,"+
						"indexLabelFontColor: \"MistyRose\","+       
						"indexLabelLineColor: \"darkgrey\","+ 
						"indexLabelPlacement: \"inside\", "+
						
						"showInLegend: false,"+
						"indexLabel: \"{x}{y}\","+ 
						"dataPoints: ["+
							"{  y: "+String.valueOf(s.getNombreSurveillant()-s.getNombreAffectationSurveillant()>=0?s.getNombreSurveillant()-s.getNombreAffectationSurveillant():0)+", name: \"Libre\" ,color: \"#01DF01\"},"+
							
							"{  y: "+String.valueOf(s.getNombreAffectationSurveillant())+", name: \"Affecté\",color: \"red\"}"+
						"]"+
					"}"+
					"]"+
				"});"+
				"A"+i+".render();  \n";
				
				xx+=chart1;
				
				
			}
		}
		model.addAttribute("calendrier2", cal);
		model.addAttribute("heures", metier.findHeureSeance());
		
		request.setAttribute("teste", xx);
		return "avencementAff";
	}
	
	public Collection<Enseignant> getListe()
	{
		Collection<Enseignant> enseignants = metier.findAllSurveillants();
		Collection<Enseignant> surveillants = new ArrayList<Enseignant>();
		for(Enseignant e : enseignants)
		{
			if(e.getaSruveiller()>0)
				surveillants.add(e);
		}
		return surveillants;
	}
	
	@RequestMapping(value="/listeEnsAaff")
	public String listeEnseignants(Model model)
	{
		
		model.addAttribute("enseignants", getListe());
		return "listeEnseignants";
	}
	@RequestMapping(value="/affectationSurveillant")
	//@ResponseBody
	public String affectationSurveillant(Model model , String id)
	{
		Collection<JourAffectation> jourAff = new ArrayList<JourAffectation>();
	    Collection<JourExamen> jours = metier.findAllJourExamen();
	    Set<String> emp = getClandrier(Long.parseLong(id));
	    for(JourExamen j : jours)
	    {
	    	JourAffectation jourAffectation = new JourAffectation();
	    	jourAffectation.setDate(j.getDate());
	    	jourAffectation.setJourEnsMast(false);
	    	for(Enseignement e : metier.getCalendrierEnseignement(Long.parseLong(id)))
	    	{
	    		if(j.getDate().getDay()-e.getJour().getId()==0){
	    			int cycle= e.getMatiereEnseignement().getCycleAnnee().getId();
	    			if(cycle==51 || cycle==53)
	    			{
	    				jourAffectation.setJourEnsMast(true);
	    			}
	    		}
	    	}
	    	
	    	Collection<SeanceAffectation> seancesAff = new ArrayList<SeanceAffectation>();
	    	
	    	Collection<Seance> seances = metier.findSeanceByDateJour(j.getDate());
	    	for(Seance s : seances)
	    	{
	    		
	    		SeanceAffectation seanceA = new SeanceAffectation();
	    		String d[] = s.getJourExamen().getDate().toString().split(" ");
	    		String h[] = s.getHeureDebut().toString().split(":");
	    		Boolean affecter = metier.verifierAffectation(d[0], s.getHeureDebut().toString(), id);
	    		Boolean stature = metier.nbAffectationParSeance(d[0],s.getHeureDebut().toString())==s.getNombreSurveillant();
	    		Boolean resp =false;
	    		Boolean ens = false;
	    		Collection<MatiereEnseignement> ME = metier.findMatiereParSeance(s);
				for(MatiereEnseignement x : ME)
				{
					if(x.getEnseignant().getId()==Long.parseLong(id)){
						resp=true;
						break ;
					}
					if(metier.findMatiereEnseigner(Long.parseLong(id)).contains(x)){
						ens=true;
						break ;
					}
				}
	    		seanceA.setAffecter(affecter);
	    		seanceA.setHeureDebut(s.getHeureDebut());
	    		seanceA.setResponsable(resp);
	    		seanceA.setSaturer(stature);
	    		seanceA.setEns(ens);
	    		seanceA.setEmp(false);
	    		seanceA.setEmpM(false);
	    		if(emp.contains(s.getJourExamen().getDate().getDay()+"X"+s.getHeureDebut()+"L"))
	    			seanceA.setEmp(true);
	    		if(emp.contains(s.getJourExamen().getDate().getDay()+"X"+s.getHeureDebut()+"M")){
	    			if(metier.isCC())
	    				seanceA.setSaturer(true);
	    			seanceA.setEmpM(true);
	    		}
	    		seancesAff.add(seanceA);
	    		
				
	    		
	    	}
	    	jourAffectation.setSeances(seancesAff);
	    	jourAff.add(jourAffectation);
	    }
	    model.addAttribute("enseignant", metier.findEnseignant(Long.parseLong(id)));
		model.addAttribute("grille", jourAff);
		model.addAttribute("heures", metier.findHeureSeance());
		return "grille";
	}
	@RequestMapping(value="/modifierAffectationEns", method=RequestMethod.POST)
	//@ResponseBody
	public String modifierAffectationEns(HttpServletRequest request,Model model)
	{
		String id = request.getParameter("id");
		String[] x = request.getParameterValues("seance[]");
		//String mail = request.getParameterValues("email")[0];
		metier.supprimerAffectationSeance(Long.parseLong(id));
		if(x!=null){
		for(String voeu:x){
	    	
	    	String d[]= voeu.split("I");
	    	Enseignant e = metier.findEnseignant(Long.parseLong(id));
	    	Seance s = metier.findSeanceByHeurDebut(d[0].split(" ")[0], d[1]);
	    	
	    	metier.addAffectationSeance(s, e);
	    }
		}
		metier.supprimerDemande(Long.parseLong(id));
		model.addAttribute("enseignants", getListe());
		//if(mail.equals("on"))
		if(request.getParameter("email")!=null){
			
			Collection<AffectationSeance> liste = metier.findAffectationById(Long.parseLong(id));
			String msg ="<table border=1 width=70% align=center bgcolor=#BDBDBD>";
			msg+="<tr align=center height=50><td colspan=4><b>M."+metier.findEnseignant(Long.parseLong(id)).getEnseignant()+"";
			msg+="<tr bgcolor=#58FA58 align=center><td colspan=4><b>Vous êtes affecté à "+liste.size()+" séances";
			msg+="<tr bgcolor=#CEECF5 align=center><td colspan=3><b>Date<td><b>Séance";
			int i=1;
			for(AffectationSeance s : liste)
			{
				JourExamen j = new JourExamen();
				j.setDate(s.getDateJour());
				msg+="<tr bgcolor=#81BEF7 align=center><td>"+i+"<td colspan=2>"+j.dateJour()+"<td>"+s.getHeureDebut().toString().split(":")[0]+":"+s.getHeureDebut().toString().split(":")[1];
				i++;
			}
			///msg+="</table>";
			//msg+="Vos épreuves : <br><br>";
			msg+="<tr bgcolor=#58FA58 align=center><td colspan=4><b>Vos épreuves";
			msg+="<tr bgcolor=#CEECF5 align=center><td colspan=2><b>Examen<td><b>Date<td><b>Séance";
			Collection<MatiereEnseignement> mat = metier.getMatiereEnseignementsByEnseignant(metier.findEnseignant(Long.parseLong(id)));
			i=1;
			for(MatiereEnseignement e : mat)
				for(Seance s : metier.findSeancePourMatiere(e)){
					msg+="<tr bgcolor=#81BEF7 align=center><td>"+i+"<td>"+e.getMatiere().getAbreviation()+" "+e.getCycleAnnee().getAnnee()+" "+e.getFiliere().getId()+"<td>"+s.getJourExamen().getDate().toString().split(" ")[0]+"<td>"+s.getHeureDebut().toString().split(":")[0]+":"+s.getHeureDebut().toString().split(":")[1];
				i++;
				}//System.out.println(e.getMatiere().getMatiere()+" "+e.getCycleAnnee().getAnnee()+" "+e.getFiliere().getId()+" "+s.getJourExamen().getDate()+" à "+s.getHeureDebut());
			msg+="</table>";
			smtpTest mail = new smtpTest();
			mail.envoieMail(msg,"med.amin.mallek@gmail.com",metier.findEnseignant(Long.parseLong(id)));
		}
			
		
		
		return "listeEnseignants";
		
		//return "listeEnseignants";
	}
	@RequestMapping(value="/affectationAuto")
	public String affectationAuto()
	{
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
		boolean sature = besoin<=aff?true:false;
		
		
		/*    */
		
		Collection<JourExamen> jours = metier.findAllJourExamen();
		JourExamen jour = new JourExamen();
		for(JourExamen j : jours)
		{
			jour = j;
			break;
		}
		for(JourExamen j : jours)
		{
			if(j.getDate().before(jour.getDate()))
				jour=j;
		}
		Date dateJour = metier.getDateActuelle();
		
		long diff =  jour.getDate().getTime() - dateJour.getTime();
		
		diff = (diff / (1000*60*60*24));
        
		
        boolean affectationAuto = diff<=7?true:false;
        
        if(affectationAuto&&!sature)
        {
		if(!metier.isCC()){
		metier.premiereAffectationExamen();
		metier.deuxiemeAffectationExamen();
		metier.troisiemeAffectationExamen();
		}else
		{
		metier.premiereAffectationCC();
		metier.deuxiemeAffectationCC();
		metier.troisiemeAffectationCC();
		}
        }
		return "home";
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
	/*
	public void premiereAffectation()
	{
		
		Collection<Seance> allSeances = metier.findAllSeance();
		Collection<Seance> seanceLibre = new ArrayList<Seance>();
		for(Seance s : allSeances)
		{
			String date[] = s.getJourExamen().getDate().toString().split(" ");
			if(s.getNombreSurveillant()>metier.nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
			{
				seanceLibre.add(s);
			}
		}
		Collection<Enseignant> enseignants = metier.findAllSurveillants();
		Collection<Enseignant> enseignantLibre = new ArrayList<Enseignant>();
		for(Enseignant e : enseignants)
		{
			if(e.getaSruveiller()>metier.nbAffectationParSurveillant(e.getId()))
				enseignantLibre.add(e);
		}
		for(Enseignant e1 : enseignantLibre)
		{
			int nbAff = metier.nbAffectationParSurveillant(e1.getId());
			
			Collection<Enseignement> jours = metier.findJourEnseignement(e1.getId());
			Set<JourEnseignement> jourEnseignement = new HashSet<JourEnseignement>();
			for(Enseignement ens : jours)
				jourEnseignement.add(ens.getJour());
			boolean sortir1=false;
			boolean sortir2=false;
			while(e1.getaSruveiller()>nbAff && !sortir1 && !sortir2)
			{
				int jj = 1;
				for(JourEnseignement jour : jourEnseignement)
				{
					int ss=1;
					for(Seance se : seanceLibre)
					{
						String[] tabD = se.getJourExamen().getDate().toString().split(" ");
						if(se.getNombreSurveillant()>metier.nbAffectationParSeance(tabD[0], se.getHeureDebut().toString()))
						{
						Boolean verif = metier.verifierAffectation(tabD[0], se.getHeureDebut().toString(), String.valueOf(e1.getId()));
						if(se.getJourExamen().getDate().getDay()==jour.getId() &&!verif)
						{	
							metier.addAffectationSeance(se, e1);
							nbAff++;
							break;
						}
						if(e1.getaSruveiller()<=nbAff)
							break;
						}
						if(seanceLibre.size()==ss)
							sortir1=true;
						ss++;
					}
					if(e1.getaSruveiller()<=nbAff)
						break;
					if(jourEnseignement.size()==jj)
						sortir2=true;
					jj++;
				}
			}
		}
	}
	
	
	
	public void deuxiemeAffectation()
	{
		Collection<Seance> allSeances = metier.findAllSeance();
		Collection<Seance> seanceLibre = new ArrayList<Seance>();
		for(Seance s : allSeances)
		{
			String date[] = s.getJourExamen().getDate().toString().split(" ");
			if(s.getNombreSurveillant()>metier.nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
			{
				seanceLibre.add(s);
			}
		}
		System.out.println(seanceLibre.size());
		Collection<Enseignant> enseignants = metier.findAllSurveillants();
		Collection<Enseignant> enseignantLibre = new ArrayList<Enseignant>();
		for(Enseignant e : enseignants)
		{
			if(e.getaSruveiller()>metier.nbAffectationParSurveillant(e.getId()))
				enseignantLibre.add(e);
		}
		for(Enseignant e1 : enseignantLibre)
		{
			int nbAff = metier.nbAffectationParSurveillant(e1.getId());
			
			Collection<Enseignement> jours = metier.findJourEnseignement(e1.getId());
			Set<JourEnseignement> jourEnseignement = new HashSet<JourEnseignement>();
			for(Enseignement ens : jours)
				jourEnseignement.add(ens.getJour());
			boolean sortir1=false;
			boolean sortir2=false;
			while(e1.getaSruveiller()>nbAff && !sortir1 && !sortir2)
			{
				int jj = 1;
				for(JourEnseignement jour : jourEnseignement)
				{
					int ss=1;
					for(Seance se : seanceLibre)
					{
						String[] tabD = se.getJourExamen().getDate().toString().split(" ");
						if(se.getNombreSurveillant()>metier.nbAffectationParSeance(tabD[0], se.getHeureDebut().toString()))
						{
						Boolean verif = metier.verifierAffectation(tabD[0], se.getHeureDebut().toString(), String.valueOf(e1.getId()));
						if(!verif)
						{	
							metier.addAffectationSeance(se, e1);
							nbAff++;
							break;
						}
						if(e1.getaSruveiller()<=nbAff)
							break;
						}
						if(seanceLibre.size()==ss)
							sortir1=true;
						ss++;
					}
					if(e1.getaSruveiller()<=nbAff)
						break;
					if(jourEnseignement.size()==jj)
						sortir2=true;
					jj++;
				}
			}
		}
	}
	
	public void troixiemeAffectation()
	{
		
	}
	*/
	public Collection<CalendrierExamen> findCalendrier2()
	{
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
				String[] tabD =(s.getJourExamen().getDate()+"").split(" ");
				se.setNombreSurveillant(s.getNombreSurveillant());
				se.setNombreCommission(s.getNombreMembreC());
				String[] date = s.getJourExamen().getDate().toString().split(" ");
				se.setNombreAffectationSurveillant(metier.nbAffectationParSeance(date[0], s.getHeureDebut().toString()));
				seances.add(se);
				
			}
			c.setSeanceExamens(seances);
			calendrier.add(c);
		}
		return calendrier;
	}
	
}
