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
	rs = Conexion.getConexion().consultaSql("SELECT * FROM cliente WHERE id_cliente = " + id_user);
	if(rs.next()){
		nombre = rs.getString( "nombre" ) + " " + rs.getString( "ap_paterno" ) + " " + rs.getString( "ap_materno" );
		razonSoc = rs.getString ("razon_soc");
		
		if(nombre==null) nombre="";
		if(razonSoc==null) razonSoc="";
		if(razonSoc.equals("") && !nombre.equals("")) razonSoc=nombre; 
	}
	rs = null;

%> 
<div class="header">
	<div class="float-left">
	 <a href="portalAdmin.jsp"><img src="../images/bannerAdmin.png"  border="0" hspace="0" vspace="0" style=" position:relative; "/></a>
	</div>
	<div class="float-left center-vertical izq" style=margin-left:260px>
	 Usuario: <%=razonSoc%> <br />
	  <a href="loginAdmin.jsp" title="Salir">Salir</a>
	</div>
</div>