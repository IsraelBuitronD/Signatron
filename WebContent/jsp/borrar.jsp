<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 <style> 
    .box 
    { 
        border:solid black 0px; 
        padding:0px; 
        margin:0px; 
    } 
    </style> 
</head>
<body>
<form name="counter">
<input class="box" type="text" size="8" name="d2">
</form> 

<script>  
 var milisec=0 
 var seconds=30 
 document.counter.d2.value='30' 

function display(){ 
 if (milisec<=0){ 
    milisec=9 
    seconds-=1 
 } 
 if (seconds<=-1){ 
    milisec=0 
    seconds+=1 
 } 
 
 else {
    milisec-=1 
    document.counter.d2.value=seconds//+"."+milisec 
    if(seconds==0){seconds=30}
    setTimeout("display()",100)
 } 
} 
display() 

</script>
</body>
</html>