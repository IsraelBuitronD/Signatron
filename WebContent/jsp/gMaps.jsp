<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="JSONRPCBridge" scope="session" class="org.jabsorb.JSONRPCBridge"/>
<%@page import="com.draicon.signatron.rpc.secure.PosicionesDAO"%>
<%//secure
	JSONRPCBridge.registerObject("PosicionesDAO",new PosicionesDAO()); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml"> 
  <head> 
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/> 
    <title>MultiMarker con iconos</title> 
    
    <script type="text/javascript" src="../lib/jabsorb/jsonrpc.js"></script>
    <script type="text/javascript" src="../lib/jquery/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="../lib/jquery/jquery-ui-1.8rc3.custom.min.js"></script>
	<script type="text/javascript" src="../js/jquery-general.js"></script>
    
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
            type="text/javascript"></script> 
    <script type="text/javascript">
    draicon={};
    draicon.signatron={};
    draicon.signatron.dataCalls={};
    draicon.signatron={

     onReady: function(){
    	draicon.signatron.jsonrpc = new JSONRpcClient(draicon.signatron.jsonRPCCallBack,"/signatron/JSON-RPC");
        if (GBrowserIsCompatible()) {
        	draicon.signatron.map = new GMap(document.getElementById("map_canvas"));
		  //var punto = new GLatLng(19.406575796666093, -99.12642002105713);
		    draicon.signatron.map.setCenter(new GLatLng(19.408437732091606, -99.13577556610107), 13); 
		    draicon.signatron.map.setUIToDefault();
       
       }
      },
      jsonRPCCallBack: function(result, error) {
    		
    		if(error) {
    			alert(error);
    		} else {
       			draicon.signatron.jsonrpc.PosicionesDAO.getGridData(draicon.signatron.getGridCB, "draicon");
        		   }
    	},

	getGridCB: function(result,error){
			if(error){
				alert(error);
				}else{
					var data = new Array();
					for(var i=0;i<result.list.length;i++){
						var datos = result.list[i];
						data[i] = {latitud: datos.latitud, longitud: datos.longitud};
					};
					for (var i = 0; i < 5; i++) {
						var latlng = new GLatLng(data[i].latitud,data[i].longitud);
			  	  	     draicon.signatron.map.addOverlay( draicon.signatron.createMarker(latlng));
					}
				}	
    		},
 	
     createMarker: function(point) {
        // Create a lettered icon for this point using our icon class
         var carIcon = new GIcon(G_DEFAULT_ICON);
        carIcon.image = "../images/car.png";
       // Set up our GMarkerOptions object
        markerOptions = { icon:carIcon };
        var marker = new GMarker(point, markerOptions);

        GEvent.addListener(marker, "click", function() {
      	  if (point != null) { 
      		    address = point; 
      		    geocoder.getLocations(point, function(response) {  draicon.signatron.showAddress(marker, response) }); 
	      	  }
        
        });
        geocoder = new GClientGeocoder(); 
        return marker;
      },

     showAddress: function(nMarker, response) { 
  	   if (!response || response.Status.code != 200) { 
  	    alert("Status Code:" + response.Status.code); 
  	  } else { 
  	    place = response.Placemark[0]; 
  	    nMarker.openInfoWindowHtml( 
  	        '<b>Latitud/Longitud: </b>' + response.name + '<br/>' +  
  	        '<b>Direcci&oacute;n: </b>' + place.address + '<br>' + 
  	        '<b>C&oacute;digo de pa&iacute;s: </b> ' + place.AddressDetails.Country.CountryNameCode); 
  	  } 
  	}
  }

    jQuery(document).ready(function(){
    	draicon.signatron.onReady();
    });
     </script> 
  </head> 
  <body > 
    <div id="map_canvas" style="width: 990px; height: 500px"></div> 
  </body> 
</html> 