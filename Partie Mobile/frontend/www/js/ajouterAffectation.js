function ajouterAffectation(heure){
    
     var ip = sessionStorage.getItem("ip");
    var x = sessionStorage.getItem("dateJour");
    var id = sessionStorage.getItem("id");
    $.getJSON("http://"+ip+":8080/gestionvoeux/affectation",{date:x,heure:heure,id:id},{
                        headers: {
		                'Content-Type': 'application/json' , 
		                'Access-Control-Allow-Origin': '*',
		                'Access-Control-Allow-Methods': 'POST, GET, OPTIONS',
		                'Access-Control-Allow-Headers':'X-Requested-With'	
		            }
		        }
             );
   
   
       var s = sessionStorage.getItem("nbAff");
       var x = parseInt(s)+1;
       sessionStorage.setItem("nbAff",x); 

    window.location.replace("menu2.html");
   // window.location.replace("listeVoeux.html");
    
    
}
