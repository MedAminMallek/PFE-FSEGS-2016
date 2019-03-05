package teste;

import java.util.Properties;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.fsegs.entitees.Enseignant;

public class smtpTest {
	
	
	public smtpTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	public static void main (String[] args) throws Exception {
		 
		final String username = "med.amin.mallek@gmail.com";
		final String password = "235748235748";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("med.amin.mallek@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("manelfeki49@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Saluut,"
				+ "\n\n Hello Baby Face :D");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	 
	  }*/
	public void envoieMail(String msg,String mail,Enseignant ens)
	{
		final String username = "med.amin.mallek@gmail.com";
		final String password = "235748235748";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("med.amin.mallek@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mail));
			message.setSubject("Affectation");
			//message.setText("M."+ens.getEnseignant()+",\n vous êtes affecté à ces séances : \n \n"
				//+ msg);
			String texte = "<font size=4>"+ msg;
			message.setContent(texte, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	 
	}
	public void envoieMail(String msg,String mail)
	{
		final String username = "med.amin.mallek@gmail.com";
		final String password = "235748235748";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("med.amin.mallek@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mail));
			message.setSubject("Demande de permutation");
			//message.setText("M."+ens.getEnseignant()+",\n vous êtes affecté à ces séances : \n \n"
				//+ msg);
			String texte = "<font size=4>"+ msg;
			message.setContent(texte, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	 
	}
	public void envoieMailParamCnx(String msg,String mail)
	{
		final String username = "med.amin.mallek@gmail.com";
		final String password = "235748235748";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("med.amin.mallek@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mail));
			message.setSubject("Paramètres de connexion");
			//message.setText("M."+ens.getEnseignant()+",\n vous êtes affecté à ces séances : \n \n"
				//+ msg);
			String texte = "<font size=4>"+ msg;
			message.setContent(texte, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	 
	}

}
