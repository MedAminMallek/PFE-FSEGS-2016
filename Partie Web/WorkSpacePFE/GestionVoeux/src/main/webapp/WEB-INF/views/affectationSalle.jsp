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
    <style type="text/css"> 
		TABLE { 
		float : left; 
		
		margin-left:6%; 
    	margin-right:5%;
		}
		</style>
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
  	-webkit-transform: scale(3); /* Safari and Chrome */
  	-o-transform: scale(1.5); /* Opera */
 	 padding: 10px;
	}
  </style>


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
                         <h4 align="center"><b>Affecations Aux Salles</h4>
                        </div>
                         <div class="panel-body" style="padding-bottom:30px;">
                          <div class="col-md-12">
                            <datalist id="rempl">
                            	<c:forEach items="${listeR}" var="e">
  								<option value="${e.enseignant}">
  								</c:forEach>
							</datalist>
                            <datalist id="enseignant">
                            	<c:forEach items="${listeE}" var="e">
  								<option value="${e.enseignant}">
  								</c:forEach>
							</datalist>
                            <br>
                            <br>
                            <form action="ajouterAffectation?date=${Date}" method="post">
                            <h3 align="center">${Date2}</h4>
                            
                            <br>
                            <h4 style="margin-left:6%;"><font color="red">${MSG}</font></h4>
                            <h4 style="margin-left:6%;"><font color="red">${MSGA}</font></h4>
                            <br>
                            <table border="1" width=50%>
                            <tr bgcolor="#E6E6E6" align="center"><td colspan="2">Salle
                            <td>Section
                            <td colspan="2">Enseignant
                            <td>Remplacement
                            <td>
                            <c:forEach items="${listeS}" var="s">
                            
							<tr bgcolor="#E6E6E6">
							
							<td width="80" align="center">${s.examen.salle.id}
							<td>${s.nbAff}/${s.examen.salle.nbSurveillant}
							<td>${s.examen.matiereEnseignement.filiere.id} ${s.examen.matiereEnseignement.cycleAnnee.id}
							<td>${s.examen.salle.capaciteExamen}
							<td><input autocomplete="off" type="text" list="enseignant" name="NX${s.examen.salle.id}X${s.examen.matiereEnseignement.filiere.id}X${s.examen.matiereEnseignement.cycleAnnee.id}X${s.examen.matiereEnseignement.matiere.id}">
							<br><input autocomplete="off" style="visibility: hidden;display: none;" type="text" placeholder="Remplaçant" id="RM${s.examen.salle.id}M${s.examen.matiereEnseignement.filiere.id}M${s.examen.matiereEnseignement.cycleAnnee.id}M${s.examen.matiereEnseignement.matiere.id}" name="RM${s.examen.salle.id}M${s.examen.matiereEnseignement.filiere.id}M${s.examen.matiereEnseignement.cycleAnnee.id}M${s.examen.matiereEnseignement.matiere.id}" list="rempl">
							<!-- 
							<div class="si-wrapper">
							<input type="text" class="si-input" lang="ar" list="enseignant">
							<button class="si-btn">
					
							<span class="icons icon-microphone"></span>
							</button>
							</div>
							 -->
							<td align="center">
							<input type="checkbox" name="M${s.examen.salle.id}M${s.examen.matiereEnseignement.filiere.id}M${s.examen.matiereEnseignement.cycleAnnee.id}M${s.examen.matiereEnseignement.matiere.id}" onclick="remp(name)">
							
							<td><input onKeyPress="x()" type="submit" name="${s.examen.salle.id}" value="Affecter">
							</td></tr>
                            </c:forEach>
                            </table>
                            <table border="1">
                            <tr bgcolor="#E6E6E6" align=center><td colspan="3">Liste des affectations
                            <tr bgcolor="#E6E6E6" align=center><td>Salle<td>Section<td>Enseignant
                            <%int x=1;  %>
                            <c:forEach items="${listeA}" var="a">
                            	<% 
                            		if(x>2) x=1;
                            	%>
                            	
                            	<tr align=center bgcolor="<%if(x==1)out.print("#00FF40");else out.print("#58D3F7");%>"><td>${a.affectation.salleExamen.id}<td>${a.affectation.filiere.id}${a.affectation.cycleAnnee.id}<td>${a.affectation.surveillant.enseignant}
                            	<br>(${a.affectation.heureReelAff.toString().split(":")[0]}:${a.affectation.heureReelAff.toString().split(":")[1]})     	
                            	
                            	<c:if test="${empty a.remp.remplacant}">
                            	<td rowspan="1" bgcolor="#E6E6E6"><b><a href="supprimerAffectation?date=${Date}&id=${a.affectation.surveillant.id}&dd=${Date}">X</a></b>
                            	</c:if>
                            	
                            	<c:if test="${not empty a.remp.remplacant}">
                            	<td bgcolor="#E6E6E6" rowspan="2"><b><a href="supprimerAffectation?date=${Date}&id=${a.affectation.surveillant.id}&dd=${Date}">X</a></b>
                            	<tr bgcolor="<%if(x==1)out.print("#00FF40");else out.print("#58D3F7");%>"><td colspan="2" align="center">Remplacé par<td align="center">${a.remp.remplacant.enseignant}
                            	</c:if>
                            	<% x++; %>
                            </c:forEach>
                            <tr><td colspan="3" align="center"><a href="imprimerAffectation?date=${Date}" target="XX">Imprimer</a></td></tr>
                            </table>
                            
                            
                            
                            </form>
                          </div>
                        </div>
                      </div>
                    </div>
                    </font>
		 </div>
		 
  		
		
		</div>
		<script type="text/javascript">
		function remp(id)
		{
			if(document.getElementById('R'+id).style.visibility=="hidden"){
				document.getElementById('R'+id).style.visibility="visible";
				document.getElementById('R'+id).style.display="inline";
				document.getElementById('R'+id).select();
				document.getElementById('R'+id).required = true;
			}else{
				document.getElementById('R'+id).style.visibility="hidden";
				document.getElementById('R'+id).style.display="none";
				
					document.getElementById('R'+id).required = false;
			}
		}
		
		</script>
		
		
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
		<script type="text/javascript">
		$(document).ready(function() {
		    $('form:first *:input[type!=hidden]:first').focus();
		});
		</script>
		<script type="text/javascript">
		function x()
		{
  			//if(window.event.keyCode == 13)
			
		}
	</script>
	
	
		
		
    
    
    
    
    <script type="text/javascript">
/*global webkitSpeechRecognition */

(function() {
	'use strict';

	if (! ('webkitSpeechRecognition' in window) ) return;

	var talkMsg = 'start talking';
	var patience = 6;

	function capitalize(str) {
		return str.length ? str[0].toUpperCase() + str.slice(1) : str;
	}

	var speechInputWrappers = document.getElementsByClassName('si-wrapper');

	[].forEach.call(speechInputWrappers, function(speechInputWrapper) {
		// find elements
		var inputEl = speechInputWrapper.querySelector('.si-input');
		var micBtn = speechInputWrapper.querySelector('.si-btn');

		// size and position them
		var inputHeight = inputEl.offsetHeight;
		var inputRightBorder = parseInt(getComputedStyle(inputEl).borderRightWidth, 10);
		var buttonSize = 0.8 * inputHeight;
		micBtn.style.top = 0.1 * inputHeight + 'px';
		micBtn.style.height = micBtn.style.width = buttonSize + 'px';
		inputEl.style.paddingRight = buttonSize - inputRightBorder + 'px';
		speechInputWrapper.appendChild(micBtn);

		// setup recognition
		var finalTranscript = '';
		var recognizing = false;
		var timeout;
		var oldPlaceholder = null;
		var recognition = new webkitSpeechRecognition();
		recognition.continuous = true;

		function restartTimer() {
			timeout = setTimeout(function() {
				recognition.stop();
			}, patience * 1000);
		}

		recognition.onstart = function() {
			oldPlaceholder = inputEl.placeholder;
			inputEl.placeholder = talkMsg;
			recognizing = true;
			micBtn.classList.add('listening');
			restartTimer();
		};

		recognition.onend = function() {
			recognizing = false;
			clearTimeout(timeout);
			micBtn.classList.remove('listening');
			if (oldPlaceholder !== null) inputEl.placeholder = oldPlaceholder;
		};

		recognition.onresult = function(event) {
			clearTimeout(timeout);
			for (var i = event.resultIndex; i < event.results.length; ++i) {
				if (event.results[i].isFinal) {
					finalTranscript += event.results[i][0].transcript;
				}
			}
			finalTranscript = capitalize(finalTranscript);
			inputEl.value = finalTranscript;
			restartTimer();
		};

		micBtn.addEventListener('click', function(event) {
			event.preventDefault();
			if (recognizing) {
				recognition.stop();
				return;
			}
			inputEl.value = finalTranscript = '';
			recognition.start();
		}, false);
	});
})();
</script>
		
	<style>
	.si-wrapper {
	display: inline-block;
	position: relative;
}

.si-wrapper input {
	margin: 0;
}

.si-wrapper button {
	position: absolute;
	top: 0;
	right: 0;
	height: 18px;
	width: 18px;
	margin: 0;
	border: 0;
	padding: 0;
	background: none;
	
}

.si-mic,
.si-mic:after,
.si-holder,
.si-holder:before,
.si-holder:after {
	position: absolute;
	background: #333;
}

/* Microphone icon */
.si-mic {
	display: block;
	height: 25%; /* 8px / 32px */
	top: 9.375%; /* 3px / 32px */
	left: 37.5%; /* 12px / 32px */
	right: 37.5%; /* 12px / 32px */
	-webkit-border-radius: 99px 99px 0 0;
	-moz-border-radius: 99px 99px 0 0;
	border-radius: 99px 99px 0 0;
}

.si-mic:before,
.si-mic:after,
.si-holder {
	-webkit-border-radius: 0 0 99px 99px;
	-moz-border-radius: 0 0 99px 99px;
	border-radius: 0 0 99px 99px;
}

.si-mic:before {
	position: absolute;
	z-index: 1;
	content: '';
	width: 150%; /* 12px / 8px */
	height: 137.5%; /* 11px / 8px */
	top: 100%; /* 8px / 8px */
	left: -25%; /* -2px / 8px */
	background: #fff;
}

.si-mic:after {
	z-index: 1;
	content: '';
	width: 100%; /* 10px / 10px */
	height: 100%; /* 10px / 10px */
	top: 110%; /* 11px / 10px */
	left: 0;
}

.si-holder {
	display: block;
	height: 40.625%; /* 13px / 32px */
	width: 50%; /* 16px / 32px */
	left: 25%; /* 8px / 32px */
	top: 37.5%; /* 12px / 32px */
}

.si-holder:after {
	content: '';
	width: 66.666%; /* 8px / 16px */
	height: 18.182%; /* 2px / 13px */
	bottom: -30.769%; /* -4px / 13px */
	left: 16.667%; /* 2px / 16px */
}

.si-holder:before {
	content: '';
	width: 33.333%; /* 4px / 16px */
	height: 27.273%; /* 3px / 13px */
	top: 92.308%; /* 12px / 13px */
	left: 33.333%; /* 4px / 16px */
}
	</style>	
    
    
    
    
    
    
          
      
	

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