<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collection"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<%
	DateFormat dateFormat = new SimpleDateFormat("d MMMM YYYY");
	Date date = new Date();
%>
			<div id="left-menu">
              <div class="sub-left-menu scroll">
                <ul class="nav nav-list">
                    <li><div class="left-bg"></div></li>
                    <li class="time">
                      <h1 class="animated fadeInLeft">21:00</h1>
                    </li>
                    <li><center><b>
                    <font color="#928B8B">
                      <p class="animated fadeInRight"><%=dateFormat.format(date) %></p>
                    </font>
                    </li>
                    <% 
                        HttpSession sess = request.getSession();
                    	List<Integer> roles = null;
                    	roles = (List) sess.getAttribute("roles");
                    	if(roles == null)
                    	response.sendRedirect("index");
                    	else
                    	{
                       if(roles.contains(1)){
                    %>
                    <li class="active ripple">
                      <a class="tree-toggle nav-header"><span class="fa fa-table"></span>Calendrier
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                      <ul class="nav nav-list tree">
                          <li><a href="calEx">Examens</a></li>
                          <li><a href="creerCalSurveillance">Surveillances</a></li>
                           <li><a href="SurveillantCharge">Charge Surveillant</a></li>
                      </ul>
                    </li>
                    
                    <li class="active ripple">
                      <a class="tree-toggle nav-header"><span class="fa fa-check-square-o"></span>Affectation Séances
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                      <ul class="nav nav-list tree">
                          <li><a href="avencementAff">Avancement</a></li>
                          <li><a href="listeEnsAaff">Affectation enseignant</a></li>
                          <li><a href="affectationAuto">Affectation automatique</a></li>
                      </ul>
                    </li>
                    <%}
                       if(roles.contains(2)){
                    %>
                    <li class="active ripple">
                      <a class="tree-toggle nav-header"><span class="fa fa-check-square-o"></span>Affectation Salles
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                      <ul class="nav nav-list tree">
                          <li><a href="listeSeanceParJour">Affectation</a></li>
                          <li><a href="listeSeanceParJourAB">Gérer Présence</a></li>                          
                      </ul>
                    </li>
                    
                    <li class="active ripple">
                      <a class="tree-toggle nav-header"><span class="icons icon-energy"></span>Types d'alertes
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                      <ul class="nav nav-list tree">
                          <li><a href="nouveauType">Nouveau type</a></li>                          
                      </ul>
                    </li>
                    
                    <li class="active ripple">
                      <a class="tree-toggle nav-header"><span class="fa fa-exclamation-triangle fa"></span>Alertes
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                      <ul class="nav nav-list tree">
                          <li><a href="consulterAlertes">Consulter Alertes</a></li>                          
                      </ul>
                    </li>
                    <%}%>
                    <%if(roles.contains(2)||roles.contains(3)){%>
                    <li class="active ripple">
                      <a class="tree-toggle nav-header"><span class="icons icon-people"></span>Absences
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                      <ul class="nav nav-list tree">
                          <li><a href="choisirSession">Par Séance</a></li>      
                          <li><a href="choisirSession2">Par Ensiegnant</a></li>                      
                      </ul>
                    </li>
                    <%}%>
                    <%if (roles.contains(3)){ %>
                    <li class="active ripple">
                      <a class="tree-toggle nav-header"><span class="fa-area-chart fa"></span>Statistiques
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                      <ul class="nav nav-list tree">
                          <li><a href="affichierSession">Absences</a></li>
                          <li><a href="affichierSession2">Surveillance</a></li>                             
                      </ul>
                    </li>
                    <%} 
                    
                    %>
                    <li class="active ripple">
                      <a class="tree-toggle nav-header"><span class="icons icon-lock"></span>Comptes
                        <span class="fa-angle-right fa right-arrow text-right"></span>
                      </a>
                      <ul class="nav nav-list tree">
                      <% if(roles.contains(4)){ %>
                          <li><a href="goGestionComtpe">Gestion Compte</a></li>
                      <%}%>
                      	  <li><a href="paramCnx">Vos paramétres</a></li>                                 
                      </ul>
                    </li>
                    <%
                    }%>
                  </ul>
                </div>
            </div>
</body>
</html>