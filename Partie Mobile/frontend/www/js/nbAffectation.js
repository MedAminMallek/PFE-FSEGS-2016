function nbAff(){
    
     var ip = sessionStorage.getItem("ip");
    $.getJSON("http://"+ip+":8080/gestionvoeux/nbAff",{id:sessionStorage.getItem("id")},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    
        sessionStorage.setItem("nbAff",value.nb);
        
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
    
}
function go()
{
     
    window.location.replace("grille.html");
   // window.location.replace("ajoutervoeu.html");
}
function gg()
{
    window.location.replace("supprimerSeance.html");
}
function goP()
{
    window.location.replace("demandePermutation.html");
    //window.location.replace("ListeSeancesS.html");
}
function goDemande()
{
    window.location.replace("EtatDeamande.html");
}
function teste()
{
    
    //document.getElementById("bt1").style.display='block'; 
    
    document.getElementById("vClick").innerHTML='<li><a class="" id="bt1"><i class="icon-user"></i>Choisir Voeux</a></li>';
    //alert(document.getElementById("bt1").textContent);
    loadjournée();
    loadjournéeSeance();
    
    var ip = sessionStorage.getItem("ip");
    var x = sessionStorage.getItem("nbAff");
    var y =sessionStorage.getItem("nbS");
    var nbAFF = parseInt(x);
    var nbS = parseInt(y);
    //if(nbAFF>=nbS)
        //document.getElementById("bt1").disabled=true;
   // if(nbAFF<1)
        //document.getElementById("bt2").disabled=true;
    var id = sessionStorage.getItem("id");
    var xy = '<h5>Votre Charge de surveillances :'+y+' séances</h5>';
    var xx = '<h5>Vous avez choisi :'+x+' séances</h5>';
    document.getElementById("ici").innerHTML=xy+xx;
    if(sessionStorage.getItem("resp")==1){
    var output1='<table><tr><td><h4>Vos épreuves:</h4>';
    var result = new Array();
    var json_obj = new Array();
    
    $.getJSON("http://"+ip+":8080/gestionvoeux/matiereRes",{id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<tr><td><h5>'+value.date+' '+value.heure+' '+value.ab+'</h5>'; 
        });
                    output1+='</table>'
               document.getElementById("ici2").innerHTML=output1;
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
}
       
    