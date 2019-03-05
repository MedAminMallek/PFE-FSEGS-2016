<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	var chart = new CanvasJS.Chart("chartContainer",
	{
		title:{
			text: "Pourcentage des Retards par séance",
			fontFamily: "Arial",
            fontColor: "black",
            fontSize: 20
		},     
                animationEnabled: true,
                
		data: [
		{        
			type: "doughnut",
			startAngle: 60,                          
			toolTipContent: "{legendText}: {y} - <strong>#percent% </strong>", 					
			showInLegend: true,
			dataPoints: [
			             
						<%=request.getAttribute("xx") %>	
						
			]
		}
		]
	});
	chart.render();
	
	
	var chart1 = new CanvasJS.Chart("chart1", {
        title: {
            text: "Type de problème",
            fontFamily: "Arial",
            fontColor: "black",
            fontSize: 20

        },
        animationEnabled: true,
        axisY: {
            tickThickness: 0,
            lineThickness: 0,
            valueFormatString: " ",
            gridThickness: 0                    
        },
        axisX: {
            tickThickness: 0,
            lineThickness: 0,
            labelFontSize: 18,
            labelFontColor: "Peru"

        },
        	dataPointMaxWidth: 40,
        data: [
        {
            indexLabelFontSize: 20,
            toolTipContent: "<span style='\"'color: {color};'\"'><strong>{indexLabel}</strong></span><span style='\"'font-size: 20px; color:peru '\"'><strong>{y}</strong></span>",

            indexLabelPlacement: "inside",
            indexLabelFontColor: "white",
            indexLabelFontWeight: 300,
            indexLabelFontFamily: "Verdana",
            color: "#369EAE",
            type: "bar",
            dataPoints: [
                
						<%=request.getAttribute("xx2") %>

            ]
        }
        ]
    });

	chart1.render();
	
	
	
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
	
	<style type="text/css">

	hr {
	margin-top: 20px;
	margin-bottom: 20px;
	
	border-style: dotted;
	}
	</style>

  </head>
 
 
 
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
		 				<div class="col-md-15">
                      	<div class="panel form-element-padding">
                        <div class="panel-heading">
                         <h4 align="center"><b>Statistiques</h4>
                        </div>
                        <div class="panel-body" align="center">
                        
                        
                        <form action="affichierSession2" method="post">
                        <font color="black" size="3">
                        
                         <table align="center">
                        <tr><td align="center" colspan="2">Année Universitaire
                        <c:if test="${not empty session }">
                        <td width="200">Session                        
                        </c:if>
                        <td>
                        <tr height="80" align="left"><td>
                        <c:if test="${empty aS}">
                        <select name="annee" id="annee" onchange="verifierDate()">                    
                        <c:forEach items="${annee}" var="a">  
                        <c:if test="${a!=0}">
                        <option value="${a}">${a}/${a+1}
                        </c:if>                       
                        </c:forEach>
                        </select>
                        <td>
                        <select name="annee2" id="annee2" onchange="verifierDate()">                    
                        <c:forEach items="${annee}" var="a">  
                        <c:if test="${a!=0}">
                        <option value="${a}">${a}/${a+1}
                        </c:if>                       
                        </c:forEach>
                        </select>
                        </c:if>
                        
                        
                        <c:if test="${ not empty aS}">
                        <select name="annee"  id="annee" onchange="verifierDate()" style="width:100%; box-sizing:border-box;">
                        <option value="${aS}"><c:if test="${aS==0}">Tous</c:if><c:if test="${aS!=0}">${aS}/${aS+1}</c:if>
                        </select>
                        <td>
                        <select name="annee2"  id="annee2" onchange="verifierDate()" style="width:100%; box-sizing:border-box;">
                        <option value="${aS2}"><c:if test="${aS2==0}">Tous</c:if><c:if test="${aS2!=0}">${aS2}/${aS2+1}</c:if>
                        </select>
                        </c:if>
                        <c:if test="${not empty session }">
                        <td><select name="session"><option value="Tous">Toutes 
                        <c:forEach items="${session}" var="s">
                        <option value="${s}">${s}
                        </c:forEach></select>
                        </c:if>
                        <c:if test="${empty session}">
                        <td height="75" width="150">
                        
                        
                        <button type="submit" name="bt1" id="bt1" class="btn ripple btn-outline btn-primary">
                        <div>
                        <span>Afficher</span>
                        <span class="ink animate" style="height: 117px; width: 120px; top: -45.5px; left: 18px;"></span></div>
                        </button>
                        
                        
                        </c:if>
                        <c:if test="${not empty session}">
                        <td width="150" height="75">
                        
                        
                        <button type="submit" name="bt2" class="btn ripple btn-outline btn-primary">
                        <div>
                        <span>Valider</span>
                        <span class="ink animate" style="height: 117px; width: 120px; top: -45.5px; left: 18px;"></span></div>
                        </button>
                        
                        
                        
                        </c:if>
                        <tr><td colspan="2"><div align="center" id="msgE"></div><td><td>
                        </table>
                        </font>
                        </form>
                        
                        <c:if test="${not empty teste}">
                       	<hr align="center" width="75%" color="black">
						<div id="chartContainer" style="height: 300px; width: 100%;"></div>
						<hr align="center" width="75%" color="black">
						<div id="chart1" style="height: 300px; width: 100%;"></div>
						</c:if>
		 				</div>
		 				
		 				</div>
		 				</div>
		 			
		 
		 </div>
  		

    
          
      </div>
      <script type="text/javascript">
	function verifierDate()
	{
		var a1 = document.getElementById('annee').value;
		var a2 = document.getElementById('annee2').value;
		
		//alert(a1+' '+a2);
		if(a1>a2)
			{
			document.getElementById('msgE').innerHTML = "<font color=red>Intervalle choisi est incorrecte</font>";
			document.getElementById('bt1').disabled = true;
			}else
				{
					document.getElementById('msgE').innerHTML = "";
					document.getElementById('bt1').disabled = false;
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