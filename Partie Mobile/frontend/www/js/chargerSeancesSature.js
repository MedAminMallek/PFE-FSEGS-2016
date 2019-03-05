function chargerSeancesSature(){
    
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeSeanceSature",{id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<li id='+ value.date+'M'+value.heure+' onclick=goToListeEns(id)><b>' +value.date+' '+value.heure +'</b></li>'; 
        });
                    
                //alert(output1);
                 $('#list').html(output1);
               $("#list").listview("refresh");
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

function blink(ob) 
{ 
if (ob.style.visibility == "visible" ) 
{ 
  ob.style.visibility = "hidden"; 
} 
else 
{ 
  ob.style.visibility = "visible"; 
} 
}
function goEnseignanats(ob)
{

    
    
    
    var refreshIntervalId = setInterval("blink(bl)",500);
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("idSeance")
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeEns",{idSeance:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<li id='+ value.id+' onclick=goListeSeanceProp(id)><b>' +value.enseignant+'</b></li>'; 
        });
                    
                
                //alert(output1);
               clearInterval(refreshIntervalId);
               document.getElementById("bl").style.visibility="hidden";
               $('#list').html(output1);
               $("#list").listview("refresh");
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
function goToListeEns(id)
{
    sessionStorage.setItem("idSeance",id);
    window.location.replace("listeEnseignants.html");   
}
function goListeSeanceProp(id)
{
    sessionStorage.setItem("idrecepteur",id);
    window.location.replace("listeseanceProp.html");
}
function listeSeanceAprop()
{
    
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeAffectationAProp",{id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<li id='+value.date+'P'+value.heure +' align=center onclick=ajouterDemande(id)><b>' +value.date+' '+ value.heure +'</b></li>'; 
        });
                    
                // alert(output1);
                 $('#list').html(output1);
               $("#list").listview("refresh");
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
function ajouterDemande(idSp)
{
    
    var ip = sessionStorage.getItem("ip");
    var idseance = sessionStorage.getItem("idSeance");
    var idRecepteur = sessionStorage.getItem("idrecepteur");
    var id = sessionStorage.getItem("id");
    //alert("SeanceDemandé: "+idseance);
    //alert("Id Recepteur: "+ idRecepteur);
    //alert("Seance proposé: "+idSp);
    //alert("Id Emetteur: "+id);
    
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/ajouterDemande",{idR:idRecepteur,seanceP:idSp,idE:id,seanceD:idseance},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += value.da;
        });
                    
        
        if(output1==1)
          alert("L'enseignant que vous avez choisit est déja affecter à cette séance");
        else{
          alert("Demande Envoyeé");
            window.location.replace("listeVoeux.html");
            }
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
function demandeE()
{
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var x =0;
    var output1='<table border=1 bgcolor=#D8D8D8 align=center><tr><td colspan=3 align=center height=35><b>Demandes Envoyées<tr><td><b>Enseignant<td><b>Seance Demandée<td><b>Seance proposée</tr>';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeDemandeEnv",{id:id},function(data){
        json_obj = data;  
    $.each(json_obj, function(index, value){
        x=1;
    output1 += '<tr><td>'+value.nomR+'<td>'+value.dateD+' '+value.heureD+'<td>'+value.dateP+' '+value.heureP;
        });
        output1+='</table>';
        if(x==1){
        document.getElementById("DemandeE").innerHTML=output1;
       // document.getElementById("ligne").innerHTML='<hr width="75%" size=5 align=center color="black">';
        }
    },{
                        headers: {
		                'Content-Type': 'application/json' , 
		                'Access-Control-Allow-Origin': '*',
		                'Access-Control-Allow-Methods': 'POST, GET, OPTIONS',
		                'Access-Control-Allow-Headers':'X-Requested-With'	
		            }
		        }
             );
    demandeR();
}
function demandeR()
{
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var y=0;
    
    var output1='<table border=1 bgcolor=#D8D8D8 align=center><tr><td colspan=3 align=center height=35><b>Demandes Reçues<tr><td><b>Enseignant<td><b>Seance Demandée<td><b>Seance proposée</tr>';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeDemandeRec",{id:id},function(data){
        json_obj = data;  
    $.each(json_obj, function(index, value){
        y=1;
        output1 += '<tr><td>'+value.nomE+'<td>'+value.dateD+' '+value.heureD+'<td>'+value.dateP+' '+value.heureP;
        var idbtn=value.idE+'X'+value.dateP+'X'+value.heureP+'X'+value.idR+'X'+value.dateD+'X'+value.heureD;
        output1+='<tr><td colspan=3 align=center><input type=button value=Accepter id='+idbtn+'% onclick=updateD(id)> <input type=button value=Refuser id='+idbtn+'* onclick=updateD(id)></td></tr>';
        });
        output1+='</table>';
        if(y==1)
        document.getElementById("DemandeR").innerHTML=output1;
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
function updateD(idDemande)
{
    //alert(document.getElementById(id).value);
    if(document.getElementById(idDemande).value=='Refuser')
    var x = idDemande.split('*');
    else
    var x = idDemande.split('%');
    //alert(x[0]);
    var xx=x[0];
    var etat = document.getElementById(idDemande).value;
    //alert(xx);
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/updateDeamnde",{demandeS:xx,etat:etat},function(data){
        json_obj = data;  
    $.each(json_obj, function(index, value){
        //alert(value.t);
         
        });
            },{
                        headers: {
		                'Content-Type': 'application/json' , 
		                'Access-Control-Allow-Origin': '*',
		                'Access-Control-Allow-Methods': 'POST, GET, OPTIONS',
		                'Access-Control-Allow-Headers':'X-Requested-With'	
		            }
		        }
             );
    window.location.replace("menu2.html");
}