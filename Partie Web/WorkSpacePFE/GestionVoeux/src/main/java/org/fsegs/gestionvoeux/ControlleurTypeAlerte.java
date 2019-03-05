package org.fsegs.gestionvoeux;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.fsegs.entitees.TypeAlerte;
import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControlleurTypeAlerte {
	
	@Autowired
	private ISurveillanceMetier metier;
	
	@RequestMapping(value="/nouveauType")
	public String nouveauType(Model model)
	{
		Collection<TypeAlerte> types = metier.findAllTypesAlertes();
		model.addAttribute("types", types);
		return "ajouterTypeAlerte";
	}
	
	@RequestMapping(value="/supprimerType")
	public String supprimerType(Model model,String id)
	{
		TypeAlerte type = metier.findTypeAlerte(Long.parseLong(id));
		if(!metier.supprimerTypeAlerte(type))
		{
			model.addAttribute("er", "Impossible d'effectuer la suppression");
		}			
		Collection<TypeAlerte> types = metier.findAllTypesAlertes();
		model.addAttribute("types", types);
		return "ajouterTypeAlerte";
	}
	
	@RequestMapping(value="/ajouterType", method=RequestMethod.POST)
	//@ResponseBody
	public String ajouterType(Model model,HttpServletRequest request)
	{
		
		String description =request.getParameter("description");
		if(metier.findTypeByLibelle(description)==0){
		String[] personnes = request.getParameterValues("personne[]");
		String teste ="";
		boolean admin=false;
		boolean resp=false;
		boolean commission=false;
		if(personnes != null)
		for(String p : personnes)
		{
			if(p.equals("admin"))
				admin=true;
			if(p.equals("resp"))
				resp=true;
			if(p.equals("com"))
				commission=true;
		}
		TypeAlerte type = new TypeAlerte(description, commission, resp);
		metier.addTypeAlerte(type);
		}else
			model.addAttribute("erreur", "Ce Type existe déja");
		Collection<TypeAlerte> types = metier.findAllTypesAlertes();
		model.addAttribute("types", types);
		return "ajouterTypeAlerte";
	}

}
