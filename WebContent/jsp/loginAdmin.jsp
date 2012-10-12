<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signatron :: Modulo Administrativo</title>
<link href="../css/draicon.css" rel="stylesheet" type="text/css" media="all">
<link href="../css/validator.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="../lib/jquery/jquery.validate.min.js"  charset="utf-8"></script>
<script type="text/javascript" src="../js/general/validator.js"></script>

</head>
<body style="text-align: center">
<div id="signup">
	<form id="loginAdmin" name="loginAdmin" method="post" action="..//LoginAdmin" autocomplete="off">
	<div class = "mainlogin font-small">
	    <div ><img src="../images/loginAdmin.png" width="530px" height="130"> </div>
	    <div  style="height: 300px; width:530px;"  class="margin-top65"> 
	    	<div >
	        	Usuario: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="userA" id="userA">
	        </div>
	    	 
	        <div> 
	        	Contraseña: &nbsp;<input type="text" name="passA" id="passA">
	        </div>
	      <div class="margin-top15" >
		       	<input type="submit" name="button" id="button" value="Enviar">
	        </div>
	    </div>
	</div>
</div>
</form>
</body>
</html>