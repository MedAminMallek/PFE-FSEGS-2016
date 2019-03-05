package org.fsegs.metier;

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
import java.util.Properties;
import java.util.Set;












































import org.fsegs.dao.ISurveillanceDAO;
import org.fsegs.dao.SurveillanceDAOImpl;


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
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class SurveillanceMetierImpl implements ISurveillanceMetier{

	private ISurveillanceDAO dao;
	

	public void setDao(ISurveillanceDAO dao) {
		this.dao = dao;
	}


	@Override
	public void addEnseignant(Enseignant e) {
		dao.addEnseignant(e);
		
	}


	@Override
	public Enseignant findEnseignant(Long id) {
		// TODO Auto-generated method stub
		return dao.findEnseignant(id);
	}


	@Override
	public void addSemstre(Semestre s) {
		dao.addSemstre(s);
		
	}


	@Override
	public void addSession(Session s) {
		// TODO Auto-generated method stub
		dao.addSession(s);
	}


	@Override
	public void addJourExamen(JourExamen j) {
		// TODO Auto-generated method stub
		dao.addJourExamen(j);
	}


	@Override
	public void addSeeance(Seance s) {
		// TODO Auto-generated method stub
		dao.addSeeance(s);
	}


	@Override
	public Semestre findSemstre(int i) {
		// TODO Auto-generated method stub
		return dao.findSemstre(i);
	}


	@Override
	public Session findSession(String i) {
		// TODO Auto-generated method stub
		return dao.findSession(i);
	}


	@Override
	public JourExamen findJourExamen() {
		// TODO Auto-generated method stub
		return dao.findJourExamen();
	}


	@Override
	public void addFiliére(Filiére f) {
		dao.addFiliére(f);
		
	}


	@Override
	public void addCycleAnnee(CycleAnnee c) {
		// TODO Auto-generated method stub
		dao.addCycleAnnee(c);
		
	}


	@Override
	public void addEnseignement(Enseignement e) {
		// TODO Auto-generated method stub
		dao.addEnseignement(e);
	}


	@Override
	public void addMatier(Matiere m) {
		// TODO Auto-generated method stub
		dao.addMatiere(m);
	}


	@Override
	public void addSalle(Salle s) {
		// TODO Auto-generated method stub
		dao.addSalle(s);
	}


	@Override
	public void addJourEnseignement(JourEnseignement j) {
		// TODO Auto-generated method stub
		dao.addJourEnseignement(j);
	}


	@Override
	public void addExamen(Examen e) {
		// TODO Auto-generated method stub
		dao.addExamen(e);
	}


	@Override
	public Collection<JourExamen> findAllJourExamen() {
		// TODO Auto-generated method stub
		return dao.findAllJourExamen();
	}


	@Override
	public Collection<Seance> findSeanceByDateJour(Date date) {
		// TODO Auto-generated method stub
		return dao.findSeanceByDateJour(date);
	}


	@Override
	public Collection<Examen> findExamens(Time heure,Date date) {
		// TODO Auto-generated method stub
		return dao.findExamens(heure,date);
	}


	@Override
	public Collection<Time> findHeureSeance() {
		// TODO Auto-generated method stub
		return dao.findHeureSeance();
	}


	@Override
	public Collection<Seance> findAllSeance() {
		// TODO Auto-generated method stub
		return dao.findAllSeance();
	}


	@Override
	public Collection<Examen> findAllExamen() {
		// TODO Auto-generated method stub
		return dao.findAllExamen();
	}


	@Override
	public Collection<MatiereEnseignement> findMatiereParSeance(Seance seance) {
		// TODO Auto-generated method stub
		return dao.findMatiereParSeance(seance);
	}



	
	@Override
	public int calculerNbSurveillantParSeance(Seance s) {
		// TODO Auto-generated method stub
		Collection<Examen> examens = dao.findExamens(s.getHeureDebut(), s.getJourExamen().getDate());
		int nombre=0;
		for(Examen e : examens)
			nombre+=e.getSalle().getNbSurveillant();
		double XX = nombre * 1.1;
		int a = (int) Math.round(XX);
		return a;
	}


	@Override
	public void updateNbSurvaillantParSeance(String date, String time, int nb) {

		dao.updateNbSurvaillantParSeance(date, time, nb);
		
	}


	@Override
	public Seance findSeanceByHeurDebut(String date, String heureDebut) {
		// TODO Auto-generated method stub
		return dao.findSeanceByHeurDebut(date, heureDebut);
	}


	@Override
	public int calculerNbMembreCommissionParSeance(Seance s) {
		Collection<Examen> examens = dao.findExamens(s.getHeureDebut(), s.getJourExamen().getDate());
		int nombre =examens.size()/10;
		if(nombre==0)
			nombre=1;
		return nombre;
	}


	@Override
	public void addMatiereEnseingement(MatiereEnseignement m) {
		// TODO Auto-generated method stub
		dao.addMatiereEnseingement(m);
		
	}


	@Override
	public void updateNbCommissionParSeance(String date, String time, int nb) {
		// TODO Auto-generated method stub
		dao.updateNbCommissionParSeance(date, time, nb);
		
	}


	/*@Override
	public Boolean besoinCalculer() {
		// TODO Auto-generated method stub
		return dao.besoinCalculer();
	}*/


	@Override
	public double calculeNbHeureSurveillant(Enseignant e) {
		// TODO Auto-generated method stub
		return dao.calculeNbHeureSurveillant(e);
	}


	@Override
	public double calculeChargeHoraireGlobaleDesSurveillants() {
		// TODO Auto-generated method stub
		return dao.calculeChargeHoraireGlobaleDesSurveillants();
	}


	@Override
	public Collection<Enseignant> findAllSurveillants() {
		// TODO Auto-generated method stub
		return dao.findAllSurveillants();
	}


	@Override
	public int chargeHoraireDeSurveillance(Enseignant e) {
		// TODO Auto-generated method stub
		double rapport =dao.calculeNbHeureSurveillant(e)/dao.calculeChargeHoraireGlobaleDesSurveillants();
		int xaz = 0 ;
		Collection<Seance> seancess = findAllSeance();
		for(Seance s : seancess)
		{
			xaz+=calculerNbSurveillantParSeance(s);
		}
		
		double nbseance = rapport * xaz;
		int a = (int) Math.round(nbseance);
		return a;
	}


	@Override
	public void updateChargeDeSurveillance(Long id, int nb) {
		// TODO Auto-generated method stub
		dao.updateChargeDeSurveillance(id, nb);
	}


	@Override
	public Enseignant findEnseignantParCIN(int Cin) {
		// TODO Auto-generated method stub
		return dao.findEnseignantParCIN(Cin);
	}


	@Override
	public void addAffectationSeance(Seance s, Enseignant e) {
		// TODO Auto-generated method stub
		dao.addAffectationSeance(s, e);
	}


	@Override
	public int nbAffectationParSeance(String d,String t) {
		// TODO Auto-generated method stub
		return dao.nbAffectationParSeance(d,t);
	}


	@Override
	public Collection<Seance> seanceLibre(String d) {
		// TODO Auto-generated method stub
		Collection<Seance> alls = (Collection<Seance>) dao.findSeanceByDateJour(d);
		Collection<Seance> seanceLibre = new ArrayList<Seance>();
		for(Seance s : alls)
		{
			if(s.getNombreSurveillant()>dao.nbAffectationParSeance(d, s.getHeureDebut().toString()))
			{
				seanceLibre.add(s);
			}
		}
		return seanceLibre;
	}


	@Override
	public int nbAffectationParSurveillant(Long id) {
		// TODO Auto-generated method stub
		return dao.nbAffectationParSurveillant(id);
	}


	@Override
	public Collection<Seance> seancesAffecterPourSur(Long id) {
		// TODO Auto-generated method stub
		return dao.seancesAffecterPourSur(id);
	}


	@Override
	public Collection<AffectationSeance> findAllAffectationSeances() {
		// TODO Auto-generated method stub
		return dao.findAllAffectationSeances();
	}


	@Override
	public Collection<Matiere> findAllMatiere() {
		// TODO Auto-generated method stub
		return dao.findAllMatiere();
	}


	@Override
	public boolean chargeCalculé() {
		// TODO Auto-generated method stub
		return dao.chargeCalculé();
	}


	@Override
	public Collection<MatiereEnseignement> findAllMatiereEns() {
		// TODO Auto-generated method stub
		return dao.findAllMatiereEns();
	}


	@Override
	public void updateRespMat(String mat, String fil, String cyc) {
		// TODO Auto-generated method stub
		dao.updateRespMat(mat, fil, cyc);
		
	}


	@Override
	public boolean IsResponsable(Long id) {
		// TODO Auto-generated method stub
		return dao.IsResponsable(id);
	}


	@Override
	public void supprimerAffectationSeance(String id, String date, String heure) {
		// TODO Auto-generated method stub
		dao.supprimerAffectationSeance(id, date, heure);
		
	}


	@Override
	public Collection<Seance> findSeancesSaturé() {
		// TODO Auto-generated method stub
		Collection<Seance> seances = findAllSeance();
		Collection<Seance> seancesSaturé = new ArrayList<Seance>();
		for(Seance s : seances)
		{
			String date[] = s.getJourExamen().getDate().toString().split(" ");
			if(s.getNombreSurveillant()<= nbAffectationParSeance(date[0],s.getHeureDebut().toString()))
			{
				seancesSaturé.add(s);
			}
		}
		return seancesSaturé;
	}


	@Override
	public Collection<AffectationSeance> findAffectationSeance(String date,
			String heure) {
		// TODO Auto-generated method stub
		return dao.findAffectationSeance(date, heure);
	}


	@Override
	public boolean verifierAffectation(String date, String heure, String id) {
		// TODO Auto-generated method stub
		return dao.verifierAffectation(date, heure, id);
	}


	@Override
	public void addDemandeEchangeS(DemandeEchangeS d) {
		// TODO Auto-generated method stub
		dao.addDemandeEchangeS(d);
	}


	@Override
	public boolean findDemande(Date date, Time heure, Long id) {
		// TODO Auto-generated method stub
		return dao.findDemande(date, heure, id);
	}


	@Override
	public Collection<DemandeEchangeS> findDemandeEnvParId(Long id) {
		// TODO Auto-generated method stub
		return dao.findDemandeEnvParId(id);
	}


	@Override
	public void supprimerDemandeEchange(String id, String dateP, String heureP) {
		// TODO Auto-generated method stub
		dao.supprimerDemandeEchange(id, dateP, heureP);
		
	}


	@Override
	public Collection<DemandeEchangeS> findDemandeRecParId(Long id) {
		// TODO Auto-generated method stub
		return dao.findDemandeRecParId(id);
	}


	@Override
	public void updateDemande(String emetteur,String recepteur,String datP,String heureP,String datD,String heureD,String etat) {
		// TODO Auto-generated method stub
		dao.updateDemande(emetteur, recepteur, datP, heureP, datD, heureD, etat);
		
		
	}


	@Override
	public AffectationSeance findAffectation(String id, String date,
			String heure) {
		// TODO Auto-generated method stub
		return dao.findAffectation(id, date, heure);
	}


	@Override
	public void updateIdAffectationSeance(String id,String date , String heure , Long nvid) {
		// TODO Auto-generated method stub
		dao.updateIdAffectationSeance(id,date ,heure ,nvid);
		
	}


	@Override
	public Collection<AffectationSeance> findAffectationById(Long id) {
		// TODO Auto-generated method stub
		return dao.findAffectationById(id);
	}


	@Override
	public void supprimerAffectationSeance(Long id) {
		// TODO Auto-generated method stub
		dao.supprimerAffectationSeance(id);
		
		
	}


	@Override
	public void supprimerDemandeEchangeD(String id, String dateD, String heureD) {
		// TODO Auto-generated method stub
		dao.supprimerDemandeEchangeD(id, dateD, heureD);
		
	}


	@Override
	public void supprimerDemande(Long id) {
		// TODO Auto-generated method stub
		Collection<AffectationSeance> affectations =findAffectationById(id);
		Collection<DemandeEchangeS> demandesEnv = findDemandeEnvParId(id);
		Collection<DemandeEchangeS> demandesRec = findDemandeRecParId(id);
		for(DemandeEchangeS dE : demandesEnv)
		{
			boolean existeEnv = false;
			for(AffectationSeance a : affectations)
			{	
				String d[] = a.getDateJour().toString().split(" ");
			
				
			
					if(dE.getDateJourP2().equals(d[0]) && dE.getHeureDebutP().toString().equals(a.getHeureDebut().toString()))
					{
						existeEnv=true;
					}
					
			
			
			
			}
			if(existeEnv==false){
				System.out.println(dE.getDateJourP2()+" "+dE.getheureDebutP2()+" a supprimer");
				supprimerDemandeEchange(String.valueOf(id), dE.getDateJourP2(), dE.getheureDebutP2());
			}
		}
		
		for(DemandeEchangeS dE : demandesRec)
		{
			boolean existeEnv = false;
			for(AffectationSeance a : affectations)
			{	
				String d[] = a.getDateJour().toString().split(" ");
			
				
			
					if(dE.getDateJourD2().equals(d[0]) && dE.getHeureDebutD().toString().equals(a.getHeureDebut().toString()))
					{
						existeEnv=true;
					}
					
			
			
			
			}
			if(existeEnv==false){
				
				supprimerDemandeEchangeD(String.valueOf(id), dE.getDateJourD2(), dE.getheureDebutD2());
			}
		}
		for(DemandeEchangeS dE : demandesRec)
		{
			boolean existeEnv = true;
			for(AffectationSeance a : affectations)
			{	
				String d[] = a.getDateJour().toString().split(" ");
			
				
			
					if(dE.getDateJourP2().equals(d[0]) && dE.getHeureDebutP().toString().equals(a.getHeureDebut().toString()))
					{
						existeEnv=false;
					}

			}
			if(existeEnv==false){
				supprimerDemandeEchangeD(String.valueOf(id), dE.getDateJourD2(), dE.getheureDebutD2());
			}
		}
		
	}


	@Override
	public Collection<Enseignement> findJourEnseignement(Long id) {
		// TODO Auto-generated method stub
		return dao.findJourEnseignement(id);
	}


	@Override
	public boolean besoinCalcule() {
		// TODO Auto-generated method stub
		return dao.besoinCalcule();
	}


	@Override
	public boolean isCC() {
		// TODO Auto-generated method stub
		return dao.isCC();
	}


	@Override
	public boolean isEnseignantMaster(Long id) {
		// TODO Auto-generated method stub
		return dao.isEnseignantMaster(id);
	}


	@Override
	public double nbHeureMastere(Long id) {
		// TODO Auto-generated method stub
		return dao.nbHeureMastere(id);
	}


	@Override
	public Collection<Examen> findExamenBySeance(Seance s) {
		// TODO Auto-generated method stub
		return dao.findExamenBySeance(s);
	}


	@Override
	public Collection<Enseignant> findSurveillantsParSéance(Seance s) {
		// TODO Auto-generated method stub
		
		return dao.findSurveillantsParSéance(s);
	}


	@Override
	public void addAffectationSalle(AffectationExamen a) {
		// TODO Auto-generated method stub
		dao.addAffectationSalle(a);
		
	}


	@Override
	public Salle findSalle(String id) {
		// TODO Auto-generated method stub
		return dao.findSalle(id);
	}


	@Override
	public Filiére findFiliére(String id) {
		// TODO Auto-generated method stub
		return dao.findFiliére(id);
	}


	@Override
	public CycleAnnee findCycleAnnee(int id) {
		// TODO Auto-generated method stub
		return dao.findCycleAnnee(id);
	}


	@Override
	public Matiere findMatiere(Long id) {
		// TODO Auto-generated method stub
		return dao.findMatiere(id);
	}


	@Override
	public Enseignant findEnseignant(String nom) {
		// TODO Auto-generated method stub
		return dao.findEnseignant(nom);
	}


	@Override
	public boolean enseignantAffectéSalle(Enseignant e, String date, String heure) {
		// TODO Auto-generated method stub
		return dao.enseignantAffectéSalle(e, date, heure);
	}


	@Override
	public boolean enseignantAffectéSalle(Enseignant e, String date,
			String heure, Salle s) {
		// TODO Auto-generated method stub
		return dao.enseignantAffectéSalle(e, date, heure, s);
	}


	@Override
	public Long nbAffectationSalle(String date, String heure, Salle s) {
		// TODO Auto-generated method stub
		return dao.nbAffectationSalle(date, heure, s);
	}


	@Override
	public Collection<AffectationExamen> findAffectationExamen(String date,
			String heure) {
		// TODO Auto-generated method stub
		return dao.findAffectationExamen(date, heure);
	}


	@Override
	public void supprimerAffectationExamen(String date, String heure, Long id) {
		// TODO Auto-generated method stub
		dao.supprimerAffectationExamen(date, heure, id);
		
	}


	@Override
	public Collection<Seance> findSeanceByDateJour(String date) {
		// TODO Auto-generated method stub
		return dao.findSeanceByDateJour(date);
	}


	@Override
	public boolean findAffectation(AffectationSeance a) {
		// TODO Auto-generated method stub
		return dao.findAffectation(a);
	}


	@Override
	public Collection<MatiereEnseignement> findMatiereEnseigner(Long id) {
		// TODO Auto-generated method stub
		return dao.findMatiereEnseigner(id);
	}


	@Override
	public boolean RespAffecté() {
		// TODO Auto-generated method stub
		return dao.RespAffecté();
	}


	@Override
	public Long nbAffectationParSeanceAuxSalle(String date, String heure) {
		// TODO Auto-generated method stub
		return dao.nbAffectationParSeanceAuxSalle(date, heure);
	}


	@Override
	public Collection<Enseignant> findAllEnseignantActif() {
		// TODO Auto-generated method stub
		return dao.findAllEnseignantActif();
	}


	@Override
	public void addRemplacement(Remplacement r) {
		// TODO Auto-generated method stub
		dao.addRemplacement(r);
	}


	@Override
	public Remplacement findRemplacement(String date, String heure,
			Enseignant ens) {
		// TODO Auto-generated method stub
		return dao.findRemplacement(date, heure, ens);
	}


	@Override
	public boolean existeRemp(String date, String heure, Enseignant ens) {
		// TODO Auto-generated method stub
		return dao.existeRemp(date, heure, ens);
	}


	@Override
	public void supprimerRemplacement(String date, String heure, Enseignant ens) {
		// TODO Auto-generated method stub
		dao.supprimerRemplacement(date, heure, ens);
		
	}


	@Override
	public String typeExamen() {
		// TODO Auto-generated method stub
		return dao.typeExamen();
	}


	@Override
	public Collection<Enseignement> getCalendrierEnseignement(Long id) {
		// TODO Auto-generated method stub
		return dao.getCalendrierEnseignement(id);
	}


	@Override
	public void updateAbsence(AffectationSeance aff, boolean p) {
		// TODO Auto-generated method stub
		dao.updateAbsence(aff, p);
		
	}


	@Override
	public AffectationExamen findAffectationExamen(String date, String heure,
			String id) {
		// TODO Auto-generated method stub
		return dao.findAffectationExamen(date, heure, id);
	}


	@Override
	public Collection<AffectationSeance> findSurveillantPourAbsence(String date,String heure) {
		// TODO Auto-generated method stub
		return dao.findSurveillantPourAbsence(date,heure);
	}


	@Override
	public Collection<Enseignant> getListeSurveillantTriParCharge() {
		// TODO Auto-generated method stub
		return dao.getListeSurveillantTriParCharge();
	}


	@Override
	public List<String> getClandrierEnseignant(Long id) {
		// TODO Auto-generated method stub
		Set<String> emp = new HashSet<String>();
		Collection<Time> heure = findHeureSeance();
		Collection<Enseignement> ens = getCalendrierEnseignement(id);
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
						emp.add(e.getJour().getId()+"X"+XX+"X"+type);
						i=1;
						break;
					}
				}
				if(i==0)
				emp.add(e.getJour().getId()+"X"+ttt+"X"+type);
			}
			else
				emp.add(e.getJour().getId()+"X"+TX+"X"+type);
		}
		
		List<String> liste = new ArrayList<String>();
		liste.addAll(emp);
		Collections.sort(liste,
                new Comparator<String>()
                {
                    public int compare(String f1, String f2)
                    {
                        return f1.toString().compareTo(f2.toString());
                    }        
                });
		
		return liste;
	}


	@Override
	public void premiereAffectationExamen() {
		// TODO Auto-generated method stub
		 Collection<Enseignant> enseignants = getListeSurveillantTriParCharge();
		 Collection<Enseignant> sur = new ArrayList<Enseignant>();
		 int nb=0;
		 for(Enseignant e : enseignants )
		 {
			 int x = nbAffectationParSurveillant(e.getId());
			 if(e.getaSruveiller()-x>0){
			 sur.add(e);
			 }
		 }
		Collection<Seance> allSeances = findAllSeance();
		Collection<Seance> seanceLibre = new ArrayList<Seance>();
		for(Seance s : allSeances)
		{
			String date[] = s.getJourExamen().getDate().toString().split(" ");
			if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
			{
				seanceLibre.add(s);
			}
		}
		int x=0;
		//Enseignant e = metier.findEnseignant(847l);
		for(Enseignant e : sur)
		{
			x++;
			Collection<Enseignement> calEns = getCalendrierEnseignement(e.getId());
			if(x==50){
			seanceLibre = new ArrayList<Seance>();
			for(Seance s : allSeances)
			{
				String date[] = s.getJourExamen().getDate().toString().split(" ");
				if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
				{
					seanceLibre.add(s);
				}
			}
			x=0;
			}
			int nbAffectation = nbAffectationParSurveillant(e.getId());
			while(e.getaSruveiller()-nbAffectation>0){
				int testeNbAff = nbAffectation;
			for(String ens : getClandrierEnseignant(e.getId()))
			{
				for(Seance s : seanceLibre)
				{
					
					boolean seanceEncoreLibre = s.getNombreSurveillant()>nbAffectationParSeance(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString());
					String[] tab = ens.split("X");
					boolean seanceVolu = s.getJourExamen().getDate().getDay()-Integer.parseInt(tab[0])==0 && s.getHeureDebut().toString().equals(tab[1]);
					boolean sortir =false;
					if(seanceEncoreLibre && seanceVolu && !verifierAffectation(tab[0], tab[1], e.getId()+""))
					{
						if(!verifierAffectation(s.getJourExamen().getDate().toString().split(" ")[0], tab[1], String.valueOf(e.getId())))
						{
							addAffectationSeance(s, e);
							nbAffectation++;
							sortir=true;
							break;
						}
						
					}
					if(sortir||e.getaSruveiller()-nbAffectation==0)
						break;
					
				}
				if(e.getaSruveiller()-nbAffectation==0)
					break;
			}
			if(testeNbAff-nbAffectation==0)
			break;
		}
		}
		
	}


	@Override
	public void deuxiemeAffectationExamen() {
		// TODO Auto-generated method stub
		Collection<Enseignant> enseignants = getListeSurveillantTriParCharge();
		 Collection<Enseignant> sur = new ArrayList<Enseignant>();
		 int nb=0;
		 for(Enseignant e : enseignants )
		 {
			 int x = nbAffectationParSurveillant(e.getId());
			 if(e.getaSruveiller()-x>0){
			 sur.add(e);
			 }
		 }
		Collection<Seance> allSeances = findAllSeance();
		Collection<Seance> seanceLibre = new ArrayList<Seance>();
		for(Seance s : allSeances)
		{
			String date[] = s.getJourExamen().getDate().toString().split(" ");
			if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
			{
				seanceLibre.add(s);
			}
		}
		int x=0;
		//Enseignant e = metier.findEnseignant(847l);
		for(Enseignant e : sur)
		{
			x++;
			Collection<Enseignement> calEns = getCalendrierEnseignement(e.getId());
			if(x==50){
			seanceLibre = new ArrayList<Seance>();
			for(Seance s : allSeances)
			{
				String date[] = s.getJourExamen().getDate().toString().split(" ");
				if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
				{
					seanceLibre.add(s);
				}
			}
			x=0;
			}
			int nbAffectation = nbAffectationParSurveillant(e.getId());
			while(e.getaSruveiller()-nbAffectation>0){
				int testeNbAff = nbAffectation;
			for(String ens : getClandrierEnseignant(e.getId()))
			{
				for(Seance s : seanceLibre)
				{
					
					boolean seanceEncoreLibre = s.getNombreSurveillant()>nbAffectationParSeance(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString());
					String[] tab = ens.split("X");
					boolean seanceVolu = s.getJourExamen().getDate().getDay()-Integer.parseInt(tab[0])==0;
					System.out.println(seanceEncoreLibre && seanceVolu);
					boolean sortir =false;
					if(seanceEncoreLibre && seanceVolu && !verifierAffectation(tab[0], tab[1], e.getId()+""))
					{
						if(!verifierAffectation(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString(), String.valueOf(e.getId())))
						{
							addAffectationSeance(s, e);
							nbAffectation++;
							sortir=true;
							break;
						}
						
					}
					if(sortir||e.getaSruveiller()-nbAffectation==0)
						break;
					
				}
				if(e.getaSruveiller()-nbAffectation==0)
					break;
			}
			if(testeNbAff-nbAffectation==0)
			break;
		}
		}
		
	}


	@Override
	public void troisiemeAffectationExamen() {
		// TODO Auto-generated method stub
		Collection<Enseignant> enseignants = getListeSurveillantTriParCharge();
		Collection<Enseignant> sur = new ArrayList<Enseignant>();
		 for(Enseignant e : enseignants )
		 {
			 int x = nbAffectationParSurveillant(e.getId());
			 if(e.getaSruveiller()-x>0){
			 sur.add(e);
			 }
		 }
		 	Collection<Seance> allSeances = findAllSeance();
			Collection<Seance> seanceLibre = new ArrayList<Seance>();
			for(Seance s : allSeances)
			{
				String date[] = s.getJourExamen().getDate().toString().split(" ");
				if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
				{
					seanceLibre.add(s);
				}
			}
			
			for(Enseignant e : sur)
			{
				int nbAffectation = nbAffectationParSurveillant(e.getId());
				while(e.getaSruveiller()-nbAffectation>0)
				{
				int testeNbAff = nbAffectation;
				for(Seance s : seanceLibre)
				{	
					boolean seanceEncoreLibre = s.getNombreSurveillant()>nbAffectationParSeance(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString());
				
					if(seanceEncoreLibre && !verifierAffectation(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString(), String.valueOf(e.getId())))
					{
						addAffectationSeance(s, e);
						nbAffectation++;
					}
				}
					if(testeNbAff-nbAffectation==0)
						break;
				}
			}
		
		
	}


	@Override
	public void premiereAffectationCC() {
		// TODO Auto-generated method stub
		Collection<Enseignant> enseignants = getListeSurveillantTriParCharge();
		 Collection<Enseignant> sur = new ArrayList<Enseignant>();
		 int nb=0;
		 for(Enseignant e : enseignants )
		 {
			 int x = nbAffectationParSurveillant(e.getId());
			 if(e.getaSruveiller()-x>0){
			 sur.add(e);
			 }
		 }
		Collection<Seance> allSeances = findAllSeance();
		Collection<Seance> seanceLibre = new ArrayList<Seance>();
		for(Seance s : allSeances)
		{
			String date[] = s.getJourExamen().getDate().toString().split(" ");
			if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
			{
				seanceLibre.add(s);
			}
		}
		int x=0;
		//Enseignant e = metier.findEnseignant(847l);
		for(Enseignant e : sur)
		{
			x++;
			Collection<Enseignement> calEns = getCalendrierEnseignement(e.getId());
			if(x==50){
			seanceLibre = new ArrayList<Seance>();
			for(Seance s : allSeances)
			{
				String date[] = s.getJourExamen().getDate().toString().split(" ");
				if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
				{
					seanceLibre.add(s);
				}
			}
			x=0;
			}
			int nbAffectation = nbAffectationParSurveillant(e.getId());
			
			Set<String> jourMastere = new HashSet<String>();
			for(String jou : getClandrierEnseignant(e.getId()))
			{
				if(jou.split("X")[2].equals("M"))
					jourMastere.add(jou.split("X")[0]);
			}
			
			while(e.getaSruveiller()-nbAffectation>0){
				int testeNbAff = nbAffectation;
			for(String ens : getClandrierEnseignant(e.getId()))
			{
				for(Seance s : seanceLibre)
				{
					
					boolean seanceEncoreLibre = s.getNombreSurveillant()>nbAffectationParSeance(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString());
					String[] tab = ens.split("X");
					
					boolean seanceVolu = s.getJourExamen().getDate().getDay()-Integer.parseInt(tab[0])==0 && s.getHeureDebut().toString().equals(tab[1]) && !jourMastere.contains(String.valueOf(s.getJourExamen().getDate().getDay()));
					boolean sortir =false;
					if(seanceEncoreLibre && seanceVolu && !verifierAffectation(tab[0], tab[1], e.getId()+""))
					{
						if(!verifierAffectation(s.getJourExamen().getDate().toString().split(" ")[0], tab[1], String.valueOf(e.getId())))
						{
							addAffectationSeance(s, e);
							nbAffectation++;
							sortir=true;
							break;
						}
						
					}
					if(sortir||e.getaSruveiller()-nbAffectation==0)
						break;
					
				}
				if(e.getaSruveiller()-nbAffectation==0)
					break;
			}
			if(testeNbAff-nbAffectation==0)
			break;
		}
		}
	}


	@Override
	public void deuxiemeAffectationCC() {
		// TODO Auto-generated method stub
		Collection<Enseignant> enseignants = getListeSurveillantTriParCharge();
		 Collection<Enseignant> sur = new ArrayList<Enseignant>();
		 int nb=0;
		 for(Enseignant e : enseignants )
		 {
			 int x = nbAffectationParSurveillant(e.getId());
			 if(e.getaSruveiller()-x>0){
			 sur.add(e);
			 }
		 }
		Collection<Seance> allSeances = findAllSeance();
		Collection<Seance> seanceLibre = new ArrayList<Seance>();
		for(Seance s : allSeances)
		{
			String date[] = s.getJourExamen().getDate().toString().split(" ");
			if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
			{
				seanceLibre.add(s);
			}
		}
		int x=0;
		//Enseignant e = metier.findEnseignant(847l);
		for(Enseignant e : sur)
		{
			x++;
			Collection<Enseignement> calEns = getCalendrierEnseignement(e.getId());
			if(x==50){
			seanceLibre = new ArrayList<Seance>();
			for(Seance s : allSeances)
			{
				String date[] = s.getJourExamen().getDate().toString().split(" ");
				if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
				{
					seanceLibre.add(s);
				}
			}
			x=0;
			}
			int nbAffectation = nbAffectationParSurveillant(e.getId());
			
			Set<String> jourMastere = new HashSet<String>();
			for(String jou : getClandrierEnseignant(e.getId()))
			{
				if(jou.split("X")[2].equals("M"))
					jourMastere.add(jou.split("X")[0]);
			}
			
			while(e.getaSruveiller()-nbAffectation>0){
				int testeNbAff = nbAffectation;
			for(String ens : getClandrierEnseignant(e.getId()))
			{
				for(Seance s : seanceLibre)
				{
					
					boolean seanceEncoreLibre = s.getNombreSurveillant()>nbAffectationParSeance(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString());
					String[] tab = ens.split("X");
					
					boolean seanceVolu = s.getJourExamen().getDate().getDay()-Integer.parseInt(tab[0])==0  && !jourMastere.contains(String.valueOf(s.getJourExamen().getDate().getDay()));
					boolean sortir =false;
					if(seanceEncoreLibre && seanceVolu && !verifierAffectation(tab[0], tab[1], e.getId()+""))
					{
						if(!verifierAffectation(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString(), String.valueOf(e.getId())))
						{
							addAffectationSeance(s, e);
							nbAffectation++;
							sortir=true;
							break;
						}
						
					}
					if(sortir||e.getaSruveiller()-nbAffectation==0)
						break;
					
				}
				if(e.getaSruveiller()-nbAffectation==0)
					break;
			}
			if(testeNbAff-nbAffectation==0)
			break;
		}
		}
		
		
	}


	@Override
	public void troisiemeAffectationCC() {
		// TODO Auto-generated method stub
		Collection<Enseignant> enseignants = getListeSurveillantTriParCharge();
		Collection<Enseignant> sur = new ArrayList<Enseignant>();
		 for(Enseignant e : enseignants )
		 {
			 int x = nbAffectationParSurveillant(e.getId());
			 if(e.getaSruveiller()-x>0){
			 sur.add(e);
			 }
		 }
		 	Collection<Seance> allSeances = findAllSeance();
			Collection<Seance> seanceLibre = new ArrayList<Seance>();
			for(Seance s : allSeances)
			{
				String date[] = s.getJourExamen().getDate().toString().split(" ");
				if(s.getNombreSurveillant()>nbAffectationParSeance(date[0], s.getHeureDebut().toString()))
				{
					seanceLibre.add(s);
				}
			}
			
			for(Enseignant e : sur)
			{
				int nbAffectation = nbAffectationParSurveillant(e.getId());
				while(e.getaSruveiller()-nbAffectation>0)
				{
				int testeNbAff = nbAffectation;
				for(Seance s : seanceLibre)
				{
					boolean seanceEncoreLibre = s.getNombreSurveillant()>nbAffectationParSeance(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString());
					
					Set<String> jourMastere = new HashSet<String>();
					for(String jou : getClandrierEnseignant(e.getId()))
					{
						if(jou.split("X")[2].equals("M"))
							jourMastere.add(jou.split("X")[0]);
					}
					boolean seanceVolu = !jourMastere.contains(String.valueOf(s.getJourExamen().getDate().getDay()));
					
					if(seanceVolu && seanceEncoreLibre && !verifierAffectation(s.getJourExamen().getDate().toString().split(" ")[0], s.getHeureDebut().toString(), String.valueOf(e.getId())))
					{
						addAffectationSeance(s, e);
						nbAffectation++;
					}
				}
					if(testeNbAff-nbAffectation==0)
						break;
				}
			}
	}


	@Override
	public Collection<MatiereEnseignement> getMatiereEnseignementsByEnseignant(
			Enseignant e) {
		// TODO Auto-generated method stub
		return dao.getMatiereEnseignementsByEnseignant(e);
	}


	@Override
	public Collection<Seance> findSeancePourMatiere(MatiereEnseignement m) {
		// TODO Auto-generated method stub
		
		return dao.findSeancePourMatiere(m);
	}


	@Override
	public void addTypeAlerte(TypeAlerte type) {
		// TODO Auto-generated method stub
		dao.addTypeAlerte(type);
		
	}


	@Override
	public Collection<TypeAlerte> getTypesAlertes() {
		// TODO Auto-generated method stub
		return dao.getTypesAlertes();
	}


	@Override
	public boolean enSeance(String date, String heure, Long id) {
		// TODO Auto-generated method stub
		return dao.enSeance(date, heure, id);
	}


	@Override
	public Examen findExamenParAlerte(Long id, String heure, String date) {
		// TODO Auto-generated method stub
		return dao.findExamenParAlerte(id, heure, date);
	}


	@Override
	public TypeAlerte findTypeAlerte(Long id) {
		// TODO Auto-generated method stub
		return dao.findTypeAlerte(id);
	}


	@Override
	public void addAlerte(Alerte alerte) {
		// TODO Auto-generated method stub
		dao.addAlerte(alerte);
	}


	@Override
	public Collection<Alerte> findAlertesBySeance(Seance seance) {
		// TODO Auto-generated method stub
		return dao.findAlertesBySeance(seance);
	}


	@Override
	public Collection<Enseignant> findEnseignantsParMatiere(
			MatiereEnseignement matiere) {
		// TODO Auto-generated method stub
		return dao.findEnseignantsParMatiere(matiere);
	}


	@Override
	public Alerte findAlerte(Long id) {
		// TODO Auto-generated method stub
		return dao.findAlerte(id);
	}


	@Override
	public String periodeExamen(Date date) {
		// TODO Auto-generated method stub
		return dao.periodeExamen(date);
	}


	@Override
	public Collection<AffectationExamen> findAllAffectationExamen() {
		// TODO Auto-generated method stub
		return dao.findAllAffectationExamen();
	}


	@Override
	public Collection<TypeAlerte> findAllTypesAlertes() {
		// TODO Auto-generated method stub
		return dao.findAllTypesAlertes();
	}


	@Override
	public Collection<Alerte> findAllAlertes() {
		// TODO Auto-generated method stub
		return dao.findAllAlertes();
	}


	@Override
	public Date getDateActuelle() {
		// TODO Auto-generated method stub
		
		return dao.getDateActuelle();
	}


	@Override
	public int nbExamenSalle(String date, String heure, Salle salle) {
		// TODO Auto-generated method stub
		return dao.nbExamenSalle(date, heure, salle);
	}


	@Override
	public Long findTypeByLibelle(String libelle) {
		// TODO Auto-generated method stub
		return dao.findTypeByLibelle(libelle);
	}


	@Override
	public boolean supprimerTypeAlerte(TypeAlerte type) {
		// TODO Auto-generated method stub
		return dao.supprimerTypeAlerte(type);
	}


	@Override
	public Collection<Examen> findExamensByMatiere(MatiereEnseignement mat) {
		// TODO Auto-generated method stub
		return dao.findExamensByMatiere(mat);
	}


	@Override
	public boolean existeExamenPourMatiere(MatiereEnseignement mat) {
		// TODO Auto-generated method stub
		return dao.existeExamenPourMatiere(mat);
	}


	@Override
	public Collection<String> getAnnéeUniversitaire() {
		// TODO Auto-generated method stub
		return dao.getAnnéeUniversitaire();
	}


	@Override
	public Collection<String> getSessionByAnnee(int annee) {
		// TODO Auto-generated method stub
		return dao.getSessionByAnnee(annee);
	}


	@Override
	public Collection<Time> getHeureByAnneeSession(int annee, String session) {
		// TODO Auto-generated method stub
		return dao.getHeureByAnneeSession(annee, session);
	}


	@Override
	public Collection<AffectationSeance> getAffectationByAnneeSession(
			int annee, String session) {
		// TODO Auto-generated method stub
		return dao.getAffectationByAnneeSession(annee, session);
	}


	@Override
	public Collection<JourExamen> getJourExamensByAnneeSession(int annee,
			String session) {
		// TODO Auto-generated method stub
		return dao.getJourExamensByAnneeSession(annee, session);
	}


	@Override
	public Collection<AffectationExamen> getAffectationExamenByAnneeSession(
			int annee, String session) {
		// TODO Auto-generated method stub
		return dao.getAffectationExamenByAnneeSession(annee, session);
	}


	@Override
	public Collection<Alerte> getAlerteByAnneeSession(int annee, String session) {
		// TODO Auto-generated method stub
		return dao.getAlerteByAnneeSession(annee, session);
	}


	@Override
	public void addUtilisateur(Utilisateur u) {
		// TODO Auto-generated method stub
		dao.addUtilisateur(u);
	}


	@Override
	public boolean FindUtilisateur(Utilisateur u) {
		// TODO Auto-generated method stub
		return dao.FindUtilisateur(u);
	}


	@Override
	public Collection<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return dao.getAllRoles();
	}


	@Override
	public Role findRole(Long id) {
		// TODO Auto-generated method stub
		return dao.findRole(id);
	}


	@Override
	public Collection<Utilisateur> findAllUtilisateurs() {
		// TODO Auto-generated method stub
		return dao.findAllUtilisateurs();
	}


	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		dao.supprimerUtilisateur(utilisateur);
	}


	@Override
	public Utilisateur findUser(String nom, String pass) {
		// TODO Auto-generated method stub
		return dao.findUser(nom, pass);
	}


	@Override
	public Enseignant loginEnseignant(String nom, String pass) {
		// TODO Auto-generated method stub
		return dao.loginEnseignant(nom, pass);
	}


	@Override
	public boolean nomUtilisateurExiste(String nom,Long id) {
		// TODO Auto-generated method stub
		return dao.nomUtilisateurExiste(nom,id);
	}


	@Override
	public void updateParamCnx(String nom, String pass,String cin) {
		// TODO Auto-generated method stub
		dao.updateParamCnx(nom, pass,cin);
		
	}


	@Override
	public Enseignant findEnseignantParMail(String mail) {
		// TODO Auto-generated method stub
		return dao.findEnseignantParMail(mail);
	}


	@Override
	public void reglerCin(Long id,String cin) {
		// TODO Auto-generated method stub
		dao.reglerCin(id,cin);
		
	}


	@Override
	public Collection<JourExamen> findAllJourExamen(String session, int AU) {
		// TODO Auto-generated method stub
		return dao.findAllJourExamen(session, AU);
	}


	@Override
	public Collection<AffectationSeance> findAffectationById(Long id,
			String type, int au) {
		// TODO Auto-generated method stub
		return dao.findAffectationById(id, type, au);
	}


	@Override
	public Collection<Time> getHeureByAnneeSession(int annee, int annee1,
			String session) {
		// TODO Auto-generated method stub
		return dao.getHeureByAnneeSession(annee, annee1, session);
	}


	@Override
	public Collection<String> getSessionByAnnee(int annee, int annee1) {
		// TODO Auto-generated method stub
		return dao.getSessionByAnnee(annee, annee1);
	}


	@Override
	public Collection<AffectationSeance> getAffectationByAnneeSession(
			int annee, int annee2, String session) {
		// TODO Auto-generated method stub
		return dao.getAffectationByAnneeSession(annee, annee2, session);
	}


	@Override
	public Collection<JourExamen> getJourExamensByAnneeSession(int annee,
			int annee2, String session) {
		// TODO Auto-generated method stub
		return dao.getJourExamensByAnneeSession(annee, annee2, session);
	}


	@Override
	public Collection<AffectationExamen> getAffectationExamenByAnneeSession(
			int annee, int annee2, String session) {
		// TODO Auto-generated method stub
		return dao.getAffectationExamenByAnneeSession(annee, annee2, session);
	}


	@Override
	public Collection<Alerte> getAlerteByAnneeSession(int annee, int annee2,
			String session) {
		// TODO Auto-generated method stub
		return dao.getAlerteByAnneeSession(annee, annee2, session);
	}


	@Override
	public void updateUtilisateurPass(String nom, String pass) {
		// TODO Auto-generated method stub
		dao.updateUtilisateurPass(nom, pass);
	}


	
	



}
