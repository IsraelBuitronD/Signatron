<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml"> 
  <head> 
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/> 
    <title>MultiMarker con iconos</title> 
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
            type="text/javascript"></script> 
    <script type="text/javascript">


    function initialize() {
        if (GBrowserIsCompatible()) {
          var map = new GMap(document.getElementById("map_canvas"));
		  //var punto = new GLatLng(19.406575796666093, -99.12642002105713);
		    map.setCenter(new GLatLng(19.408437732091606, -99.13577556610107), 15);
        map.setUIToDefault();
           
        // Create our "tiny" marker icon
 //       var carIcon = new GIcon(G_DEFAULT_ICON);
 //       carIcon.image = "../images/car.png";
		// Set up our GMarkerOptions object
//		markerOptions = { icon:carIcon };
  
       //Botonera de desplazamiento y zoom versión reducida.
 //         map.addControl(new GLargeMapControl());

          function createMarker(point) {
              // Create a lettered icon for this point using our icon class
               var carIcon = new GIcon(G_DEFAULT_ICON);
              carIcon.image = "../images/car2.png";
             
     
              // Set up our GMarkerOptions object
              markerOptions = { icon:carIcon };
              var marker = new GMarker(point, markerOptions);
     
              GEvent.addListener(marker, "click", function() {
            	  if (point != null) { 
            		    address = point; 
            		    geocoder.getLocations(point, function(response) { showAddress(marker, response) }); 
            	  }
                //marker.openInfoWindowHtml("Longitud/Latitud <b></b> <br />"+latlng+"<br /> <strong>Identificador:</strong><br />Fecha:02/03/2010 21:23:32 <br /> Lat:19.2232 Long:-99.12321<br /> Direcci&oacute;n:<br /> Velocidad: 0 km/h<br /> Calidad GPS:Excelente  <br />");
                
              });
              geocoder = new GClientGeocoder(); 
              return marker;
            }
          
          function showAddress(nMarker, response) { 
        	  //map.clearOverlays(); 
        	  if (!response || response.Status.code != 200) { 
        	    alert("Status Code:" + response.Status.code); 
        	  } else { 
        	    place = response.Placemark[0]; 
/*        	    point = new GLatLng(place.Point.coordinates[1],place.Point.coordinates[0]); 
        	    marker = new GMarker(point, markerOptions);  
        	    map.addOverlay(marker);  */
        	    nMarker.openInfoWindowHtml( 
        	        '<b>Latitud/Longitud: </b>' + response.name + '<br/>' +  
        	        //'<b>latlng:</b>' + place.Point.coordinates[0] + "," + place.Point.coordinates[1] + '<br>' + 
        	      // '<b>Status Code:</b>' + response.Status.code + '<br>' + 
        	       // '<b>Status Request:</b>' + response.Status.request + '<br>' + 
        	        '<b>Direcci&oacute;n: </b>' + place.address + '<br>' + 
        	       // '<b>Accuracy:</b>' + place.AddressDetails.Accuracy + '<br>' + 
        	        '<b>C&oacute;digo de pa&iacute;s: </b> ' + place.AddressDetails.Country.CountryNameCode); 
        	  } 
        	}
          
          
          var lat=new Array(); // 
          var lngs=new Array(); 
      
                
        lat[0]=19.408437732091606;       
        lat[1]=19.40082794850489;
  		lat[2]=19.406575796666093;
  		lngs[0]=-99.13577556610107;       
  		lngs[1]=-99.13702011108398;
  		lngs[2]=-99.12642002105713;
  	
          for (var i = 0; i < 3; i++) {
          	 var latlng = new GLatLng(lat[i],lngs[i]);
               map.addOverlay(createMarker(latlng));
            }
        }
      }
  
     </script> 
  </head> 
  <body onload="initialize()" onunload="GUnload()"> 
    <div id="map_canvas" style="width: 990px; height: 500px"></div> 
  </body> 
</html> 