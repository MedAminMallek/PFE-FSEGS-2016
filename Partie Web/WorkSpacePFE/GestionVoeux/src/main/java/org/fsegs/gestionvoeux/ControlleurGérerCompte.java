package org.fsegs.gestionvoeux;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fsegs.entitees.Role;
import org.fsegs.entitees.Utilisateur;
import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.tools.xjc.gen.Mod;

@Controller
public class ControlleurGérerCompte {

	@Autowired
	private ISurveillanceMetier metier;
	@RequestMapping(value="/goGestionComtpe")
	public String gestionComtpe(Model model)
	{
		Collection<Role> roles = metier.getAllRoles();
		model.addAttribute("roles", roles);
		Collection<Utilisateur> utilisateurs = metier.findAllUtilisateurs();
		model.addAttribute("utilisateurs", utilisateurs);
		return "gestionDesComptes";
	}
	@RequestMapping(value="suppUtilisateur")
	public String supprimerUtilisateur(Model model,String nom)
	{
		
		metier.supprimerUtilisateur(new Utilisateur(nom, null));
		
		Collection<Role> roles = metier.getAllRoles();
		model.addAttribute("roles", roles);
		Collection<Utilisateur> utilisateurs = metier.findAllUtilisateurs();
		model.addAttribute("utilisateurs", utilisateurs);
		
		return "gestionDesComptes";
	}
	@RequestMapping(value="/ajouterCompte")
	//@ResponseBody
	public String ajouterCompte(Model model,HttpServletRequest request)
	{
		if(request.getParameter("bt1").equals("Ajouter")){
		String xx="";
		String nom = request.getParameter("nom");
		String pass = request.getParameter("pass");
		String[] role = request.getParameterValues("role[]");
		String erreur="";
		if(!metier.FindUtilisateur(new Utilisateur(nom, pass))){
			Utilisateur u = new Utilisateur(nom, pass);
			Collection<Role> roles = new ArrayList<Role>();
			if(role!=null){
			for(String x : role)
			{
				Role r = metier.findRole(Long.parseLong(x));
				roles.add(r);
			}
			u.setRoles(roles);
			metier.addUtilisateur(u);
			}else
			{
				erreur+= "Vous devez attribuer au moins un rôle à cet utilisateur";
			}
		
		}else
		{
			erreur+="Ce nom d'utilisateur existe déja<br>";
		}
		model.addAttribute("erreur", erreur);
		model.addAttribute("nom", nom);
		model.addAttribute("pass", pass);
		Collection<Role> roles = metier.getAllRoles();
		model.addAttribute("roles", roles);
		Collection<Utilisateur> utilisateurs = metier.findAllUtilisateurs();
		model.addAttribute("utilisateurs", utilisateurs);
		return "gestionDesComptes";
		}else{
			
			String nom = request.getParameter("nom");
			String pass = request.getParameter("pass");
			String nomA = request.getParameter("userName");
			String PassA = request.getParameter("userPass");
			String[] role = request.getParameterValues("role[]");
			
			metier.supprimerUtilisateur(new Utilisateur(nomA, PassA));
			
			String erreur="";
			if(!metier.FindUtilisateur(new Utilisateur(nom, pass))){
				Utilisateur u = new Utilisateur(nom, pass);
				Collection<Role> roles = new ArrayList<Role>();
				if(role!=null){
				for(String x : role)
				{
					Role r = metier.findRole(Long.parseLong(x));
					roles.add(r);
				}
				u.setRoles(roles);
				metier.supprimerUtilisateur(new Utilisateur(nom, pass));
				metier.addUtilisateur(u);
				}else
				{
					erreur+= "Vous devez attribuer au moins un rôle à cet utilisateur";
				}
			
			}else
			{
				erreur+="Ce nom d'utilisateur existe déja<br>";
			}
			model.addAttribute("erreur", erreur);
			Collection<Role> roles = metier.getAllRoles();
			model.addAttribute("roles", roles);
			Collection<Utilisateur> utilisateurs = metier.findAllUtilisateurs();
			model.addAttribute("utilisateurs", utilisateurs);
			return "gestionDesComptes";
		}
	}
	@RequestMapping(value="findUser")
	public String findUser(Model model,String nom,String pass)
	{
		Utilisateur user = metier.findUser(nom, pass);
		model.addAttribute("userM", user);
		Collection<Role> roles = metier.getAllRoles();
		model.addAttribute("roles", roles);
		Collection<Utilisateur> utilisateurs = metier.findAllUtilisateurs();
		model.addAttribute("utilisateurs", utilisateurs);
		return "gestionDesComptes";
	}
	@RequestMapping(value="/paramCnx")
	public String paramCnx(Model model,HttpServletRequest request)
	{
		HttpSession sess = request.getSession();
		Utilisateur u =  (Utilisateur) sess.getAttribute("user");		
		model.addAttribute("user", u);
		return "paramCnx";
	}
	@RequestMapping(value="/modiferParam")
	public String modiferParam(Model model,HttpServletRequest request)
	{
		String nom = request.getParameter("nom");
		String pass = request.getParameter("pass");
		String nomAn = request.getParameter("nomAn");
		String passAn = request.getParameter("passAn");
		
		
		metier.updateUtilisateurPass(nomAn, pass);
		Utilisateur user = metier.findUser(nomAn, pass);
		HttpSession sess = request.getSession();
		sess.setAttribute("user", user);		
		model.addAttribute("user", user);
		model.addAttribute("msg", "Vos paramètres de connexion ont été modifiées");
		
		return "paramCnx";
	}
}
