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

<style type="text/css">
  	input[type=checkbox]
	{
  	/* Double-sized Checkboxes */
  	-ms-transform: scale(1.5); /* IE */
  	-moz-transform: scale(1.5); /* FF */
  	-webkit-transform: scale(1.5); /* Safari and Chrome */
  	-o-transform: scale(1.5); /* Opera */
 	 padding: 10px;
	}
	.tableOne {
    margin:auto;
    
  }
  </style>


      <div class="container-fluid mimin-wrapper">
  
          <!-- start:Left Menu -->            
          <%@include file="Menu1.jsp" %>
          
          <!-- end: Left Menu -->
          
         
         
		 <div id="content">
		       <form action="updateP" method="post">
		       <input type="text" hidden="true" name="jour" value="${jour}">
		       <input type="text" hidden="true" name="seance" value="${seance}">
		       <font color="black" size="4">
                    
		 			<div class="col-md-15">
                      <div class="panel form-element-padding">
                        <div class="panel-heading">
                         <h4 align="center"><b>Gérer Présence</h4>
                        </div>
                         <div class="panel-body" style="padding-bottom:30px;">
                          <div class="col-md-12">
                          <!-- 
                           <br>
                           <div align="center">
                           Afficher:
                           <select id="liste">
                           <option>Tous
                           <option>Présents
                           <option>Absents
                           </select>
							</div>	
                           <br>
                           <br>
                             -->
                           <div id=tab1>
                           <table align=center border="1">
                           <tr align="center"><td width="300">Enseignant<td>Présence
                           	<c:forEach items="${aff}" var="a">
                           		<% String name=""; %>
                           		<c:if test="${a.affectation.presence==true}"><% name="p"; %></c:if>
                           		<c:if test="${a.affectation.presence==false}"><% name="a"; %></c:if>
                           		<tr name="<%= name%>"><td>${a.affectation.enseignant.enseignant}
                           		<td align="center">
                           		<% String x=""; %>
                           		<c:if test="${a.affectéSalle==true}">
                           			<% x="disabled='disabled'"; %>
                           		</c:if>
                           		<div class="form-group form-animate-checkbox" align="center" style="margin: 0px 0px 0px 25px;">
                           		<c:if test="${a.affectation.presence==true}"><input type="checkbox" class="checkbox" name="${a.affectation.enseignant.id}" checked="checked" <%=x %>></c:if>
                           		<c:if test="${a.affectation.presence==false}"><input name="${a.affectation.enseignant.id}" class="checkbox" type="checkbox"></c:if>
                           		</div>
                           	</c:forEach>
                           </table> 
                           </div>
                           
                           <br><br>
                           <center>
                            <button class="btn ripple-infinite btn-gradient btn-info" type="submit">
                                    <div>
                                      <span>Valider</span>
                                    </div>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                    </font>
                    </form>
		 </div>
		 
  		
		
		</div>
	
	<script type="text/javascript">
	function teste()
	{
		document.getElementById("tab1").style.visibility = "hidden";
		//document.getElementById("1").style.visibility = "hidden";
		//var tousElements = document.getElementsByName("1");
		var value = document.getElementById("liste").value;
		if(value=='Présents')
			{
			var elements = new Array();
			elements = document.getElementsByName("a");
			var x=0;
			while(x<elements.length)
			{
				elements[x].style.visibility = "hidden";
				x++;
			}
			var elements2 = new Array();
			elements = document.getElementsByName("p");
			var x2=0;
			while(x2<elements2.length)
			{
				elements2[x2].style.visibility = "visible";
				x++;
			}
			}
		
	}
	</script>
	
	
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