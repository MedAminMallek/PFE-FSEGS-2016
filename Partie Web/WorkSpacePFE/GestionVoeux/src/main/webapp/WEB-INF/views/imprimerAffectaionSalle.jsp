<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Impression</title>
</head>
<body onload="window.print()">
							<font size="4"><div align="left">Faculté des Sciences Economiques et de gestion-Sfax</div>
							<div align="right">Année Universitaire ${AU}</div>
							<div align="right">Session ${session}</div>
							<div align="left" style="margin: 0cm 0cm 1cm 2cm;">${date}</div>
							</font>
							<font size="4.5">
							<table align="center" border="1" style="width: 100%">
                            
                            <tr height="50" align=center><td rowspan="2">Salle<td rowspan="2">Section<td width="600" colspan="2">Enseignant<td rowspan="2" width=250>Signature<td rowspan="2">Nbr.Copies
                            <tr align="center"><td>Surveillant<td>Remplaçant
                            
                            <c:forEach items="${listeA}" var="a">
                            	
                            	
                            	<c:if test="${empty a.remp.remplacant}">
                            	<tr height="40" align=center><td>${a.affectation.salleExamen.id}<td>${a.affectation.filiere.id}${a.affectation.cycleAnnee.id}<td align="left"> ${a.affectation.surveillant.enseignant}<td bgcolor="black"><td><td>     	
                            	</c:if>
                            	<c:if test="${not empty a.remp.remplacant}">
                            	<tr height="40" align=center><td>${a.affectation.salleExamen.id}<td>${a.affectation.filiere.id}${a.affectation.cycleAnnee.id}<td align="left" width="300"> ${a.affectation.surveillant.enseignant} <td width="300" align="left"> ${a.remp.remplacant.enseignant}<td><td>    	
                            	</c:if>
                            	
                            	
                            	
                            </c:forEach>
                            </table>
                            </font>
</body>
</html>