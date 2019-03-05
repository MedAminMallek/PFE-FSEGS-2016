function envoyerMail()
{
    var mail = document.getElementById('mail').value;
    if(mail!=''){
    var ip = sessionStorage.getItem("ip");
    var mail = document.getElementById('mail').value;
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/envoierMailCompte",{mail:mail},function(data){
        json_obj = data;   
    $.each(json_obj, function(index, value){
        
        if(value.envoyer=='1')
            {
                document.getElementById('msg').innerHTML='<font color=white><b>Envoyé avec succès</font>';
            }
            else
            {
                document.getElementById('msg').innerHTML='<font color=red><b>Adresse mail introuvable</font>';
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
        }else
            alert('Vous devez saisir votre adresse mail');
    
}