function getJour(){

     var ip = sessionStorage.getItem("ip");
    var output1='';
    var result = new Array();
    var json_obj = new Array();
    
    $.getJSON("http://"+ip+":8080/gestionvoeux/chargerJour",function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
    output1 += '<li id='+ value.date +' onclick=h(id)><b>' + value.date +'</b></li>'; 
       // alert(output1);
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
