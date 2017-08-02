function CheckValue(__form,message)
{
 
var form = document.forms[0];


var userInput = form[form.id+":username"];
if(userInput.value===''){
    
    alert(message);
    userInput.focus();
    return false;
}
return true;    
}


function ShowProgress(data){
    if (data.status==="begin"){
        document.getElementById('loading_wrapper').style.display="block";
        delay(1000);
    }
    else{
        document.getElementById('loading_wrapper').style.display="none";
    } 
        
    return true;
}