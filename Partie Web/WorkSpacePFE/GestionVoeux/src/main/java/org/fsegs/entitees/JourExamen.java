package org.fsegs.entitees;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.fsegs.dao.ISurveillanceDAO;
import org.fsegs.dao.SurveillanceDAOImpl;
import org.fsegs.metier.ISurveillanceMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@Entity
@Table(name="JourExamen")
public class JourExamen implements Serializable{
	
	private int au;
	@Id
	private Date date;
	
	public int getAu() {
		return au;
	}
	public void setAu(int au) {
		this.au = au;
	}
	public Date getDate() {		
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public JourExamen(int au, Date date) {
		super();
		this.au = au;
		this.date = date;
		
	}
	public JourExamen() {
		super();
	}
	
	
	public String toString()
	{
		/*String[] da=date.split("/");
		int annee=Integer.parseInt(da[2]);
		int mois=Integer.parseInt(da[1]);
		int jour=Integer.parseInt(da[0]);
		DateFormat f= DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar d = Calendar.getInstance();
		d.set(annee,mois-1,jour);
		Date dat1=d.getTime();
		String[] x =dat1.toLocaleString().split(" "); 
		//return x[0]+" "+x[1]+" "+x[2];
		return x[0]+" "+x[1]+" "+x[2];*/
		return"";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JourExamen other = (JourExamen) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	public String getJASON()
	{
		String[] d = date.toString().split(" "); 
		return "{\"date\":\""+d[0]+"\"}";
	}
	public String dateA()
	{
		return date.toString().split(" ")[0];
	}
	public String dateJour()
	{
		String x[] = date.toLocaleString().split(" ");
		switch (date.getDay()) {
		case 1:
			return "Lundi "+x[0]+" "+x[1]+" "+x[2];
		case 2:
			return "Mardi "+x[0]+" "+x[1]+" "+x[2];
		case 3:
			return "Mercredi "+x[0]+" "+x[1]+" "+x[2];
		case 4:
			return "Jeudi "+x[0]+" "+x[1]+" "+x[2];
		case 5:
			return "Vendredi "+x[0]+" "+x[1]+" "+x[2];
		case 6:
			return "Samedi "+x[0]+" "+x[1]+" "+x[2];
		case 7:
			return "Dimanche "+x[0]+" "+x[1]+" "+x[2];
			
		}
		return "";
	}
	
	

}
