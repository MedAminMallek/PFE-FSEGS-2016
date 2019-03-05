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
    <script type="text/javascript">
	window.onload = function () {
	<%= request.getAttribute("teste")%>
	
	
	var els = document.getElementsByTagName("a");
	for (var i = 0, l = els.length; i < l; i++) {
	    var el = els[i];
	    if (el.href === 'http://canvasjs.com/') {
	        el.innerHTML = "";
	        el.href = "";
	    }
	}
	}
	</script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/JS/canvasjs.min.js"></script>
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
                         <h4 align="center"><b>Avancement</h4>
                        </div>
                         <div class="panel-body" style="padding-bottom:30px;">
                          <div class="col-md-12">
                            
                            
                            
                           <table align="center" border="2" style="width:60%">
                            	<tr bgcolor="#2E9AFE"><td width="250"></td>
                            		
                            	<c:forEach items="${heures}" var="h">
                            	<td align="center"><b>${h.toString().split(':')[0]}:${h.toString().split(':')[1]}                      
                            	</c:forEach>
                            	</tr>
                            	<c:forEach items="${calendrier2}" var="j">
                            	
                            	<tr>
                            		<td width="250" bgcolor="#2E9AFE">${j.affichageDate()}</td>
                            		
                            		<c:forEach items="${heures}" var="h">
                            		
                            		<td height="75px" align="center">
                            		<c:forEach items="${j.seanceExamens}" var="s">
                            			
                            			<c:if test="${s.heureDebut.equals(h)}">
                            			
                            			<c:if test="${s.nombreAffectationSurveillant >= s.nombreSurveillant}">
                            				${s.url}  
                            			</font>
                            			</c:if>
                            			<c:if test="${s.nombreAffectationSurveillant < s.nombreSurveillant}">
                            				${s.url}  
                            			</font>
                            			</c:if>
                            			
                            			</c:if>
                            		</c:forEach>
                            		
                            		</td>
                            		</c:forEach>
                            	</tr>
                            	<!-- 
                            	<tr>
                            		<td>Commission</td>
                            		<c:forEach items="${heures}" var="h">
                            		<td align="center">
                            		
                            		<c:forEach items="${j.seanceExamens}" var="s">
                            			<c:if test="${s.heureDebut.equals(h)}">
                            				<input type="number"  min="1" required="required" value="${s.nombreCommission}" name="${j.date} M ${s.heureDebut}">  
                            			</c:if>
                            		</c:forEach>
                            		
                            		</td>
                            		</c:forEach>
                            	</tr> -->
                            	                
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