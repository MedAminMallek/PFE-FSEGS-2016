package org.fsegs.gestionvoeux;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.fsegs.entitees.Alerte;
import org.fsegs.entitees.Examen;
import org.fsegs.entitees.TypeAlerte;
import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ConrolleurDéclancherAlerte {
	
	@Autowired
	private ISurveillanceMetier metier;
	
	@RequestMapping(value="/getTypes")
	@ResponseBody
	public String getTypes(HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
		String typesJASON="";
		Collection<TypeAlerte> types = metier.getTypesAlertes();
		int i=1;
		for(TypeAlerte type : types)
		{
			typesJASON+="{\"id\":\""+type.getId()+"\",\"lib\":\""+type.getLibelle()+"\"}";
			if(i<types.size())
				typesJASON+=",";
			i++;
		}
		
		
		return "["+typesJASON+"]";
	}
	@RequestMapping(value="/enSeance")
	@ResponseBody
	public String enSeance(HttpServletResponse response,String date,String heure,String id)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
		String typesJASON="";
		int m = metier.getDateActuelle().getMonth()+1;
		String mm = m>9?m+"":"0"+m;
		int j = metier.getDateActuelle().getDate();
		String jj = j>9?j+"":"0"+j;
		date=metier.getDateActuelle().getYear()+1900+"-"+(mm)+"-"+(jj);
		
		int h = metier.getDateActuelle().getHours();
		String hh = h>9?h+"":"0"+h;
		int min = metier.getDateActuelle().getMinutes();
		String minm = min>9?min+"":"0"+min;
		heure = hh+":"+minm;
		boolean enSeance = metier.enSeance(date, heure, Long.parseLong(id));
		
		if(enSeance){
			Examen ex = metier.findExamenParAlerte(Long.parseLong(id), heure, date);
			return "[{\"EnSeance\":\""+enSeance+"\",\"salle\":\""+ex.getSalle().getId()+"\",\"matiere\":\""+ex.getMatiereEnseignement().getMatiere().getAbreviation()+" "+ex.getMatiereEnseignement().getCycleAnnee().getId()+" "+ex.getMatiereEnseignement().getFiliere().getId()+"\"}]";
		}else
			return "[{\"EnSeance\":\""+enSeance+"\"}]";
	}
	@RequestMapping(value="/ajouterAlerte")
	@ResponseBody
	public String ajouterAlerte(HttpServletResponse response,String date,String heure,String id,String description,String type)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	    response.setCharacterEncoding("iso-8859-1");
	    
	    int m = metier.getDateActuelle().getMonth()+1;
		String mm = m>9?m+"":"0"+m;
		int j = metier.getDateActuelle().getDate();
		String jj = j>9?j+"":"0"+j;
		date=metier.getDateActuelle().getYear()+1900+"-"+(mm)+"-"+(jj);
	    
	    int h = metier.getDateActuelle().getHours();
		String hh = h>9?h+"":"0"+h;
		int min = metier.getDateActuelle().getMinutes();
		String minm = min>9?min+"":"0"+min;
		heure = hh+":"+minm;
		
	    Alerte alerte = new Alerte();
	    alerte.setSurveillant(metier.findEnseignant(Long.parseLong(id)));
	    alerte.setHeure(new Time(Integer.parseInt(heure.split(":")[0]), Integer.parseInt(heure.split(":")[1]), 00));
	   
	    DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();
		d.set(Integer.parseInt(date.split("-")[0]),Integer.parseInt(date.split("-")[1])-1,Integer.parseInt(date.split("-")[2]),0,0,0);
		Date dat1=d.getTime();
		
		alerte.setDate(dat1);
		alerte.setDescription(description);
		alerte.setType(metier.findTypeAlerte(Long.parseLong(type)));
		
		
		metier.addAlerte(alerte);
		
		return "";
	}

}
