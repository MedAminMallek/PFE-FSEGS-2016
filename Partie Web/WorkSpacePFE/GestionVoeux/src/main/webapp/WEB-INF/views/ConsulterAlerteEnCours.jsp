<%@page import="org.fsegs.entitees.JourExamen"%>
<%@page import="org.fsegs.entitees.JourEnseignement"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>

<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	
	<meta charset="utf-8">
	
	<meta name="author" content="Isna Nur Azis">
	<meta name="keyword" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Faculté des sciences economiques et de gestion</title>
 
    <!-- start: Css -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/asset/css/bootstrap.min.css">

      <!-- plugins -->
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/asset/css/plugins/font-awesome.min.css"/>
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/asset/css/plugins/simple-line-icons.css"/>
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/asset/css/plugins/animate.min.css"/>
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/asset/css/plugins/fullcalendar.min.css"/>
		<link href="<%= request.getContextPath()%>/resources/asset/css/style.css" rel="stylesheet">
	<!-- end: Css -->

		<link rel="shortcut icon" href="<%= request.getContextPath()%>/resources/asset/img/logofsegs.png">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <style type="text/css">
  	input[type=checkbox]
	{
  	/* Double-sized Checkboxes */
  	-ms-transform: scale(1.5); /* IE */
  	-moz-transform: scale(1.5); /* FF */
  	-webkit-transform: scale(1.7); /* Safari and Chrome */
  	-o-transform: scale(1.5); /* Opera */
 	 padding: 10px;
	}
  </style>

 <body>
      <!-- start: Header -->
        <%@include file="header1.jsp" %>
      <!-- end: Header -->



      <div class="container-fluid mimin-wrapper">
  
          <!-- start:Left Menu -->            
          <%@include file="Menu1.jsp" %>
          
          <!-- end: Left Menu -->
          
          
        
		 <div id="content">
		 <br>
		 	<font color="black" size="4">
		 	<div class="col-md-15">
                      <div class="panel form-element-padding">
                        <div class="panel-heading">
                         <h4 align="center"><b>Liste Des Alertes</h4>
                        </div>
                         <div class="panel-body" style="padding-bottom:30px;">
                          <div class="col-md-12">
                            <c:if test="${not empty erreur }">
                             <h2 align="center"><font color="red">${erreur}</font></h2>
                            </c:if>
                          	<c:if test="${empty erreur }">
                            <h3 align="center">Seance En Cours: ${seance}</h3>
                            <br><br>
                            <table align="center" border="1">
                            <tr align="center" bgcolor="#2E9AFE"><td>Heure<td>Surveillant<td>Salle<td>Alerte<td>Examen<td>Liste Enseignants
                            <c:forEach var="a" items="${liste}">
                            <tr align="center"><td>${a.alerte.heure}<td>${a.alerte.surveillant.enseignant}<br>(${a.alerte.surveillant.telMobile})
                            <td>${a.examen.salle.id}
                            <td><font color="red">
                            ${a.alerte.type.libelle }<br>
                            ${a.alerte.description }
                            </font>
                            <td>${a.examen.matiereEnseignement.matiere.abreviation} ${a.examen.matiereEnseignement.cycleAnnee.id} ${a.examen.matiereEnseignement.filiere.id}
                            <td><a href="listeEnseignant?id=${a.alerte.id}">Voir</a>
                            </c:forEach>
                            </table>
                            </c:if>
                          </div>
                        </div>
                      </div>
                    </div>
                    </font>         
		 </div>
		</div>
	
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/theme1/paging.js"></script> 


    <!-- start: Javascript --><!-- 
    <script src="<%= request.getContextPath()%>/resources/asset/js/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/asset/js/jquery.ui.min.js"></script> -->
    <script src="<%= request.getContextPath()%>/resources/asset/js/bootstrap.min.js"></script>

    <!-- plugins -->
    <script src="<%= request.getContextPath()%>/resources/asset/js/plugins/moment.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/asset/js/plugins/fullcalendar.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/asset/js/plugins/jquery.nicescroll.js"></script>
    <script src="<%= request.getContextPath()%>/resources/asset/js/plugins/jquery.vmap.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/asset/js/plugins/maps/jquery.vmap.world.js"></script>
    <script src="<%= request.getContextPath()%>/resources/asset/js/plugins/jquery.vmap.sampledata.js"></script>
    <script src="<%= request.getContextPath()%>/resources/asset/js/plugins/chart.min.js"></script>


    <!-- custom -->
     <script src="<%= request.getContextPath()%>/resources/asset/js/main.js"></script>
     
  	<!-- end: Javascript -->
  </body>
</html>