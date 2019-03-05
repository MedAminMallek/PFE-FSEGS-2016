package org.fsegs.dao;

import java.math.BigInteger;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fsegs.entitees.AffectationExamen;
import org.fsegs.entitees.AffectationSeance;
import org.fsegs.entitees.Alerte;
import org.fsegs.entitees.CycleAnnee;
import org.fsegs.entitees.DemandeEchangeS;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Enseignement;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.Filiére;
import org.fsegs.entitees.JourEnseignement;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.Matiere;
import org.fsegs.entitees.MatiereEnseignement;
import org.fsegs.entitees.Remplacement;
import org.fsegs.entitees.Role;
import org.fsegs.entitees.Salle;
import org.fsegs.entitees.Seance;
import org.fsegs.entitees.Semestre;
import org.fsegs.entitees.Session;
import org.fsegs.entitees.TypeAlerte;
import org.fsegs.entitees.Utilisateur;

public class SurveillanceDAOImpl implements ISurveillanceDAO {

	@PersistenceContext
	private EntityManager em;
	
	
	
	
	
	private int m=getDateActuelle().getMonth();
	private int a=getDateActuelle().getYear();
	private int d = getDateActuelle().getDate();

	@Override
	public void addEnseignant(Enseignant e) {
		em.persist(e);
	}
	@Override
	public Enseignant findEnseignant(Long id) {
		Enseignant e= em.find(Enseignant.class, id);
		return e;
	}
	@Override
	public void addSemstre(Semestre s) {
		em.persist(s);
		
	}
	@Override
	public void addSession(Session s) {
		em.persist(s);
		
	}
	@Override
	public void addJourExamen(JourExamen j) {
		em.persist(j);
		
	}
	@Override
	public void addSeeance(Seance s) {
		em.persist(s);
		
		
	}
	@Override
	public Semestre findSemstre(int i) {
		// TODO Auto-generated method stub
		return em.find(Semestre.class, i);
	}
	@Override
	public Session findSession(String i) {
		// TODO Auto-generated method stub
		return em.find(Session.class, i);
	}
	@Override
	public JourExamen findJourExamen() {
		// TODO Auto-generated method stub
		
		return null;
	}
	@Override
	public void addFiliére(Filiére f) {
		em.persist(f);
		
	}
	@Override
	public void addCycleAnnee(CycleAnnee c) {
		// TODO Auto-generated method stub
		em.persist(c);
		
	}
	@Override
	public void addEnseignement(Enseignement e) {
		// TODO Auto-generated method stub
		em.persist(e);
	}
	@Override
	public void addMatiere(Matiere m) {
		// TODO Auto-generated method stub
		em.persist(m);
	}
	@Override
	public void addSalle(Salle s) {
		// TODO Auto-generated method stub
		em.persist(s);
	}
	@Override
	public void addJourEnseignement(JourEnseignement j) {
		// TODO Auto-generated method stub
		em.persist(j);
	}
	@Override
	public void addExamen(Examen e) {
		// TODO Auto-generated method stub
		em.persist(e);
	}
	
	@Override
	public Collection<JourExamen> findAllJourExamen() {
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer ***************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		Collection<JourExamen> jourss = new ArrayList<JourExamen>();
		List<JourExamen> jours = em.createQuery("from JourExamen where au="+AU+" ORDER BY date ASC").getResultList();
		for(JourExamen j : jours)
		{
			
			if(periodeExamen(j.getDate()).equals(type))
				jourss.add(j);
		}
		return jourss;
	}
	@Override
	public Collection<Seance> findSeanceByDateJour(Date date) {
		List<Seance> seances;
		seances = em.createQuery("from Seance where date = '"+date+"'").getResultList();
		
		return seances;
	}
	@Override
	public Collection<Examen> findExamens(Time heure,Date date) {
		
		List<Examen> examens;
		examens = em.createQuery("from Examen where date='"+date+"' and heureDebut='"+heure+"'").getResultList();
		return examens;
	}
	@Override
	public Collection<Time> findHeureSeance() {
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		Collection<Seance> heuress = new ArrayList<Seance>();
		
		Collection<Seance> seances=em.createQuery("from Seance s where s.jourExamen.au="+AU+"").getResultList();
		for(Seance s : seances)
		{
			if(periodeExamen(s.getJourExamen().getDate()).equals(type))
				heuress.add(s);
		}
		Set<Time> hs = new HashSet<Time>();
		for(Seance s1 : heuress)
			hs.add(s1.getHeureDebut());
		
		List<Time> time = new ArrayList<Time>();
		time.addAll(hs);
		//Collections.sort(time);
		Collections.sort(time, new Comparator<Time>() {
	        @Override
	        public int compare(Time t1, Time t2)
	        {

	            return  t1.before(t2)?-1:1;
	        }
	    });
		
		
		ArrayList<Time> heure=(ArrayList<Time>) em.createQuery("select distinct(heureDebut) from Seance where jourExamen.au="+AU+"").getResultList();
		return time;
	}
	@Override
	public Collection<Seance> findAllSeance() {
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		Collection<Seance> seancess = new ArrayList<Seance>();
		
		List<Seance> seances = em.createQuery("from Seance where jourExamen.au="+AU+" order by jourExamen.date asc").getResultList();
		
		for(Seance s : seances)
		{
			if(periodeExamen(s.getJourExamen().getDate()).equals(type))
				seancess.add(s);
		}
		
		
		return seancess;
	}
	@Override
	public Collection<Examen> findAllExamen() {
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		Collection<Examen> examensss = new ArrayList<Examen>();
		
		List<Examen> examens = em.createQuery("from Examen e where e.seance.jourExamen.au="+AU+"").getResultList();
		for(Examen e : examens)
			if(periodeExamen(e.getSeance().getJourExamen().getDate()).equals(type))
				examensss.add(e);
		
		return examensss;
	}
	@Override
	public Collection<MatiereEnseignement> findMatiereParSeance(Seance seance) {
		List<MatiereEnseignement> matieres = em.createQuery("select distinct(matiereEnseignement) From Examen where heureDebut='"+seance.getHeureDebut()+"' and date='"+seance.getJourExamen().getDate()+"'").getResultList();
		return matieres;
	}
	
	
	@Override
	public void updateNbSurvaillantParSeance(String date, String time, int nb) {
		
		
		Seance s = findSeanceByHeurDebut(date, time);
		s.setNombreSurveillant(nb);
		//em.getTransaction().commit();
	}
	@Override
	public Seance findSeanceByHeurDebut(String date, String heureDebut) {
		Seance s = (Seance) em.createQuery("from Seance where date like '"+date+"%' and heureDebut like '"+heureDebut+"%'").getSingleResult();
		return s;
	}
	@Override
	public void addMatiereEnseingement(MatiereEnseignement m) {
		// TODO Auto-generated method stub
		em.persist(m);
		
	}
	@Override
	public void updateNbCommissionParSeance(String date, String time, int nb) {

		Seance s = findSeanceByHeurDebut(date, time);
		s.setNombreMembreC(nb);
		
	}
	
	@Override
	public double calculeNbHeureSurveillant(Enseignant e) {
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		String nature = typeExamen();
		Collection<Enseignement> ens = new ArrayList<Enseignement>();
				;
		switch (nature) {
		case "RR":
			ens = em.createQuery("from Enseignement where enseignant_id="+e.getId()+" and au="+AU+"").getResultList();
			break;
		case "EX1":
			ens = em.createQuery("from Enseignement where enseignant_id="+e.getId()+" and au="+AU+" and semestre=1").getResultList();
			break;
		case "EX2":
			ens = em.createQuery("from Enseignement where enseignant_id="+e.getId()+" and au="+AU+" and semestre=2").getResultList();
			break;
		case "CC1":
			ens = em.createQuery("from Enseignement where enseignant_id="+e.getId()+" and au="+AU+" and semestre=1").getResultList();
			break;
		case "CC2":
			ens = em.createQuery("from Enseignement where enseignant_id="+e.getId()+" and au="+AU+" and semestre=2").getResultList();
			break;		
		}
		//ens = em.createQuery("from Enseignement where enseignant_id="+e.getId()+" and au="+AU+"").getResultList();
		
		Set<String> liste = new HashSet<String>();
		for(Enseignement e1 : ens)
		{
			liste.add(e1.getJour().getId()+""+e1.getHeureDebut());
		}
		// - les séances de master
		double x = 0;
		if(isCC())
			if(isEnseignantMaster(e.getId()))
				x=nbHeureMastere(e.getId());
		return (liste.size()*1.5)-x;
	}
	@Override
	public double calculeChargeHoraireGlobaleDesSurveillants() {
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		String nature = typeExamen();
		Collection<Enseignement> ens = new ArrayList<Enseignement>();
				;
		switch (nature) {
		case "RR":
			ens = em.createQuery("from Enseignement where au="+AU+"").getResultList();
			break;
		case "EX1":
			ens = em.createQuery("from Enseignement where au="+AU+" and semestre=1").getResultList();
			break;
		case "EX2":
			ens = em.createQuery("from Enseignement where au="+AU+" and semestre=2").getResultList();
			break;
		case "CC1":
			ens = em.createQuery("from Enseignement where au="+AU+" and semestre=1").getResultList();
			break;
		case "CC2":
			ens = em.createQuery("from Enseignement where au="+AU+" and semestre=2").getResultList();
			break;		
		}
		
		
		
		//Collection<Enseignement> ens = em.createQuery("from Enseignement").getResultList();
		int nb=0;
		Set<String> liste = new HashSet<String>();
		for(Enseignement e : ens)
		{
			
			if(e.getEnseignant().isCommission()==false && e.getEnseignant().isActif()){
				if(nature.equals("CC1")||nature.equals("CC2"))
				{
					if(e.getMatiereEnseignement().getCycleAnnee().getId()!=53 && e.getMatiereEnseignement().getCycleAnnee().getId()!=51){
					nb++;
					liste.add(e.getJour().getId()+""+e.getHeureDebut()+" "+e.getEnseignant().getId());
					}
				}else
				{
						nb++;
						liste.add(e.getJour().getId()+""+e.getHeureDebut()+" "+e.getEnseignant().getId());
				}
			}
		}
		return liste.size()*1.5;
		
	}
	@Override
	public Collection<Enseignant> findAllSurveillants() {
		// TODO Auto-generated method stub
		Collection<Enseignant> ens = em.createQuery("from Enseignant where commission=0 and actif=1 order by enseignant asc").getResultList();
		return ens;
	}
	@Override
	public void updateChargeDeSurveillance(Long id, int nb) {
		// TODO Auto-generated method stub
			//Enseignant e =  findEnseignant(id);
			//e.setaSruveiller(nb);
			em.createQuery("update Enseignant set aSruveiller="+nb+" where id="+id+"").executeUpdate();
		
	}
	@Override
	public Enseignant findEnseignantParCIN(int Cin) {
		// TODO Auto-generated method stub
		Enseignant e = (Enseignant) em.createQuery("from Enseignant where cin="+Cin+"").getSingleResult();
		return e;
	}
	@Override
	public void addAffectationSeance(Seance s, Enseignant e) {
		// TODO Auto-generated method stub
		em.persist(new AffectationSeance(e, s.getJourExamen().getDate(), s.getHeureDebut()));
	}
	@Override
	public int nbAffectationParSeance(String d,String t) {
		// TODO Auto-generated method stub
		Collection<AffectationSeance> list = em.createQuery("from AffectationSeance").getResultList();
		int x=0;
		for(AffectationSeance a : list)
		{
			String[] datetab=a.getDateJour().toString().split(" ");
			if(datetab[0].equals(d) && a.getHeureDebut().toString().equals(t))
				x++;
		}
		//int x = em.createQuery("select count(*) from AffectationSeance").getFirstResult(); 
		return x;
	}
	@Override
	public Collection<Seance> findSeanceByDateJour(String date) {
		List<Seance> seances;
		seances = em.createQuery("from Seance where date = '"+date+"'").getResultList();
		
		return seances;
	}
	@Override
	public int nbAffectationParSurveillant(Long id) {
		// TODO Auto-generated method stub ******************************
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		int x=0;
		Collection<AffectationSeance> list = em.createQuery("from AffectationSeance where enseignant_id="+id+"").getResultList();
		for(AffectationSeance afs : list)
		{
			int m1 = afs.getDateJour().getMonth()+1;
			int a1 = afs.getDateJour().getYear()+1900;
			int AU1 = 0 ;
			if(9<=m1 && m1<=12)
				AU1 = a1;
			if(1<=m1 && m1<=6)
				AU1 = a1-1;
			
			if(AU-AU1==0 && periodeExamen(afs.getDateJour()).equals(type))
				x++;
		}

		
		return x;
	}
	@Override
	public Collection<Seance> seancesAffecterPourSur(Long id) {
		
		//*****************************
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		
		Collection<AffectationSeance> list = em.createQuery("from AffectationSeance where enseignant_id="+id+" ORDER BY dateJour ASC").getResultList();
		Collection<Seance> listS = new ArrayList<Seance>();
		for(AffectationSeance aff : list)
		{
			int m1 = aff.getDateJour().getMonth()+1;
			int a1 = aff.getDateJour().getYear()+1900;
			int AU1 = 0 ;
			if(9<=m1 && m1<=12)
				AU1 = a1;
			if(1<=m1 && m1<=6)
				AU1 = a1-1;
			
			if(AU-AU1==0 && periodeExamen(aff.getDateJour()).equals(type)){
				String tab[] = aff.getDateJour().toString().split(" ");
				listS.add(findSeanceByHeurDebut(tab[0], aff.getHeureDebut().toString()));
			}
			
				
		}
		return listS;
	}
	@Override
	public Collection<AffectationSeance> findAllAffectationSeances() {
		// TODO Auto-generated method stub
		//*********************************
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		
		Collection<AffectationSeance> affectationSeances = em.createQuery("from AffectationSeance").getResultList();
		Collection<AffectationSeance> affe = new ArrayList<AffectationSeance>();
		for(AffectationSeance af : affectationSeances)
		{
			int m1 = af.getDateJour().getMonth()+1;
			int a1 = af.getDateJour().getYear()+1900;
			int AU1 = 0 ;
			if(9<=m1 && m1<=12)
				AU1 = a1;
			if(1<=m1 && m1<=6)
				AU1 = a1-1;
			
			if(AU-AU1==0 && periodeExamen(af.getDateJour()).equals(type))
				affe.add(af);
		}
		return affe;
	}
	@Override
	public Collection<Matiere> findAllMatiere() {
		// TODO Auto-generated method stub
		return em.createQuery("from Matiere").getResultList();
	}
	@Override
	public boolean chargeCalculé() {
		// TODO Auto-generated method stub
		Collection<Enseignant> ens = em.createQuery("from Enseignant").getResultList();
		int x = 0;
		for(Enseignant e : ens)
			x+=e.getaSruveiller();
		if(x>0)
		return true;
		else
			return false;
	}
	@Override
	public Collection<MatiereEnseignement> findAllMatiereEns() {
		// TODO Auto-generated method stub
	
		return em.createQuery("from MatiereEnseignement").getResultList();
	
	}
	@Override
	public void updateRespMat(String mat, String fil, String cyc) {
		// TODO Auto-generated method stub
		Collection<Enseignement> list1 = new  ArrayList<Enseignement>();
		try{
		list1 = em.createQuery("from Enseignement where matiere="+mat+" and filiere='"+fil+"' and cycleAnnee="+cyc+"").getResultList();
		}catch(Exception e)
		{
			
		}
		for(Enseignement e : list1)
		{
			if(e.getNe().equals("CR"))
			{
			try{
			em.createQuery("update MatiereEnseignement set enseignant_id="+e.getEnseignant().getId()+" where matiere_id="+mat+" and filiere_id='"+fil+"' and cycleAnnee_id="+cyc+"").executeUpdate();
			}catch(Exception e1)
			{}
				
			}
		}
	}
	@Override
	public boolean IsResponsable(Long id) {
		// TODO Auto-generated method stub
		Collection<Examen> liste = em.createQuery("from Examen").getResultList();
		for(Examen e : liste)
		{
			if(e.getMatiereEnseignement().getEnseignant().equals(findEnseignant(id)))
				return true;
		}
		return false;
	}
	@Override
	public void supprimerAffectationSeance(String id, String date, String heure) {
		// TODO Auto-generated method stub
		AffectationSeance a = (AffectationSeance) em.createQuery("from AffectationSeance where enseignant_id="+id+" and heureDebut='"+heure+"' and dateJour='"+date+"'").getSingleResult();
		em.remove(a);
		
	}
	@Override
	public Collection<AffectationSeance> findAffectationSeance(String date,
			String heure) {
		// TODO Auto-generated method stub
		Collection<AffectationSeance> a =  em.createQuery("from AffectationSeance where heureDebut='"+heure+"' and dateJour='"+date+"' order by dateJour ASC").getResultList();
		return a;
	}
	@Override
	public boolean verifierAffectation(String date, String heure, String id) {
		// TODO Auto-generated method stub
		Long x = (Long) em.createQuery("select count(*) from AffectationSeance where heureDebut='"+heure+"' and dateJour='"+date+"' and enseignant_id="+id+"").getSingleResult();
		if(x>0)
		return true;
		else
			return false;
	}
	@Override
	public void addDemandeEchangeS(DemandeEchangeS d) {
		// TODO Auto-generated method stub
		em.persist(d);
		
	}
	@Override
	public boolean findDemande(Date date, Time heure, Long id) {
		// TODO Auto-generated method stub
		Long demande = (Long) em.createQuery("select count(*) from DemandeEchangeS where heureDebutD='"+heure+"' and dateJourD='"+date+"' and emetteur_id="+id+"").getSingleResult();
		if(demande>0)
		return true;
		else
			return false;
	}
	@Override
	public Collection<DemandeEchangeS> findDemandeEnvParId(Long id) {
		// TODO Auto-generated method stub
		Collection<DemandeEchangeS> demandes = em.createQuery("from DemandeEchangeS where emetteur_id="+id+" and etat='En Cours'").getResultList();
		return demandes;
	}
	@Override
	public void supprimerDemandeEchange(String id, String dateP, String heureP) {
		// TODO Auto-generated method stub
		DemandeEchangeS demande = (DemandeEchangeS) em.createQuery("from DemandeEchangeS where emetteur_id="+id+" and dateJourP='"+dateP+"' and heureDebutP='"+heureP+"'").getSingleResult();
		em.remove(demande);
		
	}
	@Override
	public Collection<DemandeEchangeS> findDemandeRecParId(Long id) {
		// TODO Auto-generated method stub
		Collection<DemandeEchangeS> demandes = em.createQuery("from DemandeEchangeS where recepteur_id="+id+" and etat='En Cours'").getResultList();
		return demandes;
	}
	@Override
	public void updateDemande(String emetteur,String recepteur,String datP,String heureP,String datD,String heureD,String etat) {
		// TODO Auto-generated method stub
		
		DemandeEchangeS d = (DemandeEchangeS) em.createQuery("from DemandeEchangeS where heureDebutP='"+heureP+"' and heureDebutD='"+heureD+"' and dateJourP='"+datP+"' and dateJourD='"+datD+"' and recepteur_id="+recepteur+" and emetteur_id="+emetteur+"").getSingleResult();
		d.setEtat(etat);
	}
	@Override
	public AffectationSeance findAffectation(String id, String date,
			String heure) {
		// TODO Auto-generated method stub
		AffectationSeance a = (AffectationSeance) em.createQuery("from AffectationSeance a where a.enseignant.id="+id+" and a.dateJour='"+date+"' and a.heureDebut='"+heure+"'").getSingleResult();
		return a;
	}
	@Override
	public void updateIdAffectationSeance(String id,String date , String heure,
			Long nvid) {
		// TODO Auto-generated method stub
		em.createQuery("update AffectationSeance set enseignant_id="+nvid+" where enseignant_id="+id+" and dateJour='"+date+"' and heureDebut='"+heure+"'").executeUpdate();
	}
	@Override
	public Collection<AffectationSeance> findAffectationById(Long id) {
		// TODO Auto-generated method stub
		//****************************************
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		
		
		
		Collection<AffectationSeance> affs = em.createQuery("from AffectationSeance where enseignant_id="+id+"").getResultList();
		Collection<AffectationSeance> afseances = new ArrayList<AffectationSeance>();
		
		for(AffectationSeance af : affs)
		{
			Date date11=af.getDateJour();
			int m1 =date11.getMonth()+1;
			int a1 =date11.getYear()+1900;
			int AU1 = 0 ;
			if(9<=m1 && m1<=12)
				AU1 = a1;
			if(1<=m1 && m1<=6)
				AU1 = a1-1;
			
			if(AU==AU1 && periodeExamen(date11).equals(type))
				afseances.add(af);
		}
		
		return afseances;
	}
	@Override
	public void supprimerAffectationSeance(Long id) {
		// TODO Auto-generated method stub
		//***********************************
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		Collection<AffectationSeance> aff = findAffectationById(id);
		for(AffectationSeance af : aff)
	    {	
			Date date11=af.getDateJour();
			int m1 =date11.getMonth()+1;
			int a1 =date11.getYear()+1900;
			int AU1 = 0 ;
			if(9<=m1 && m1<=12)
				AU1 = a1;
			if(1<=m1 && m1<=6)
				AU1 = a1-1;
			
			if(AU==AU1 && periodeExamen(date11).equals(type))
				em.remove(af);
	    }
		
	}
	@Override
	public void supprimerDemandeEchangeD(String id, String dateD, String heureD) {
		// TODO Auto-generated method stub
		DemandeEchangeS demande = (DemandeEchangeS) em.createQuery("from DemandeEchangeS where recepteur_id="+id+" and dateJourD='"+dateD+"' and heureDebutD='"+heureD+"' and etat != 'Accepter'").getSingleResult();
		em.remove(demande);
		
	}
	@Override
	public Collection<Enseignement> findJourEnseignement(Long id) {
		// TODO Auto-generated method stub
		//Collection<Enseignement> jours =  em.createQuery("Enseignement where enseignant_id="+id+"").getResultList();
		List<Enseignement> list =  em.createQuery("from Enseignement p where p.enseignant.id = :id").setParameter("id", id).getResultList(); 
		return (Collection<Enseignement>) list;
	}
	@Override
	public boolean besoinCalcule() {
		// TODO Auto-generated method stub
		Collection<Seance> seances = findAllSeance();
		int x1=0;
		for(Seance s : seances)
		{
			x1+=s.getNombreSurveillant();
		}
		Long x = (Long) em.createQuery("select sum(nombreSurveillant) from Seance").getSingleResult();
		if(x1==0)
		return false;
		else
			return true;
	}
	@Override
	public boolean isCC() {
		// TODO Auto-generated method stub
		Collection<JourExamen> jours = findAllJourExamen();
		JourExamen jour = null ;
		for(JourExamen j : jours)
		{
			jour=j;
		}
		int x= jour.getDate().getMonth()+1;
		if(x!=3 && x!=4 && x!= 11)
			return false;
		else
			return true;
	}
	@Override
	public boolean isEnseignantMaster(Long id) {
		// TODO Auto-generated method stub
		Collection<Enseignement> enseignements = em.createQuery("from Enseignement where enseignant_id="+id+"").getResultList();
		for(Enseignement e : enseignements)
		{
			int c = e.getMatiereEnseignement().getCycleAnnee().getId();
			if(c==51|| c==53)
			{
				return true;
			}
		}
		return false;
	}
	@Override
	public double nbHeureMastere(Long id) {
		// TODO Auto-generated method stub
		
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		Collection<Enseignement> enseignements = new ArrayList<Enseignement>();
		if(typeExamen().equals("CC1")){
			enseignements = em.createQuery("from Enseignement where au="+AU+" and semestre=1 and enseignant_id="+id+" and (cycleAnnee=51 or cycleAnnee=53)").getResultList();
		}
		if(typeExamen().equals("CC2")){
			enseignements = em.createQuery("from Enseignement where au="+AU+" and semestre=2 and enseignant_id="+id+" and (cycleAnnee=51 or cycleAnnee=53)").getResultList();
		}
		//Collection<Enseignement> enseignements = em.createQuery("from Enseignement where semestre=1 and enseignant_id="+id+" and (cycleAnnee=51 or cycleAnnee=53)").getResultList();
		Set<String> liste = new HashSet<String>();
		for(Enseignement e : enseignements)
		{
			liste.add(e.getJour().getId()+e.getHeureDebut());
		}
		return liste.size()*1.5;
	}
	@Override
	public Collection<Examen> findExamenBySeance(Seance s) {
		// TODO Auto-generated method stub
		Collection<Examen> examens = em.createQuery("from Examen where date='"+s.getJourExamen().getDate()+"' and heureDebut='"+s.getHeureDebut()+"' order by salle asc").getResultList();
		if(examens!=null)
			return examens;
		else
			return new ArrayList<Examen>();
	}
	@Override
	public Collection<Enseignant> findSurveillantsParSéance(Seance s) {
		// TODO Auto-generated method stub
		
		Collection<Enseignant> enseignants = em.createQuery("select enseignant from AffectationSeance where dateJour='"+s.getJourExamen().getDate()+"' and heureDebut='"+s.getHeureDebut()+"' order by enseignant asc").getResultList();
		return enseignants;
	}
	@Override
	public void addAffectationSalle(AffectationExamen a) {
		// TODO Auto-generated method stub
		try{
		em.persist(a);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	@Override
	public Salle findSalle(String id) {
		// TODO Auto-generated method stub
		return em.find(Salle.class, id);
	}
	@Override
	public Filiére findFiliére(String id) {
		// TODO Auto-generated method stub
		return em.find(Filiére.class, id);
	}
	@Override
	public CycleAnnee findCycleAnnee(int id) {
		// TODO Auto-generated method stub
		return em.find(CycleAnnee.class, id);
	}
	@Override
	public Matiere findMatiere(Long id) {
		// TODO Auto-generated method stub
		return em.find(Matiere.class, id);
	}
	@Override
	public Enseignant findEnseignant(String nom) {
		// TODO Auto-generated method stub
		try{
		return (Enseignant) em.createQuery("from Enseignant where upper(enseignant) =upper('"+nom+"')").getSingleResult();
	
		}catch(Exception e)
		{
			return null;
		}
	}
	@Override
	public boolean enseignantAffectéSalle(Enseignant e, String date, String heure) {
		// TODO Auto-generated method stub
		Long a = (Long) em.createQuery("select count(*) from AffectationExamen where dateJour='"+date+"' and heureDebut='"+heure+"' and surveillant.id="+e.getId()+"").getSingleResult();
		if(a>0)
			return  true;
		else
			return false;
	}
	@Override
	public boolean enseignantAffectéSalle(Enseignant e, String date,
			String heure, Salle s) {
		// TODO Auto-generated method stub
		
		Long a = (Long) em.createQuery("select count(*) from AffectationExamen where dateJour='"+date+"' and heureDebut='"+heure+"' and surveillant.id="+e.getId()+" and salleExamen.id='"+s.getId()+"'").getSingleResult();
		if(a>0)
			return  true;
		else
			return false;
	}
	@Override
	public void supprimerAffectationExamen(String date,String heure,Long id) {
		// TODO Auto-generated method stub
		Collection<AffectationExamen> aff =  em.createQuery("from AffectationExamen where dateJour='"+date+"' and heureDebut='"+heure+"' and surveillant.id="+id+"").getResultList();
		if(aff.size()==1)
			for(AffectationExamen a : aff)
				em.remove(a);
	}
	@Override
	public Long nbAffectationSalle(String date, String heure, Salle s) {
		// TODO Auto-generated method stub
		Long x = (Long) em.createQuery("select count(*) from AffectationExamen where dateJour='"+date+"' and heureDebut='"+heure+"' and salleExamen.id='"+s.getId()+"'").getSingleResult();
		return x;
	}
	@Override
	public Collection<AffectationExamen> findAffectationExamen(String date, String heure) {
		// TODO Auto-generated method stub
		Collection<AffectationExamen> a = em.createQuery("from AffectationExamen where dateJour='"+date+"' and heureDebut='"+heure+"' order by salleExamen.id asc").getResultList();
		return a;
	}
	@Override
	public boolean findAffectation(AffectationSeance a) {
		// TODO Auto-generated method stub
		Long x = (Long) em.createQuery("select count(*) from AffectationSeance where enseignant.id="+a.getEnseignant().getId()+" and dateJour='"+a.getDateJour()+"' and heureDebut='"+a.getHeureDebut()+"'").getSingleResult();
		if(x>0)
			return true;
		else
			return false;
	}
	@Override
	public Collection<MatiereEnseignement> findMatiereEnseigner(Long id) {
		// TODO Auto-generated method stub
		
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		String nature = typeExamen();
		int semestre = 0;
		if(nature.equals("CC1")||nature.equals("EX1"))
			semestre=1;
		if(nature.equals("CC2")||nature.equals("EX2"))
			semestre=2;
		Collection<MatiereEnseignement> ms =null;
		
		if(nature.equals("RR")){
			ms = em.createQuery("select matiereEnseignement from Enseignement e where e.au="+AU+" and e.enseignant.id="+id+"").getResultList();
		}else
			ms = em.createQuery("select matiereEnseignement from Enseignement e where e.semestre.id="+semestre+" and e.au="+AU+" and e.enseignant.id="+id+"").getResultList();
		
		Set<MatiereEnseignement> mm = new HashSet<MatiereEnseignement>();
		for(MatiereEnseignement me : ms)
		{
			mm.add(me);
		}
		return mm;
	}
	@Override
	public boolean RespAffecté() {
		// TODO Auto-generated method stub
		Collection<AffectationSeance> affe = findAllAffectationSeances();
		
		Long x = (Long) em.createQuery("select count(*) from AffectationSeance").getSingleResult();
		if(affe.size()>0)
			return true;
		else
			return false;
	}
	@Override
	public Long nbAffectationParSeanceAuxSalle(String date,String heure) {
		// TODO Auto-generated method stub
		Long x = (Long) em.createQuery("select count(*) from AffectationExamen where dateJour='"+date+"' and heureDebut='"+heure+"'").getSingleResult();
		return x;
	}
	@Override
	public Collection<Enseignant> findAllEnseignantActif() {
		// TODO Auto-generated method stub
		Collection<Enseignant> e = em.createQuery("from Enseignant where actif=true").getResultList();
		return e;
	}
	@Override
	public void addRemplacement(Remplacement r) {
		// TODO Auto-generated method stub
		em.persist(r);
		
	}
	@Override
	public Remplacement findRemplacement(String date, String heure,
			Enseignant ens) {
		// TODO Auto-generated method stub
		
		Remplacement r = (Remplacement) em.createQuery("from Remplacement where dateJour='"+date+"' and heureDebut='"+heure+"' and surveillant.id="+ens.getId()+"").getSingleResult();
		return r;
		
	}
	@Override
	public boolean existeRemp(String date, String heure, Enseignant ens) {
		// TODO Auto-generated method stub
		Long r = (Long) em.createQuery("select count(*) from Remplacement where dateJour='"+date+"' and heureDebut='"+heure+"' and surveillant.id="+ens.getId()+"").getSingleResult();
		if(r>0)
		return true;
		else
			return false;
	}
	@Override
	public void supprimerRemplacement(String date, String heure, Enseignant ens) {
		// TODO Auto-generated method stub
		if(existeRemp(date, heure, ens))
			em.remove(findRemplacement(date, heure, ens));
		
	}
	@Override
	public String typeExamen() {
		// TODO Auto-generated method stub
		DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();		
		Date dateJour=d.getTime();
		dateJour.setYear(this.a);
		dateJour.setMonth(this.m);
		dateJour.setDate(this.d);
		return periodeExamen(dateJour);
	}
	@Override
	public Collection<Enseignement> getCalendrierEnseignement(Long id) {
		// TODO Auto-generated method stub
		
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		String nature = typeExamen();
		Collection<Enseignement> ens = new ArrayList<Enseignement>();
				;
		switch (nature) {
		case "RR":
			ens = em.createQuery("from Enseignement e where enseignant_id="+id+" and au="+AU+" order by e.jour.id ASC").getResultList();
			break;
		case "EX1":
			ens = em.createQuery("from Enseignement e where enseignant_id="+id+" and au="+AU+" and semestre=1 order by e.jour.id ASC").getResultList();
			break;
		case "EX2":
			ens = em.createQuery("from Enseignement e where enseignant_id="+id+" and au="+AU+" and semestre=2 order by e.jour.id ASC").getResultList();
			break;
		case "CC1":
			ens = em.createQuery("from Enseignement e where enseignant_id="+id+" and au="+AU+" and semestre=1 order by e.jour.id ASC").getResultList();
			break;
		case "CC2":
			ens = em.createQuery("from Enseignement e where enseignant_id="+id+" and au="+AU+" and semestre=2 order by e.jour.id ASC").getResultList();
			break;		
		}
		
		Collection<Enseignement> enseignement = new ArrayList<Enseignement>();
		Set<String> set = new HashSet<String>();
		
		for(Enseignement e : ens)
		{
			if(set.add(e.getJour()+e.getHeureDebut()))
			{
				enseignement.add(e);
			}
		}
		
		return enseignement;
	}
	@Override
	public void updateAbsence(AffectationSeance aff,boolean p) {
		// TODO Auto-generated method stub
		AffectationSeance se = findAffectation(String.valueOf( aff.getEnseignant().getId()), aff.getDateJour().toString().split(" ")[0], aff.getHeureDebut().toString());
		
		se.setPresence(p);
		
	}
	@Override
	public AffectationExamen findAffectationExamen(String date, String heure,
			String id) {
		// TODO Auto-generated method stub
		return (AffectationExamen) em.createQuery("From AffectationExamen a where a.dateJour='"+date+"' and a.heureDebut='"+heure+"' and a.surveillant.id="+id+"").getSingleResult();
	}
	@Override
	public Collection<AffectationSeance> findSurveillantPourAbsence(String date , String heure) {
		// TODO Auto-generated method stub
		Collection<AffectationSeance> a =  em.createQuery("from AffectationSeance where heureDebut='"+heure+"' and dateJour='"+date+"' order by presence ASC").getResultList();
		return a;
	}
	@Override
	public Collection<Enseignant> getListeSurveillantTriParCharge() {
		// TODO Auto-generated method stub
		Collection<Enseignant> listeSurveillants = em.createQuery("from Enseignant e where e.actif="+true+" and e.commission="+false+" and e.aSruveiller>0 order by e.aSruveiller desc").getResultList();
		return listeSurveillants;
	}
	@Override
	public Collection<MatiereEnseignement> getMatiereEnseignementsByEnseignant(
			Enseignant e) {
		// TODO Auto-generated method stub
		
		//*****************************
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		
		Collection<Enseignement> emploie =null;
		if(type.equals("CC1")||type.equals("EX1"))
			emploie = em.createQuery("from Enseignement e where e.au="+AU+" and e.semestre.id=1 and e.enseignant.id="+e.getId()+"").getResultList();
		if(type.equals("CC2")||type.equals("EX2"))
			emploie = em.createQuery("from Enseignement e where e.au="+AU+" and e.semestre.id=2 and enseignant_id="+e.getId()+"").getResultList();
		if(type.equals("RR"))
			emploie = em.createQuery("from Enseignement e where e.au="+AU+" and e.enseignant.id="+e.getId()+"").getResultList();
		
		
		//emploie = em.createQuery("from Enseignement e where e.enseignant.id="+e.getId()+"").getResultList();
		Set<MatiereEnseignement> matiere = new HashSet<MatiereEnseignement>();
		for(Enseignement ens : emploie)
		{
			matiere.add(ens.getMatiereEnseignement());
		}
		return matiere;
	}
	@Override
	public Collection<Seance> findSeancePourMatiere(MatiereEnseignement m) {
		// TODO Auto-generated method stub
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		Collection<Examen> examens = em.createQuery("from Examen e where e.matiereEnseignement.matiere.id="+m.getMatiere().getId()+" and e.matiereEnseignement.filiere.id='"+m.getFiliere().getId()+"' and e.matiereEnseignement.cycleAnnee.id="+m.getCycleAnnee().getId()+"").getResultList();
		Set<Seance> seances = new HashSet<Seance>();
		for(Examen e :examens)
		{
			if(periodeExamen(e.getSeance().getJourExamen().getDate()).equals(type))
			seances.add(e.getSeance());
		}
		return seances;
	}
	@Override
	public void addTypeAlerte(TypeAlerte type) {
		// TODO Auto-generated method stub
		em.persist(type);
		
	}
	@Override
	public Collection<TypeAlerte> getTypesAlertes() {
		// TODO Auto-generated method stub
		Long i = (Long) em.createQuery("select count(*) from TypeAlerte").getSingleResult();
		if(i>0l){
			Collection<TypeAlerte> types = em.createQuery("from TypeAlerte").getResultList();
			return types;
		}
		return new ArrayList<TypeAlerte>();
		
		
		
	}
	@Override
	public boolean enSeance(String date, String heure,Long id) {
		// TODO Auto-generated method stub
		Collection<Time> heures = em.createQuery("select heureDebut from AffectationExamen where dateJour='"+date+"' and surveillant.id="+id+"").getResultList();
		//Long i = (Long) em.createQuery("select count(*) from ").getSingleResult();
		Collection<Time> h = new ArrayList<Time>();
		if(heures.size()<=0){
			h = em.createQuery("select heureDebut from Remplacement where dateJour='"+date+"' and remplacant.id="+id+"").getResultList();
			heures = h;
		}
		if(heures.size()<=0)
			return false;
		for(Time t : heures)
		{
			DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(date.split("-")[0]),Integer.parseInt(date.split("-")[1])+1,Integer.parseInt(date.split("-")[2]),t.getHours(),t.getMinutes(),0);
			Date dateD=d.getTime();
			if(!typeExamen().equals("CC"))
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
			d1.set(Integer.parseInt(date.split("-")[0]),Integer.parseInt(date.split("-")[1])+1,Integer.parseInt(date.split("-")[2]),Integer.parseInt(heure.split(":")[0]),Integer.parseInt(heure.split(":")[1]),0);
			Date dateAlerte=d1.getTime();
			
			if(dateAlerte.after(dateD)&&dateAlerte.before(dateF)){
				if(h.size()==0)
				{
					String x = (dateD.getMonth()+1)>9?(dateD.getMonth()+1)+"":"0"+(dateD.getMonth()+1)+"";
					String x1 = dateD.getDate()>9?dateD.getDate()+"":"0"+dateD.getDate()+"";
					
					Long xx = (Long) em.createQuery("select count(*) from Remplacement where dateJour='"+date+"' and heureDebut='"+t.toString()+"' and surveillant.id="+id+"").getSingleResult();
					
					if(xx>0)
						return false;
				}
				
				return true;
			}
			
		}
		return false;
	}
	@Override
	public Examen findExamenParAlerte(Long id, String heure, String date) {
		// TODO Auto-generated method stub
		Collection<Time> heures = em.createQuery("select heureDebut from AffectationExamen where dateJour='"+date+"' and surveillant.id="+id+"").getResultList();
		//Long i = (Long) em.createQuery("select count(*) from ").getSingleResult();
		if(heures.size()<=0){
			Collection<Time> h = em.createQuery("select heureDebut from Remplacement where dateJour='"+date+"' and remplacant.id="+id+"").getResultList();
			heures = h;
		}
		Examen examen = null;
		for(Time t : heures)
		{
			DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(date.split("-")[0]),Integer.parseInt(date.split("-")[1])+1,Integer.parseInt(date.split("-")[2]),t.getHours(),t.getMinutes(),0);
			Date dateD=d.getTime();
			if(!typeExamen().equals("CC"))
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
			d1.set(Integer.parseInt(date.split("-")[0]),Integer.parseInt(date.split("-")[1])+1,Integer.parseInt(date.split("-")[2]),Integer.parseInt(heure.split(":")[0]),Integer.parseInt(heure.split(":")[1]),0);
			Date dateAlerte=d1.getTime();
			AffectationExamen affEx;
			
			if(dateAlerte.after(dateD)&&dateAlerte.before(dateF)){
				 Long i = (Long) em.createQuery("select count(*) from AffectationExamen where surveillant.id="+id+" and dateJour='"+date+"' and heureDebut='"+t.toString().split(":")[0]+":"+t.toString().split(":")[1]+"'").getSingleResult();
				 if(i>0)
				 affEx= (AffectationExamen) em.createQuery("from AffectationExamen where surveillant.id="+id+" and dateJour='"+date+"' and heureDebut='"+t.toString().split(":")[0]+":"+t.toString().split(":")[1]+"'").getSingleResult();
				 else{
					 Long idS = (Long) em.createQuery("select surveillant.id from Remplacement where remplacant.id="+id+" and dateJour='"+date+"' and heureDebut='"+t.toString().split(":")[0]+":"+t.toString().split(":")[1]+"'").getSingleResult();
					 affEx= (AffectationExamen) em.createQuery("from AffectationExamen where surveillant.id="+idS+" and dateJour='"+date+"' and heureDebut='"+t.toString().split(":")[0]+":"+t.toString().split(":")[1]+"'").getSingleResult();
				 }
				 examen = (Examen) em.createQuery("from Examen where salle.id='"+affEx.getSalleExamen().getId()+"' and matiereEnseignement.matiere.id='"+affEx.getMatiere().getId()+"' and matiereEnseignement.cycleAnnee.id="+affEx.getCycleAnnee().getId()+" and matiereEnseignement.filiere.id='"+affEx.getFiliere().getId()+"'").getSingleResult();
			}
			
		}
		return examen;
	}
	@Override
	public TypeAlerte findTypeAlerte(Long id) {
		// TODO Auto-generated method stub
		return em.find(TypeAlerte.class, id);
	}
	@Override
	public void addAlerte(Alerte alerte) {
		// TODO Auto-generated method stub
		em.persist(alerte);
		
	}
	@Override
	public Collection<Alerte> findAlertesBySeance(Seance seance) {
		// TODO Auto-generated method stub
		
		DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();
		d.set(seance.getJourExamen().getDate().getYear(), seance.getJourExamen().getDate().getMonth(), seance.getJourExamen().getDate().getDate(), seance.getHeureDebut().getHours(), seance.getHeureDebut().getMinutes());
		Date dateD=d.getTime();
		
		if(!typeExamen().equals("CC"))
		{
			d.add(Calendar.HOUR, 2);
			d.add(Calendar.MINUTE, 30);
		}else
		{
			d.add(Calendar.HOUR, 1);
			d.add(Calendar.MINUTE, 30);
				
		}
		
		Date dateF = d.getTime();
		
		Collection<Alerte> alertes = em.createQuery("from Alerte where date='"+seance.getJourExamen().getDate().toString().split(" ")[0]+"' order by heure DESC").getResultList();
		Collection<Alerte> alertesSeance = new ArrayList<Alerte>();
		for(Alerte al : alertes)
		{
			DateFormat f2= DateFormat.getDateInstance(DateFormat.SHORT);
			Calendar d2 = Calendar.getInstance();
			d2.set(al.getDate().getYear(), al.getDate().getMonth(), al.getDate().getDate(), al.getHeure().getHours(), al.getHeure().getMinutes());
			Date d22=d2.getTime();
			if(d22.after(dateD)&&d22.before(dateF))
				alertesSeance.add(al);
		}
		return alertesSeance;
	}
	@Override
	public Collection<Enseignant> findEnseignantsParMatiere(
			MatiereEnseignement matiere) {
		// TODO Auto-generated method stub
		
		return em.createQuery("select distinct enseignant from Enseignement m where m.matiereEnseignement.matiere.id="+matiere.getMatiere().getId()+" and m.matiereEnseignement.filiere.id='"+matiere.getFiliere().getId()+"' and m.matiereEnseignement.cycleAnnee.id="+matiere.getCycleAnnee().getId()+"").getResultList();
		
	}
	@Override
	public Alerte findAlerte(Long id) {
		// TODO Auto-generated method stub
		return em.find(Alerte.class,id);
	}
	@Override
	public String periodeExamen(Date date) {
		// TODO Auto-generated method stub
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();	
		Date dateActuelle=date;
		int x =0;
		
		
		dXX.set(dateActuelle.getYear()+1900, dateActuelle.getMonth(),dateActuelle.getDate());
		Date date2=dXX.getTime();
		
		if(date2.getMonth()>=0 && date2.getMonth()<=5)
			x=0;
		else
			x=1;
		
		dXX.set(date2.getYear()+1900+(x>0?0:-1), 8, 0);
		Date DBCC1=dXX.getTime();
		dXX.set(date2.getYear()+1900+(x>0?0:-1), 10, 30);
		Date DFCC1=dXX.getTime();
		
		dXX.set(date2.getYear()+1900+(x>0?0:-1), 11, 1);
		Date DBEX1=dXX.getTime();
		dXX.set(date2.getYear()+1900+(x>0?+1:0), 0, 30);
		Date DFEX1=dXX.getTime();
		
		dXX.set(date2.getYear()+1900+(x>0?+1:0), 1, 1);
		Date DBCC2=dXX.getTime();
		dXX.set(date2.getYear()+1900 +(x>0?+1:0), 3, 15);
		Date DFCC2=dXX.getTime();
		
		dXX.set(date2.getYear()+1900+(x>0?+1:0), 3, 15);
		Date DBEX2=dXX.getTime();
		dXX.set(date2.getYear()+1900+(x>0?+1:0), 5, 5);
		Date DFEX2=dXX.getTime();
		
		dXX.set(date2.getYear()+1900+(x>0?+1:0), 5, 6);
		Date DBRR=dXX.getTime();
		dXX.set(date2.getYear()+1900+(x>0?+1:0), 5, 30);
		Date DFRR=dXX.getTime();
		
		if(date2.after(DBCC1)&&date2.before(DFCC1))
			return "CC1";
		if(date2.after(DBEX1)&&date2.before(DFEX1))
			return "EX1";
		if(date2.after(DBCC2)&&date2.before(DFCC2))
			return "CC2";
		if(date2.after(DBEX2)&&date2.before(DFEX2))
			return "EX2";
		if(date2.after(DBRR)&&date2.before(DFRR))
			return "RR";
		
		
		return date2.after(DBEX1)+" "+date2.before(DFEX1)+"  "+date2.toLocaleString()+" "+DBEX1.toLocaleString()+" "+DFEX1.toLocaleString();
	}
	@Override
	public Collection<AffectationExamen> findAllAffectationExamen() {
		// TODO Auto-generated method stub
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		Collection<AffectationExamen> affectationExamens = em.createQuery("from AffectationExamen").getResultList();
		Collection<AffectationExamen> affs = new ArrayList<AffectationExamen>();
		for(AffectationExamen af : affectationExamens)
		{
			Date date11=af.getDateJour();
			int m1 =date11.getMonth()+1;
			int a1 =date11.getYear()+1900;
			int AU1 = 0 ;
			if(9<=m1 && m1<=12)
				AU1 = a1;
			if(1<=m1 && m1<=6)
				AU1 = a1-1;
			
			if(AU-AU1==0 && periodeExamen(af.getDateJour()).equals(type))
			{
				affs.add(af);
			}
		}
		
		
		return affs;
	}
	@Override
	public Collection<TypeAlerte> findAllTypesAlertes() {
		// TODO Auto-generated method stub
		Collection<TypeAlerte> types = em.createQuery("from TypeAlerte").getResultList();
		
		return types;
	}
	@Override
	public Collection<Alerte> findAllAlertes() {
		// TODO Auto-generated method stub
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		

		Collection<Alerte> alertes = em.createQuery("from Alerte").getResultList();
		Collection<Alerte> als = new ArrayList<Alerte>();
		for(Alerte t : alertes)
		{
			Date date11=t.getDate();
			
			int m1 =date11.getMonth()+1;
			int a1 =date11.getYear()+1900;
			int AU1 = 0 ;
			if(9<=m1 && m1<=12)
				AU1 = a1;
			if(1<=m1 && m1<=6)
				AU1 = a1-1;
			
			if(AU-AU1 == 0 && periodeExamen(date11).equals(type))
				als.add(t);
		}
		
		return als;
	}
	
	@Override
	public int nbExamenSalle(String date, String heure, Salle salle) {
		// TODO Auto-generated method stub
		
		Collection<Examen> ex = em.createQuery("from Examen where salle.id='"+salle.getId()+"' and seance.heureDebut='"+heure+"' and seance.jourExamen.date='"+date+"'").getResultList();
		
		return ex.size();
	}
	@Override
	public Long findTypeByLibelle(String libelle) {
		// TODO Auto-generated method stub
		Long type = (Long) em.createQuery("select count(*) from TypeAlerte where upper(libelle)=upper('"+libelle+"')").getSingleResult();
		return type;
	}
	@Override
	public boolean supprimerTypeAlerte(TypeAlerte type) {
		// TODO Auto-generated method stub
		Long i = (Long) em.createQuery("select count(*) from Alerte where type="+type.getId()+"").getSingleResult();
		if(i==0){
		em.remove(em.contains(type) ? type : em.merge(type));
		return true;
		}else
			return false;
	}
	@Override
	public Collection<Examen> findExamensByMatiere(MatiereEnseignement mat) {
		// TODO Auto-generated method stub
		
		
		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		
		Collection<Examen> ex = em.createQuery("from Examen where seance.jourExamen.au="+AU+" and matiereEnseignement.matiere.id="+mat.getMatiere().getId()+" and matiereEnseignement.filiere.id='"+mat.getFiliere().getId()+"' and matiereEnseignement.cycleAnnee.id="+mat.getCycleAnnee().getId()+"").getResultList();
		Collection<Examen> examens = new ArrayList<Examen>();
		for(Examen e : ex)
		{
			if(periodeExamen(e.getSeance().getJourExamen().getDate()).equals(type))
				examens.add(e);
		}
		return examens;
	}
	@Override
	public boolean existeExamenPourMatiere(MatiereEnseignement mat) {
		// TODO Auto-generated method stub

		DateFormat fXX= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar dXX = Calendar.getInstance();
		
		//a supprimer  ************************************
		dXX.set(this.a, this.m, this.d);
		
		Date dateActuelle=dXX.getTime();
		String type = periodeExamen(dateActuelle);
		
		int m = getDateActuelle().getMonth()+1;
		int a = getDateActuelle().getYear()+1900;
		int AU = 0 ;
		if(9<=m && m<=12)
			AU = a;
		if(1<=m && m<=6)
			AU = a-1;
		
		
		Long i = (Long) em.createQuery("select count(*) from Examen where seance.jourExamen.au="+AU+" and matiereEnseignement.matiere.id="+mat.getMatiere().getId()+" and matiereEnseignement.filiere.id='"+mat.getFiliere().getId()+"' and matiereEnseignement.cycleAnnee.id="+mat.getCycleAnnee().getId()+"").getSingleResult();
		Collection<Examen> ex = findExamensByMatiere(mat);
		if(ex.size()>0)
		return true;
		else
			return false;
	}
	@Override
	public Collection<String> getAnnéeUniversitaire() {
		// TODO Auto-generated method stub
		
		Collection<JourExamen> jours = em.createQuery("from JourExamen").getResultList();
		Set<String> annee = new HashSet<String>();
		for(JourExamen j : jours)
		{
			int m = j.getDate().getMonth()+1;
			int a = j.getDate().getYear()+1900;
			int au =0;
			if(9<=m && m<=12)
				au = a;
			if(1<=m && m<=6)
				au = a-1;
			annee.add(au+"");
		}
		
		return annee;
	}
	@Override
	public Collection<String> getSessionByAnnee(int annee) {
		// TODO Auto-generated method stub
		Set<String> session = new HashSet<String>();
		if(annee != 0){
		Collection<JourExamen> jours = em.createQuery("from JourExamen where au="+annee+"").getResultList();
		
		for(JourExamen j : jours)
		{
			String x = periodeExamen(j.getDate());
			if(x.equals("CC1") || x.equals("CC2"))
				session.add("Controle Continu");
			if(x.equals("EX1") || x.equals("EX2"))
				session.add("Principale");
			if(x.equals("RR"))
				session.add("Rattrapage");
		}
		}else
		{
			Collection<JourExamen> jours = em.createQuery("from JourExamen").getResultList();
			
			for(JourExamen j : jours)
			{
				String x = periodeExamen(j.getDate());
				if(x.equals("CC1") || x.equals("CC2"))
					session.add("Controle Continu");
				if(x.equals("EX1") || x.equals("EX2"))
					session.add("Principale");
				if(x.equals("RR"))
					session.add("Rattrapage");
			}
		}
		
		return session;
	}
	@Override
	public Collection<Time> getHeureByAnneeSession(int annee, String session) {
		// TODO Auto-generated method stub
		
		Set<Time> heures = new HashSet<Time>();
		Collection<Seance> seances = new ArrayList<Seance>();
		
		if(annee == 0)
			seances = em.createQuery("from Seance").getResultList();
		else
			seances = em.createQuery("from Seance where jourExamen.au="+annee+"").getResultList();
		
		if(session.equals("Tous"))
		{
		for(Seance j : seances)
		{			
			heures.add(j.getHeureDebut());
		}
		}
		if(session.equals("CC"))
		{
			for(Seance j : seances)
			{	
				if(periodeExamen(j.getJourExamen().getDate()).equals("CC1") || periodeExamen(j.getJourExamen().getDate()).equals("CC2"))
					heures.add(j.getHeureDebut());
			}
		}
		if(session.equals("EX"))
		{
			for(Seance j : seances)
			{	
				if(periodeExamen(j.getJourExamen().getDate()).equals("EX1") || periodeExamen(j.getJourExamen().getDate()).equals("EX2"))
					heures.add(j.getHeureDebut());
			}
		}
		if(session.equals("RR"))
		{
			for(Seance j : seances)
			{	
				if(periodeExamen(j.getJourExamen().getDate()).equals("RR"))
					heures.add(j.getHeureDebut());
			}
		}
		
		
		
		
		
		List<Time> time = new ArrayList<Time>();
		time.addAll(heures);
		//Collections.sort(time);
		Collections.sort(time, new Comparator<Time>() {
	        @Override
	        public int compare(Time t1, Time t2)
	        {

	            return  t1.before(t2)?-1:1;
	        }
	    });
		
		return time;
	}
	@Override
	public Collection<AffectationSeance> getAffectationByAnneeSession(
			int annee, String session) {
		// TODO Auto-generated method stub
		
		Collection<AffectationSeance> affectationSeances = em.createQuery("from AffectationSeance").getResultList();
		Collection<AffectationSeance> affe = new ArrayList<AffectationSeance>();
		
		if(annee==0)
		{
		for(AffectationSeance af : affectationSeances)
		{	
			if(session.equals("Tous"))
				affe.add(af);
			else
			{
				if(session.equals("CC"))
				{
					if(periodeExamen(af.getDateJour()).equals("CC1")||periodeExamen(af.getDateJour()).equals("CC2"))
						affe.add(af);
				}
				if(session.equals("EX"))
				{
					if(periodeExamen(af.getDateJour()).equals("EX1")||periodeExamen(af.getDateJour()).equals("EX2"))
						affe.add(af);
				}
				if(session.equals("RR"))
				{
					if(periodeExamen(af.getDateJour()).equals("RR"))
						affe.add(af);
				}
			}
		
		}
		}else
		{
			for(AffectationSeance af : affectationSeances)
			{
				int m = af.getDateJour().getMonth()+1;
				int a = af.getDateJour().getYear()+1900;
				int au =0;
				if(9<=m && m<=12)
					au = a;
				if(1<=m && m<=6)
					au = a-1;
				if(au==annee){
				if(session.equals("Tous"))
					affe.add(af);
				else
				{
					if(session.equals("CC"))
					{
						if(periodeExamen(af.getDateJour()).equals("CC1")||periodeExamen(af.getDateJour()).equals("CC2"))
							affe.add(af);
					}
					if(session.equals("EX"))
					{
						if(periodeExamen(af.getDateJour()).equals("EX1")||periodeExamen(af.getDateJour()).equals("EX2"))
							affe.add(af);
					}
					if(session.equals("RR"))
					{
						if(periodeExamen(af.getDateJour()).equals("RR"))
							affe.add(af);
					}
				}
			}
			}
		}
		return affe;
	}
	@Override
	public Collection<JourExamen> getJourExamensByAnneeSession(int annee,
			String session) {
		// TODO Auto-generated method stub
		
		Collection<JourExamen> JourExamen = new ArrayList<JourExamen>();
		
		if(annee == 0)
			JourExamen = em.createQuery("from JourExamen").getResultList();
		else
			JourExamen = em.createQuery("from JourExamen where au="+annee+"").getResultList();
		
		Set<JourExamen> jours = new HashSet<JourExamen>();
		
		for(JourExamen af : JourExamen)
		{	
			if(session.equals("Tous"))
				jours.add(af);
			else
			{
				if(session.equals("CC"))
				{
					if(periodeExamen(af.getDate()).equals("CC1")||periodeExamen(af.getDate()).equals("CC2"))
						jours.add(af);
				}
				if(session.equals("EX"))
				{
					if(periodeExamen(af.getDate()).equals("EX1")||periodeExamen(af.getDate()).equals("EX2"))
						jours.add(af);
				}
				if(session.equals("RR"))
				{
					if(periodeExamen(af.getDate()).equals("RR"))
						jours.add(af);
				}
			}
		
		}
		
		List<JourExamen> time = new ArrayList<JourExamen>();
		time.addAll(jours);
		//Collections.sort(time);
		Collections.sort(time, new Comparator<JourExamen>() {
	        @Override
	        public int compare(JourExamen t1, JourExamen t2)
	        {

	            return  t1.getDate().before(t2.getDate())?-1:1;
	        }
	    });
		
		return time;
	}
	@Override
	public Collection<AffectationExamen> getAffectationExamenByAnneeSession(
			int annee, String session) {
		// TODO Auto-generated method stub
		
		
		Collection<AffectationExamen> affectationExamens = em.createQuery("from AffectationExamen").getResultList();
		Collection<AffectationExamen> affe = new ArrayList<AffectationExamen>();
		
		if(annee==0)
		{
		for(AffectationExamen af : affectationExamens)
		{	
			if(session.equals("Tous"))
				affe.add(af);
			else
			{
				if(session.equals("CC"))
				{
					if(periodeExamen(af.getDateJour()).equals("CC1")||periodeExamen(af.getDateJour()).equals("CC2"))
						affe.add(af);
				}
				if(session.equals("EX"))
				{
					if(periodeExamen(af.getDateJour()).equals("EX1")||periodeExamen(af.getDateJour()).equals("EX2"))
						affe.add(af);
				}
				if(session.equals("RR"))
				{
					if(periodeExamen(af.getDateJour()).equals("RR"))
						affe.add(af);
				}
			}
		
		}
		}else
		{
			for(AffectationExamen af : affectationExamens)
			{
				int m = af.getDateJour().getMonth()+1;
				int a = af.getDateJour().getYear()+1900;
				int au =0;
				if(9<=m && m<=12)
					au = a;
				if(1<=m && m<=6)
					au = a-1;
				if(au==annee){
				if(session.equals("Tous"))
					affe.add(af);
				else
				{
					if(session.equals("CC"))
					{
						if(periodeExamen(af.getDateJour()).equals("CC1")||periodeExamen(af.getDateJour()).equals("CC2"))
							affe.add(af);
					}
					if(session.equals("EX"))
					{
						if(periodeExamen(af.getDateJour()).equals("EX1")||periodeExamen(af.getDateJour()).equals("EX2"))
							affe.add(af);
					}
					if(session.equals("RR"))
					{
						if(periodeExamen(af.getDateJour()).equals("RR"))
							affe.add(af);
					}
				}
			}
			}
		}
		
		
		return affe;
	}
	@Override
	public Collection<Alerte> getAlerteByAnneeSession(int annee, String session) {
		// TODO Auto-generated method stub
		
		
		Collection<Alerte> alertes = em.createQuery("from Alerte").getResultList();
		Set<Alerte> als = new HashSet<Alerte>();
		if(annee==0)
		{
			for(Alerte al : alertes)
			{
				if(session.equals("Tous"))
				{
					als.add(al);
				}else
				{
				if(session.equals("CC"))
				{
					if(periodeExamen(al.getDate()).equals("CC1")||periodeExamen(al.getDate()).equals("CC2"))
						als.add(al);
				}
				if(session.equals("EX"))
				{
					if(periodeExamen(al.getDate()).equals("EX1")||periodeExamen(al.getDate()).equals("EX2"))
						als.add(al);
				}
				if(session.equals("RR"))
				{
					if(periodeExamen(al.getDate()).equals("RR"))
						als.add(al);
				}
				}
			}
		}else
		{
			for(Alerte al : alertes)
			{
				int m = al.getDate().getMonth()+1;
				int a = al.getDate().getYear()+1900;
				int au =0;
				if(9<=m && m<=12)
					au = a;
				if(1<=m && m<=6)
					au = a-1;
				if(annee==au)
				{
					if(session.equals("Tous"))
					{
						als.add(al);
					}else
					{
					if(session.equals("CC"))
					{
						if(periodeExamen(al.getDate()).equals("CC1")||periodeExamen(al.getDate()).equals("CC2"))
							als.add(al);
					}
					if(session.equals("EX"))
					{
						if(periodeExamen(al.getDate()).equals("EX1")||periodeExamen(al.getDate()).equals("EX2"))
							als.add(al);
					}
					if(session.equals("RR"))
					{
						if(periodeExamen(al.getDate()).equals("RR"))
							als.add(al);
					}
					}
				}
			}
		}
		
		return als;
	}
	
	
	@Override
	public void addUtilisateur(Utilisateur u) {
		// TODO Auto-generated method stub
		
		em.persist(u);
		
	}
	@Override
	public boolean FindUtilisateur(Utilisateur u) {
		// TODO Auto-generated method stub
		Utilisateur u1 = em.find(Utilisateur.class, u.getNom());
		if(u1 ==null)
			return false;
		else
			return true;
	}
	@Override
	public Collection<Role> getAllRoles() {
		// TODO Auto-generated method stub
		Collection<Role> roles = em.createQuery("from Role").getResultList();
		return roles;
	}
	@Override
	public Role findRole(Long id) {
		// TODO Auto-generated method stub
		Role role = (Role) em.createQuery("from Role where id="+id+"").getSingleResult();
		return role;
	}
	@Override
	public Collection<Utilisateur> findAllUtilisateurs() {
		// TODO Auto-generated method stub
		Collection<Utilisateur> utilisateurs = em.createQuery("from Utilisateur").getResultList();
		for(Utilisateur u : utilisateurs)
		{
			Collection<BigInteger> rols = em.createNativeQuery("select roles_id from utilisateur_role where Utilisateur_nom='"+u.getNom()+"'").getResultList();
			List<Role> roles = new ArrayList<Role>();
		
			
			for(BigInteger x : rols)
			{
				roles.add(findRole(x.longValue()));
			}
			
			Collections.sort(roles, new Comparator<Role>() {
		        @Override
		        public int compare(Role t1, Role t2)
		        {

		            return  t1.getLibelle().compareTo(t2.getLibelle());
		        }
		    });	
			
			u.setRoles(roles);
			
		}
		return utilisateurs;
	}
	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		em.createNativeQuery("DELETE FROM utilisateur_role WHERE Utilisateur_nom='"+utilisateur.getNom()+"'").executeUpdate();
		em.createNativeQuery("DELETE FROM utilisateur WHERE nom='"+utilisateur.getNom()+"'").executeUpdate();
		
	}
	@Override
	public Utilisateur findUser(String nom, String pass) {
		// TODO Auto-generated method stub
		Long  i = (Long) em.createQuery("select count(*) from Utilisateur where nom='"+nom+"' and motDePasse='"+pass+"'").getSingleResult();
		if(i>=1){
		
			Utilisateur u = (Utilisateur) em.createQuery("from Utilisateur where nom='"+nom+"' and motDePasse='"+pass+"'").getSingleResult();
			Collection<BigInteger> rols = em.createNativeQuery("select roles_id from utilisateur_role where Utilisateur_nom='"+u.getNom()+"'").getResultList();
			List<Role> roles = new ArrayList<Role>();
			for(BigInteger x : rols)
			{
				roles.add(findRole(x.longValue()));
			}
			
			u.setRoles(roles);
			return u;
			
		}
		else
		return null;
	}
	@Override
	public Enseignant loginEnseignant(String nom, String pass) {
		// TODO Auto-generated method stub
		Long i =(Long) em.createQuery("select count(*) from Enseignant where nomUtilisateur='"+nom+"' and motDePasse='"+pass+"'").getSingleResult();
		if(i==1){
			Enseignant e =(Enseignant) em.createQuery("from Enseignant where nomUtilisateur='"+nom+"' and motDePasse='"+pass+"'").getSingleResult();
			return e;
		}else
			return null;
	}

	
	@Override
	public boolean nomUtilisateurExiste(String nom,Long id) {
		// TODO Auto-generated method stub
		Long i = (Long) em.createQuery("select count(*) from Enseignant where nomUtilisateur='"+nom+"' and id != "+id+"").getSingleResult();
		if(i>=1)
			return true;
		else
			return false;
	}
	@Override
	public void updateParamCnx(String nom, String pass,String cin) {
		// TODO Auto-generated method stub
		
		em.createNativeQuery("update enseignant set nomUtilisateur='"+nom+"', motDePasse='"+pass+"' where cin="+cin+"").executeUpdate();
		
	}
	@Override
	public Enseignant findEnseignantParMail(String mail) {
		// TODO Auto-generated method stub
		Long i = (Long) em.createQuery("select count(*) from Enseignant where email='"+mail+"'").getSingleResult();
		if(i==1)
			return (Enseignant) em.createQuery("from Enseignant where email='"+mail+"'").getSingleResult();
		else
			return null;
	}
	@Override
	public void reglerCin(Long id,String cin) {
		// TODO Auto-generated method stub
		em.createNativeQuery("update enseignant set cin='"+cin+"' where id="+id+"").executeUpdate();
		
	}
	@Override
	public Collection<JourExamen> findAllJourExamen(String session,int AU) {
		// TODO Auto-generated method stub
		
		String type = session;
		Collection<JourExamen> jourss = new ArrayList<JourExamen>();
		List<JourExamen> jours = em.createQuery("from JourExamen where au="+AU+" ORDER BY date ASC").getResultList();
		for(JourExamen j : jours)
		{
			
			if(periodeExamen(j.getDate()).equals(type))
				jourss.add(j);
		}
		return jourss;
	}
	@Override
	public Collection<AffectationSeance> findAffectationById(Long id,
			String type, int au) {
		// TODO Auto-generated method stub
		
		
		
		
		
		Collection<AffectationSeance> affs = em.createQuery("from AffectationSeance where enseignant_id="+id+"").getResultList();
		Collection<AffectationSeance> afseances = new ArrayList<AffectationSeance>();
		
		for(AffectationSeance af : affs)
		{
			Date date11=af.getDateJour();
			int m1 =date11.getMonth()+1;
			int a1 =date11.getYear()+1900;
			int AU1 = 0 ;
			if(9<=m1 && m1<=12)
				AU1 = a1;
			if(1<=m1 && m1<=6)
				AU1 = a1-1;
			
			if(au==AU1 && periodeExamen(date11).equals(type))
				afseances.add(af);
		}
		
		return afseances;
	}
	@Override
	public Collection<Time> getHeureByAnneeSession(int annee, int annee1,
			String session) {
		Set<Time> heures = new HashSet<Time>();
		Collection<Seance> seances = new ArrayList<Seance>();
		
		if(annee == 0)
			seances = em.createQuery("from Seance").getResultList();
		else
			seances = em.createQuery("from Seance where jourExamen.au>="+annee+" and jourExamen.au<="+annee1).getResultList();
		
		if(session.equals("Tous"))
		{
		for(Seance j : seances)
		{			
			heures.add(j.getHeureDebut());
		}
		}
		if(session.equals("CC"))
		{
			for(Seance j : seances)
			{	
				if(periodeExamen(j.getJourExamen().getDate()).equals("CC1") || periodeExamen(j.getJourExamen().getDate()).equals("CC2"))
					heures.add(j.getHeureDebut());
			}
		}
		if(session.equals("EX"))
		{
			for(Seance j : seances)
			{	
				if(periodeExamen(j.getJourExamen().getDate()).equals("EX1") || periodeExamen(j.getJourExamen().getDate()).equals("EX2"))
					heures.add(j.getHeureDebut());
			}
		}
		if(session.equals("RR"))
		{
			for(Seance j : seances)
			{	
				if(periodeExamen(j.getJourExamen().getDate()).equals("RR"))
					heures.add(j.getHeureDebut());
			}
		}
		
		
		
		
		
		List<Time> time = new ArrayList<Time>();
		time.addAll(heures);
		//Collections.sort(time);
		Collections.sort(time, new Comparator<Time>() {
	        @Override
	        public int compare(Time t1, Time t2)
	        {

	            return  t1.before(t2)?-1:1;
	        }
	    });
		
		return time;
	}
	@Override
	public Collection<String> getSessionByAnnee(int annee, int annee1) {
		// TODO Auto-generated method stub
		Set<String> session = new HashSet<String>();
		if(annee != 0){
		Collection<JourExamen> jours = em.createQuery("from JourExamen where au>="+annee+" and au<="+annee1+"").getResultList();
		
		for(JourExamen j : jours)
		{
			String x = periodeExamen(j.getDate());
			if(x.equals("CC1") || x.equals("CC2"))
				session.add("Controle Continu");
			if(x.equals("EX1") || x.equals("EX2"))
				session.add("Principale");
			if(x.equals("RR"))
				session.add("Rattrapage");
		}
		}else
		{
			Collection<JourExamen> jours = em.createQuery("from JourExamen where au>="+annee+" and au<="+annee1).getResultList();
			
			for(JourExamen j : jours)
			{
				String x = periodeExamen(j.getDate());
				if(x.equals("CC1") || x.equals("CC2"))
					session.add("Controle Continu");
				if(x.equals("EX1") || x.equals("EX2"))
					session.add("Principale");
				if(x.equals("RR"))
					session.add("Rattrapage");
			}
		}
		
		return session;
	}
	@Override
	public Collection<AffectationSeance> getAffectationByAnneeSession(
			int annee, int annee2, String session) {
		// TODO Auto-generated method stub
		Collection<AffectationSeance> affectationSeances = em.createQuery("from AffectationSeance").getResultList();
		Collection<AffectationSeance> affe = new ArrayList<AffectationSeance>();
		
		if(annee==0)
		{
		for(AffectationSeance af : affectationSeances)
		{	
			if(session.equals("Tous"))
				affe.add(af);
			else
			{
				if(session.equals("CC"))
				{
					if(periodeExamen(af.getDateJour()).equals("CC1")||periodeExamen(af.getDateJour()).equals("CC2"))
						affe.add(af);
				}
				if(session.equals("EX"))
				{
					if(periodeExamen(af.getDateJour()).equals("EX1")||periodeExamen(af.getDateJour()).equals("EX2"))
						affe.add(af);
				}
				if(session.equals("RR"))
				{
					if(periodeExamen(af.getDateJour()).equals("RR"))
						affe.add(af);
				}
			}
		
		}
		}else
		{
			for(AffectationSeance af : affectationSeances)
			{
				int m = af.getDateJour().getMonth()+1;
				int a = af.getDateJour().getYear()+1900;
				int au =0;
				if(9<=m && m<=12)
					au = a;
				if(1<=m && m<=6)
					au = a-1;
				if(au>=annee && au<=annee2){
				if(session.equals("Tous"))
					affe.add(af);
				else
				{
					if(session.equals("CC"))
					{
						if(periodeExamen(af.getDateJour()).equals("CC1")||periodeExamen(af.getDateJour()).equals("CC2"))
							affe.add(af);
					}
					if(session.equals("EX"))
					{
						if(periodeExamen(af.getDateJour()).equals("EX1")||periodeExamen(af.getDateJour()).equals("EX2"))
							affe.add(af);
					}
					if(session.equals("RR"))
					{
						if(periodeExamen(af.getDateJour()).equals("RR"))
							affe.add(af);
					}
				}
			}
			}
		}
		return affe;
	}
	@Override
	public Collection<JourExamen> getJourExamensByAnneeSession(int annee,
			int annee2, String session) {
		// TODO Auto-generated method stub
		Collection<JourExamen> JourExamen = new ArrayList<JourExamen>();
		
		if(annee == 0)
			JourExamen = em.createQuery("from JourExamen").getResultList();
		else
			JourExamen = em.createQuery("from JourExamen where au>="+annee+" and au<="+annee2+"").getResultList();
		
		Set<JourExamen> jours = new HashSet<JourExamen>();
		
		for(JourExamen af : JourExamen)
		{	
			if(session.equals("Tous"))
				jours.add(af);
			else
			{
				if(session.equals("CC"))
				{
					if(periodeExamen(af.getDate()).equals("CC1")||periodeExamen(af.getDate()).equals("CC2"))
						jours.add(af);
				}
				if(session.equals("EX"))
				{
					if(periodeExamen(af.getDate()).equals("EX1")||periodeExamen(af.getDate()).equals("EX2"))
						jours.add(af);
				}
				if(session.equals("RR"))
				{
					if(periodeExamen(af.getDate()).equals("RR"))
						jours.add(af);
				}
			}
		
		}
		
		List<JourExamen> time = new ArrayList<JourExamen>();
		time.addAll(jours);
		//Collections.sort(time);
		Collections.sort(time, new Comparator<JourExamen>() {
	        @Override
	        public int compare(JourExamen t1, JourExamen t2)
	        {

	            return  t1.getDate().before(t2.getDate())?-1:1;
	        }
	    });
		
		return time;
	}
	@Override
	public Collection<AffectationExamen> getAffectationExamenByAnneeSession(
			int annee, int annee2, String session) {
		// TODO Auto-generated method stub
		Collection<AffectationExamen> affectationExamens = em.createQuery("from AffectationExamen").getResultList();
		Collection<AffectationExamen> affe = new ArrayList<AffectationExamen>();
		
		if(annee==0)
		{
		for(AffectationExamen af : affectationExamens)
		{	
			if(session.equals("Tous"))
				affe.add(af);
			else
			{
				if(session.equals("CC"))
				{
					if(periodeExamen(af.getDateJour()).equals("CC1")||periodeExamen(af.getDateJour()).equals("CC2"))
						affe.add(af);
				}
				if(session.equals("EX"))
				{
					if(periodeExamen(af.getDateJour()).equals("EX1")||periodeExamen(af.getDateJour()).equals("EX2"))
						affe.add(af);
				}
				if(session.equals("RR"))
				{
					if(periodeExamen(af.getDateJour()).equals("RR"))
						affe.add(af);
				}
			}
		
		}
		}else
		{
			for(AffectationExamen af : affectationExamens)
			{
				int m = af.getDateJour().getMonth()+1;
				int a = af.getDateJour().getYear()+1900;
				int au =0;
				if(9<=m && m<=12)
					au = a;
				if(1<=m && m<=6)
					au = a-1;
				if(au>=annee && au <= annee2){
				if(session.equals("Tous"))
					affe.add(af);
				else
				{
					if(session.equals("CC"))
					{
						if(periodeExamen(af.getDateJour()).equals("CC1")||periodeExamen(af.getDateJour()).equals("CC2"))
							affe.add(af);
					}
					if(session.equals("EX"))
					{
						if(periodeExamen(af.getDateJour()).equals("EX1")||periodeExamen(af.getDateJour()).equals("EX2"))
							affe.add(af);
					}
					if(session.equals("RR"))
					{
						if(periodeExamen(af.getDateJour()).equals("RR"))
							affe.add(af);
					}
				}
			}
			}
		}
		
		
		return affe;
	}
	@Override
	public Collection<Alerte> getAlerteByAnneeSession(int annee, int annee2,
			String session) {
		// TODO Auto-generated method stub
		Collection<Alerte> alertes = em.createQuery("from Alerte").getResultList();
		Set<Alerte> als = new HashSet<Alerte>();
		if(annee==0)
		{
			for(Alerte al : alertes)
			{
				if(session.equals("Tous"))
				{
					als.add(al);
				}else
				{
				if(session.equals("CC"))
				{
					if(periodeExamen(al.getDate()).equals("CC1")||periodeExamen(al.getDate()).equals("CC2"))
						als.add(al);
				}
				if(session.equals("EX"))
				{
					if(periodeExamen(al.getDate()).equals("EX1")||periodeExamen(al.getDate()).equals("EX2"))
						als.add(al);
				}
				if(session.equals("RR"))
				{
					if(periodeExamen(al.getDate()).equals("RR"))
						als.add(al);
				}
				}
			}
		}else
		{
			for(Alerte al : alertes)
			{
				int m = al.getDate().getMonth()+1;
				int a = al.getDate().getYear()+1900;
				int au =0;
				if(9<=m && m<=12)
					au = a;
				if(1<=m && m<=6)
					au = a-1;
				if(au>=annee && au <= annee2)
				{
					if(session.equals("Tous"))
					{
						als.add(al);
					}else
					{
					if(session.equals("CC"))
					{
						if(periodeExamen(al.getDate()).equals("CC1")||periodeExamen(al.getDate()).equals("CC2"))
							als.add(al);
					}
					if(session.equals("EX"))
					{
						if(periodeExamen(al.getDate()).equals("EX1")||periodeExamen(al.getDate()).equals("EX2"))
							als.add(al);
					}
					if(session.equals("RR"))
					{
						if(periodeExamen(al.getDate()).equals("RR"))
							als.add(al);
					}
					}
				}
			}
		}
		
		return als;
	}
	@Override
	public void updateUtilisateurPass(String nom, String pass) {
		// TODO Auto-generated method stub
		
		em.createQuery("update Utilisateur set motDePasse='"+pass+"' where nom='"+nom+"'").executeUpdate();
		
	}
	
	
	@Override
	public Date getDateActuelle() {
		// TODO Auto-generated method stub
		
		// ************************* ************************* **************************
		
		DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();		
		Date dateJour=d.getTime();
		dateJour.setYear(dateJour.getYear()+1900);
		d.set(2016, 0,4, 9, 25);
		Date dateJour2 = d.getTime();
		
		
		return dateJour2;
	}

}
