function loadCalen()
{
    var nbS= sessionStorage.getItem("nbS");
    var nbA= sessionStorage.getItem("nbAff");
    document.getElementById("DIVC").innerHTML=nbA+'/'+nbS;
    var ip = sessionStorage.getItem("ip");
    var output1='<table border="1" align=center width="70%" height="20%"><tr align=center><td>';
    var result = new Array();
    var json_obj = new Array();
    var tab = new Array();
    var i=0;
    $.getJSON("http://"+ip+":8080/gestionvoeux/calendrierJASON",function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
        tab[i]=value.heure;
        i++;
        output1+='<td height=35><b>'+value.heure+'</td>';
        
        });
               
                output1+='</tr>';
               
                var retrievedData = sessionStorage.getItem("tabJ");
                var tabJ = JSON.parse(retrievedData);
                var retrievedData2 = sessionStorage.getItem("tabJS");
                var tabJS = JSON.parse(retrievedData2);
                var js=0;
                var j=0;
                var celuleAc = new Array();
                var celuleAc2 = new Array();
                var tabEMP = new Array();
                var tabEMPM = new Array();
                var CAC =0;
                var CAC2 =0;
                var IL=0;
                var IM=0;
                while(j<tabJ.length)
                {
                output1+='<tr align=center><td><b>'+tabJ[j];
                var x=0;
                while(x<tab.length)
                    {
                        var fin=0;
                        output1+='<td height=35 id=\'*'+tabJ[j]+tab[x]+'\' >';
                        js=0;
                        while(js < tabJS.length && fin==0)
                        {
                        var tabSeance = tabJS[js].split('*');
                        if(tabSeance[0]==tabJ[j] && tabSeance[1]==tab[x]){
                        
                        if(tabSeance[4]=='true')
                            {
                                celuleAc[CAC]='*'+tabJ[j]+tab[x];
                                CAC++;
                            }
                            if(tabSeance[4]=='false' && tabSeance[5]=='true')
                            {
                                celuleAc2[CAC2]='*'+tabJ[j]+tab[x];
                                CAC2++;
                            }
                            if(tabSeance[6]=='true')
                                {
                                    tabEMP[IL]='*'+tabJ[j]+tab[x];
                                    IL++;
                                }
                            if(tabSeance[7]=='true')
                                {
                                    tabEMPM[IM]='*'+tabJ[j]+tab[x];
                                    IM++;
                                }
                        if(tabSeance[2]=='true')
                            if(tabSeance[3]=='true'){
                                
                                output1+='<input name="seance[]" type="checkbox" checked id='+tabJ[j]+tab[x]+' onclick=categoryOnClick()>';
                            }else
                            {
                                output1+='<input name="seance[]" type="checkbox" checked id='+tabJ[j]+tab[x]+' onclick=categoryOnClick()>';
                            }
                        else
                            if(tabSeance[3]=='true')
                                output1+='<input name="seance[]" type="checkbox" disabled id='+tabJ[j]+tab[x]+' onclick=categoryOnClick()>';
                            else
                                output1+='<input name="seance[]" type="checkbox" id='+tabJ[j]+tab[x]+' onclick=categoryOnClick()>';    
                        fin=1;
                        }
                        if(fin==1)break;
                        js++;    
                        }
                        x++;
                        //alert(x);
                        output1+='</td>';
                    }
                j++;
                
                }
                output1+='</tr></table>';
                //alert(output1);
                document.getElementById("c").innerHTML=output1;
                
                var XIL =0;
                while(XIL<tabEMP.length){
                document.getElementById(tabEMP[XIL]).style.backgroundColor='#00FF87';
                    XIL++;
                    }
        
                var XIM =0;
                while(XIM<tabEMPM.length){
                document.getElementById(tabEMPM[XIM]).style.backgroundColor='#088A08';
                    XIM++;
                    }
            
                
                var XCAC =0;
                while(XCAC<celuleAc.length){
                document.getElementById(celuleAc[XCAC]).style.backgroundColor='#003F87';
                    XCAC++;
                    }
        
                var XCAC2 =0;
                while(XCAC2<celuleAc2.length){
                document.getElementById(celuleAc2[XCAC2]).style.backgroundColor='#58ACFA';
                    XCAC2++;
                    }
                
                
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
function loadjournée()
{
    var ip = sessionStorage.getItem("ip");
    var output1='<table border="1" align=center width="75%" height="10%"><tr align=center><td>';
    var result = new Array();
    var json_obj = new Array();
    var tab = [];
    var i=0;
    $.getJSON("http://"+ip+":8080/gestionvoeux/calendrier3JASON",function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
        tab[i]=value.date;
       //alert(value.date);
        i++;        
        });                
                var x=0;
        
                while(x<tab.length)
                    {
                      // alert(tab[x]);
                        x++;
                    }
                    
                    sessionStorage.setItem("tabJ", JSON.stringify(tab));
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
function loadjournéeSeance()
{
    var id =sessionStorage.getItem("id");
    var ip = sessionStorage.getItem("ip");
    var result = new Array();
    var json_obj = new Array();
    var tab = [];
    var i=0;
    $.getJSON("http://"+ip+":8080/gestionvoeux/calendrier2JASON",{id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
        tab[i]=value.date+'*'+value.heure+'*'+value.affecter+'*'+value.sature+'*'+value.resp+'*'+value.ensM+'*'+value.emp+'*'+value.empM;
       //alert(value.date);
        i++;        
        });                
                var x=0;
        
                while(x<tab.length)
                    {
                       //alert(tab[x]);
                        x++;
                    }
                
                sessionStorage.setItem("tabJS", JSON.stringify(tab));
                //document.getElementById("bt1").disabled=false;
                var auto = sessionStorage.getItem('auto');
                if(auto=='0')
                document.getElementById("vClick").innerHTML='<li><a class="" onclick="go()" id="bt1"><i class="icon-user"></i>Choisir Voeux</a></li>';
                else
                document.getElementById("vClick").innerHTML='<li><a class="" id="bt1"><i class="icon-user"></i>Choisir Voeux</a></li>';    
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
function getValue()
{
    
    var rows = document.getElementsByName('seance[]');
	var selectedRows = [];
	for (var i = 0, l = rows.length; i < l; i++) {
	 if (rows[i].checked) {
	  selectedRows.push(rows[i]);
	 }
	}
    var nbS= sessionStorage.getItem("nbS");
    var x = selectedRows.length;
    if(x<nbS)
        {
            alert("Il Vous reste Encore Des Séances A Choisir");
        }
    
    var retrievedData2 = sessionStorage.getItem("tabJS");
    var tabJS = JSON.parse(retrievedData2);
    var x=0;
    var y=0;
    var w=0;
    var tableau = new Array();
    while(x<tabJS.length){
    var tabSeance = tabJS[x].split('*');
    if(document.getElementById(tabSeance[0]+tabSeance[1]).checked == true){
        
       // alert(document.getElementById(tabSeance[0]+tabSeance[1]).value);
        tableau[y]=tabSeance[0]+'M'+tabSeance[1];
        y++;
        }
    x++;
        }
    while(w<tableau.length)
        {
           // alert(tableau[w]);
            w++;
        }
    var ip = sessionStorage.getItem("ip");
    var id = sessionStorage.getItem("id");
    $.getJSON("http://"+ip+":8080/gestionvoeux/ajouterVoeuxGrille",{voeux:JSON.stringify(tableau),id:id},function(data){
        json_obj = data;
       
        
    $.each(json_obj, function(index, value){
        
       alert(value.x);        
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
   
   
    var s = sessionStorage.getItem("nbAff");
    var x = parseInt(s)+1;
    sessionStorage.setItem("nbAff",w); 

    window.location.replace("menu2.html");
    //window.location.replace("listeVoeux.html");
}