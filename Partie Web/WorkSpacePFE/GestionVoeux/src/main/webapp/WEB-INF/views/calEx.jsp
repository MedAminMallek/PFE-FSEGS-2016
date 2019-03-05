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
		 
		 	<div class="col-md-15">
                      <div class="panel form-element-padding">
                        <div class="panel-heading">
                         <h4 align="center"><b>Calendrier d'examen</h4>
                        </div>
                         <div class="panel-body" style="padding-bottom:30px;">
                          <div class="col-md-12">
                            
                            
                            	<div align="center">
                            		Session ${session} ${AU}
                            	</div>
                            <br>
                            <table align="center"  border="2" "width:70%">
                            	<tr><td height="50" width="250"></td>
                            	<c:forEach items="${heures}" var="h">
                            	<td align="center" bgcolor="#2E9AFE"><b>${h.toString().split(':')[0]}:${h.toString().split(':')[1]}                      
                            	</c:forEach>
                            	</tr>
                            	<c:forEach items="${cal}" var="j">
                            	<tr align="center">
                            		<td height="50" bgcolor="#2E9AFE">${j.dateJour()}</td>
                            		<c:forEach items="${heures}" var="h">
                            		<td align="center">
                            		
                            		<c:forEach items="${j.seances}" var="s">
                            			<c:if test="${s.heureDebut.equals(h)}">
                            				
                            				<a href="afficherMat?date=${j.date}&heure=${s.heureDebut}">
                            				<span class="icons icon-magnifier-add"></span>
                            				</a>
                            				
                            			</c:if>
                            		</c:forEach>
                            		
                            		</td>
                            		</c:forEach>
                            	</tr>
                            	</c:forEach>
                            </table>
                            
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