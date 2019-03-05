package org.fsegs.metier;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

public interface ISurveillanceMetier {
	
	public void addEnseignant(Enseignant e);
	public void addSemstre(Semestre s);
	public void addSession(Session s);
	public void addJourExamen(JourExamen j);
	public void addSeeance(Seance s);
	public void addFiliére(Filiére f);
	public void addCycleAnnee(CycleAnnee c);
	public void addEnseignement(Enseignement e);
	public void addMatier(Matiere m);
	public void addSalle(Salle s);
	public void addJourEnseignement(JourEnseignement j);
	public void addExamen(Examen e);
	public void addMatiereEnseingement(MatiereEnseignement m);
	public void addAffectationSeance(Seance s,Enseignant e);
	public void addDemandeEchangeS(DemandeEchangeS d);
	public void addAffectationSalle(AffectationExamen a);
	public void addRemplacement(Remplacement r);
	public void addTypeAlerte(TypeAlerte type);
	public void addAlerte(Alerte alerte);
	public void addUtilisateur(Utilisateur u);
	public boolean nomUtilisateurExiste(String nom,Long id);
	public void updateParamCnx(String nom , String pass,String cin);
	public Enseignant findEnseignantParMail(String mail);
	
	public Collection<AffectationSeance> findAffectationById(Long id,String type, int au);
	
	public Collection<Time> getHeureByAnneeSession(int annee,int annee1, String session);
	public Collection<String> getSessionByAnnee(int annee,int annee1);
	public Collection<AffectationSeance> getAffectationByAnneeSession(int annee,int annee2,String session);
	public Collection<JourExamen> getJourExamensByAnneeSession(int annee,int annee2,String session);
	public Collection<AffectationExamen> getAffectationExamenByAnneeSession(int annee,int annee2,String session);
	public Collection<Alerte> getAlerteByAnneeSession(int annee,int annee2,String session);
	public void updateUtilisateurPass(String nom,String pass);
	
	public Collection<JourExamen> findAllJourExamen(String session,int AU);
	public boolean FindUtilisateur(Utilisateur u);
	public Collection<Role> getAllRoles();
	public Role findRole(Long id);
	public Collection<Utilisateur> findAllUtilisateurs();
	public void supprimerUtilisateur(Utilisateur utilisateur);
	public Utilisateur findUser(String nom,String pass);
	public Enseignant loginEnseignant(String nom, String pass);
	
	public boolean existeExamenPourMatiere(MatiereEnseignement mat);
	public Collection<Examen> findExamensByMatiere(MatiereEnseignement mat);
	public Long findTypeByLibelle(String libelle);
	public Collection<TypeAlerte> findAllTypesAlertes();
	public Collection<Alerte> findAllAlertes();
	public int nbExamenSalle(String date , String heure , Salle salle);
	public Collection<AffectationExamen> findAllAffectationExamen();
	public Alerte findAlerte(Long id);
	public Collection<Alerte> findAlertesBySeance(Seance seance);
	public TypeAlerte findTypeAlerte(Long id);
	public boolean enSeance(String date,String heure,Long id);
	public Examen findExamenParAlerte(Long id,String heure,String date);
	public Collection<TypeAlerte> getTypesAlertes();
	public Collection<MatiereEnseignement> getMatiereEnseignementsByEnseignant(Enseignant e);
	public Collection<Seance> findSeancePourMatiere(MatiereEnseignement m);
	public Collection<Enseignant> findEnseignantsParMatiere(MatiereEnseignement matiere);
	
	public Collection<AffectationSeance> findSurveillantPourAbsence(String date , String heure);
	public AffectationExamen findAffectationExamen(String date,String heure,String id);
	public void updateAbsence(AffectationSeance aff,boolean p);
	public String typeExamen();
	public boolean RespAffecté();
	public void updateIdAffectationSeance(String id,String date , String heure , Long nvid);
	public AffectationSeance findAffectation(String id,String date , String heure);
	public Collection<DemandeEchangeS> findDemandeEnvParId(Long id);
	public boolean findDemande(Date date,Time heure, Long id);
	public boolean verifierAffectation(String date,String heure,String id);
	public Collection<AffectationSeance> findAffectationSeance(String date,String heure);
	//public Boolean besoinCalculer();
	public Collection<JourExamen> findAllJourExamen();
	public Collection<Seance> findAllSeance();
	public Collection<Examen> findAllExamen();
	
	public Collection<Examen> findExamenBySeance(Seance s);
	public boolean findAffectation(AffectationSeance a);
	public boolean existeRemp(String date, String heure,Enseignant ens);
	
	public Collection<Enseignant> getListeSurveillantTriParCharge();
	
	public Salle findSalle(String id);
	public Filiére findFiliére(String id);
	public CycleAnnee findCycleAnnee(int id);
	public Matiere findMatiere(Long id);
	public Enseignant findEnseignant(String nom);
	public Long nbAffectationSalle(String date,String heure,Salle s);
	public Collection<Seance> findSeanceByDateJour(String date);
	
	public Collection<Enseignant> findAllEnseignantActif();
	public Remplacement findRemplacement(String date , String heure , Enseignant ens);
	
	public boolean enseignantAffectéSalle(Enseignant e,String date,String heure);
	public boolean enseignantAffectéSalle(Enseignant e,String date,String heure,Salle s);
	
	public Collection<Enseignement> getCalendrierEnseignement(Long id);
	
	public void updateNbSurvaillantParSeance(String date,String time,int nb);
	public Seance findSeanceByHeurDebut(String date,String heureDebut);
	public void updateNbCommissionParSeance(String date,String time,int nb);
	public double calculeNbHeureSurveillant(Enseignant e);
	public double calculeChargeHoraireGlobaleDesSurveillants();
	public Collection<Enseignant> findAllSurveillants();
	public int chargeHoraireDeSurveillance(Enseignant e);
	public void updateChargeDeSurveillance(Long id,int nb);
	public int nbAffectationParSeance(String d,String t);
	public Collection<Seance> seanceLibre(String d);
	public int nbAffectationParSurveillant(Long id);
	public Collection<Seance> seancesAffecterPourSur(Long id);
	public boolean chargeCalculé();
	public Collection<MatiereEnseignement> findAllMatiereEns();
	public void updateRespMat(String mat,String fil,String cyc);
	
	public Collection<Seance> findSeanceByDateJour(Date date);
	public Collection<Examen> findExamens(Time heure,Date date);
	
	public Collection<MatiereEnseignement> findMatiereParSeance(Seance seance);
	
	public Long nbAffectationParSeanceAuxSalle(String date,String heure);
	
	public Collection<MatiereEnseignement> findMatiereEnseigner(Long id);
	public Collection<Time> findHeureSeance();
	
	public int calculerNbSurveillantParSeance(Seance s);
	public int calculerNbMembreCommissionParSeance(Seance s);
	
	public Enseignant findEnseignantParCIN(int Cin);
	public Enseignant findEnseignant(Long id);
	public Semestre findSemstre(int i);
	public Session findSession(String i);
	public JourExamen findJourExamen();
	public Collection<AffectationSeance> findAllAffectationSeances();
	public Collection<Matiere> findAllMatiere();
	public boolean IsResponsable(Long id);
	public Collection<Seance> findSeancesSaturé();
	public Collection<AffectationSeance> findAffectationById(Long id);
	public Collection<Enseignement> findJourEnseignement(Long id);
	
	public void updateDemande(String emetteur,String recepteur,String datP,String heureP,String datD,String heureD,String etat);
	public boolean besoinCalcule();
	public boolean isCC();
	public void supprimerDemande(Long id);
	public void supprimerAffectationSeance(Long id);
	public void supprimerAffectationSeance(String id , String date , String heure);
	public void supprimerDemandeEchange(String id , String dateP, String heureP);
	public void supprimerDemandeEchangeD(String id , String dateD, String heureD);
	public void supprimerAffectationExamen(String date,String heure,Long id);
	public void supprimerRemplacement(String date,String heure,Enseignant ens);
	public boolean supprimerTypeAlerte(TypeAlerte type);
	public Collection<DemandeEchangeS> findDemandeRecParId(Long id);
	public boolean isEnseignantMaster(Long id);
	public double nbHeureMastere(Long id);
	public Collection<Enseignant> findSurveillantsParSéance(Seance s);
	public Collection<AffectationExamen> findAffectationExamen(String date , String heure);
	
	
	
	
	public List<String> getClandrierEnseignant(Long id);
	public void premiereAffectationExamen();
	public void deuxiemeAffectationExamen();
	public void troisiemeAffectationExamen();
	
	public void premiereAffectationCC();
	public void deuxiemeAffectationCC();
	public void troisiemeAffectationCC();
	
	
	
	
	public String periodeExamen(Date date);
	
	
	public Collection<String> getAnnéeUniversitaire();
	public Collection<String> getSessionByAnnee(int annee);
	public Collection<Time> getHeureByAnneeSession(int annee, String session);
	public Collection<AffectationSeance> getAffectationByAnneeSession(int annee,String session);
	public Collection<AffectationExamen> getAffectationExamenByAnneeSession(int annee,String session);
	public Collection<Alerte> getAlerteByAnneeSession(int annee , String session);
	public Collection<JourExamen> getJourExamensByAnneeSession(int annee, String session);
	
	public Date getDateActuelle();
	public void reglerCin(Long id,String cin);
}

