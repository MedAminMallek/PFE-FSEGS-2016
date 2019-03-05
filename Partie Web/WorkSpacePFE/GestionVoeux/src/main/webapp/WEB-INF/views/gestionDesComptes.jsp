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
                         <h4 align="center"><b>Gestion des Comptes</h4>
                        </div>
                         <div class="panel-body" style="padding-bottom:30px;">
                          <div class="col-md-12">
                            <form method="post" action="ajouterCompte">
                            <c:if test="${not empty erreur}">
                            <div align="center">
                            <font color="red">${erreur}</font>
                            </div>
                            </c:if>
                            <table align="center" border="1" width=70%>
                            <tr bgcolor="#2E9AFE" align="center"><td colspan="2">Nouveau Compte</td></tr>
                            <tr bgcolor="#2E9AFE" align="center"><td width="50%">Nom d'utilisateur<td>Rôles
                            <tr align="center"><td>
                            <c:if test="${not empty erreur}">
                            <input type="text" value="${nom}" name="nom" required="required" autocomplete="off"><td align="left" rowspan="3">
                            </c:if>
                            <c:if test="${empty erreur}">
                            
                            <c:if test="${not empty userM}">
                            <input type="text" value="${userM.nom}" readonly="readonly" name="nom" required="required" autocomplete="off"><td align="left" rowspan="3">
                            </c:if>
                            
                            <c:if test="${empty userM}">
                            <input type="text" name="nom" required="required" autocomplete="off"><td align="left" rowspan="3">
                            </c:if>
                            
                            </c:if>
                            <div class="form-group form-animate-checkbox" style="margin: 0px 0px 0px 25px;">
                            
                            <c:if test="${empty userM}">
                            <c:forEach items="${roles}" var="r">
                            <input name="role[]" value="${r.id}" type="checkbox" class="checkbox" style="margin: 0px 20px 0px 0px;">  ${r.libelle}
                            <br><br>
                            </c:forEach>
                            </c:if>
                            
                            <c:if test="${ not empty userM}">
                            <c:forEach items="${roles}" var="r">
                            <c:if test="${userM.roles.contains(r)}">
                            <input name="role[]" value="${r.id}" checked="checked" type="checkbox" class="checkbox" style="margin: 0px 20px 0px 0px;">  ${r.libelle}
                            </c:if>
                            <c:if test="${not userM.roles.contains(r)}">
                            <input name="role[]" value="${r.id}" type="checkbox" class="checkbox" style="margin: 0px 20px 0px 0px;">  ${r.libelle}
                            </c:if>
                            
                            <br><br>
                            </c:forEach>
                            </c:if>
                            
                            </div>
                            <tr align="center"><td bgcolor="#2E9AFE">Mot de passe
                            <tr align="center"><td>
                            
                            <c:if test="${not empty erreur}">
                            
                            <input type="text" value="${pass}" required="required" name="pass" autocomplete="off">
                            
                            
                            </c:if>
                            
                            
                            
                            <c:if test="${empty erreur}">
                             
                             <c:if test="${not empty userM}">
                             <input type="text" value="${userM.motDePasse}" required="required" name="pass" autocomplete="off">
                             </c:if>
                             
                             <c:if test="${empty userM}">
                             <input type="text" required="required" name="pass" autocomplete="off">
                             </c:if>
                             
                             </c:if>
                            <c:if test="${empty userM}">
                            <tr align="center"><td colspan="2" bgcolor="#2E9AFE"><input name="bt1" type="submit" value="Ajouter" style="width: 300px;">
                            </c:if>
                            <c:if test="${not empty userM}">
                            <tr align="center"><td colspan="2" bgcolor="#2E9AFE"><input name="bt1" type="submit" value="Modifier" style="width: 300px;">
                            </c:if>
                            </table>
                            <input type="text" name="userName" value="${userM.nom}" hidden="true">
                            <input type="text" name="userPass" value="${userM.motDePasse}" hidden="true">
                            </form>
                            <br><br><br>
                            
                            <table align="center" border="1" width=70%> 
                            <tr align="center" bgcolor="#2E9AFE"><td width="50%" colspan="4">Listes des utilisateurs
                            <tr align="center" bgcolor="#2E9AFE"><td width="50%">Utilisateurs<td colspan="3">Roles
                            <c:forEach items="${utilisateurs}" var="u">
                            <tr align="center" height="90"><td>${u.nom}<td align="left">
                            
                            <c:forEach items="${u.roles}" var="r">
                            ${r.libelle}<br>
                            </c:forEach>
                            
                            <td><a href="suppUtilisateur?nom=${u.nom}" style="color: red;"><i class="fa fa-times-circle-o"></i></a>
                            <td><a href="findUser?nom=${u.nom}&pass=${u.motDePasse}" style="color: red;"><i class="fa fa-wrench"></i></a>
                            </c:forEach>
                            </table>
                            
                            
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
     
  </body>
</html>