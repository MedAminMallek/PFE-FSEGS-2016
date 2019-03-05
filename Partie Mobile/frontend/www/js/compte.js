function updateParam()
{
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var nom = document.getElementById('nom').value;
    var pass = document.getElementById('pass').value;
    
    if(nom != '' && pass != '')
        {
    
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/updateParamCnx",{nom:nom,pass:pass,id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
        if(value.update=='1')
            //output1 += 'Paramètres sont à jour';
            document.getElementById("er").innerHTML='<font color=green><b>Mise à jour a été effectuée avec succès</font>';
        else
            //output1 += 'Nom d\'utilisateur existe déja';
            document.getElementById("er").innerHTML='<font color=red><b>Nom d\'utilisateur existe déja</font>';
        });
                    
                 //window.location.replace("login.html");
               
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
    else
        alert('Il Faut Saisir Tous Les Données');
}