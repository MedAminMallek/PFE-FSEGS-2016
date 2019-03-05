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
							<!-- 
							<table align="left" border="1" width=100%>
							<tr align="center" height="65"><td colspan="3"><b>${titre}
							<% int x=1; int j=1; %>
							<tr height="50">
							<c:forEach items="${liste}" var="e">
							<% if(j==4){ j=1; %><tr height="50"><% } %><td width="33%">${e.enseignant} ${e.telMobile}<% j++; %>
							<%x++; %>
							</c:forEach>
                            </table>
                             -->
                             
                             <table align="left" border="1" width=100%>
                             	<tr align="center" height="65"><td colspan="2"><b>${titre}
                             	<tr align="center"><td><b>Nom de l'enseignant<td><b>Numéro de téléphone
                             	<c:forEach items="${liste}" var="e">
                             	<tr align="center"><td>${e.enseignant}<td>${e.telMobile}
                             	</c:forEach>
                             </table>
                            </font>
</body>
</html>