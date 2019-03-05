function chargerSeancesSature(){
    
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var output1='';
    var i=0;
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeSeanceSature",{id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<option>' +value.date+' '+value.heure +'</option>'; 
    document.forms.f.dmc.options[i]=new Option(value.date+' '+value.heure,value.date+' '+value.heure);    
        i++;
        });
                    
                //alert(output1);
               document.getElementById('d').innerHTML=output1;
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
function listeSeanceAprop()
{
    
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var i=0;
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeAffectationAProp",{id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<option>' +value.date+' '+value.heure +'</option>';
        
     document.forms.f.dmc2.options[i]=new Option(value.date+' '+value.heure,value.date+' '+value.heure);    
        i++;
        
          
        });
                    
                // alert(output1);
                document.getElementById('p').innerHTML=output1;
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
function supp(id)
{
    
    document.getElementById(id).value='';
}
function getListeEnseignant()
{
    var seanceD = document.getElementById('1').value;
    var seanceP = document.getElementById('2').value;
    if(seanceD != '' && seanceP != '')
        {
    
    var idSeanceD = seanceD.split(' ')[0]+'M'+seanceD.split(' ')[1];
    var idSeanceP = seanceP.split(' ')[0]+'M'+seanceP.split(' ')[1];
    
    sessionStorage.setItem('seanceDemandee',idSeanceD);
    sessionStorage.setItem('seanceProposee',idSeanceP);        
    
    window.location.replace("listeEnseignants.html");
    
            
    //alert(idSeanceD+" "+idSeanceP);
            
        }else
            document.getElementById('alerte').innerHTML='<font color=red><b>Il faut remplir tous les champs</b></font>';
}
function chargerListeEns(bl)
{
     var refreshIntervalId = setInterval("blink(bl)",500);
    var sd = sessionStorage.getItem('seanceDemandee');
    var sp = sessionStorage.getItem('seanceProposee');
    var ip = sessionStorage.getItem("ip");
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeEns2",{idSeanceD:sd,idSeanceP:sp},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<li id='+ value.id+' onClick="ajouterDemande(id)"><b>' +value.enseignant+'</b></li>'; 
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
function ajouterDemande(idR)
{
    
    var ip = sessionStorage.getItem("ip");
    var idseanceD = sessionStorage.getItem("seanceDemandee");
    var idseanceP = sessionStorage.getItem("seanceProposee");
    var id = sessionStorage.getItem("id");
    //alert("SeanceDemandé: "+idseance);
    //alert("Id Recepteur: "+ idRecepteur);
    //alert("Seance proposé: "+idSp);
    //alert("Id Emetteur: "+id);
    
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/ajouterDemande",{idR:idR,seanceP:idseanceP,idE:id,seanceD:idseanceD},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += value.da;
        });
                    
        
        if(output1==1)
          alert("L'enseignant que vous avez choisit est déja affecter à cette séance");
        else{
          alert("Demande Envoyeé");
            window.location.replace("menu2.html");
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