function go(){
    var ip = sessionStorage.getItem("ip");
    
    $.getJSON("http://"+ip+":8080/gestionvoeux/nbAff",{id:sessionStorage.getItem("id")},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    
       // document.cookie="nbAff="+value.nb+";";
       
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
    window.location.replace("menu2.html");
   // window.location.replace("listeVoeux.html");
}
function goDeclacherAlerte()
    {
    
    var ip = sessionStorage.getItem("ip");
     var now = new Date();
     var annee   = now.getFullYear();
     var mois    = now.getMonth() + 1;
     var jour    = now.getDate();
     var heure   = now.getHours();
     var minute  = now.getMinutes();
    var date = annee+'-'+mois+'-'+jour;
    var h = heure+':'+minute;
    $.getJSON("http://"+ip+":8080/gestionvoeux/enSeance",{id:sessionStorage.getItem("id"),date:date,heure:h},function(data){
        json_obj = data;   
    $.each(json_obj, function(index, value){
        
        if(value.EnSeance=='true'){
            var salle = value.salle;
            var matiere = value.matiere;
            sessionStorage.setItem("AlerteSalle",salle);
            sessionStorage.setItem("AlerteMat",matiere);
            window.location.replace("ajouterAlerte.html");
            }
        else
        alert('Vous n\'etes pas affecté à une salle en ce moment');
        
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
        //window.location.replace("ajouterAlerte.html");
    }
    function goConsulter()
    {
        window.location.replace("consulterAlerte.html");
    }
function ecrireNom()
{
    var nom = sessionStorage.getItem("nom");
    document.getElementById("nom").innerHTML="Bienvenu <br>"+nom;
}
function goEpreuves()
{
     window.location.replace("Epreuves.html");
}
function goCompte()
{
    window.location.replace("compte.html");
}