function chargerSeancesAsupp(){
    
     var ip = sessionStorage.getItem("ip");
    //var x = sessionStorage.getItem("dateJour");
    var id = sessionStorage.getItem("id");
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeAffectation",{id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<li id='+value.date+'*'+value.heure +' align=center><b>' +value.date+' '+ value.heure +'</b></li>'; 
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
function supprimerChoix(id)
{
    //alert(id);
    var x = id.split('*');
     var ip = sessionStorage.getItem("ip");
     var id = sessionStorage.getItem("id");
    $.getJSON("http://"+ip+":8080/gestionvoeux/SupprimerAffectation",{date:x[0],heure:x[1],id:id},{
                        headers: {
		                'Content-Type': 'application/json' , 
		                'Access-Control-Allow-Origin': '*',
		                'Access-Control-Allow-Methods': 'POST, GET, OPTIONS',
		                'Access-Control-Allow-Headers':'X-Requested-With'	
		            }
		        }
             );
   
   
       var s = sessionStorage.getItem("nbAff");
       var x = parseInt(s)-1;
       sessionStorage.setItem("nbAff",x); 

    window.location.replace("listeVoeux.html");
}
