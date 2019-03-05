package teste;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.fsegs.entitees.AffectationExamen;
import org.fsegs.entitees.AffectationSeance;
import org.fsegs.entitees.Alerte;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Enseignement;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.MatiereEnseignement;
import org.fsegs.entitees.Role;
import org.fsegs.entitees.Seance;
import org.fsegs.entitees.TypeAlerte;
import org.fsegs.entitees.Utilisateur;
import org.fsegs.metier.ISurveillanceMetier;
import org.fsegs.modele.AlerteStat;
import org.fsegs.modele.ExamenSalle;
import org.fsegs.modele.TypeModele;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Teste3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		ISurveillanceMetier metier =(ISurveillanceMetier) context.getBean("metier");
		
		/*
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();	
		//dXX.set(2015, 11, 5);
		Date dateActuelle=dXX.getTime();
		
		
		System.out.println(metier.periodeExamen(dateActuelle));
		
		System.out.println(metier.findAllJourExamen().size());
		
		*/
		/*
		for(AffectationExamen af : metier.findAllAffectationExamen())
		{
			System.out.println(af.getDateJour()+" "+af.getHeureDebut()+" "+af.getSalleExamen().getId()+" "+af.getHeureReelAff());
		}
		
		String chart = "var chart = new CanvasJS.Chart(\"chartContainer\","+
			"{"+
		
                "animationEnabled: true,"+
		"legend: {"+
			"verticalAlign: \"bottom\","+
			"horizontalAlign: \"center\""+
		"},"+
		"theme: \"theme1\","+
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
				"{  y: 52, name: \"Libre\" ,color: \"green\"},"+
				
				"{  y: 12, name: \"Affecté\",color: \"red\"}"+
			"]"+
		"}"+
		"]"+
	"});"+
	"chart.render();";
		*/
		
		//System.out.println(metier.enSeance("2016-01-06", "11:35", 20504l));
		/*
		
		Collection<AffectationExamen> affectationsExamens = metier.findAllAffectationExamen();
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
		
		for(AffectationExamen afsfs : affectations)
			System.out.println(afsfs.getHeureDebut()+" "+afsfs.getMatiere().getId()+" "+afsfs.getFiliere().getId()+" "+afsfs.getCycleAnnee().getId()+" "+afsfs.getHeureReelAff());
		
		
		for(JourExamen j : metier.getJourExamensByAnneeSession(0, "Tous"))
			System.out.println(j.getDate());*/
		/*
		Collection<TypeAlerte> types = metier.findAllTypesAlertes();
		Set<AlerteStat> alerteStat = new HashSet<AlerteStat>();
		for(TypeAlerte ty : types)
		{
			alerteStat.add(new AlerteStat(ty, 0));
		}
		
		for(Alerte a : metier.getAlerteByAnneeSession(0, "EX"))
		{
			for(AlerteStat as : alerteStat)
			{
				if(a.getType().equals(as.getTypeAlerte()))
				{
					as.setNb(as.getNb()+1);
				}
			}
		}
		
		for(AlerteStat als : alerteStat)
		{
			System.out.println(als.getTypeAlerte().getLibelle()+" "+als.getNb());
		}*/
		/*
		String x ="25.36";
		x=x.split("\\.")[0];
		System.out.println(x);
		*/
		/*
		Calendar d = Calendar.getInstance();
		d.set(2015,9,15,8,30,0);
		Date dateD=d.getTime();
		String x = (dateD.getMonth()+1)>9?(dateD.getMonth()+1)+"":"0"+(dateD.getMonth()+1)+"";
		String x1 = dateD.getDate()>9?dateD.getDate()+"":"0"+dateD.getDate()+"";
		System.out.println(dateD.getYear()+1900+" "+x+" "+x1);
		*/
		/*
		Utilisateur u = new Utilisateur("Mohamed Amin", "Mallek123");
		if(!metier.FindUtilisateur(u))
		metier.addUtilisateur("Mohamed Amin", "Mallek123");
		*/
		/*Role r = metier.findRole(1l);
		Utilisateur u = new Utilisateur("Amin1235", "1254");
		Collection<Role> roles = new ArrayList<Role>();
		roles.add(r);
		u.setRoles(roles);
		metier.addUtilisateur(u);*/
		//System.out.println(r.getLibelle());
		/*
		for(Utilisateur u : metier.findAllUtilisateurs()){
			System.out.println(u.toString());
			for(Role r : u.getRoles())
				System.out.println(r.getLibelle());
		}
		*/
		//System.out.println(metier.findUser("amin", "1223"));
		
		//System.out.println(metier.loginEnseignant("1198352", "119832")!=null?metier.loginEnseignant("1198352", "1198352").getJSON():null);
		//System.out.println(metier.nomUtilisateurExiste("emn"));
		//metier.updateParamCnx("BenMeftah", "235748235748",1198352);
		
		//System.out.println(metier.findEnseignant(16l).getNomUtilisateur()+" "+metier.findEnseignant(16l).getMotDePasse());
		
		//System.out.println(metier.findEnseignantParMail("emna.benmefteh@fsegs.rnu.tn"));
		/*
		int x=0;
		for(Enseignant e : metier.findAllSurveillants())
		{
			if(e.getaSruveiller()>metier.findAffectationById(e.getId()).size())
				x++;
		}
		int h = metier.getDateActuelle().getHours();
		String hh = h>9?h+"":"0"+h;
		int m = metier.getDateActuelle().getMinutes();
		String mm = m>9?m+"":"0"+m;
		System.out.println(hh+":"+mm);
		*/
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
		boolean sature = besoin==aff?true:false;
		
		
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
        
        System.out.println(affectationAuto);
		//System.out.println(jour.getDate());
		
	}

}
