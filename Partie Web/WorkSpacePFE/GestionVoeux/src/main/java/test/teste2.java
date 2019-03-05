package teste;

import java.math.RoundingMode;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fsegs.entitees.AffectationSeance;
import org.fsegs.entitees.Alerte;
import org.fsegs.entitees.DemandeEchangeS;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Enseignement;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.JourEnseignement;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.Matiere;
import org.fsegs.entitees.MatiereEnseignement;
import org.fsegs.entitees.Seance;
import org.fsegs.entitees.TypeAlerte;
import org.fsegs.metier.ISurveillanceMetier;
import org.fsegs.modele.AlerteModele;
import org.fsegs.modele.JourAffectation;
import org.fsegs.modele.MatiereParResp;
import org.fsegs.modele.SeanceAffectation;
import org.fsegs.modele.SeanceExamen;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DeadlockLoserDataAccessException;

public class teste2 {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		ISurveillanceMetier metier =(ISurveillanceMetier) context.getBean("metier");
		/*
		List<String> list = metier.getClandrierEnseignant(805l);
		
		for(String s : metier.getClandrierEnseignant(805l))
			System.out.println(s);
		*/
		
		/*
		 Collection<Enseignant> ens = metier.getListeSurveillantTriParCharge();
		 Collection<Enseignant> sur = new ArrayList<Enseignant>();
		 int nb=0;
		 for(Enseignant e : ens )
		 {
			 int x = metier.nbAffectationParSurveillant(e.getId());
			 if(e.getaSruveiller()-x>0){
			 sur.add(e);
			 }
		 }
		 
		 for(Enseignant s : sur)
			 System.out.println(s.getEnseignant()+s.getaSruveiller());*/
		/*
		Collection<AffectationSeance> liste = metier.findAffectationById(16l);
		String x ="";
		for(AffectationSeance s : liste)
		{
			JourExamen j = new JourExamen();
			j.setDate(s.getDateJour());
			x+=j.dateJour()+" à "+s.getHeureDebut().toString().split(":")[0]+":"+s.getHeureDebut().toString().split(":")[1]+"\n";
		}
		
		
		*/
		/*
		Collection<MatiereEnseignement> mat = metier.getMatiereEnseignementsByEnseignant(metier.findEnseignant(16l));
		for(MatiereEnseignement e : mat)
			for(Seance s : metier.findSeancePourMatiere(e))
			System.out.println(e.getMatiere().getMatiere()+" "+e.getCycleAnnee().getAnnee()+" "+e.getFiliere().getId()+" "+s.getJourExamen().getDate()+" à "+s.getHeureDebut());
		*/
		
		System.out.println(metier.calculeChargeHoraireGlobaleDesSurveillants());
		System.out.println(metier.calculeNbHeureSurveillant(metier.findEnseignant(16l)));
		System.out.println(metier.chargeHoraireDeSurveillance(metier.findEnseignant(16l)));
		System.out.println(metier.nbHeureMastere(16l));
		
	}

}
