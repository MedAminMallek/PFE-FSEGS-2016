function getMatiere()
{
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeEpreuves",{id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<li id='+ value.idMat+'*'+value.idCycle+'*'+value.idFiliere+'*'+value.abrev+' onclick=getSalles(id)><b>' + value.abrev+' '+value.idCycle+' '+value.idFiliere+'</b></li>'; 
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
function getSalles(id)
{
    sessionStorage.setItem("epreuve",id);
    window.location.replace("ListeSalle.html");
}
function chargerSalles()
{
    var param = sessionStorage.getItem("epreuve");
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    var idM = param.split('*')[0];
    var idC = param.split('*')[1];
    var idF = param.split('*')[2];
    
   
    var output1='<table border=1 style="border-collapse: collapse;" width=75% align=center><tr align=center><td><b>Salle<td><b>Date';
    var result = new Array();
    var json_obj = new Array();
    $.getJSON("http://"+ip+":8080/gestionvoeux/listeSallePourMat",{idMat:idM,idC:idC,idF:idF},function(data){
        json_obj = data;
       
       
    $.each(json_obj, function(index, value){
    
    output1 += '<tr align=center><td>'+value.salle+'<td>'+value.date+' à '+value.heure; 
        });
                    
                // alert(output1);
                output1+='</table>';
                document.getElementById("tab").innerHTML=output1;
                document.getElementById("mat").innerHTML=param.split('*')[3]+' '+idF+' '+idC;
               //  $('#list').html(output1);
            //   $("#list").listview("refresh");
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