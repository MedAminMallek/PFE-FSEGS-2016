package org.fsegs.gestionvoeux;

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

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JEditorPane;

import org.fsegs.entitees.AffectationSeance;
import org.fsegs.entitees.DemandeEchangeS;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Enseignement;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.MatiereEnseignement;
import org.fsegs.entitees.Seance;
import org.fsegs.metier.ISurveillanceMetier;
import org.fsegs.modele.MatiereParResp;
import org.fsegs.modele.SeanceExamen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import teste.smtpTest;

@Controller
public class ControlleurGestionVoeux {

	@Autowired
	private ISurveillanceMetier metier;
	@RequestMapping(value="/login")
	@ResponseBody
	public String login(String nom,String passw,HttpServletRequest request,HttpServletResponse response)
	{
		//response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
		try{
			if(metier.loginEnseignant(nom,passw)!=null)
			return "["+metier.loginEnseignant(nom,passw).getJSON()+"]";
			else
				return "[{\"id\":\"-1\"}]";
				
		}catch(Exception e)
		{
			return "[{\"id\":\"-1\"}]";
		}
		
	}
	@RequestMapping(value="/chargerJour")
	@ResponseBody
	public String chargerJour(HttpServletRequest request,HttpServletResponse response)
	{
		//response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    Collection<JourExamen> jourJSON = metier.findAllJourExamen();
		String json="[";
		int n=1;
		for(JourExamen jj : jourJSON)
		{
			if(n==jourJSON.size())
				json+=jj.getJASON();
			else
				json+=jj.getJASON()+",";
			n++;
		}
		json+="]";
		
		return json;
		
	}
	@RequestMapping(value="/nbAff")
	@ResponseBody
	public String nbAffectation(HttpServletRequest request,HttpServletResponse response,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
		try{
		int x = metier.nbAffectationParSurveillant(Long.parseLong(id));
		return "[{\"nb\":\""+x+"\"}]";
		}catch(Exception e)
		{
			return "[{\"nb\":\"0\"}]";
		}
	}
	
	@RequestMapping(value="/seances")
	@ResponseBody
	public String seances(HttpServletRequest request,HttpServletResponse response,String date,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
		Collection<Seance> seances = metier.seanceLibre(date);
		Collection<Seance> seancesChoisi = metier.seancesAffecterPourSur(Long.parseLong(id));
		Collection<Seance> seanceAenvoier = new ArrayList<Seance>();
		boolean existe=false;
		for(Seance s : seances)
		{
			/*
			existe=false;
			for(Seance x : seancesChoisi)
			{
				if(s.getHeureDebut().toString().equals(x.getHeureDebut().toString())&& s.getJourExamen().getDate().toString().equals(x.getJourExamen().getDate().toString()))
					existe=true;
			}
			if(existe==false)
				seanceAenvoier.add(s);
			*/
			if(seancesChoisi.contains(s)==false)
				seanceAenvoier.add(s);
		}
		String json="[";
		int n=1;
		for(Seance jj : seanceAenvoier)
		{
			if(n==seanceAenvoier.size())
				json+=jj.getJSON();
			else
				json+=jj.getJSON()+",";
			n++;
		}
		json+="]";
		
		return json;
	}
	
	@RequestMapping(value="/affectation")
	@ResponseBody
	public String ajouterAff(HttpServletRequest request,HttpServletResponse response,String date,String id,String heure)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    Enseignant e = metier.findEnseignant(Long.parseLong(id));
	    Seance s = metier.findSeanceByHeurDebut(date, heure);
	    metier.addAffectationSeance(s, e);
	    
	    return "";
	}
	@RequestMapping(value="/matiereRes")
	@ResponseBody
	public String matiereRsp(HttpServletRequest request,HttpServletResponse response,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    Collection<Seance> seances = metier.findAllSeance();
		Collection<MatiereParResp> list = new ArrayList<MatiereParResp>();
		for(Seance e : seances)
		{
			Collection<MatiereEnseignement> ME = metier.findMatiereParSeance(e);
			for(MatiereEnseignement x : ME)
			{
				if(x.getEnseignant().getId()==Long.parseLong(id)){
					MatiereParResp xxx = new MatiereParResp();
					xxx.setMatiere(x);
					xxx.setSeance(e);
					list.add(xxx);
				}
			}
		}
		String json="[";
		int n=1;
		for(MatiereParResp jj : list)
		{
			if(n==list.size())
				json+=jj.getJASON();
			else
				json+=jj.getJASON()+",";
			n++;
		}
		json+="]";

		return json;
	}
	@RequestMapping(value="/listeAffectation")
	@ResponseBody
	public String listeAff(HttpServletRequest request,HttpServletResponse response,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    String json="[";
	    Collection<Seance> s = metier.seancesAffecterPourSur(Long.parseLong(id));
		int n=1;
		for(Seance s1 : s)
		{
			if(n==s.size())
				json+=s1.getJSON();
			else
				json+=s1.getJSON()+",";
			n++;
		}
		json+="]";

	   
	    
	    return json;
	}
	@RequestMapping(value="/listeAffectationAProp")
	@ResponseBody
	public String listeAffAProp(HttpServletRequest request,HttpServletResponse response,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    String json="[";
	    Collection<Seance> s = metier.seancesAffecterPourSur(Long.parseLong(id));
	    Collection<DemandeEchangeS> de = metier.findDemandeEnvParId(16l);
	    Set<String> deEnv = new HashSet<String>();
	    for(DemandeEchangeS x : de)
	    {
	    	deEnv.add(x.getDateJourP()+""+x.getHeureDebutP());
	    }
		int n=1;
		for(Seance s1 : s)
		{
			if(deEnv.contains(s1.getJourExamen().getDate()+""+s1.getHeureDebut())==false && s1.getJourExamen().getDate().after(metier.getDateActuelle())){
			if(n==s.size())
				json+=s1.getJSON();
			else
				json+=s1.getJSON()+",";
			}
			n++;
		}
		json+="]";
		return json;
	}   
	    
	@RequestMapping(value="/SupprimerAffectation")
	@ResponseBody
	public String SupprimerAffectation(HttpServletRequest request,HttpServletResponse response,String date,String id,String heure)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    metier.supprimerAffectationSeance(id, date, heure);
	    metier.supprimerDemandeEchange(id, date, heure);
	    return "";
	}
	@RequestMapping(value="/listeSeanceSature")
	@ResponseBody
	public String listeSéanceS(HttpServletRequest request,HttpServletResponse response,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    Collection<Seance> seances = metier.findSeancesSaturé();
		Set<String> se = new HashSet<String>();
		Collection<Seance> seancesSS = new ArrayList<Seance>();
		for(Seance s : seances)
		{
			String date[] = s.getJourExamen().getDate().toString().split(" ");
			Collection<AffectationSeance> affs = metier.findAffectationSeance(date[0], s.getHeureDebut().toString());
			Boolean teste = true;
			for(AffectationSeance as : affs)
			{
				if(as.getEnseignant().getId()==Long.parseLong(id) || metier.findDemande(as.getDateJour(), as.getHeureDebut(), Long.parseLong(id))){
				teste=false;
				break;
				}
			}
			if(teste)
			{
				long diff =  s.getJourExamen().getDate().getTime() - metier.getDateActuelle().getTime();
				
				diff = (diff / (1000*60*60*24));
				if(s.getJourExamen().getDate().after(metier.getDateActuelle())||diff==0){
				seancesSS.add(s);
				String[] dat = s.getJourExamen().getDate().toString().split(" ");
				se.add(s.getJSON());
				}
			}
		}
		List<String> liste = new ArrayList<String>();
		liste.addAll(se);
		Collections.sort(liste,
                new Comparator<String>()
                {
                    public int compare(String f1, String f2)
                    {
                        return f1.toString().compareTo(f2.toString());
                    }        
                });
		String jason ="[";
		int n = 1;
		for(String x : liste)
		{
			jason+=x;
			if(n<liste.size())
				jason+=",";
			n++;
		}
		jason+="]";
	    
	    
	    return jason;
	}
	@RequestMapping(value="/listeEns")
	@ResponseBody
	public String listeEns(HttpServletRequest request,HttpServletResponse response,String idSeance)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    String[] tab = idSeance.split("M");
	    
	    Collection<AffectationSeance> affs = metier.findAffectationSeance(tab[0], tab[1]);
		Collection<Enseignant> ense = new ArrayList<Enseignant>();
		for(AffectationSeance as : affs)
		{ense.add(as.getEnseignant());
		}
		String jj="";
		int n=1;
		for(Enseignant e : ense)
		{
		jj+=e.getJSON();
		if(n<ense.size())
			jj+=",";
		n++;
		}
		jj="["+jj+"]";
		
	    return jj;
	}
	@RequestMapping(value="/listeEns2")
	@ResponseBody
	public String listeEns2(HttpServletRequest request,HttpServletResponse response,String idSeanceD,String idSeanceP)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    String[] tab = idSeanceD.split("M");
	    
	    Collection<AffectationSeance> affs = metier.findAffectationSeance(tab[0], tab[1]);
		Collection<Enseignant> ense = new ArrayList<Enseignant>();
		for(AffectationSeance as : affs)
		{
			if(!metier.verifierAffectation(idSeanceP.split("M")[0],idSeanceP.split("M")[1], String.valueOf(as.getEnseignant().getId())))
				ense.add(as.getEnseignant());
		}
		String jj="";
		int n=1;
		for(Enseignant e : ense)
		{
		jj+=e.getJSON();
		if(n<ense.size())
			jj+=",";
		n++;
		}
		jj="["+jj+"]";
		
	    return jj;
	}
	
	@RequestMapping(value="/ajouterDemande")
	@ResponseBody
	public String ajouterDemande(HttpServletRequest request,HttpServletResponse response,String seanceD,String seanceP,String idE, String idR)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    String sp[] = seanceP.split("M");
	    Seance seanceProposé = metier.findSeanceByHeurDebut(sp[0], sp[1]);
	    if(metier.verifierAffectation(sp[0], sp[1], idR))
	    {
	    	return "[{\"da\":\"1\"}]";
	    }
	    String sd[] = seanceD.split("M");
	    Seance seanceDemandé = metier.findSeanceByHeurDebut(sd[0], sd[1]);
	    Enseignant emetteur = metier.findEnseignant(Long.parseLong(idE));
	    Enseignant recepteur = metier.findEnseignant(Long.parseLong(idR));
	    String etat = "En Cours";
	    
	    
		
	    DemandeEchangeS demande = new DemandeEchangeS(emetteur, recepteur, seanceProposé.getJourExamen().getDate(), seanceProposé.getHeureDebut(), seanceDemandé.getJourExamen().getDate(), seanceDemandé.getHeureDebut(), etat);
	    metier.addDemandeEchangeS(demande);
	    smtpTest mailer = new smtpTest();
	    String sms ="<table align=center border=1><tr align=center><td colspan=3>"+recepteur.getEnseignant()+"<tr align=center><td colspan=3>Vous avez reçu une demande de permutation";
	    sms+="<tr align=center><td>Enseignant<td>Séance demandée<td>Séance proposée";
	    sms+="<tr align=center><td>"+emetteur.getEnseignant()+"<td>"+seanceDemandé.getJourExamen().getDate().toString().split(" ")[0]+"<br>"+seanceDemandé.getHeureDebut();
	    sms+="<td>"+seanceProposé.getJourExamen().getDate().toString().split(" ")[0]+"<br>"+seanceProposé.getHeureDebut();
	    mailer.envoieMail(sms, "med.amin.mallek@gmail.com");
	    return "[{\"da\":\"0\"}]";
	}
	@RequestMapping(value="/listeDemandeEnv")
	@ResponseBody
	public String listeDemandeEnv(HttpServletRequest request,HttpServletResponse response,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    Collection<DemandeEchangeS> demandes = metier.findDemandeEnvParId(Long.parseLong(id));
	    String jason="";
	    int n=1;
	    for(DemandeEchangeS d : demandes)
	    {
	    	jason+=d.getJASON();
	    	if(n<demandes.size())
	    		jason+=",";
	    	
	    	n++;
	    }
	    return "["+jason+"]";
	}
	@RequestMapping(value="/listeDemandeRec")
	@ResponseBody
	public String listeDemandeRec(HttpServletRequest request,HttpServletResponse response,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    Collection<DemandeEchangeS> demandes = metier.findDemandeRecParId(Long.parseLong(id));
	    String jason="";
	    int n=1;
	    for(DemandeEchangeS d : demandes)
	    {
	    	jason+=d.getJASON();
	    	if(n<demandes.size())
	    		jason+=",";
	    	
	    	n++;
	    }
	    return "["+jason+"]";
	}
	@RequestMapping(value="/updateDeamnde")
	@ResponseBody
	public String updateDemande(HttpServletRequest request,HttpServletResponse response,String demandeS,String etat)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    String[] d = demandeS.split("X");
		metier.updateDemande(d[0], d[3], d[1], d[2], d[4], d[5], etat);
		if(etat.equals("Accepter")){
		metier.updateIdAffectationSeance(d[0], d[1], d[2], Long.parseLong(d[3]));
		metier.updateIdAffectationSeance(d[3], d[4], d[5], Long.parseLong(d[0]));
		Collection<AffectationSeance> affectations =metier.findAffectationById(Long.parseLong(d[3]));
		Collection<DemandeEchangeS> demandesRec = metier.findDemandeRecParId(Long.parseLong(d[3]));
		smtpTest mailler = new smtpTest();
		
		String sms ="M,"+metier.findEnseignant(Long.parseLong(d[0])).getEnseignant()+"<br>Votre demande concernant la séance : "+d[4]+" à "+d[5]+" a été acceptée";
		mailler.envoieMail(sms, "med.amin.mallek@gmail.com");
		for(DemandeEchangeS dE : demandesRec)
		{
			boolean existeEnv = false;
			for(AffectationSeance a : affectations)
			{	
				String d22[] = a.getDateJour().toString().split(" ");
					if(dE.getDateJourD2().equals(d22[0]) && dE.getHeureDebutD().toString().equals(a.getHeureDebut().toString()))
					{
						existeEnv=true;
					}

			}
			if(existeEnv==false){
				metier.supprimerDemandeEchangeD(d[3], dE.getDateJourD2(), dE.getheureDebutD2());
			}
		}
		}else
		{
			smtpTest mailler = new smtpTest();
			
			String sms ="M,"+metier.findEnseignant(Long.parseLong(d[0])).getEnseignant()+"<br>Votre demande concernant la séance : "+d[4]+" à "+d[5]+" a été réfusée";
			mailler.envoieMail(sms, "med.amin.mallek@gmail.com");
			metier.supprimerDemandeEchange(d[0], d[1], d[2]);
		}
		//recevoir pls demande 
	    return "[{\"t\":\"1\"}]";
	}
	@RequestMapping(value="/calendrierJASON")
	@ResponseBody
	public String CalenJASON(HttpServletRequest request,HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    Collection<Time> heures = metier.findHeureSeance();
	    String jason="[";
	    int n=1;
	    for(Time t : heures)
	    {
	    	String[] x=t.toString().split(":");
	    	
	    	jason+="{\"heure\":\""+x[0]+":"+x[1]+"\"}";
	    	if(n<heures.size())
	    		jason+=",";
	    	n++;
	    }
		return jason+"]";
	}
	@RequestMapping(value="/calendrier2JASON")
	@ResponseBody
	public String Calen2JASON(HttpServletRequest request,HttpServletResponse response,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    String jason="[";
	    int n =1;
	    int w=1;
	    Collection<JourExamen> jours = metier.findAllJourExamen();
	    for(JourExamen j : jours)
	    {
	    	w=1;
	    	Collection<Seance> seances = metier.findSeanceByDateJour(j.getDate());
	    	for(Seance s : seances)
	    	{
	    		String d[] = s.getJourExamen().getDate().toString().split(" ");
	    		String h[] = s.getHeureDebut().toString().split(":");
	    		Boolean affecter = metier.verifierAffectation(d[0], s.getHeureDebut().toString(), id);
	    		Boolean stature = metier.nbAffectationParSeance(d[0],s.getHeureDebut().toString())==s.getNombreSurveillant();
	    		Boolean resp =false;
	    		Boolean ens = false;
	    		Boolean emp =false;
	    		Boolean empM =false;
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
	    		
				Set<String> cal = getClandrier(Long.parseLong(id));
				String XXL = s.getJourExamen().getDate().getDay()+"X"+s.getHeureDebut()+"L";
				String XXM = s.getJourExamen().getDate().getDay()+"X"+s.getHeureDebut()+"M";
				if(cal.contains(s.getJourExamen().getDate().getDay()+"X"+s.getHeureDebut()+"L"))
					emp=true;
				if(cal.contains(s.getJourExamen().getDate().getDay()+"X"+s.getHeureDebut()+"M")){
					if(metier.isCC())
						stature=true;
					empM=true;
				}
				String seannceJASON = "{\"date\":\""+d[0]+"\",\"heure\":\""+h[0]+":"+h[1]+"\",\"affecter\":\""+affecter+"\",\"sature\":\""+stature+"\",\"resp\":\""+resp+"\",\"ensM\":\""+ens+"\",\"emp\":\""+emp+"\",\"empM\":\""+empM+"\"}";
	    		if(n==jours.size()&&w==seances.size())
	    			jason+=seannceJASON;
	    		else
	    			jason+=seannceJASON+",";
	    		
	    		w++;
	    	}
	    	n++;
	    }
	    jason+="]";

	    return jason;
	}
	@RequestMapping(value="/calendrier3JASON")
	@ResponseBody
	public String Calen3JASON(HttpServletRequest request,HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    String jason="[";
	    int n =1;
	    Collection<JourExamen> jours = metier.findAllJourExamen();
	    for(JourExamen j : jours)
	    {
	    	String[] d = j.getDate().toString().split(" ");
	    	String jourJason = "{\"date\":\""+d[0]+"\"}";
	    	if(n==jours.size())
	    		jason+=jourJason;
	    	else
	    		jason+=jourJason+",";
	    	n++;
	    }
	    
	    jason+="]";

	    return jason;
	}
	@RequestMapping(value="/ajouterVoeuxGrille")
	@ResponseBody
	public String ajouterVoeuxGrille(HttpServletRequest request,HttpServletResponse response,String id,String voeux)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    metier.supprimerAffectationSeance(Long.parseLong(id));
	    
	    String tableau[]= voeux.split(",");
	    for(String voeu:tableau){
	    	
	    	String t[] = voeu.split("\"");
	    	String d[]= t[1].split("M");
	    	Enseignant e = metier.findEnseignant(Long.parseLong(id));
	    	Seance s = metier.findSeanceByHeurDebut(d[0], d[1]);
	    	metier.addAffectationSeance(s, e);
	    }
	    metier.supprimerDemande(Long.parseLong(id));
	    return "[{\"x\":\""+tableau[0]+"\"}]";
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
	@RequestMapping(value="/updateParamCnx")
	@ResponseBody
	public String upadteParamCnx(HttpServletResponse response,String nom,String pass,Long id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    if(!metier.nomUtilisateurExiste(nom,id))
	    {
	    	Enseignant e = metier.findEnseignant(id);
	    	metier.updateParamCnx(nom, pass, e.getCin());
	    	return "[{\"update\":\"1\"}]";
	    }else
	    {
	    	return "[{\"update\":\"0\"}]";
	    }
	    
		
	}
	@RequestMapping(value="/envoierMailCompte")
	@ResponseBody
	public String envoyerMailCompte(HttpServletResponse response, String mail)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    Enseignant e = metier.findEnseignantParMail(mail);
	    if(e!=null)
	    {
	    	smtpTest mailler = new smtpTest();
	    	String msg ="M. "+e.getEnseignant()+"<br>Votre nom d'utilisateur : "+e.getNomUtilisateur()+"<br>Votre mot de passe : "+e.getMotDePasse();
	    	mailler.envoieMailParamCnx(msg, "med.amin.mallek@gmail.com");
	    	return "[{\"envoyer\":\"1\"}]";
	    }
		return "[{\"envoyer\":\"0\"}]";
	}
}
