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

 <body onload="categoryOnClick()">
      <!-- start: Header -->
        <%@include file="header1.jsp" %>
      <!-- end: Header -->

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


      <div class="container-fluid mimin-wrapper">
  
          <!-- start:Left Menu -->            
          <%@include file="Menu1.jsp" %>
          
          <!-- end: Left Menu -->
          
         
         <form action="modifierAffectationEns" method="post" onsubmit="xx()">
		 <div id="content">
		       
		       <font color="black" size="4">
                    
		 			<div class="col-md-15">
                      <div class="panel form-element-padding">
                        <div class="panel-heading">
                         <h4 align="center"><b>Affecations</h4>
                        </div>
                         <div class="panel-body" style="padding-bottom:30px;">
                          <div class="col-md-12">
                           <h2 align="center">${enseignant.enseignant}</h2>
                           <br>
                           <h4 align="center">Charge de surveillances:<div id="C"></div></h4>
                           <table align="center" border="1" width=30%>
                           <tr align="center" bgcolor="#D8D8D8"><td>
                           <c:forEach items="${heures}" var="h">
                           <td>${h.toString().split(':')[0]}:${h.toString().split(':')[1]}
                           </c:forEach>
                           </tr>
                           <c:forEach items="${grille}" var="g">
                           
                           <tr align="center" bgcolor="#D8D8D8"><td>${g.getDate2()}
                           <c:forEach items="${heures}" var="h">
                           	 <c:forEach items="${g.seances}" var="s">
                           		<c:if test="${s.heureDebut.equals(h)}">
                           			<c:if test="${s.responsable}">
                           				<c:if test="${s.affecter}">
                           					
                           					<td bgcolor="blue">
                           					<input type="checkbox" class="checkbox" name="seance[]" value="${g.date}I${s.heureDebut}" checked="checked" onclick="categoryOnClick()">
                           					
                           				</c:if>
                           				<c:if test="${s.affecter==false}">
                           					<c:if test="${s.saturer}">
                           						<td bgcolor="blue"><input type="checkbox" name="seance[]" value="${g.date}I${s.heureDebut}" disabled="disabled" onclick="categoryOnClick()">
                           					</c:if>
                           					<c:if test="${s.saturer==false}">
                           						<td bgcolor="blue"><input type="checkbox" name="seance[]" value="${g.date}I${s.heureDebut}" onclick="categoryOnClick()">
                           					</c:if>
                           				</c:if>
                           			</c:if>
                           			<c:if test="${s.responsable==false}"> 
                           			                          			
                           			<c:if test="${s.ens==false}">
                           			<% String c="white"; %>
                           			<c:if test="${s.emp==true}">
                           				<% c = "#green"; %>
                           			</c:if>
                           			<c:if test="${s.empM==true}">
                           				<% c = "#088A08"; %>
                           			</c:if>
                           			<c:if test="${s.affecter}">
                           					<td bgcolor="<% out.print(c);%>"><input type="checkbox" name="seance[]" value="${g.date}I${s.heureDebut}" checked="checked" onclick="categoryOnClick()">
                           			</c:if>
                           			<c:if test="${s.affecter==false}">
                           					<c:if test="${s.saturer}">
                           						<td bgcolor="<%=c%>"><input type="checkbox" name="seance[]" value="${g.date}I${s.heureDebut}" disabled="disabled" onclick="categoryOnClick()">
                           					</c:if>
                           					<c:if test="${s.saturer==false}">
                           						<td bgcolor="<%=c%>"><input type="checkbox" name="seance[]" value="${g.date}I${s.heureDebut}" onclick="categoryOnClick()">
                           					</c:if>
                           			</c:if>
                           			</c:if>
                           			
                           			<c:if test="${s.ens}">
                           			<c:if test="${s.affecter}">
                           					<td bgcolor="#58ACFA"><input type="checkbox" name="seance[]" value="${g.date}I${s.heureDebut}" checked="checked" onclick="categoryOnClick()">
                           			</c:if>
                           			<c:if test="${s.affecter==false}">
                           					<c:if test="${s.saturer}">
                           						<td bgcolor="#58ACFA"><input type="checkbox" name="seance[]" value="${g.date}I${s.heureDebut}" disabled="disabled" onclick="categoryOnClick()">
                           					</c:if>
                           					<c:if test="${s.saturer==false}">
                           						<td bgcolor="#58ACFA"><input type="checkbox" name="seance[]" value="${g.date}I${s.heureDebut}" onclick="categoryOnClick()">
                           					</c:if>
                           			</c:if>
                           			</c:if>
                           			
                           			</c:if>
                           		</c:if>
                           		</c:forEach>
                           </c:forEach>
                           </c:forEach>
                           </table>
                           <br>
                           <table border="1" align=center width=30%>
                           <tr bgcolor="#D8D8D8"><td>Examen<td bgcolor="blue"><font color="white">Responsable</font><td bgcolor="#58ACFA">Impliquer</tr>
                           <tr bgcolor="#D8D8D8"><td>Emploie<td bgcolor="#green">Licence<td bgcolor="#088A08">Master</tr>
                           </table>
                           <br>
                           <center>
                           <div><input type="checkbox" name="email"> Envoyer un mail </div>
                            <button class="btn ripple-infinite btn-gradient btn-info" id="bt1">
                                    <div>
                                      <span onclick="xx()">Valider</span>
                                    </div>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                    </font>
		 </div>
		 <input type="text" value="${enseignant.id}" name="id" id="id">
		 </form>
  		
		
		</div>
		
		
	
	
		<script type="text/javascript">
		function categoryOnClick() {
			document.getElementById("id").style.visibility = "hidden";
			
		    var rows = document.getElementsByName('seance[]');
		    var selectedRows = [];
		    for (var i = 0, l = rows.length; i < l; i++) {
		        if (rows[i].checked) {
		            selectedRows.push(rows[i]);
		        }
		    }
		    document.getElementById("C").innerHTML=selectedRows.length+'/'+${enseignant.aSruveiller};
		    var x = selectedRows.length;
		    
		    var y = ${enseignant.aSruveiller};
		    if(x>y){
		    	document.getElementById("bt1").disabled= true;
		    	document.getElementById("C").style.color = "red";
		    	document.getElementById("C").style.fontWeight = "900";
		    }else{
		    	
		    	document.getElementById("bt1").disabled= false;
		    	document.getElementById("C").style.color = "black";
		    	document.getElementById("C").style.fontWeight = "300";
		    }
		    //alert(selectedRows.length);
		}
		function xx(){
			
			var rows = document.getElementsByName('seance[]');
		    var selectedRows = [];
		    for (var i = 0, l = rows.length; i < l; i++) {
		        if (rows[i].checked) {
		            selectedRows.push(rows[i]);
		        }
		    }
		    var x = selectedRows.length;
		    var y = ${enseignant.aSruveiller};
		    if(x<y)
		    	alert("La charge de surveillance est incompléte !");
		    	
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