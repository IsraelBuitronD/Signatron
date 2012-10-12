<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="JSONRPCBridge" scope="session" class="org.jabsorb.JSONRPCBridge" />

<%@ page import = "java.util.Date"%>
<%@ page import = "java.util.*"%>
<%@ page import = "java.text.*"%>
<%@ page import = "java.text.SimpleDateFormat.*"%>
<%@ page import = "java.text.ParseException"%>

<%@page import="com.draicon.signatron.json.serializer.openjpa.DateSerializer"%>
<%@page import="com.draicon.signatron.json.serializer.openjpa.SetSerializer"%>
<%@page import="com.draicon.signatron.rpc.secure.PosicionesDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.VehiculosDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.ClienteDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.ComandosDAO"%>
<%@page import="com.draicon.signatron.rpc.secure.AlarmasDAO"%> 
<%
	//specific serializers
	JSONRPCBridge.registerSerializer( new DateSerializer() );
	JSONRPCBridge.registerSerializer( new SetSerializer() );
	//secure
	JSONRPCBridge.registerObject("PosicionesDAO", new PosicionesDAO());
	JSONRPCBridge.registerObject("VehiculosDAO", new VehiculosDAO());
	JSONRPCBridge.registerObject("ClienteDAO", new ClienteDAO());
	JSONRPCBridge.registerObject("ComandosDAO", new ComandosDAO());
	JSONRPCBridge.registerObject("AlarmasDAO", new AlarmasDAO());


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Signatron</title>
<link rel="icon" href="../favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" />

<link rel="stylesheet" type="text/css" href="../css/draicon.css" />
<link rel="stylesheet" type="text/css" href="../css/tabs.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../themes/blitzer-old/jquery-ui-1.7.2.custom.css" />
<!-- <link rel="stylesheet" type="text/css" media="screen" href="../themes/ui-lightness/jquery-ui-1.8.2.custom.css" /> -->
<!-- <link rel="stylesheet" type="text/css" media="screen" href="../themes/flick/jquery-ui-1.7.2.custom.css" /> -->
<link rel="stylesheet" type="text/css" media="screen" href="../css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="../css/multiselect.css" />

<script type="text/javascript" src="../lib/jabsorb/jsonrpc.js"></script>
<script type="text/javascript" src="../lib/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../lib/jquery/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="../lib/jquery/grid.locale-sp.js"></script>
<script type="text/javascript" src="../lib/jquery/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="../js/jquery-general.js"></script>
<script src="../js/general/date.format.js" type="text/javascript"></script>
<!-- <script src="../js/general/funcMaps.js" type="text/javascript"></script> -->
<!-- src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAXK8gjrA5WaYhZrzqdGwREBRN9UogWyQi4nT1VvUt9tD8SA1YgBTntxKySa0J_Uy-5n-XmyuhOxwRAw" -->
<script	
	src ="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAXK8gjrA5WaYhZrzqdGwREBQ71VaWDoYe01msrvG4Y6MRyuI9shTHbsl3VyBIdySDDGXMfR8yumro3A"
	
	type="text/javascript"></script>
<script src="../lib/jquery/jquery.bgiframe.js" type="text/javascript"></script>
<script src="../js/multiselect.js" type="text/javascript"></script>
<style type="text/css">
<!--
#topLeft, #topRight, #bottonLeft, #bottomRight 
{
	position: fixed;
	background: red;
	z-index: 10000;
}
#topLeft, #bottonLeft
{
	height: 10px;
	width: 100%;
	left: 0;
}
#topRight, #bottomRight
{
	height: 100%;
	width: 10px;
}
#topLeft
{
	top: 0;
}
#topRight
{
	top: 0;
	right: 0;
}
#bottonLeft
{
	bottom: 0;
}
#bottomRight
{
	bottom: 0;
	right: 10;
}
-->
</style>

<script type="text/javascript">
// localhost:  src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
draicon={};
draicon.signatron={	
	mensajes: {
		motivoDesconocido: '<span class="motivoDesconocido">DESCONOCIDO</span>'
	},

	fechaString : function (fechaLong){

			if(fechaLong) {
				fechaPosicion=new Date(fechaLong);
				fechaPosicion=new Date(fechaPosicion.getTime()+draicon.signatron.offset_ms);
				return (fechaPosicion).format("dd/mm/yyyy H:MM:ss");
			}
		},
	
	fechaServer: new Date(),		
	authToken: "draicon",
	mapas: {
		geocoder: new GClientGeocoder(),
		//funcion que crea las marcas geotags a partir del parámetro de (point) -(latlng)
		createMarker: function(point, seguimiento, datos) {
			// Create a lettered icon for this point using our icon class
			var carIcon = new GIcon(G_DEFAULT_ICON), prefijo_imagen=(seguimiento)? "arrow":"carro";
			switch(datos.orientacion){
				case 'Norte'   : carIcon.image = "../images/"+prefijo_imagen+"_norte.png"; break;  
				case 'Sur'     : carIcon.image = "../images/"+prefijo_imagen+"_sur.png"; break;  
				case 'Este'    : carIcon.image = "../images/"+prefijo_imagen+"_este.png"; break;  
				case 'Oeste'   : carIcon.image = "../images/"+prefijo_imagen+"_oeste.png"; break;  
				case 'Noreste' : carIcon.image = "../images/"+prefijo_imagen+"_noreste.png"; break;  
				case 'Noroeste': carIcon.image = "../images/"+prefijo_imagen+"_noroeste.png"; break;  
				case 'Sureste' : carIcon.image = "../images/"+prefijo_imagen+"_sureste.png"; break;  
				case 'Suroeste': carIcon.image = "../images/"+prefijo_imagen+"_suroeste.png"; break;  
			}
			
			if     (datos.calidad=="11"){datos.calidad="Excelente";}
			else if(datos.calidad=="10"){datos.calidad="Buena";}
			else if(datos.calidad=="01" || datos.calidad=="00"){datos.calidad="Mala";}


			//if(datos.fecha) {
	  	  	//    fechaPosicion=new Date(datos.fecha);
		  	// 	  fechaPosicion=new Date(fechaPosicion.getTime()+draicon.signatron.offset_ms);
		  	//    datos.fecha=(fechaPosicion).format("dd/mm/yyyy H:MM:ss");
			//}

			//carIcon.iconSize=new GSize({width:34, height:34});
			//carIcon.iconSize=new GSize(34,34);
			carIcon.iconSize = new GSize(32, 32);
			carIcon.shadow = null;
			carIcon.iconAnchor = new GPoint(16, 16);
			carIcon.infoWindowAnchor = new GPoint(16, 16);

			// Set up our GMarkerOptions object
			var markerOptions = {
				icon :carIcon
			};
			var marker = new GMarker(point, markerOptions);

			GEvent.addListener(marker, "click", function() {
				if (point != null) {
					draicon.signatron.mapas.geocoder.getLocations(point,
							function(response) {
								draicon.signatron.mapas.showAddress(marker,
										response, datos.vehiculo,
										datos.velocidad, datos.motivo,
										datos.calidad, datos.fecha,
										(seguimiento) ? datos.numPosicion
												: null);
							});
				}
			});
			return marker;
		},
		//función que busca la direccion
			showAddress : function(nMarker, response, vehiculo, velocidad, motivo, calidad, fecha, numPosicion) {
				if (!response || response.Status.code != 200) {
					//alert("Status Code:" + response.Status.code);
				} else {
					var place = response.Placemark[0];
					nMarker
							.openInfoWindowHtml('<H1>'
									+ ((numPosicion) ? ('Posici&oacute;n ' + numPosicion + '<br/>')
											: '')
									+ vehiculo
									+ '</H1></b>'
									+ '<b>Latitud/Longitud: </b>'
									+ response.name
									+ '<br/>'
									+ '<b>Velocidad: </b>'
									+ velocidad
									+ ' Km/h<br>'
									+ '<b>Fecha y Hora: </b>'
									+ fecha
									+ '<br>'
									+ '<b>Motivo del Reporte: </b>'
									+ motivo
									+ '<br>'
									+ '<b>Calidad de GPS: </b>'
									+ calidad
									+ '<br>'
									+ '<b>Direcci&oacute;n: </b>'
									+ place.address
									+ '<br>'
									+ '<b>C&oacute;digo de pa&iacute;s: </b> '
									+ ((place.AddressDetails && place.AddressDetails.Country) ? place.AddressDetails.Country.CountryNameCode
											: '')) + '<br><br><br><br><br>';
				}
			}
		},
		timer : {
			delay :1, // seconds
			refresh : function() {
				draicon.signatron.fechaServer = new Date();
			}
		},
		onReady : function() {
			draicon.signatron.id_user = $("#id_user").val();
			draicon.signatron.usoHorario = $("#huso_horario").val();
			if (draicon.signatron.id_user == null
					|| draicon.signatron.id_user == "null") {
				window.location = "../index.jsp";
				return;
			}
			if (isNaN(draicon.signatron.usoHorario)) {
				draicon.signatron.usoHorario = 0;
			}
			draicon.signatron.offset_ms = (draicon.signatron.usoHorario * 3600000);
			if (GBrowserIsCompatible()) {
				draicon.signatron.map = new GMap2(document.getElementById("map_canvas"));
				draicon.signatron.map.addMapType(G_SATELLITE_3D_MAP);
				//draicon.signatron.map.setCenter(new GLatLng(19.408437732091606,-99.13577556610107), 5);
				draicon.signatron.map.setCenter(new GLatLng(24.126702,-101.601562), 5);
				draicon.signatron.map.setUIToDefault();

				GEvent.addListener(draicon.signatron.map, "zoomend", function(oldLevel, newLevel) {
					draicon.signatron.gmapFilter.savePosition();
				});
				GEvent.addListener(draicon.signatron.map, "moveend",
						function() {
							draicon.signatron.gmapFilter.savePosition();
						});
			}
			draicon.signatron.jsonrpc = new JSONRpcClient(draicon.signatron.jsonRPCCallBack, "/signatron/JSON-RPC");
		},
		jsonRPCCallBack : function(result, error) {
			if (error) {
				alert(error);
			} else {
				//setTimeout(draicon.signatron.timer.refresh, draicon.signatron.timer.delay*1000);
				draicon.signatron.gmapFilter.init();
				draicon.signatron.reporteTab.init();
				draicon.signatron.envioComados.init();
				draicon.signatron.talarmas.init();
				draicon.signatron.vehi = $("#cboVehiculos").val();
				draicon.signatron.vehiR = $("#cboVehiculosR").val();
				draicon.signatron.jsonrpc.VehiculosDAO.getVehiculosCliente(
						draicon.signatron.getVehiculosClienteCB,
						draicon.signatron.authToken, draicon.signatron.id_user);
				draicon.signatron.envioComados.getComandos();
				draicon.signatron.talarmas.call();
			}
		},
		getVehiculosClienteCB : function(result, error) {
			if (error) {
				draicon.signatron.gmapFilter.resetMap();
				alert("Error al obtener los vehiculos del cliente\n" + error);
			} else {
				var options = '';
				for ( var i = 0, datos; i < result.list.length; i++) {
					datos = result.list[i];
					options += '<option value="' + datos.idVehiculo + '">'
							+ datos.nombre + '</option>';
				}
				;
				$("select#cboVehiculos").html(options);
				$("select#cboVehiculosR").html(options);
				$("select#cboVehiculosComandos").html(
						'<option value=""></option>' + options);

				if (result.list.length > 0) {
					$("#zoomCliente").removeAttr('disabled');
					draicon.signatron.gmapFilter.reset();
				} else {
					draicon.signatron.gmapFilter.resetMap();
				}
			}
		}
	};

	jQuery(document).ready( function() {
		draicon.signatron.onReady();
	});
</script>
</head>
<body>
<div id="marcoAlerta" style="display:none">
	<div id="topLeft">&nbsp;</div>
	<div id="topRight">&nbsp;</div>
	<div id="bottonLeft">&nbsp;</div>
	<div id="bottomRight">&nbsp;</div> 
</div>
<div id="dialogAlertas" title="Alerta">
	<span style="font-weight: bold;">Hay nuevas alertas por atender</span>
	<div class="center"><button type="button">Ver alarmas</button></div>
</div>

<div class="main"><%@ include file="header.jsp"%>
<%@ include file="tabs.jsp"%></div>
<div class="cont-center center-vertical">
<a href="http://draicon.com"><img src="../images/draicon3.png"  border="0" hspace="0" vspace="0" align="middle"/></a>
</div>
</body>
</html>