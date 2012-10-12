<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml"> 
  <head> 
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/> 
    <title>Google Maps JavaScript API Example: Custom Icon</title> 
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
            type="text/javascript"></script> 
    <script type="text/javascript"> 
    function initialize() {
      if (GBrowserIsCompatible()) {
        var map = new GMap2(document.getElementById("map_canvas"));
        map.setCenter(new GLatLng(19.408437732091606, -99.13577556610107), 13);
        map.setUIToDefault();
 
        // Create a base icon for all of our markers that specifies the
        // shadow, icon dimensions, etc.
        var baseIcon = new GIcon(G_DEFAULT_ICON);
        baseIcon.shadow = "http://www.google.com/mapfiles/shadow50.png";
        baseIcon.iconSize = new GSize(20, 34);
        baseIcon.shadowSize = new GSize(37, 34);
        baseIcon.iconAnchor = new GPoint(9, 34);
        baseIcon.infoWindowAnchor = new GPoint(9, 2);
 
        // Creates a marker whose info window displays the letter corresponding
        // to the given index.
        function createMarker(point, index) {
          // Create a lettered icon for this point using our icon class
           var letter = String.fromCharCode("A".charCodeAt(0) + index);
          var letteredIcon = new GIcon(baseIcon);
          letteredIcon.image = "http://www.google.com/mapfiles/marker" + letter + ".png";
         
 
          // Set up our GMarkerOptions object
          markerOptions = { icon:letteredIcon };
          var marker = new GMarker(point, markerOptions);
 
          GEvent.addListener(marker, "click", function() {
            marker.openInfoWindowHtml("Marca <b>" + letter + "</b> <br />"+latlng);
          });
          return marker;
        }
       
        //var latlngs = new Array();
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
             map.addOverlay(createMarker(latlng, i));
          }
      // var point = new GLatLng (19.408437732091606, -99.13577556610107); 
      
      // map.addOverlay(createMarker(point));
      }
    }
    </script> 
  </head> 
  <body onload="initialize()" onunload="GUnload()"> 
    <div id="map_canvas" style="width: 500px; height: 300px"></div> 
  </body> 
</html>