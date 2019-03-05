function getTypes()
{
    
    
    document.getElementById("s").innerHTML=sessionStorage.getItem("AlerteSalle");
    document.getElementById("m").innerHTML=sessionStorage.getItem("AlerteMat");
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var output1='<table width=270 border=0><tr height=70 align=center><td colspan=1><b>Type de l\'alerte';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/getTypes",function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1+= '<tr align=center><td><div class="widget uib_w_2 d-margins" data-uib="app_framework/checkbox" data-ver="2">'+
              '<input type="checkbox" id="'+value.id+'" value="'+value.id+'" onclick=afficherDescription(id)>'+
              '<label class="content-box" for="'+value.id+'">'+value.lib+'</label></div>'+
              '<input placeholder="Description" type=text id='+value.id+'D style=visibility:hidden;display:none;>';
        });
               output1+='<tr><td colspan="1" align="center"><a class="button widget uib_w_2 d-margins widthButton" data-uib="app_framework/button" data-ver="2"                  onclick="envoyer()">Envoyer</a></td></tr></table>';
                document.getElementById("types").innerHTML=output1;
    },{
                        headers: {
		                'Content-Type': 'application/json' , 
		                'Access-Control-Allow-Origin': '*',
		                'Access-Control-Allow-Methods': 'POST, GET, OPTIONS',
		                'Access-Control-Allow-Headers':'X-Requested-With'	
		            }
		        }
             );
}
function afficherDescription(id)
{
    //alert(id);
    
    if(document.getElementById(id).checked == true){
        document.getElementById(id+'D').style.visibility='visible';
        document.getElementById(id+'D').style.display="inline";
        }
    if(document.getElementById(id).checked == false){
        document.getElementById(id+'D').style.visibility='hidden';
        document.getElementById(id+'D').style.display="none";
    }
}

function envoyer() {
    var now = new Date();
    var annee   = now.getFullYear();
    var mois    = now.getMonth() + 1;
    var jour    = now.getDate();
    var heure   = now.getHours();
    var minute  = now.getMinutes();
    var c = document.getElementById("form").getElementsByTagName('input');
    for (var i = 0; i < c.length; i++) {
        if (c[i].type == 'checkbox') {
            if(c[i].checked == true){
                
               des = document.getElementById(c[i].value+'D').value;     
              // alert(c[i].value+' '+des);
               envoyerAlerte(annee+'-'+mois+'-'+jour,heure+':'+minute,c[i].value,sessionStorage.getItem("id"),des);
                
            }
            
        }
    }
    location.reload();
}
function envoyerAlerte(date,heure,type,id,desc)
{
   
    var ip = sessionStorage.getItem("ip");
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/ajouterAlerte",{id:id,date:date,heure:heure,type:type,description:desc},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){});
                 
                
    },{
		            headers: {
		                'Content-Type': 'application/json' , 
		                'Access-Control-Allow-Origin': '*',
		                'Access-Control-Allow-Methods': 'POST, GET, OPTIONS',
		                'Access-Control-Allow-Headers':'X-Requested-With'	
		            }
		        }
             );
            
    
}
function getAlerte()
{
    sessionStorage.setItem("VU",'1');
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var er = 0;
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/getAlertePourResp",{id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
        if(value.erreur!=null){
        output1+= '<h4><font color=red>'+ value.erreur +'</font></h4>';
            er=1;
        }else{
            output1+='<tr align=center><td>'+value.heure+'<td>'+value.salle+'<td>'+value.examen+'<td><font color=red><b>'+value.type+'</font>';
        }
        });
              
                if(er!=1)
                    output1='<table border=1 width=85%><tr height=30 align=center><td><b>Heure<td><b>Salle<td><b>Examen<td><b>Problème'+output1+'</table>';
                document.getElementById("liste").innerHTML=output1;
    },{
                        headers: {
		                'Content-Type': 'application/json' , 
		                'Access-Control-Allow-Origin': '*',
		                'Access-Control-Allow-Methods': 'POST, GET, OPTIONS',
		                'Access-Control-Allow-Headers':'X-Requested-With'	
		            }
		        }
             );
}