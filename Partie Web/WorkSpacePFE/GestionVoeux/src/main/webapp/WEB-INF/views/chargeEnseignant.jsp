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



      <div class="container-fluid mimin-wrapper">
  
          <!-- start:Left Menu -->            
          <%@include file="Menu1.jsp" %>
          
          <!-- end: Left Menu -->
          
          
         <form action="miseAjourCharge" method="post">
		 <div id="content">
		 <br>
		 	<font color="black" size="4">
		 	<div class="col-md-15">
                      <div class="panel form-element-padding">
                        <div class="panel-heading">
                         <h4 align="center"><b>Charge De Surveillances</h4>
                        </div>
                         <div class="panel-body" style="padding-bottom:30px;">
                          <div class="col-md-12">
                            <table align="center" border="1">
                            <tr><td width="300">Nom du surveillant:</td>
                            <td width="200"><input type="text"  id="search" placeholder="Taper pour chercher"></td></tr>
                            </table>
                            <br>
                           <table align="center">
                           <tr>
                           <td width="300">Enseignant</td><td width="300" align="right">Nombre des séances</td>
                           </tr>
                           <tr><td></td><td></td></tr>
                           </table>
                           <table id="tableData" align="center">
                           <tbody>
                           <c:forEach items="${charges}" var="c">
                           	<tr>
                           		<td width="400">${c.enseignant}</td>
                           		<td width="350" align="right"><input type="number" min="0" name="${c.id}" value="${c.aSruveiller}" required="required" style="width:60px;"></td>
                           	</tr>
                           </c:forEach>
                           </tbody>
                           </table>
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
		 </div>
		 
  		</form>
		
		
		
		
		</div>
	
        <br><br>
        <br><br>
        <br><br>
		
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
	<script type="text/javascript">
	var $rows = $('#tableData tr');
	$('#search').keyup(function() {
	    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

	    $rows.show().filter(function() {
	        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
	        return !~text.indexOf(val);
	    }).hide();
	});
	</script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resources/theme1/paging.js"></script> 
<script type="text/javascript">
            $(document).ready(function() {
                $('#tableData').paging({
                	limit:8
                	
                });
            });
        </script>
        <script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<style type="text/css">

.paging-nav {
  text-align: right;
  padding-top: 2px;
}

.paging-nav a {
  margin: auto 1px;
  text-decoration: none;
  display: inline-block;
  padding: 5px 10px;
  background: #91b9e6;
  color: white;
  border-radius: 100px;
}

.paging-nav .selected-page {
  background: #187ed5;
  font-weight: bold;
}

.paging-nav,
#tableData {
  width: 600px;
  margin: 0 auto;
  font-family: Arial, sans-serif;
}
</style>

    <!-- start: Javascript --><!-- 
    <script src="<%= request.getContextPath()%>/resources/asset/js/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/resources/asset/js/jquery.ui.min.js"></script> -->
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