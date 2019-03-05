function login(){
    
    
	
    //window.location.replace("login.html");
    
    var user = document.getElementById("user").value
    var pass = document.getElementById("pass").value
    
   var ip = sessionStorage.getItem("ip");
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/login",{nom:user,passw:pass},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    
        if(value.id!=-1){
            sessionStorage.setItem("nom", value.enseignant);
            sessionStorage.setItem("id", value.id);
            sessionStorage.setItem("nbS",value.nbS);
            sessionStorage.setItem("nbAff",value.nbAff);
            sessionStorage.setItem("resp",value.resp);
            sessionStorage.setItem("auto",value.auto);
           
            
            
            window.location.replace("menu1.html");
        }
        else{
            
           
            alert("Données erronées !");
        }
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