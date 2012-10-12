<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="JSONRPCBridge" scope="session"
	class="org.jabsorb.JSONRPCBridge" />

<%@page import="com.draicon.signatron.json.serializer.openjpa.DateSerializer"%>
<%@page import="com.draicon.signatron.json.serializer.openjpa.SetSerializer"%>	
<%@page import="com.draicon.signatron.rpc.secure.PosicionesDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.VehiculosDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.ClienteDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.UsuariosDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.AdministradorDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.GpsDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.ComandosDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.SetupDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.CatMotivoDAO"%>

<%
	//secure
	JSONRPCBridge.registerObject("PosicionesDAO", new PosicionesDAO());
	JSONRPCBridge.registerObject("VehiculosDAO", new VehiculosDAO());
	JSONRPCBridge.registerObject("ClienteDAO", new ClienteDAO());
	JSONRPCBridge.registerObject("UsuariosDAO", new UsuariosDAO());
	JSONRPCBridge.registerObject("AdministradorDAO", new AdministradorDAO());
	JSONRPCBridge.registerObject("GpsDAO", new GpsDAO());
	JSONRPCBridge.registerObject("ComandosDAO", new ComandosDAO());
	JSONRPCBridge.registerObject("SetupDAO", new SetupDAO());
	JSONRPCBridge.registerObject("CatMotivoDAO", new CatMotivoDAO());

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Signatron Administraci&oacute;n</title>

<link rel="stylesheet" type="text/css" href="../css/draicon.css" />
<link rel="stylesheet" type="text/css" href="../css/tabs.css" />

 <link rel="stylesheet" type="text/css" media="screen" href="../themes/overcast/jquery-ui-1.7.2.custom.css" />
<!-- <link rel="stylesheet" type="text/css" media="screen" href="../themes/ui-lightness/jquery-ui-1.7.2.custom.css" /> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="../themes/flick/jquery-ui-1.7.2.custom.css" /> -->
<link rel="stylesheet" type="text/css" media="screen" href="../css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="../css/multiselect.css" />

<script type="text/javascript" src="../lib/jabsorb/jsonrpc.js"></script>
<script type="text/javascript" src="../lib/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../lib/jquery/jquery-ui-1.8rc3.custom.min.js"></script>
<script type="text/javascript" src="../js/jquery-general.js"></script>
<script type="text/javascript" src="../lib/jquery/grid.locale-sp.js"></script>
<script type="text/javascript" src="../lib/jquery/jquery.jqGrid.min.js"></script>

<script src="../lib/jquery/jquery.bgiframe.js" type="text/javascript"></script>
<script src="../js/multiselect.js" type="text/javascript"></script>
<script type="text/javascript">
	draicon = {};
	draicon.signatron = {};
	draicon.signatron = {	
		onReady : function() {
			draicon.signatron.jsonrpc = new JSONRpcClient(draicon.signatron.jsonRPCCallBack, "/signatron/JSON-RPC");
		},
		jsonRPCCallBack: function(result, error) {
    		if(error) {
    			alert(error);
    		} else {
    			//draicon.signatron.jsonrpc.ClienteDAO.getGridData(draicon.signatron.tcliente.getGridCBCliente, "draicon");
    			//draicon.signatron.jsonrpc.UsuariosDAO.getGridData(draicon.signatron.tusuarios.getGridCBUsuarios, "draicon");
    			draicon.signatron.jsonrpc.GpsDAO.getGridData(draicon.signatron.tgps.getGridCBGPS, "draicon");
        		//draicon.signatron.jsonrpc.VehiculosDAO.getGridData(draicon.signatron.tvehiculos.getGridCBVehiculos, "draicon");
    			//draicon.signatron.jsonrpc.AdministradorDAO.getGridData(draicon.signatron.tadministrador.getGridCBAdministrador, "draicon");
        		//draicon.signatron.jsonrpc.SetupDAO.getGridData(draicon.signatron.tsetup.getGridCBSetup, "draicon");
        		//draicon.signatron.jsonrpc.CatMotivoDAO.getGridData(draicon.signatron.tcomandos.getGridCBComandos, "draicon");
        		//draicon.signatron.jsonrpc.VehiculosDAO.getGridData(draicon.signatron.tvehiculos.getGridCBCboCliente, "draicon");

        		//draicon.signatron.tcliente.init();
        		//draicon.signatron.tusuarios.init();
        		//draicon.signatron.tadministrador.init();
        		draicon.signatron.tgps.init();
        		//draicon.signatron.tvehiculos.init();
        		//draicon.signatron.tcomandos.init();
        		//draicon.signatron.tsetup.init();
        		
        	}
    	}
	};	

	jQuery(document).ready( function() {
		draicon.signatron.onReady();
	});
</script>
</head>
<body>

<div class="main"><%@ include file="headerA.jsp"%></br>
<%@ include file="tabsAdmin.jsp"%></div> 
</body>
<div class="cont-center">
 powered  by <a href="http://draicon.com"><img src="../images/draicon2.png"  border="0" hspace="0" vspace="0" align="middle"/></a>
</div>
</html>