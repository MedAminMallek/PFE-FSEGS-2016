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
	<meta name="description" content="Miminium Admin Template v.1">
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

 <body>
      <!-- start: Header -->
        <%@include file="header1.jsp" %>
      <!-- end: Header -->




      <div class="container-fluid mimin-wrapper">
  
          <!-- start:Left Menu -->            
          <%@include file="Menu1.jsp" %>
          
          <!-- end: Left Menu -->
          
         
         
		 <div id="content">
		       
		       <font color="black" size="4">
                    <br><br>
		 			<div class="col-md-15">
                      <div class="panel form-element-padding">
                        <div class="panel-heading">
                         <h4 align="center"><b>Liste des Séances</h4>
                        </div>
                         <div class="panel-body" style="padding-bottom:30px;">
                          <div class="col-md-12">
                            
                            <br>
                            <br>
                            <form action="chargerSeanceParJourAbsence" method="post">
                           	<table align=center>
                           		<tr align="center"><td>Journées
                         		<tr align="center"><td>
                         		<select name="jour">
                         		<c:forEach items="${listeJ}" var="j">
                         		<option value="${j.dateA()}">${j.dateA()}
                         		</c:forEach>
                         		</select>
                         		<c:if test="${not empty listeS}">
                         		<tr align="center"><td>Séances
                         		<tr align="center"><td>
                         		<select name="seance">
                         		<c:forEach items="${listeS}" var="s">
                         		<option value="${s.heureDebut}">${s.heureDebut}
                         		</c:forEach>
                         		</select>
                         		
                         		
                         		
                         		<tr align="center" height="90"><td><!-- <input type="submit" value="Affecter" name="bt1"> -->
                         		
                         		<button type="submit" name="bt1" value="Affecter" class="btn ripple btn-outline btn-primary">
                        		<div>
                        		<span>Affecter</span>
                        		<span class="ink animate" style="height: 117px; width: 120px; top: -45.5px; left: 18px;"></span></div>
                        		</button>
                        		
                         		</c:if>
                         		<c:if test="${empty listeS}">
                         		<tr align="center" height="90"><td><!-- <input type="submit" value="Continuer" name="bt1">  -->
                         		<button type="submit" name="bt1" value="Continuer" class="btn ripple btn-outline btn-primary">
                        		<div>
                        		<span>Continuer</span>
                        		<span class="ink animate" style="height: 117px; width: 120px; top: -45.5px; left: 18px;"></span></div>
                        		</button>
                         		</c:if>
                           	</table>
                         	</form>
                          </div>
                        </div>
                      </div>
                    </div>
                    </font>
		 </div>
		 
  		
		
		</div>
		
		
	
	
		
		
    
          
      
	

    <!-- start: Javascript -->
    <script src="<%= request.getContextPath()%>/resources/asset/js/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/asset/js/jquery.ui.min.js"></script>
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