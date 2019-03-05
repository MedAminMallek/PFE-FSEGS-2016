package teste;


import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.fsegs.entitees.CycleAnnee;
import org.fsegs.entitees.Enseignant;
import org.fsegs.entitees.Enseignement;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.Filiére;
import org.fsegs.entitees.JourEnseignement;
import org.fsegs.entitees.JourExamen;
import org.fsegs.entitees.Matiere;
import org.fsegs.entitees.MatiereEnseignement;
import org.fsegs.entitees.Salle;
import org.fsegs.entitees.Seance;
import org.fsegs.entitees.Semestre;
import org.fsegs.entitees.Session;
import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.context.support.*;

public class teste {

	public static void main(String[] args) {
		
		
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		ISurveillanceMetier metier =(ISurveillanceMetier) context.getBean("metier");
		
		
		Enseignant e1 = new Enseignant(1L, "Ben Meftah Emna", "I", "PES", "MA", "F", 10L,true,0,
				"","aa.aa@bb.com","74852963","50123321",false);
		Enseignant e2 = new Enseignant(2L, "Haddar Nahla","I", "PES", "MA", "M", 10L,true,0,
				"","aa.aa@bb.com","74852963","50123321",false);
		Enseignant e3 = new Enseignant(3L, "Neji Mahmoud", "I", "PES", "MA", "M", 10L,true,0,
				"","aa.aa@bb.com","74852963","50123321",false);
		Enseignant e4 = new Enseignant(4L, "Lassad Ellouze", "I", "PES", "MA", "F", 10L,true,0,
				"","aa.aa@bb.com","74852963","50123321",false);
		Enseignant e5 = new Enseignant(5L, "Gargouri Bilel", "I", "PES", "MA", "M", 10L,true,0,
				"","aa.aa@bb.com","74852963","50123321",true);
		metier.addEnseignant(e1);
		metier.addEnseignant(e2);
		metier.addEnseignant(e3);
		metier.addEnseignant(e4);
		metier.addEnseignant(e5);
		
		
		Filiére f1 = new Filiére("1l","AG");
		Filiére f2 = new Filiére("2l","FI");
		metier.addFiliére(f1);
		metier.addFiliére(f2);
		
		Semestre s1 = new Semestre(1, "Premiere semestre");
		Semestre s2 = new Semestre(2, "Deuxiéme semestre");
		metier.addSemstre(s1);
		metier.addSemstre(s2);
		
		Session se1 = new Session("P", "Principale");
		Session se2 = new Session("CC", "Controle Continue");
		Session se3 = new Session("R", "Rattrapage");
		metier.addSession(se1);
		metier.addSession(se2);
		metier.addSession(se3);
		
		Salle sal1 = new Salle("7", "TD", "B1", 30, 20, 1);
		Salle sal2 = new Salle("8", "TD", "B1", 30, 20, 1);
		Salle sal3 = new Salle("25", "CR", "B1", 60, 30, 2);
		Salle sal4 = new Salle("A1", "CR", "", 100, 50, 3);
		Salle sal5 = new Salle("A2", "CR", "", 80, 40, 3);
		Salle sal6 = new Salle("A3", "CR", "", 60, 40, 2);
		Salle sal7 = new Salle("A4", "CR", "", 60, 40, 2);
		Salle sal8 = new Salle("32", "CR", "B2", 50, 20, 2);
		metier.addSalle(sal1);
		metier.addSalle(sal2);
		metier.addSalle(sal3);
		metier.addSalle(sal4);
		metier.addSalle(sal5);
		metier.addSalle(sal6);
		metier.addSalle(sal7);
		metier.addSalle(sal8);
		
		CycleAnnee c1 = new CycleAnnee(11, "Premiere annee", "P");
		CycleAnnee c2 = new CycleAnnee(21, "Deuxiéme annee", "P");
		CycleAnnee c3 = new CycleAnnee(31, "Troixiéme annee", "P");
		CycleAnnee c4 = new CycleAnnee(41, "Premiere annee Master", "D");
		CycleAnnee c5 = new CycleAnnee(51, "Deuxiéme annee Master", "D");
		metier.addCycleAnnee(c1);
		metier.addCycleAnnee(c2);
		metier.addCycleAnnee(c3);
		metier.addCycleAnnee(c4);
		metier.addCycleAnnee(c5);
		
		JourEnseignement j1 = new JourEnseignement(1, "Lundi");
		JourEnseignement j2 = new JourEnseignement(2, "Mardi");
		JourEnseignement j3 = new JourEnseignement(3, "Mercredi");
		JourEnseignement j4 = new JourEnseignement(4, "Jeudi");
		JourEnseignement j5 = new JourEnseignement(5, "Vendredi");
		metier.addJourEnseignement(j1);
		metier.addJourEnseignement(j2);
		metier.addJourEnseignement(j3);
		metier.addJourEnseignement(j4);
		metier.addJourEnseignement(j5);
		
		
		
		Matiere m1 = new Matiere(1l, "I", "systéme de gestion de base de données","SGBD");
		Matiere m2 = new Matiere(2l, "G", "Marketing", "Marketing");
		Matiere m3 = new Matiere(3l, "E", "Economie", "Eco");
		metier.addMatier(m1);
		metier.addMatier(m2);
		metier.addMatier(m3);
		
		MatiereEnseignement me1 = new MatiereEnseignement(m1, f1, c1, e1);
		MatiereEnseignement me2 = new MatiereEnseignement(m2, f1, c2, e1);
		MatiereEnseignement me3 = new MatiereEnseignement(m3, f1, c3, e1);
		
		MatiereEnseignement me4 = new MatiereEnseignement(m1, f1, c3, e2);
		MatiereEnseignement me5 = new MatiereEnseignement(m2, f2, c2, e2);
		
		MatiereEnseignement me6 = new MatiereEnseignement(m3, f2, c1, e3);
		MatiereEnseignement me7 = new MatiereEnseignement(m3, f2, c2, e3);
		
		metier.addMatiereEnseingement(me1);
		metier.addMatiereEnseingement(me2);
		metier.addMatiereEnseingement(me3);
		metier.addMatiereEnseingement(me4);
		metier.addMatiereEnseingement(me5);
		metier.addMatiereEnseingement(me6);
		metier.addMatiereEnseingement(me7);
		
		
		Enseignement ens1 = new Enseignement(2016, s1,"CR","08:15", j1,me1,0,0,0,e1);		
		Enseignement ens2 = new Enseignement(2016, s1,"CR","09:55", j2,me2,0,0,0,e1);
		Enseignement ens7 = new Enseignement(2016, s1,"CR","13:15", j3,me3,0,0,0,e1);
		
		Enseignement ens3 = new Enseignement(2016, s1,"TD","11:35", j2,me4,0,0,0,e2);
		Enseignement ens8 = new Enseignement(2016, s1,"TD","08:15", j1,me5,0,0,0,e2);
		
		Enseignement ens5 = new Enseignement(2016, s1,"TD","08:15", j4,me6,0,0,0,e3);
		Enseignement ens10 = new Enseignement(2016, s1,"TD","08:15", j1,me7,0,0,0,e3);
		
		Enseignement ens4 = new Enseignement(2016, s1,"TD","08:15", j1,me4,0,0,0,e4);
		Enseignement ens11 = new Enseignement(2016, s1,"TD","09:55", j5,me4,0,0,0,e4);		
		Enseignement ens6 = new Enseignement(2016, s1,"CR","08:15", j3,me6,0,0,0,e5);
		
		
		metier.addEnseignement(ens1);
		metier.addEnseignement(ens2);
		metier.addEnseignement(ens3);
		metier.addEnseignement(ens4);
		metier.addEnseignement(ens5);
		metier.addEnseignement(ens6);
		metier.addEnseignement(ens7);
		metier.addEnseignement(ens8);
		metier.addEnseignement(ens11);
		metier.addEnseignement(ens10);
		
		DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();
		d.set(2017,3,4,0,0,0);
		Date dat1=d.getTime();
		
		JourExamen jo1 = new JourExamen(2016, dat1);
		d.set(2017,3,5,0,0,0);
		Date dat2=d.getTime();
		JourExamen jo2 = new JourExamen(2016, dat2);
		d.set(2017,3,6,0,0,0);
		Date dat3=d.getTime();
		JourExamen jo3 = new JourExamen(2016, dat3);
		d.set(2017,3,7,0,0,0);
		Date dat4=d.getTime();
		JourExamen jo4 = new JourExamen(2016, dat4);
		d.set(2017,3,8,0,0,0);
		Date dat5=d.getTime();
		JourExamen jo5 = new JourExamen(2016, dat5);
		d.set(2017,3,9,0,0,0);
		Date dat6=d.getTime();
		JourExamen jo6 = new JourExamen(2016, dat6);
		d.set(2017,3,10,0,0,0);
		Date dat7=d.getTime();
		JourExamen jo7 = new JourExamen(2016, dat7);
		d.set(2017,0,11,0,0,0);
		Date dat8=d.getTime();
		JourExamen jo8 = new JourExamen(2016, dat8);
		d.set(2017,0,12,0,0,0);
		Date dat9=d.getTime();
		JourExamen jo9 = new JourExamen(2016, dat9);
		d.set(2017,0,13,0,0,0);
		Date dat10=d.getTime();
		JourExamen jo10 = new JourExamen(2016, dat10);
		
		metier.addJourExamen(jo1);
		metier.addJourExamen(jo2);
		metier.addJourExamen(jo3);
		//metier.addJourExamen(jo4);
		//metier.addJourExamen(jo5);
		//metier.addJourExamen(jo6);
		//metier.addJourExamen(jo7);
		//metier.addJourExamen(jo8);
		//metier.addJourExamen(jo9);
		//metier.addJourExamen(jo10);
		
		Seance sea1 = new Seance(jo1, new Time(8, 30, 0), 0, 0);
		Seance sea2 = new Seance(jo1, new Time(11, 30, 0), 0, 0);
		Seance sea3 = new Seance(jo1, new Time(14, 30, 0), 0, 0);
		metier.addSeeance(sea1);
		metier.addSeeance(sea2);
		metier.addSeeance(sea3);
		
		Seance sea21 = new Seance(jo2, new Time(8, 30, 0), 0, 0);
		Seance sea22 = new Seance(jo2, new Time(11, 30, 0), 0, 0);
		Seance sea23 = new Seance(jo2, new Time(14, 30, 0), 0, 0);
		metier.addSeeance(sea21);
		metier.addSeeance(sea22);
		metier.addSeeance(sea23);
		
		
		//Seance sea21 = new Seance(jo3, new Time(8, 30, 0), 0, 0);
		Seance sea32 = new Seance(jo3, new Time(11, 30, 0), 0, 0);
		Seance sea33 = new Seance(jo3, new Time(14, 30, 0), 0, 0);
		//metier.addSeeance(sea21);
		metier.addSeeance(sea32);
		metier.addSeeance(sea33);
		
		//Jour1
		
		Examen ex111 = new Examen(sal1,sea1,me1);
		Examen ex112 = new Examen(sal2,sea1,me1);
		Examen ex113 = new Examen(sal3,sea1,me1);
		Examen ex114 = new Examen(sal4,sea1,me7);
		metier.addExamen(ex111);
		metier.addExamen(ex112);
		metier.addExamen(ex113);
		metier.addExamen(ex114);
		
		Examen ex121 = new Examen(sal5,sea2,me2);
		Examen ex122 = new Examen(sal6,sea2,me2);
		Examen ex123 = new Examen(sal7,sea2,me2);
		Examen ex124 = new Examen(sal8,sea2,me2);
		metier.addExamen(ex121);
		metier.addExamen(ex122);
		metier.addExamen(ex123);
		metier.addExamen(ex124);
		
		Examen ex131 = new Examen(sal1,sea3,me3);
		Examen ex132 = new Examen(sal3,sea3,me3);
		Examen ex133 = new Examen(sal5,sea3,me3);
		Examen ex134 = new Examen(sal7,sea3,me3);
		metier.addExamen(ex131);
		metier.addExamen(ex132);
		metier.addExamen(ex133);
		metier.addExamen(ex134);
		
		//Jour2
		
		Examen ex2111 = new Examen(sal4,sea21,me4);
		Examen ex2112 = new Examen(sal5,sea21,me4);
		Examen ex2113 = new Examen(sal6,sea21,me4);
		Examen ex2114 = new Examen(sal7,sea21,me4);
		metier.addExamen(ex2111);
		metier.addExamen(ex2112);
		metier.addExamen(ex2113);
		metier.addExamen(ex2114);
		
		Examen ex2121 = new Examen(sal4,sea22,me5);
		Examen ex2122 = new Examen(sal3,sea22,me5);
		Examen ex2123 = new Examen(sal2,sea22,me5);
		Examen ex2124 = new Examen(sal1,sea22,me5);
		metier.addExamen(ex2121);
		metier.addExamen(ex2122);
		metier.addExamen(ex2123);
		metier.addExamen(ex2124);
		
		Examen ex2131 = new Examen(sal4,sea23,me6);
		Examen ex2132 = new Examen(sal5,sea23,me6);
		Examen ex2133 = new Examen(sal6,sea23,me6);
		Examen ex2134 = new Examen(sal7,sea23,me6);
		metier.addExamen(ex2131);
		metier.addExamen(ex2132);
		metier.addExamen(ex2133);
		metier.addExamen(ex2134);
		
		
		//jour3
		
		Examen ex3121 = new Examen(sal1,sea32,me7);
		Examen ex3122 = new Examen(sal2,sea32,me7);
		Examen ex3123 = new Examen(sal3,sea32,me7);
		Examen ex3124 = new Examen(sal4,sea32,me7);
		Examen ex3125 = new Examen(sal5,sea32,me7);
		Examen ex3126 = new Examen(sal6,sea32,me7);
		Examen ex3127 = new Examen(sal7,sea32,me7);
		metier.addExamen(ex3121);
		metier.addExamen(ex3122);
		metier.addExamen(ex3123);
		metier.addExamen(ex3124);
		metier.addExamen(ex3125);
		metier.addExamen(ex3126);
		metier.addExamen(ex3127);
		
		Examen ex3131 = new Examen(sal1,sea33,me7);
		Examen ex3132 = new Examen(sal2,sea33,me7);
		Examen ex3133 = new Examen(sal3,sea33,me7);
		Examen ex3134 = new Examen(sal4,sea33,me7);
		metier.addExamen(ex3131);
		metier.addExamen(ex3132);
		metier.addExamen(ex3133);
		metier.addExamen(ex3134);
		
		
	}

}
