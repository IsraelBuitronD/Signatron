draicon.signatron.gmapFilter={
	refresh: 20,
	seconds: 0,
	timer: null,
	mapPositionZoom: null,
	clearTimer: function() {
		if(draicon.signatron.gmapFilter.timer!=null) {
			clearInterval(draicon.signatron.gmapFilter.timer);
			draicon.signatron.gmapFilter.timer=null;
		}
	},
	clock: function() {
		var paq=draicon.signatron.gmapFilter;
		paq.seconds++;
		if(paq.seconds>=paq.refresh){
			paq.seconds=0;
			draicon.signatron.gmapFilter.sendIDAuto(false);
		}else if((paq.refresh-paq.seconds)==5){
			
		}
	},
	sendID: function(){
		draicon.signatron.vehi = $("#cboVehiculos").val();
		$("#zoomCliente").removeAttr('disabled');
		draicon.signatron.gmapFilter.resetZoom();
		draicon.signatron.gmapFilter.sendIDAuto(true);
	},
	sendIDAuto: function(hideFilter) {
		if(draicon.signatron.vehi==null){
			$('#dialogData').dialog('open');
		}else{
			draicon.signatron.gmapFilter.clearTimer();
			//variables para el timer del header donde cuenta el tiempo restante para refrescar y llama la función display de header
			 draicon.signatron.header.milisec=0;
			 draicon.signatron.header.seconds=20;
			 document.counter.d2.value='20';
			draicon.signatron.header.display();
			$("#map_filter_mensaje").show();
			//$('#map_filter_mensaje').dialog('open');
			if(hideFilter) {
				$("#accordion" ).accordion( { active: 2 } );
				jQuery("#geoLocalizacionResultado").click();
			}
			draicon.signatron.jsonrpc.PosicionesDAO.getUltimasPosicionesVehiculos(draicon.signatron.gmapFilter.getGridCBFilter, draicon.signatron.authToken, draicon.signatron.vehi);
			//jQuery('#cedulaGrid').show();
		}
	},
	showTab: function(index) {
		switch(index){
			case 0:
				jQuery("#geoLocalizacionFiltroCont").show();
				jQuery("#geoLocalizacionResultadoCont").hide();
				break;
			case 1:
				jQuery("#geoLocalizacionFiltroCont").hide();
				jQuery("#geoLocalizacionResultadoCont").show();
				break;
		}
	},
	init: function(){
		//jQuery('#cedulaGrid').hide();
		
		//grid de resultados para la cedula
		draicon.signatron.gmapFilter.resetMap();
		jQuery("#resultadosID").jqGrid({
			//datatype: "json",
			height: 80,
			colNames:['ID','Veh&iacute;culo','Latitud', 'Longitud', 'Calidad','S1','S2','S3',
						'S4','S5','S6','Orientaci&oacute;n','Velocidad', 'Fecha posici&oacute;n', 'Motivo'], 
		   	colModel :[ 
		   	        {name:'id_posicion', index:'id_posicion', width:60, align:'center',hidden: true}, 
		   	        {name:'idVehiculo', index:'id_vehiculo', width:170, align:'center'},  
		   	        {name:'latitud', index:'latitud', width:60, align:'right'}, 
		   	        {name:'longitud', index:'longitud', width:70, align:'right'},
		   	        {name:'calidad', index:'calidad', width:80, align:'center'},
		   	        
		   	     	{name:'sensor1', index:'sensor1', width:20, align:'center'},
		   	    	{name:'sensor2', index:'sensor2', width:20, align:'center'},
		   	        {name:'sensor3', index:'sensor3', width:20, align:'center'},
		   	        {name:'sensor4', index:'sensor4', width:20, align:'center'},
		   	        {name:'sensor5', index:'sensor5', width:20, align:'center'},
		   	     	{name:'sensor6', index:'sensor6', width:20, align:'center'},
		   	     	{name:'direccion', index:'direccion', width:80, align:'center'},  
		   	     
		   	     	{name:'velocidad', index:'velocidad', width:70, align:'center'},  
		   	     	{name:'fechaPosicion', index:'fechaPosicion', width:110, align:'center'},  
		   	     	{name:'motivo', index:'motivo', width:140, align:'left'}  
//		   	     	{name:'fecha', index:'fecha', width:50, align:'right'},  
		   	      ], 
		   	  // pager: '#pagerResultadosID',
		       //rowNum:10,
		       //rowList:[10,20,30],
		       //viewrecords: true,
		   	    height :460,  
				hidegrid: false, 
				multiselect: false,
		        caption: "Veh&iacute;culos"
		});
		
		$("#zoomCliente").click(function () {
			//alert("zoom cliente : " + $("#hZoomCliente").val());
			$("#msgZoomCliente span").toggle();
			if($("#hZoomCliente").val()=="0") {
				$("#hZoomCliente").val("1");
				draicon.signatron.gmapFilter.savePosition();
			} else {
				$("#hZoomCliente").val("0");
				if(draicon.signatron.gmapFilter.mapPositionZoom!=null) {
					var obj=draicon.signatron.gmapFilter.mapPositionZoom;
					draicon.signatron.map.setCenter(obj.center, obj.zoom);
				}
			}
		});
		$("#zoomCliente").attr('disabled', 'disabled');
		//$('#map_filter_mensaje').dialog('close');
		$("#map_filter_mensaje").hide();
		$("#map_filter_timer").hide();
  /////////////revisar???  	
//    	jQuery("#resultadosID").click( function(){
//			draicon.signatron.idGridSelec = jQuery("#resultadosID").jqGrid('getGridParam','selrow');
//			if (draicon.signatron.idGridSelec)	{
//				draicon.signatron.ret = jQuery("#reporteTabular").jqGrid('getRowData',draicon.signatron.idGridSelec);
//				//alert("id="+draicon.signatron.ret.id_posicion+" latitud="+draicon.signatron.ret.latitud+"...");
//				draicon.signatron.reporteTab.showMapa();
//			} else { alert("Por favor seleccione una columna");}
//		});
	},
	savePosition: function() {
		if($("#hZoomCliente").val()=="1") {
			draicon.signatron.map.savePosition();
		}
	},
	resetZoom: function() {
		if($("#hZoomCliente").val()=="1") {
			$("#msgZoomCliente span").toggle();
			$("#hZoomCliente").val("0");
			//$("#msgZoomCliente").click();
			//$("#hZoomCliente").val("0");
		}
	},
	reset: function(){
		$("#cboVehiculos option").attr("selected","selected");
		draicon.signatron.gmapFilter.sendID();
	},
	verUltimaUbicacionVehiculo: function(idVehiculo){
		if($("#cboVehiculos option:selected").length>0){
			$("#cboVehiculos option").removeAttr("selected");
			//$("#cboVehiculos option").attr("selected","");
		}
		$("#cboVehiculos option[value='" + idVehiculo + "']").attr("selected","selected");
		draicon.signatron.gmapFilter.sendID();
	},
	resetMap: function() {
		draicon.signatron.map.clearOverlays();
		draicon.signatron.map.setCenter(new GLatLng(24.126702,-101.601562), 5);
	},
	// llena el mapa y la cedula	
    getGridCBFilter: function(result,error){
		if(error){
			draicon.signatron.gmapFilter.clearTimer();
		}else{
			draicon.signatron.map.clearOverlays();
			jQuery("#resultadosID").clearGridData();
			
			var len=result.list.length, gMLatLng=new Array();
			var alarmaOnIni='<span class="alarmaOn">', alarmaOnFin='</span>';
			var autoOnIni='<span class="autoOn">', autoOnFin='</span>';
			for(var i=0, j=0, datos, orientacion, calidad, fechaPosicion, diferenciaFechas, minuto=60000; i<len;i++){
				datos = result.list[i];

				if      (datos.orientacion>=0   && datos.orientacion< 22 ){orientacion="Norte";}
				else if (datos.orientacion>=337 && datos.orientacion<=360){orientacion="Norte";}
				else if (datos.orientacion>=22  && datos.orientacion< 67 ){orientacion="Noreste";}
				else if (datos.orientacion>=67  && datos.orientacion< 112){orientacion="Este";}
				else if (datos.orientacion>=112 && datos.orientacion< 157){orientacion="Sureste";}
				else if (datos.orientacion>=157 && datos.orientacion< 202){orientacion="Sur";}
				else if (datos.orientacion>=202 && datos.orientacion< 247){orientacion="Suroeste";}
				else if (datos.orientacion>=247 && datos.orientacion< 292){orientacion="Oeste";}
				else if (datos.orientacion>=292 && datos.orientacion< 337){orientacion="Noroeste";}
				else {orientacion="";}
				
				calidad=datos.calidad;
				if     (calidad=="11"){calidad="Excelente";}
				else if(calidad=="10"){calidad="Buena";}
				else if(calidad=="01" || calidad=="00"){calidad="Mala";}
				
				if(!(datos.latitud==0 || datos.longitud==0)){
					gMLatLng[j] = new GLatLng(datos.latitud,datos.longitud);
					fecha = draicon.signatron.fechaString(datos.fecha.time)
		  	  	    draicon.signatron.map.addOverlay(draicon.signatron.mapas.createMarker(gMLatLng[j], false, {orientacion:orientacion, vehiculo:datos.idVehiculo.nombre, velocidad:datos.velocidad, motivo:datos.idMotivo.motivo, calidad:datos.calidad, fecha:fecha}));
		  	  	    j++;
				}
	  	  	    
				if(datos.fecha) {
		  	  	    fechaPosicion=new Date(datos.fecha.time);
		  	  	    fechaPosicion=new Date(fechaPosicion.getTime()+draicon.signatron.offset_ms);

				}
	  	  	    
				
				jQuery("#resultadosID").jqGrid('addRowData',i+1, {
					id_posicion: datos.idPosicion,  
					idVehiculo: datos.idVehiculo.nombre, 
					latitud:   datos.latitud, 
					longitud: datos.longitud, 
					calidad: calidad,  
					sensor1: datos.sensor1, 
					sensor2: datos.sensor2, 
					sensor3: datos.sensor3, 
					sensor4: datos.sensor4,
					sensor5: ((datos.sensor5)? (alarmaOnIni+datos.sensor5+alarmaOnFin):datos.sensor5),
					sensor6: ((datos.sensor6)? (autoOnIni  +datos.sensor6+autoOnFin)  :datos.sensor6),
					direccion: orientacion, 
					velocidad: datos.velocidad + " Km/h",  
					fechaPosicion: (fechaPosicion).format("dd/mm/yy H:MM:ss"),
					
					//tiempoTranscurrido: mensajeTiempo
					motivo: ((datos.idMotivo)? datos.idMotivo.motivo: draicon.signatron.mensajes.motivoDesconocido)
				});
				
			};
			if(len>0){
				var latlngbounds=new GLatLngBounds(), statusZoom=parseInt($("#hZoomCliente").val(), 10);
				for (var i=0; i<gMLatLng.length; i++) {
					latlngbounds.extend(gMLatLng[i]);
				}
				
				draicon.signatron.gmapFilter.mapPositionZoom={
					center: latlngbounds.getCenter(),
					zoom:   draicon.signatron.map.getBoundsZoomLevel(latlngbounds)
					
				};
				
				if(statusZoom==0){
					var obj=draicon.signatron.gmapFilter.mapPositionZoom;
//					if (obj.zoom>16){
//						obj.zoom=16;
//					}
					draicon.signatron.map.setCenter(obj.center, obj.zoom);
				}else{
					draicon.signatron.map.returnToSavedPosition();
				}
				// ????
				draicon.signatron.cargado = 1;
			}
			
			if(len>0 && draicon.signatron.gmapFilter.timer===null) {
				draicon.signatron.gmapFilter.timer=setInterval("draicon.signatron.gmapFilter.clock()", 1000);
			}
		}
		$("#map_filter_mensaje").hide();
		//$('#map_filter_mensaje').dialog('close');
	}
};