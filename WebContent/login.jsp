<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.draicon.signatron.db.*"%>
<%@ page import="java.sql.*"%>
<%
	String username = request.getParameter("username");
	String msgInfo  = request.getParameter("msgInfo");
	
	if(username==null) username = "";
	if(msgInfo==null)  msgInfo = "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="icon" href="favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	<title>Signatron - Sistema de Rastreo Satelital</title>
	
	<script src="js/login.js" type="text/javascript"></script> 
	<script src="lib/jquery/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="../lib/jquery/jquery.validate.min.js"  charset="utf-8"></script>
	<script type="text/javascript" src="../js/general/validator.js"></script>
	 <link href="css/login.css" rel="stylesheet" type="text/css">
</head>
<body onload="inicializa()" oncontextmenu="return false">
<form id="forma" name="forma" method="post" action="Login" autocomplete="off">
<div id="signup" >
	<table width="640" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:30px" >
	  <tr>
	   <td><img src="images/login/spacer.gif" width="6" height="1" border="0" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="145" height="1" border="0" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="131" height="1" border="0" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="60" height="1" border="0" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="104" height="1" border="0" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="11" height="1" border="0" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="17" height="1" border="0" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="156" height="1" border="0" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="10" height="1" border="0" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="1" border="0" alt="" /></td>
	  </tr>

	  <tr>
	   <td colspan="9"><img name="login_r1_c1" src="images/login/login_r1_c1.jpg" width="640" height="153" border="0" id="login_r1_c1" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="153" border="0" alt="" /></td>
	  </tr>
	  <tr>
	   <td rowspan="2" colspan="3"><img name="login_r2_c1" src="images/login/login_r2_c1.jpg" width="282" height="85" border="0" id="login_r2_c1" alt="" /></td>
	   <td colspan="3" bgcolor="#CCCCCC"><table width="175" border="0" cellspacing="0" cellpadding="0" >
	     <tr>
	       <td><label>
	         <input type="text"  name="username" id="username"  value="<%=username%>" maxlength="25" size="15" />
            </label></td>
          </tr>
	     <tr>
	       <td>&nbsp;</td>
          </tr>
	     <tr>
	       <td><label>
	        <input type="password" name="passw" id="passw" maxlength="25" size="15" />
            </label></td>
          </tr>
        </table></td>
	   <td rowspan="2" colspan="3"><img name="login_r2_c7" src="images/login/login_r2_c7.jpg" width="183" height="85" border="0" id="login_r2_c7" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="76" border="0" alt="" /></td>
	  </tr>
	  <tr>
	   <td colspan="3"><img name="login_r3_c4" src="images/login/login_r3_c4.jpg" width="175" height="9" border="0" id="login_r3_c4" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="9" border="0" alt="" /></td>
	  </tr>
	  <tr>
	   <td rowspan="4" colspan="2"><img name="login_r4_c1" src="images/login/login_r4_c1.jpg" width="151" height="220" border="0" id="login_r4_c1" alt="" /></td>
	   <td colspan="5" bgcolor="#CCCCCC" class="font-alert" style="vertical-align:middle;">
		    <% if(msgInfo!=""){
		    	%>
		    <img src="images/login/stop.png" width="25" height="25" />
		   <%=msgInfo %>
		   
		   <% 
			   } 
			%> 
		    
	   </td>
	   <td rowspan="4" colspan="2"><img name="login_r4_c8" src="images/login/login_r4_c8.jpg" width="166" height="220" border="0" id="login_r4_c8" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="50" border="0" alt="" /></td>
	  </tr>
	  <tr>
	   <td colspan="5"><img name="login_r5_c3" src="images/login/login_r5_c3.jpg" width="323" height="10" border="0" id="login_r5_c3" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="10" border="0" alt="" /></td>
	  </tr>
	  <tr>
	   <td colspan="2"  background="images/login/login_r4_c3.jpg" class="font-basic">
		Versi&oacute;n 1.0.4
	   </td>
	   <td  background="images/login/login_r4_c3.jpg">
	   <input type="button" class="boton" accesskey="l" id="login_btn" name="login" value="Enviar" onclick="validaDatos(this.form)" />
	   
	   </td>
	   <td rowspan="2" colspan="2"><img name="login_r6_c6" src="images/login/login_r6_c6.jpg" width="28" height="160" border="0" id="login_r6_c6" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="33" border="0" alt="" /></td>
	  </tr>
	  <tr>
	   <td colspan="3"><img name="login_r7_c3" src="images/login/login_r7_c3.jpg" width="295" height="127" border="0" id="login_r7_c3" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="127" border="0" alt="" /></td>
	  </tr>
	  <tr>
	   <td rowspan="2"><img name="login_r8_c1" src="images/login/login_r8_c1.jpg" width="6" height="67" border="0" id="login_r8_c1" alt="" /></td>
 	  <td colspan="7"  background="images/login/login_bg.jpg"> 
             <table width="624" border="0" cellspacing="0" cellpadding="0" >
              <tr>
                <td  width="40" align="center"><img src="images/login/chrome.png" width="21" height="20" /></td>
                <td class="font-basic2"  >Google Chrome <br />recomendado</td>
                <td width="200" >&nbsp;</td>  
                <td class="font-basic2" align="right">www.signatrongps.com<br><br> 
                powered by <a href="http://www.draicon.com"><img src="images/draicon.png" width="98" height="17" /></a>
                </td>
                
              </tr>
            </table>

       </td>	   

	   <td rowspan="2"><img name="login_r8_c9" src="images/login/login_r8_c9.jpg" width="10" height="67" border="0" id="login_r8_c9" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="49" border="0" alt="" /></td>
	  </tr>
	  <tr>
	   <td colspan="7"><img name="login_r9_c2" src="images/login/login_r9_c2.jpg" width="624" height="18" border="0" id="login_r9_c2" alt="" /></td>
	   <td><img src="images/login/spacer.gif" width="1" height="18" border="0" alt="" /></td>
	  </tr>
	</table>
   </div>
</form>

</body>
</html>