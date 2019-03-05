package org.fsegs.dao;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;

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
import org.springframework.dao.DeadlockLoserDataAccessException;

public interface ISurveillanceDAO {
	
	
	/*  AffectationExamenDAO  */ 
	public void addAffectationSalle(AffectationExamen a);
	public Collection<AffectationExamen> findAllAffectationExamen();
	public AffectationExamen findAffectationExamen(String date,String heure,String id);
	public Collection<AffectationExamen> findAffectationExamen(String date , String heure);
	public Collection<AffectationExamen> getAffectationExamenByAnneeSession(int annee,String session);
	
	/* AffectationSéanceDAO */
	public void addAffectationSeance(Seance s,Enseignant e);
	public void updateAbsence(AffectationSeance aff,boolean p);
	public AffectationSeance findAffectation(String id,String date , String heure);
	public Collection<AffectationSeance> findAffectationSeance(String date,String heure);
	public Collection<AffectationSeance> findSurveillantPourAbsence(String date , String heure);
	public Collection<AffectationSeance> findAffectationById(Long id);
	public boolean findAffectation(AffectationSeance a);
	public Collection<AffectationSeance> findAllAffectationSeances();
	public Collection<AffectationSeance> getAffectationByAnneeSession(int annee,String session);
	public Collection<AffectationSeance> findAffectationById(Long id,String type, int au);
	
	/* AlerteDAO */
	public void addAlerte(Alerte alerte);
	public Collection<Alerte> findAllAlertes();
	public Collection<Alerte> findAlertesBySeance(Seance seance);
	public Alerte findAlerte(Long id);
	public Collection<Alerte> getAlerteByAnneeSession(int annee , String session);
	
	/*CycleAnnéeDAO */
	public void addCycleAnnee(CycleAnnee c);
	public CycleAnnee findCycleAnnee(int id);
	
	/* DemandeEchangeDAO */
	public void addDemandeEchangeS(DemandeEchangeS d);
	public Collection<DemandeEchangeS> findDemandeEnvParId(Long id);
	public Collection<DemandeEchangeS> findDemandeRecParId(Long id);
	
	/* EnseignantDAO */
	public void addEnseignant(Enseignant e);
	public Collection<Enseignant> findEnseignantsParMatiere(MatiereEnseignement matiere);
	public double calculeNbHeureSurveillant(Enseignant e);
	public Collection<Enseignant> findAllSurveillants();
	public Collection<Enseignant> getListeSurveillantTriParCharge();
	public Collection<Enseignant> findSurveillantsParSéance(Seance s);
	public Enseignant findEnseignant(String nom);
	public boolean enseignantAffectéSalle(Enseignant e,String date,String heure);
	public boolean enseignantAffectéSalle(Enseignant e,String date,String heure,Salle s);
	public Collection<Enseignant> findAllEnseignantActif();
	public Enseignant findEnseignantParCIN(int Cin);
	public Enseignant findEnseignant(Long id);
	public Enseignant loginEnseignant(String nom, String pass);
	public boolean nomUtilisateurExiste(String nom,Long id);
	public void updateParamCnx(String nom , String pass,String cin);
	public Enseignant findEnseignantParMail(String mail);
	
	/*EnseignementDAO */
	public void addEnseignement(Enseignement e);
	public Collection<Enseignement> getCalendrierEnseignement(Long id);
	public Collection<Enseignement> findJourEnseignement(Long id);
	
	/* ExamenDAO */
	public void addExamen(Examen e);
	public Collection<Examen> findExamensByMatiere(MatiereEnseignement mat);
	public Examen findExamenParAlerte(Long id,String heure,String date);
	public Collection<Examen> findAllExamen();
	public Collection<Examen> findExamens(Time heure,Date date);
	public Collection<Examen> findExamenBySeance(Seance s);
	
	/* JourExamenDAO */ 
	public void addJourExamen(JourExamen j);
	public Collection<JourExamen> findAllJourExamen();
	public Collection<JourExamen> findAllJourExamen(String session,int AU);
	public JourExamen findJourExamen();
	public Collection<JourExamen> getJourExamensByAnneeSession(int annee, String session);
	
	/* MatiéreDAO */ 
	public void addMatiere(Matiere m);
	public Matiere findMatiere(Long id);
	public Collection<Matiere> findAllMatiere();
	
	/* MatièreEnseignementDAO */
	public Collection<MatiereEnseignement> getMatiereEnseignementsByEnseignant(Enseignant e);
	public void addMatiereEnseingement(MatiereEnseignement m);
	public boolean existeExamenPourMatiere(MatiereEnseignement mat);
	public Collection<MatiereEnseignement> findMatiereParSeance(Seance seance);
	public Collection<Seance> findSeancePourMatiere(MatiereEnseignement m);
	public Collection<MatiereEnseignement> findMatiereEnseigner(Long id);
	public Collection<MatiereEnseignement> findAllMatiereEns();
	
	/* RemplaçementDAO */
	public boolean existeRemp(String date, String heure,Enseignant ens);
	public void addRemplacement(Remplacement r);
	public Remplacement findRemplacement(String date , String heure , Enseignant ens);
	
	/* SalleDAO */
	public void addSalle(Salle s);
	public int nbExamenSalle(String date , String heure , Salle salle);
	public Salle findSalle(String id);
	public Long nbAffectationSalle(String date,String heure,Salle s);
	
	/* SéanceDAO */
	public void addSeeance(Seance s);
	public Collection<Seance> seancesAffecterPourSur(Long id);
	public Collection<Seance> findAllSeance();
	public Collection<Seance> findSeanceByDateJour(Date date);
	public Collection<Seance> findSeanceByDateJour(String date);
	public Seance findSeanceByHeurDebut(String date,String heureDebut);
	
	/*TypeAlerteDAO */ 
	public void addTypeAlerte(TypeAlerte type);
	public Collection<TypeAlerte> findAllTypesAlertes();
	public TypeAlerte findTypeAlerte(Long id);
	public Collection<TypeAlerte> getTypesAlertes();
	public boolean supprimerTypeAlerte(TypeAlerte type);
	
	/* UtilisateurDAO */
	public void addUtilisateur(Utilisateur u);
	public boolean FindUtilisateur(Utilisateur u);
	public Collection<Role> getAllRoles();
	public Role findRole(Long id);
	public Collection<Utilisateur> findAllUtilisateurs();
	public void supprimerUtilisateur(Utilisateur utilisateur);
	public Utilisateur findUser(String nom,String pass);
	
	
	/* *** Méthode d'inserstion dans la base *** */
	
	public void addSemstre(Semestre s);
	public void addSession(Session s);
	public void addFiliére(Filiére f);
	public void addJourEnseignement(JourEnseignement j);
	
	public Collection<Time> getHeureByAnneeSession(int annee,int annee1, String session);	
	public Collection<String> getSessionByAnnee(int annee,int annee1);
	public Collection<AffectationSeance> getAffectationByAnneeSession(int annee,int annee2,String session);
	public Collection<JourExamen> getJourExamensByAnneeSession(int annee,int annee2,String session);
	public Collection<AffectationExamen> getAffectationExamenByAnneeSession(int annee,int annee2,String session);
	public Collection<Alerte> getAlerteByAnneeSession(int annee,int annee2,String session);
	public void updateUtilisateurPass(String nom,String pass);

	public Long findTypeByLibelle(String libelle);
	public boolean enSeance(String date,String heure,Long id);
	public boolean RespAffecté();
	public boolean findDemande(Date date,Time heure, Long id);
	public boolean verifierAffectation(String date,String heure,String id);
	//public Boolean besoinCalculer();
	public double calculeChargeHoraireGlobaleDesSurveillants();
	public String typeExamen();
	public int nbAffectationParSeance(String d,String t);
	public int nbAffectationParSurveillant(Long id);
	public Collection<Time> findHeureSeance();
	public boolean chargeCalculé();
	public boolean IsResponsable(Long id);
	public boolean besoinCalcule();
	public Long nbAffectationParSeanceAuxSalle(String date,String heure);
	public Filiére findFiliére(String id);
	public Semestre findSemstre(int i);
	public Session findSession(String i);
	public void updateIdAffectationSeance(String id,String date , String heure , Long nvid);
	public void updateRespMat(String mat,String fil,String cyc);
	public void updateChargeDeSurveillance(Long id,int nb);
	public void updateNbSurvaillantParSeance(String date,String time,int nb);
	public void updateNbCommissionParSeance(String date,String time,int nb);
	public void updateDemande(String emetteur,String recepteur,String datP,String heureP,String datD,String heureD,String etat);
	public void supprimerAffectationSeance(Long id);
	public void supprimerAffectationSeance(String id , String date , String heure);
	public void supprimerDemandeEchange(String id , String dateP, String heureP);
	public void supprimerDemandeEchangeD(String id , String dateD, String heureD);
	public void supprimerAffectationExamen(String date,String heure,Long id);
	public void supprimerRemplacement(String date,String heure,Enseignant ens);
	
	public boolean isCC();
	public boolean isEnseignantMaster(Long id);
	public double nbHeureMastere(Long id);
	public String periodeExamen(Date date);
	public Collection<String> getAnnéeUniversitaire();
	public Collection<String> getSessionByAnnee(int annee);
	public Collection<Time> getHeureByAnneeSession(int annee, String session);
	public Date getDateActuelle();
	public void reglerCin(Long id, String cin);
	
}
