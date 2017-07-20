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


function ShowProgress(){
    return true;
}