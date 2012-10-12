// Arma el mapa de la parte de consulta historica de reportes
draicon.signatron.gmapFilterReport = {
	initialize: 0,
	init: function() {
		if (GBrowserIsCompatible()) {
			draicon.signatron.gmapFilterReport.initialize=1;
			draicon.signatron.mapReporte = new GMap2(document.getElementById("map_reporte"));
			draicon.signatron.mapReporte.addMapType(G_SATELLITE_3D_MAP);
			draicon.signatron.mapReporte.setCenter(new GLatLng(24.126702,-101.601562), 5);
			draicon.signatron.mapReporte.setUIToDefault();
		}
	},
	showPoint: function(vehiculo, direccion, latitud, longitud, velocidad, motivo, calidad, fecha) {
		var latlng = new GLatLng(latitud, longitud), latlngbounds=new GLatLngBounds();
		draicon.signatron.mapReporte.clearOverlays();
		latlngbounds.extend(latlng);
		
		
		//if(datos.fecha) {
  	  	//    fechaPosicion=new Date(datos.fecha);
	  	// 	  fechaPosicion=new Date(fechaPosicion.getTime()+draicon.signatron.offset_ms);
	  	//    datos.fecha=(fechaPosicion).format("dd/mm/yyyy H:MM:ss");
		//}
		
		
		
		
		//draicon.signatron.mapReporte.addOverlay(draicon.signatron.mapas.createMarker(latlng, false, {orientacion:direccion, vehiculo: vehiculo, velocidad: velocidad, motivo: motivo, calidad: calidad, fecha:fecha}));
		draicon.signatron.mapReporte.addOverlay(draicon.signatron.mapas.createMarker(latlng, false, {	orientacion:direccion,
																										vehiculo: vehiculo,
																										velocidad: velocidad,
																										motivo: motivo,
																										calidad: calidad,
																										fecha:draicon.signatron.fechaString(fecha)
																									}));

		var objCenter={center: latlngbounds.getCenter(), zoom: draicon.signatron.mapReporte.getBoundsZoomLevel(latlngbounds)};
		draicon.signatron.mapReporte.setCenter(objCenter.center, objCenter.zoom);
	}
};