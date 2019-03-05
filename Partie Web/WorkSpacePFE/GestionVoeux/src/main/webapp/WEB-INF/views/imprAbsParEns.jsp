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
							<div align="left" style="margin: 0cm 0cm 1cm 2cm;"></div>
							</font>
							<font size="4.5">
							<h2 align="center">Liste des absences</h2>
							<table border="0" align="left" width="50%">
							
							<tr align="left"><td colspan="1"><b>${ens}
							<!-- <tr align="center"><td>Journée<td>Séance -->
							<c:forEach items="${liste}" var="a">
							<tr align="left"><td>${a.date()} à ${a.heure()}
							</c:forEach>
							</table> 
                             
                            </font>
</body>
</html>