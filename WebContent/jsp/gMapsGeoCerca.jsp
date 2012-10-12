<!--
 Copyright 2008 Google Inc. 
 Licensed under the Apache License, Version 2.0: 
 http://www.apache.org/licenses/LICENSE-2.0 
 --> 
 
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAA-O3c-Om9OcvXMOJXreXHAxQGj0PqsCtxKvarsoS-iqLdqZSKfxS27kJqGZajBjvuzOBLizi931BUow"
      type="text/javascript"></script> 
   <link rel="stylesheet" type="text/css" href="../css/geocercas.css" /> 
   <script src="../js/gMapGeoCerca.js" type="text/javascript"></script>   

<body onload="initialize()" onunload="GUnload"> 
 
<table><tr style="vertical-align:top"> 
  <td style="width:15em"> 
 
<table><tr> 
<td><div id="hand_b"
	 onclick="stopEditing()"/></td> 
<td><div id="shape_b"
	onclick="startShape()"/></td>
<td><div id="delete_b"
	onclick="deleteShape()"/></td> 	 
</tr></table> 
 
    <input type="hidden" id="featuredetails" rows=2> 
    </input> 
<p>Para definir una geocerca en el mapa, click en un bot&oacute;n y luego en el mapa.  Doble-click para dejar de dibujar una cerca. Click en un elemento para cambiar el color. Para editar una l&iacute;nea o forma, arrastrar los puntos.  Click en un punto para borrarlo.
</p> 
     <table id ="featuretable"> 
     <tbody id="featuretbody"></tbody>
      <div><input type="submit" name="button" id="button" value="Enviar Geocerca" /></div>
    </table> 
  </td> 
  <td> 
   

    <div id="frame"></div> 
    <div id="map" style="width: 800px; height: 500px"></div> 
  </td> 
</tr></table> 
</body> 
</html> 