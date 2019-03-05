package org.fsegs.gestionvoeux;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fsegs.entitees.Role;
import org.fsegs.entitees.TypeAlerte;
import org.fsegs.entitees.Utilisateur;
import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlleurLogin {
	
	@Autowired
	private ISurveillanceMetier metier;
	
	@RequestMapping(value="/authentification")
	public String login(Model model,HttpServletRequest request)
	{	
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("nb")!=null)
		{
			String nb = session.getAttribute("nb").toString();
			if(Integer.parseInt(nb)>2)
				model.addAttribute("bloque", "Yes");
		}
		// add session 
		// voir temps de vie de session
		
		
		
		return "login";
	}
	@RequestMapping(value="/authentificationParam")
	//@ResponseBody
	public String login2(Model model,HttpServletRequest request,HttpServletResponse res)
	{
		String nom=request.getParameter("nom");
		String pass = request.getParameter("pass");
		Utilisateur utilisateur = metier.findUser(nom, pass);
		if(utilisateur!=null){
			HttpSession session = request.getSession();
			session.setAttribute("user", utilisateur);
			session.setAttribute("userC", true);
			Collection<Integer> roles = new ArrayList<Integer>();
			for(Role r : utilisateur.getRoles())
			{
				if(r.getLibelle().equals("Responsable de suivi de déroulement des examens"))
					roles.add(1);
				if(r.getLibelle().equals("Gestionnaire des séances de surveillance"))
					roles.add(2);
				if(r.getLibelle().equals("Superviseur")) 
					roles.add(3);
				if(r.getLibelle().equals("Gestionnaire des comptes"))
					roles.add(4);
			}
			session.setAttribute("roles", roles);
			session.setAttribute("nb", null);
			return "home";
			
		
		}else
		{
			
			HttpSession session = request.getSession();
			if(session.getAttribute("nb")!=null)
			{
				String x = session.getAttribute("nb").toString();
				session.setAttribute("nb", Integer.parseInt(x)+1);
				
				if(Integer.parseInt(x)+1>2)
				{
					model.addAttribute("bloque", "Yes");
				}
				
			}else
			{
				session.setAttribute("nb", 1);
				
			}
			
			
			
			
			
			
			
			
			model.addAttribute("msg","Paramètres de connexion erronés");
			return "login";
		}
	}
	@RequestMapping(value="/deconnexion")
	public String deconnexion(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();		
		return "login";
	}

}
