<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
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
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/asset/css/plugins/icheck/skins/flat/aero.css"/>
  <link href="<%= request.getContextPath()%>/resources/asset/css/style.css" rel="stylesheet">
  <!-- end: Css -->

  <link rel="shortcut icon" href="<%= request.getContextPath()%>/resources/asset/img/logofsegs.png">
  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
    </head>

    <body id="mimin">

      <div class="container">
		
        <form class="form-signin" method="post" action="authentificationParam">
          <div class="panel periodic-login">
              <span class="atomic-number"></span>
              <div class="panel-body text-center">
                  <h1 class="atomic-symbol"><img src="<%= request.getContextPath()%>/resources/asset/img/customLogo.png" height="100" width="100"></h1>
                  
                  
                  <p class="element-name">FSEGS</p>
					
                  <i class="icons icon-arrow-down"></i>
                  <div class="form-group form-animate-text" style="margin-top:40px !important;">
                    <input type="text" class="form-text" required name="nom" autocomplete="off">
                    <span class="bar"></span>
                    <label>Nom d'utilisateur</label>
                  </div>
                  <div class="form-group form-animate-text" style="margin-top:40px !important;">
                    <input type="password" class="form-text" required name="pass">
                    <span class="bar"></span>
                    <label>Mot de passe</label>
                  </div>
                  <font color="red" size="3" style="background-color: white;"><b>${msg}</b></font>
                  <% String x=""; %>
                  <c:if test="${not empty bloque}">
                  <%  x = "disabled=\"disabled\""; %>
                  </c:if>
                  <input type="submit" class="btn col-md-12" value="S'authentifier" <%= x %>/>
              </div>
                
          </div>
        </form>

      </div>

      <!-- end: Content -->
      <!-- start: Javascript -->
      <script src="<%= request.getContextPath()%>/resources/asset/js/jquery.min.js"></script>
      <script src="<%= request.getContextPath()%>/resources/asset/js/jquery.ui.min.js"></script>
      <script src="<%= request.getContextPath()%>/resources/asset/js/bootstrap.min.js"></script>

      <script src="<%= request.getContextPath()%>/resources/asset/js/plugins/moment.min.js"></script>
      <script src="<%= request.getContextPath()%>/resources/asset/js/plugins/icheck.min.js"></script>

      <!-- custom -->
      <script src="<%= request.getContextPath()%>/resources/asset/js/main.js"></script>
      <script type="text/javascript">
       $(document).ready(function(){
         $('input').iCheck({
          checkboxClass: 'icheckbox_flat-aero',
          radioClass: 'iradio_flat-aero'
        });
       });
     </script>
     <!-- end: Javascript -->
   </body>
   </html>