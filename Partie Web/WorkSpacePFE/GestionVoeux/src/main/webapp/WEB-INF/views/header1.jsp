<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<nav class="navbar navbar-default header navbar-fixed-top">
          <div class="col-md-12 nav-wrapper">
            <div class="navbar-header" style="width:100%;">
              <div class="opener-left-menu is-open">
                <span class="top"></span>
                <span class="middle"></span>
                <span class="bottom"></span>
              </div>
                <a href="index.html" class="navbar-brand">
                                 
                 <b>Système De Suivi De La Surveillance</b>
                </a>


              <ul class="nav navbar-nav navbar-right user-nav">
                
                  <li class="dropdown avatar-dropdown">
                   
                   <img src="<%= request.getContextPath()%>/resources/asset/img/logOut.jpg" class="img-circle avatar" alt="user name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"/>
                   
                  </li>
                  <li>
                
                  <a href="deconnexion" class="opener-right-menu">Déconnexion</a></li>
              </ul>
            </div>
          </div>
        </nav>
</body>
</html>