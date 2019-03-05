package org.fsegs.gestionvoeux;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fsegs.entitees.AffectationExamen;
import org.fsegs.entitees.AffectationSeance;
import org.fsegs.entitees.Alerte;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.TypeAlerte;
import org.fsegs.metier.ISurveillanceMetier;
import org.fsegs.modele.AlerteStat;
import org.fsegs.modele.HeureModele;
import org.fsegs.modele.JourModele;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlleurConsulterStat {
	
	@Autowired
	private ISurveillanceMetier metier;
	
	@RequestMapping(value="/affichierSession")
	public String affichierSession(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		
		
		int an =0;
		int an2 =0;
		if(request.getParameter("bt1")==null){
		Collection<String> annees = metier.getAnnéeUniversitaire();
		model.addAttribute("annee", annees);
		}else
		{
			
				an = Integer.parseInt(request.getParameter("annee"));
				an2 = Integer.parseInt(request.getParameter("annee2"));
				Collection<String> session = metier.getSessionByAnnee(an,an2);
				Collection<String> ann = new ArrayList<String>();
				ann.add(an+"");
				model.addAttribute("annee", metier.getAnnéeUniversitaire());
				model.addAttribute("aS", an);
				model.addAttribute("aS2", an2);
				model.addAttribute("session", session);
			
		}
		if(request.getParameter("bt2")!=null)
		{
			try {
				String an1 = request.getParameter("annee");
				String an3 = request.getParameter("annee2");
				String se = request.getParameter("session");
				model.addAttribute("teste","1");
				response.sendRedirect("stat?an="+an1+"&an2="+an3+"&se="+se+"");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return "stat";
	}
	
	@RequestMapping(value="/affichierSession2")
	public String affichierSession2(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		

		int an =0;
		int an2 =0;
		if(request.getParameter("bt1")==null){
		Collection<String> annees = metier.getAnnéeUniversitaire();
		model.addAttribute("annee", annees);
		}else
		{
			
				an = Integer.parseInt(request.getParameter("annee"));
				an2 = Integer.parseInt(request.getParameter("annee2"));
				Collection<String> session = metier.getSessionByAnnee(an,an2);
				Collection<String> ann = new ArrayList<String>();
				ann.add(an+"");
				model.addAttribute("annee", metier.getAnnéeUniversitaire());
				model.addAttribute("aS", an);
				model.addAttribute("aS2", an2);
				model.addAttribute("session", session);
			
		}
		if(request.getParameter("bt2")!=null)
		{
			try {
				String an1 = request.getParameter("annee");
				String an3 = request.getParameter("annee2");
				String se = request.getParameter("session");
				model.addAttribute("teste","1");
				response.sendRedirect("statSeance?an="+an1+"&an2="+an3+"&se="+se+"");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "statSurveillance";
	}
	
	@RequestMapping(value="/stat")
	//@ResponseBody
	public String stat(Model model,HttpServletRequest request,int an ,int an2, String se)
	{
		Collection<String> annees = metier.getAnnéeUniversitaire();
		model.addAttribute("annee", annees);
		model.addAttribute("teste","1");
		
		String Session ="Tous";
		if(se.equals("Controle Continu"))
			Session="CC";
		if(se.equals("Principale"))
			Session="EX";
		if(se.equals("Rattrapage"))
			Session="RR";
		
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		Date dateActuelle=dXX.getTime();
		
		dateActuelle = metier.getDateActuelle();
		
		
		Collection<Time> heures = metier.getHeureByAnneeSession(an,an2, Session);
		Collection<HeureModele> liste = new ArrayList<HeureModele>();
		for(Time h : heures)
		{
			liste.add(new HeureModele(h, 0));
		}
		Collection<AffectationSeance> examens = metier.getAffectationByAnneeSession(an,an2,Session);
		for(AffectationSeance aff : examens)
		{
			DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(aff.getDateJour().toString().split(" ")[0].split("-")[0]),Integer.parseInt(aff.getDateJour().toString().split(" ")[0].split("-")[1])-1,Integer.parseInt(aff.getDateJour().toString().split(" ")[0].split("-")[2]),aff.getHeureDebut().getHours(),aff.getHeureDebut().getMinutes(),0);
			Date dateAffectation=d.getTime();
			if(!aff.isPresence()&& dateActuelle.after(dateAffectation))
			{
			for(HeureModele hm : liste)
			{
				if(hm.getHeure().equals(aff.getHeureDebut()))
					hm.setNbAbsence(hm.getNbAbsence()+1);
			}
			}
		}
		String xx ="";
		int i=1;
		for(HeureModele hm : liste){
			//{  y: 52, name: "Time At Work", legendMarkerType: "triangle"},
			if(!(hm.getNbAbsence()==0)){
			xx+="{y:"+hm.getNbAbsence()+",name:\""+hm.getHeure().toString().split(":")[0]+"H"+hm.getHeure().toString().split(":")[1]+"\",legendMarkerType: \"triangle\"}";
			if(i<liste.size())
				xx+=",";
			}
			i++;
		}
		
		request.setAttribute("xx", xx);
		
		
		Collection<JourExamen> jours = metier.getJourExamensByAnneeSession(an,an2,Session);
		Collection<JourModele> liste1 = new ArrayList<JourModele>();
		for(JourExamen j : jours)
		{
			liste1.add(new JourModele(j, 0));
		}
		
		for(AffectationSeance afs : examens)
		{
			DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(afs.getDateJour().toString().split(" ")[0].split("-")[0]),Integer.parseInt(afs.getDateJour().toString().split(" ")[0].split("-")[1])-1,Integer.parseInt(afs.getDateJour().toString().split(" ")[0].split("-")[2]),afs.getHeureDebut().getHours(),afs.getHeureDebut().getMinutes(),0);
			Date dateAffectation=d.getTime();
			if(!afs.isPresence()&& dateActuelle.after(dateAffectation))
			{
				for(JourModele jj : liste1)
				{
					if(afs.getDateJour().equals(jj.getJour().getDate()))
						jj.setNbAbsence(jj.getNbAbsence()+1);
				}
			}
		}
		String xx1 ="";
		int i1=1;
		for(JourModele hm : liste1){
			//{  y: 52, name: "Time At Work", legendMarkerType: "triangle"},
			//if(!(hm.getNbAbsence()==0)){
				xx1+="{ label: \" "+hm.getJour().dateJour().split(" ")[0]+" "+hm.getJour().getDate().toString().split(" ")[0]+" \",  y: "+hm.getNbAbsence()+"  }";
			if(i1<liste1.size())
				xx1+=",";
			//}
			i1++;
		}
		request.setAttribute("xx1", xx1);
		return "stat";
	}
	
	@RequestMapping(value="/statSeance")
	//@ResponseBody
	public String getStatSeance(HttpServletRequest request,int an ,int an2, String se,Model model)
	{
		
		
		Collection<String> annees = metier.getAnnéeUniversitaire();
		model.addAttribute("annee", annees);
		model.addAttribute("teste","1");
		
		String Session ="Tous";
		if(se.equals("Controle Continu"))
			Session="CC";
		if(se.equals("Principale"))
			Session="EX";
		if(se.equals("Rattrapage"))
			Session="RR";
		
		Collection<Time> heures = metier.getHeureByAnneeSession(an,an2,Session);
		Collection<HeureModele> liste = new ArrayList<HeureModele>();
		for(Time h : heures)
		{
			liste.add(new HeureModele(h, 0));
		}
		Collection<AffectationExamen> affectationsExamens = metier.getAffectationExamenByAnneeSession(an,an2,Session);
		Set<AffectationExamen> affectations = new HashSet<AffectationExamen>();
		
		for(AffectationExamen a : affectationsExamens)
		{
			for(AffectationExamen afa : affectationsExamens)
			{
				if(a.getDateJour().equals(afa.getDateJour()) && a.getHeureDebut().equals(afa.getHeureDebut()) && a.getMatiere().equals(afa.getMatiere()) && a.getCycleAnnee().equals(afa.getCycleAnnee()) && a.getFiliere().equals(afa.getFiliere()) && a.getSalleExamen().equals(afa.getSalleExamen())&&a.getHeureReelAff().before(afa.getHeureReelAff()))
				{
					if(true)
					{
						affectations.remove(afa);
						affectations.add(a);
					}
				}else
					affectations.add(a);
				
			}
		}
		
		affectationsExamens = affectations;
		
		for(AffectationExamen af : affectationsExamens)
		{
			Date date = af.getDateJour();
			Time heure = af.getHeureDebut();
			Time heureR = af.getHeureReelAff();
			DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d = Calendar.getInstance();
			d.set(date.getYear()+1900,date.getMonth(),date.getDay(),heure.getHours(),heure.getMinutes(),0);
			d.add(Calendar.MINUTE, -5);
			Date dateD=d.getTime();
			Calendar dX = Calendar.getInstance();
			dX.set(date.getYear()+1900,date.getMonth(),date.getDay(),heureR.getHours(),heureR.getMinutes(),0);
			Date dateDX=dX.getTime();
			if(dateD.before(dateDX))
			{
				for(HeureModele hm : liste)
				{
					if(hm.getHeure().equals(af.getHeureDebut()))
						hm.setNbAbsence(hm.getNbAbsence()+1);
				}
			}
			
		}
		
		
		String xx1 ="";
		int i1=1;
		for(HeureModele hm : liste){
			//{  y: 52, name: "Time At Work", legendMarkerType: "triangle"},
			if(!(hm.getNbAbsence()==0)){
				xx1+="{y : "+hm.getNbAbsence()+", indexLabel: \" Séance de "+hm.getHeure().toString().split(":")[0]+":"+hm.getHeure().toString().split(":")[1]+" : #percent%\", legendText: \""+hm.getHeure().toString().split(":")[0]+":"+hm.getHeure().toString().split(":")[1]+"\" }";
			if(i1<liste.size())
				xx1+=",";
			}
			i1++;
		}
		request.setAttribute("xx", xx1);
		
		Collection<TypeAlerte> types = metier.findAllTypesAlertes();
		Set<AlerteStat> alerteStat = new HashSet<AlerteStat>();
		for(TypeAlerte ty : types)
		{
			alerteStat.add(new AlerteStat(ty, 0));
		}
		
		for(Alerte a : metier.getAlerteByAnneeSession(an,an2,Session))
		{
			for(AlerteStat as : alerteStat)
			{
				if(a.getType().equals(as.getTypeAlerte()))
				{
					as.setNb(as.getNb()+1);
				}
			}
		}
		String Xtype="";
		int i=1;
		double somme =0;
		for(AlerteStat als : alerteStat)
		{
			somme+=als.getNb();
		}
		
		
		List<AlerteStat> time = new ArrayList<AlerteStat>();
		time.addAll(alerteStat);
		//Collections.sort(time);
		Collections.sort(time, new Comparator<AlerteStat>() {
	        @Override
	        public int compare(AlerteStat t1, AlerteStat t2)
	        {

	            return  t1.getNb()>=t2.getNb()?-1:1;
	        }
	    });
		
		
		
		for(AlerteStat als : time)
		{
			if(als.getNb()>0){
			double xy = (als.getNb()/somme)*100;
			
			String xyy = String.valueOf(xy);
			if(xyy.length()>=4){
				xyy=xyy.split("\\.")[0]+"."+(xyy.split("\\.")[1].length()>2?xyy.split("\\.")[1].substring(0, 2):xyy.split("\\.")[1].substring(0, 1));
			}
			Xtype+="{ y: "+als.getNb()+", label: \""+xyy+"%\", indexLabel: \""+als.getTypeAlerte().getLibelle()+"\" }";
			if(i<time.size())
				Xtype+=",";
			}
			
			
			i++;
			
		}
		
		request.setAttribute("xx2", Xtype);
		
		
		
		return "statSurveillance";
	}

}
