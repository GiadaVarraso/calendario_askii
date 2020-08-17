$(document).ready(function(){

    const weekdays = ["dom", "lun", "mar", "mer", "gio", "ven","sab"]; //array dei giorni della settimana
    const monthdays=[31,28,31,30,31,30,31,31,30,31,30,31]; //numero massimo di giorni del mese
    var today = new Date();
    var giorno=today.getUTCDay(); // 0 è sunday   (giorno rispetto alla settimana)


    function dateTime (){   

        

        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        var ore=today.getHours();
        var minuti=String(today.getMinutes()).padStart(2, '0');

        $(`#${giorno}`).append(" ✅ "); // per segnare il giorno attuale
      
        
     
        $(`.${weekdays[giorno]}`).attr("numerodd",dd);
        setWeek(mm);
      
        today = ' ' + mm + '/' + dd + '/' + yyyy ;
        time=  ' ' + ore+'.'+ minuti;
       $('#date').html(today);
       $('#time').html(time);

    }

 dateTime()


function setWeek(mm){
    
    if (mm == 01|| mm == 02|| mm == 03|| mm == 04||
        mm == 05|| mm == 06|| mm == 07|| mm == 08||
        mm == 09 )
        { mm= mm.slice(1) }

        
    var maxdd= monthdays[mm];
  

    var domani=(`${today.getDate()+1}`) // qui triviamo il numero di domani
    domani=numeromaxdd(domani,maxdd)
    
    
    giorno++
    giorno=ggmax(giorno)
    $(`.${weekdays[giorno]}`).attr("numerodd",domani);
    domani++
    giorno++
    domani=numeromaxdd(domani,maxdd)
    giorno=ggmax(giorno)
    $(`.${weekdays[giorno]}`).attr("numerodd",domani);
    domani++
    giorno++
    domani=numeromaxdd(domani,maxdd)
    giorno=ggmax(giorno)
    $(`.${weekdays[giorno]}`).attr("numerodd",domani); 
    domani++
    giorno++    
    domani=numeromaxdd(domani,maxdd)
    giorno=ggmax(giorno)   
    $(`.${weekdays[giorno]}`).attr("numerodd",domani); //sab   0
    domani++
    giorno++    
    domani= numeromaxdd(domani,maxdd)
    giorno=ggmax(giorno)    
    $(`.${weekdays[giorno]}`).attr("numerodd",domani);
    domani++
    giorno++    
    domani=numeromaxdd(domani,maxdd)  
    giorno=ggmax(giorno)      
    $(`.${weekdays[giorno]}`).attr("numerodd",domani);
 
   
}


function numeromaxdd(domani,maxdd){
    
    if (domani> maxdd)   // numero massimo di giorni del mese
    {  
        return 01
    } else 
    { 
        return domani
    }

    
}

function ggmax(giorno){
   
    if (giorno<weekdays.length){
        return giorno
    }else{        
        return 0
    }
}















})