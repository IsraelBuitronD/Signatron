 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@page import="com.draicon.signatron.db.*"%>
<%@page import="java.sql.*"%> 
 <link href="css/draicon.css" rel="stylesheet" type="text/css">

 
<%
	String idCliente = request.getParameter("idCliente");
	String id_user = (String) session.getAttribute("id_user");
	String username = request.getParameter("username");
	
    
	ResultSet rs = null;
	String query;
	String nombre = "";
	String razonSoc = "";
	int husoHorario = 0;
	rs = Conexion.getConexion().consultaSql("SELECT * FROM cliente WHERE id_cliente = " + id_user);
	if(rs.next()){
		nombre = rs.getString( "nombre" ) + " " + rs.getString( "ap_paterno" ) + " " + rs.getString( "ap_materno" );
		razonSoc = rs.getString ("razon_soc");
		husoHorario = rs.getInt("huso");
		if(nombre==null) nombre="";
		if(razonSoc==null) razonSoc="";
		if(razonSoc.equals("") && !nombre.equals("")) razonSoc=nombre; 
	}
	rs = null;
	
%> 



<div class="header">
	<div class="float-left">
	 <a href="portal.jsp"><img src="../images/banner.png"  border="0" hspace="0" vspace="0" style=" position:relative; "/></a>
	</div>
	<div class="float-left center-vertical izq" style=margin-left:180px>
	 Usuario: <%=razonSoc%><br>	
	  <a href="javascript:history.go(0)">Actualizar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  
	  <a href="../index.jsp" title="Salir">Salir</a>
	</div>
</div>
<input type="hidden" name="id_user" id="id_user"  value="<%=id_user%>"/>
<input type="hidden" name="huso_horario" id="huso_horario"  value="<%=husoHorario%>"/>