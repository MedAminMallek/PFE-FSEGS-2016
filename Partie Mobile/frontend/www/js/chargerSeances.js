function chargerSeances(){
    
    var ip = sessionStorage.getItem("ip");
    var x = sessionStorage.getItem("dateJour");
    var id = sessionStorage.getItem("id");
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/seances",{date:x,id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<li id='+ value.heure +' onclick=ajouterAffectation(id)><b>' + value.heure +'</b></li>'; 
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
