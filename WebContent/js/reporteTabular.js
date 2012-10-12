draicon.signatron.datos=null;
draicon.signatron.tiempos=null;

draicon.signatron.reporteTab = {
	showGrid : function() {
		jQuery('#gridRepTab').show('fold', '', 1000, '');
		jQuery('#selectVehi').hide();
		jQuery('#cboVehi').hide();
		jQuery('#radioRangos').hide();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#datepickers').hide();
		jQuery('#mapa').hide();
		jQuery('#mapaRec').hide();
		jQuery('#consHist').hide();
		jQuery('#repEst').hide();
		jQuery('#repEstGeo').hide();
		jQuery('#radioReportes').hide();
		jQuery('#rEstadistico').hide();
		jQuery('#rEstadisticoGC').hide();
		$( "#MR" ).button({ disabled: false });
	},
	selectVehi : function() {
		jQuery('#radioReportes').show();
		jQuery('#gridRepTab').hide();
		jQuery('#estadistico').hide();
		jQuery('#mapa').hide();
		jQuery('#mapaRec').hide();
		jQuery('#consHist').hide();
		jQuery('#repEst').hide();
		jQuery('#repEstGeo').hide();
		jQuery('#rEstadistico').hide();
		jQuery('#rEstadisticoGC').hide();
		jQuery('#bBusquedaR').hide();
		$( "#MR" ).button({ disabled: true });
		draicon.signatron.cboVehiculosR = $("#cboVehiculosR").val();
	},

	showEstadistico : function() {
		jQuery('#estadistico').show();
		jQuery('#selectVehi').hide();
		jQuery('#cboVehi').hide();
		jQuery('#radioRangos').hide();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#datepickers').hide();
		jQuery('#mapa').hide();
		jQuery('#mapaRec').hide();
		jQuery('#consHist').hide();
		jQuery('#repEst').hide();
		jQuery('#repEstGeo').hide();
		jQuery('#radioReportes').hide();
		jQuery('#bBusquedaR').hide();

		jQuery('#rEstadisticoGC').hide();


	},
	showEstadisticoGC : function() {
		jQuery('#estadisticoGC').show();
		jQuery('#selectVehi').hide();
		jQuery('#cboVehi').hide();
		jQuery('#radioRangos').hide();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#datepickers').hide();
		jQuery('#mapa').hide();
		jQuery('#mapaRec').hide();
		jQuery('#consHist').hide();
		jQuery('#repEst').hide();
		jQuery('#repEstGeo').hide();
		jQuery('#radioReportes').hide();
		jQuery('#bBusquedaR').hide();

		jQuery('#rEstadistico').hide();

	},

	showMapa : function() {
		jQuery('#mapa').show('clip', '', 500, '');
		jQuery('#gridRepTab').hide();
		jQuery('#estadistico').hide();
		jQuery('#selectVehi').hide();
		jQuery('#cboVehi').hide();
		jQuery('#radioRangos').hide();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#datepickers').hide();
		jQuery('#mapaRec').hide();
		jQuery('#consHist').hide();
		jQuery('#repEst').hide();
		jQuery('#repEstGeo').hide();
		jQuery('#radioReportes').hide();
		jQuery('#bBusquedaR').hide();
		jQuery('#rEstadistico').hide();
		jQuery('#rEstadisticoGC').hide();
		draicon.signatron.VM = document.getElementById("VM").checked;
		if(draicon.signatron.gmapFilterReport.initialize==0){
			draicon.signatron.gmapFilterReport.init();
		}
	},

	showDatepickers : function() {
		jQuery('#datepickers').show();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#bBusquedaR').show();
		draicon.signatron.cboDias = $("#cboDias").val(0);
		draicon.signatron.startdate = $("#startdate").val("");
		draicon.signatron.enddate = $("#enddate").val("");
	},

	showHoy : function() {
		jQuery('#datepickers').hide();
		jQuery('#hoy').show();
		jQuery('#ComboDias').hide();
		jQuery('#bBusquedaR').show();
	},

	showComboDias : function() {
		jQuery('#datepickers').hide();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').show();
		jQuery('#bBusquedaR').show();
		draicon.signatron.cboDias = $("#cboDias").val(0);
		draicon.signatron.startdate = $("#startdate").val("");
		draicon.signatron.enddate = $("#enddate").val("");
	},

	showRP : function() {
		jQuery('#mapaRec').hide();
		jQuery('#consHist').show();
		jQuery('#repEst').hide();
		jQuery('#repEstGeo').hide();
		jQuery('#selectVehi').show();
		jQuery('#cboVehi').show();
		jQuery('#radioRangos').show();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#datepickers').hide();
		jQuery('#bBusquedaR').hide();
		draicon.signatron.cboVehiculosR = $("#cboVehiculosR").val(0);
		draicon.signatron.cboVehiculosR = $("#cboDias").val(0);
		draicon.signatron.startdate = $("#startdate").val("");
		draicon.signatron.enddate = $("#enddate").val("");
		$('#enddate').datepicker("option", "minDate", null);
		$('#startdate').datepicker("option", "maxDate", null);


	},
	showMD : function() {
		jQuery('#mapaRec').show();
		jQuery('#consHist').hide();
		jQuery('#repEst').hide();
		jQuery('#repEstGeo').hide();
		jQuery('#selectVehi').show();
		jQuery('#cboVehi').show();
		jQuery('#radioRangos').show();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#datepickers').hide();
		jQuery('#bBusquedaR').hide();
		draicon.signatron.cboVehiculosR = $("#cboVehiculosR").val(0);
		draicon.signatron.cboVehiculosR = $("#cboDias").val(0);
		draicon.signatron.startdate = $("#startdate").val("");
		draicon.signatron.enddate = $("#enddate").val("");
	},
	showE : function() {
		jQuery('#repEst').show();
		jQuery('#mapaRec').hide();
		jQuery('#consHist').hide();
		jQuery('#repEstGeo').hide();
		jQuery('#selectVehi').show();//
		jQuery('#radioRangos').show();//
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#cboVehi').show();
		jQuery('#datepickers').hide();//
		jQuery('#bBusquedaR').hide();//
		draicon.signatron.cboVehiculosR = $("#cboVehiculosR").val(0);
		draicon.signatron.cboVehiculosR = $("#cboDias").val(0);
		draicon.signatron.startdate = $("#startdate").val("");
		draicon.signatron.enddate = $("#enddate").val("");

	},
	showEG : function() {
		jQuery('#repEstGeo').show();
		jQuery('#consHist').hide();
		jQuery('#mapaRec').hide();
		jQuery('#repEst').hide();
		jQuery('#selectVehi').show();
		jQuery('#radioRangos').hide();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#cboVehi').show();
		jQuery('#datepickers').show();
		jQuery('#bBusquedaR').show();
		draicon.signatron.cboVehiculosR = $("#cboVehiculosR").val(0);
		draicon.signatron.cboVehiculosR = $("#cboDias").val(0);
		draicon.signatron.startdate = $("#startdate").val("");
		draicon.signatron.enddate = $("#enddate").val("");
	},

	showExcel : function(){   
		draicon.signatron.fecha1 = draicon.signatron.startdate;
		draicon.signatron.fecha2 = draicon.signatron.enddate;
		window.open("..//reportePeriodo?vehi="+draicon.signatron.cboVehiculosR+"&combo="+draicon.signatron.cboDias+"&tipoPerio="+draicon.signatron.paramBusqv+"&fecha1="+draicon.signatron.fecha1+"&fecha2="+draicon.signatron.fecha2+ "&opcion=EXCEL");
	},
	showPDF : function(){       
		draicon.signatron.fecha1 = draicon.signatron.startdate;
		draicon.signatron.fecha2 = draicon.signatron.enddate;
		//alert ("tipo paramBusqv "+ draicon.signatron.paramBusqv);
		window.open("..//reportePeriodo?vehi="+draicon.signatron.cboVehiculosR+"&combo="+draicon.signatron.cboDias+"&tipoPerio="+draicon.signatron.paramBusqv+"&fecha1="+draicon.signatron.fecha1+"&fecha2="+draicon.signatron.fecha2 + "&opcion=NORMAL");
	},	
	
	bBusquedaR : function() {
		draicon.signatron.cboVehiculosR = $("#cboVehiculosR").val();
		draicon.signatron.cboDias = $("#cboDias").val();
		draicon.signatron.startdate = $("#startdate").val();
		draicon.signatron.enddate = $("#enddate").val();
		jQuery('#bBusquedaR').hide();

		if (document.getElementById("rHoy").checked) {
			draicon.signatron.paramBusqv = 1;
		} else if (document.getElementById("rUltimosD").checked) {
			draicon.signatron.paramBusqv = 2;
		} else if (document.getElementById("rRangoF").checked) {
			draicon.signatron.paramBusqv = 3;
		}

		// draicon.signatron.RP = document.getElementById("RP").checked;
		// draicon.signatron.MD = document.getElementById("MD").checked;

		// draicon.signatron.EG = document.getElementById("EG").checked;
		//			
		// draicon.signatron.rHoy = document.getElementById("rHoy").checked;
		// draicon.signatron.rUltimosD =
		// document.getElementById("rUltimosD").checked;
		// draicon.signatron.rRangoF =
		// document.getElementById("rRangoF").checked;

		// draicon.signatron.jsonrpc.PosicionesDAO.getGridData(draicon.signatron.reporteTab.getGridCBReport,
		// "draicon");
		jQuery("#reporteTabular").clearGridData();
		// draicon.signatron.jsonrpc.PosicionesDAO.getGridFilterDataInt(draicon.signatron.reporteTab.getGridCBReport,
		// "draicon", "idVehiculo.idVehiculo", draicon.signatron.cboVehiculosR);
		if (draicon.signatron.cboDias=="" || draicon.signatron.cboDias==null) {
			draicon.signatron.cboDias= 0;
		}

		// filtrar entre tipo de reporte
		if (document.getElementById("RP").checked) {
			$('#dialogMensajeEspera').dialog('open');
			draicon.signatron.jsonrpc.PosicionesDAO.getGridDataFilterWhere(
					function(result, error) {draicon.signatron.reporteTab.getGridCBReport(result, error, 1); }, 
					draicon.signatron.authToken,
					"idVehiculo.idVehiculo", draicon.signatron.cboVehiculosR,
					draicon.signatron.startdate, draicon.signatron.enddate,
					draicon.signatron.cboDias, draicon.signatron.paramBusqv,
					draicon.signatron.id_user, 2);
			draicon.signatron.reporteTab.showGrid();
		} else if (document.getElementById("MD").checked) {
			$('#dialogMensajeEspera').dialog('open');
			draicon.signatron.jsonrpc.PosicionesDAO.getGridDataFilterWhere(
					function(result, error) {draicon.signatron.reporteTab.getGridCBReport(result, error, 2); }, 
					draicon.signatron.authToken,
					"idVehiculo.idVehiculo", draicon.signatron.cboVehiculosR,
					draicon.signatron.startdate, draicon.signatron.enddate,
					draicon.signatron.cboDias, draicon.signatron.paramBusqv,
					draicon.signatron.id_user, 2);
			draicon.signatron.reporteTab.showMapa();
		} else if (document.getElementById("E").checked) {
			
			draicon.signatron.jsonrpc.PosicionesDAO.getDatosVehiculo(
					function(result, error) {draicon.signatron.reporteTab.getDatosVehiculo(result, error, 3); }, 
					draicon.signatron.authToken,
					"idVehiculo.idVehiculo", draicon.signatron.cboVehiculosR,
					draicon.signatron.startdate, draicon.signatron.enddate,
					draicon.signatron.cboDias, draicon.signatron.paramBusqv,
					draicon.signatron.id_user, 2);
			draicon.signatron.reporteTab.showEstadistico();
			draicon.signatron.reporteEstadistico.parametros();
			
			jQuery('#rEstadistico').show();
		} else if (document.getElementById("EG").checked) {
			draicon.signatron.reporteTab.showEstadisticoGC();
			draicon.signatron.reporteGeocerca.parametros();
			jQuery('#rEstadisticoGC').show();
			
		}
	},
	init : function() {
		jQuery('#radioReportes').hide();
		jQuery('#selectVehi').hide();
		jQuery('#cboVehi').hide();
		jQuery('#radioRangos').hide();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#datepickers').hide();
		jQuery('#gridRepTab').hide();
		jQuery('#mapa').hide();
		jQuery('#datepickers').hide();
		jQuery('#hoy').hide();
		jQuery('#ComboDias').hide();
		jQuery('#bBusquedaR').hide();
		jQuery('#consHist').hide();
		jQuery('#mapaRec').hide();
		jQuery('#repEst').hide();
		jQuery('#repEstGeo').hide();
		jQuery('#rEstadistico').hide();
		jQuery('#rEstadisticoGC').hide(); 

		$( "#FL" ).button({ disabled: false });
		$( "#MR" ).button({ disabled: true });
		$( "#VM" ).button({ disabled: true });
		draicon.signatron.id_user = $("#id_user").val();

		
		jQuery("#reporteTabular").jqGrid({
			height : 250,
			colNames : ['ID', 'Veh&iacute;culo', 'Latitud', 'Longitud',
					'Calidad', 'Motivo', 'S1', 'S2', 'S3', 'S4', 'S5', 'S6',
					'Orientaci&oacute;n', 'Velocidad', 'Fecha Posici&oacute;n'],
			colModel : [{
						name : 'id_consecu',
						index : 'id_consecu',
						width : 20,
						align : 'center'
					}, {
						name : 'idVehiculo',
						index : 'id_vehiculo',
						width : 50,
						align : 'right'
					}, {
						name : 'latitud',
						index : 'latitud',
						width : 35,
						align : 'right'
					}, {
						name : 'longitud',
						index : 'longitud',
						width : 35,
						align : 'right'
					}, {
						name : 'calidad',
						index : 'calidad',
						width : 45,
						align : 'center'
					}, {
						name : 'idMotivo',
						index : 'id_motivo',
						width : 70,
						align : 'center'
					}, {
						name : 'sensor1',
						index : 'sensor1',
						width : 15,
						align : 'center'
					}, {
						name : 'sensor2',
						index : 'sensor2',
						width : 15,
						align : 'center'
					}, {
						name : 'sensor3',
						index : 'sensor3',
						width : 15,
						align : 'center'
					}, {
						name : 'sensor4',
						index : 'sensor4',
						width : 15,
						align : 'center'
					}, {
						name : 'sensor5',
						index : 'sensor5',
						width : 15,
						align : 'center'
					}, {
						name : 'sensor6',
						index : 'sensor6',
						width : 15,
						align : 'center'
					}, {
						name : 'direccion',
						index : 'direccion',
						width : 35,
						align : 'center'
					}, {
						name : 'velocidad',
						index : 'velocidad',
						width : 35,
						align : 'center'
					}, {
						name : 'fecha',
						index : 'fecha',
						width : 50,
						align : "center"
/*	                    sorttype : "date",
						formatter : 'date',
						formatoptions : {
							srcformat : 'd/m/y',
							newformat : 'd/m/Y'
						}*/
					}

			],
			multiselect : false,
			rowNum : 10,
			width : 990,
			height : "100%",
			rowList : [10, 20, 30],
			sortname : 'id',
			viewrecords : true,
			sortorder : "desc",
			// rownumbers: true,
			gridview : true,
			toolbar : [true, "top"],
			caption : "Reporte Tabular"
		});
		
		jQuery("#reporteTabular").jqGrid('navGrid','#pgreporteTabular',{edit:false,add:false,del:false});
		
		//Lanza reporte en PDF
		$("#t_reporteTabular").append("<img id='pdf' onclick='draicon.signatron.reporteTab.showPDF()' src='../images/acrobatpro.png'/>");
		$("#t_reporteTabular").append("<img id='excel' onclick='draicon.signatron.reporteTab.showExcel()' src='../images/excel.png'/>");
		//$("#t_reporteTabular").append("<input type='button' value='Excel' style='height:20px;font-size:-3'/><img src='../images/excel.png'/>");
		//$("input","#t_reporteTabular").click(function(){

 
		// cuando se selecciona un row del grid en Consulta historica
		jQuery("#reporteTabular").click(function() {
			// clic sobre el grid
			draicon.signatron.idGridSelec = jQuery("#reporteTabular").jqGrid('getGridParam', 'selrow');
			if (draicon.signatron.idGridSelec) {
				draicon.signatron.ret = jQuery("#reporteTabular").jqGrid(
						'getRowData', draicon.signatron.idGridSelec);
				draicon.signatron.reporteTab.showMapa();
				/*draicon.signatron.gmapFilterReport.showPoint(draicon.signatron.ret.idVehiculo,
															 draicon.signatron.ret.direccion,
															 draicon.signatron.ret.latitud,
															 draicon.signatron.ret.longitud,
															 draicon.signatron.ret.velocidad,
															 draicon.signatron.ret.idMotivo,
															 draicon.signatron.ret.calidad,
															 draicon.signatron.ret.fecha);
				*/
				
				draicon.signatron.mapReporte.clearOverlays();
				
				gMLatLng = new GLatLng(draicon.signatron.ret.latitud,draicon.signatron.ret.longitud);
				draicon.signatron.mapReporte.addOverlay(draicon.signatron.mapas.createMarker(gMLatLng, false, {orientacion:draicon.signatron.ret.direccion,
																												vehiculo:draicon.signatron.ret.idVehiculo,
																												velocidad:draicon.signatron.ret.velocidad,
																												motivo:draicon.signatron.ret.idMotivo,
																												calidad:draicon.signatron.ret.calidad,
																												fecha:draicon.signatron.ret.fecha
																												}));
				
				
				
				
				draicon.signatron.mapReporte.setCenter(gMLatLng, 15);
				
				
			} else {
				alert("Seleccione un registro");
			}
		});
	},

	getGridCBReport : function(result, error, tipo) {
		if (error) {
			alert(error + " error en reporteTabular");
		}
		else {
			var data = new Array();
			
			for (var i = 0, calidad, fechaPosicion; i < result.list.length; i++) {
				var datos = result.list[i];
				
				/*Calculando la orientacion del punto dado*/
				if		(datos.orientacion >= 0 && datos.orientacion < 22.5) {datos.orientacion = "Norte";}
				else if (datos.orientacion >= 337.5 && datos.orientacion <= 360) {datos.orientacion = "Norte";}
				else if (datos.orientacion >= 22.5 && datos.orientacion < 67.5) {	datos.orientacion = "Noreste";}
				else if (datos.orientacion >= 67.5 && datos.orientacion < 112.5) {datos.orientacion = "Este";}
				else if (datos.orientacion >= 112.5 && datos.orientacion < 157.5) {datos.orientacion = "Sureste";}
				else if (datos.orientacion >= 157.5 && datos.orientacion < 202.5) {datos.orientacion = "Sur";}
				else if (datos.orientacion >= 202.5 && datos.orientacion < 247.5) {datos.orientacion = "Suroeste";}
				else if (datos.orientacion >= 247.5 && datos.orientacion < 292.5) {datos.orientacion = "Oeste";}
				else if (datos.orientacion >= 292.5 && datos.orientacion < 337.5) {datos.orientacion = "Noroeste";}
				else 	{orientacion="";}
				
				/*Calculando la calidad de la senal del punto dado*/
				calidad=datos.calidad;
				if 		(calidad=="11") {calidad="Excelente";} 
				else if (calidad=="10") {calidad="Buena";} 
				else if (calidad=="01" || calidad=="00") {calidad="Mala";}
								
				//fechaPosicion=new Date(datos.fecha.time);
				//fechaPosicion=new Date(fechaPosicion.getTime()+draicon.signatron.offset_ms);
			
				data[i] = {
					id_posicion : datos.idPosicion,
					id_consecu : i+1,
					idVehiculo : datos.idVehiculo.nombre,
					latitud : datos.latitud,
					longitud : datos.longitud,
					calidad : calidad,
					idMotivo : (datos.idMotivo)? datos.idMotivo.motivo:draicon.signatron.mensajes.motivoDesconocido,
					sensor1 : datos.sensor1,
					sensor2 : datos.sensor2,
					sensor3 : datos.sensor3,
					sensor4 : datos.sensor4,
					sensor5 : datos.sensor5,
					sensor6 : datos.sensor6,
					direccion : datos.orientacion,
					velocidad : datos.velocidad, 
					fecha : draicon.signatron.fechaString(datos.fecha.time) //fechaPosicion.format("dd/mm/yy H:MM:ss")
				};
			};

			
			
			
			//Reporte tabular
			if(tipo==1){
				for (var i=0; i<data.length; i++) {
					jQuery("#reporteTabular").jqGrid('addRowData', i + 1, data[i]);
				}
			}
			
			
			//Mapa de Recorrido
			else if(tipo==2) {
				var gMLatLng=new Array();
				draicon.signatron.mapReporte.clearOverlays();
			
				for (var i=0, j=0,point, pos = data.length; i<data.length; i++) {
					point=data[i];
					if(!(point.latitud==0 && point.longitud==0)){
						
						gMLatLng[j] = new GLatLng(point.latitud,point.longitud);
						draicon.signatron.mapReporte.addOverlay(draicon.signatron.mapas.createMarker(gMLatLng[j], true, {orientacion:point.direccion,
																														vehiculo:point.idVehiculo,
																														velocidad:point.velocidad,
																														motivo:datos.idMotivo.motivo,
																														calidad:point.calidad,
																														fecha:point.fecha,
																														numPosicion:  pos--}));
						j++;
						
					}
				}
	  	  	    
				var latlngbounds=new GLatLngBounds();
				for (var i=0; i<gMLatLng.length; i++) {
					latlngbounds.extend(gMLatLng[i]);
				}
				
				draicon.signatron.mapReporte.setCenter(latlngbounds.getCenter(), draicon.signatron.mapReporte.getBoundsZoomLevel(latlngbounds));
			}
			
			//Reporte de distancia recorrida
			
			else if(tipo == 3){
				
				var distancia=0;
				if(data.length > 1){
					var latitud1, latitud2;
					var longitud1, longitud2;
						
					for (var i=0; i<data.length; i++) {
						var dato = data[i];
						
						if(i==0){
							latitud1 = dato.latitud;
							longitud1 = dato.longitud;
						}else{
							latitud2 = dato.latitud;
							longitud2 = dato.longitud;
							
							distancia = distancia + draicon.signatron.fuel.distance(latitud1, latitud2, longitud1, longitud2, 'K')
						}
						
						
						
					}
					
				}
				var disStr = '0.00';
				if(distancia>0){
					disStr = distancia + '';
				}
				
				$("#kilometraje_recorrido").html(disStr.substring(0,disStr.indexOf('.', 0)+ 3));
				
				draicon.signatron.startdate = $("#startdate").val();
				draicon.signatron.enddate = $("#enddate").val();
				
				$("#startd").html(draicon.signatron.startdate);
				$("#endd").html(draicon.signatron.enddate);
	
				
			}
			$('#dialogMensajeEspera').dialog('close');
		}
	},
	
	getDatosVehiculo : function(result, error, tipo) {
		if (error) {
			alert(error + " error en reporteTabular");
		}else {
			
			draicon.signatron.datos = new Array();
			//alert('long:' + result.posiciones.list.length);
			
			for (var i = 0, calidad, fechaPosicion; i < result.posiciones.list.length; i++) {
				var datos = result.posiciones.list[i];
				
				draicon.signatron.datos[i] = {
					id_posicion : datos.idPosicion,
					id_consecu : i+1,
					idVehiculo : datos.idVehiculo.nombre,
					latitud : datos.latitud,
					longitud : datos.longitud,
					calidad : calidad,
					idMotivo : (datos.idMotivo)? datos.idMotivo.motivo:draicon.signatron.mensajes.motivoDesconocido,
					sensor1 : datos.sensor1,
					sensor2 : datos.sensor2,
					sensor3 : datos.sensor3,
					sensor4 : datos.sensor4,
					sensor5 : datos.sensor5,
					sensor6 : datos.sensor6,
					direccion : datos.orientacion,
					velocidad : datos.velocidad, 
					altitud: datos.altitud,
					fecha : draicon.signatron.fechaString(datos.fecha.time) //fechaPosicion.format("dd/mm/yy H:MM:ss")
				};
			};
			
			//aqui se van a llenar los datos de los tiempo en el arreglo
			draicon.signatron.tiempos = new Array();
			
			//0. tiempo prendido
			draicon.signatron.tiempos[0] = {
				nombre: 'Tiempo de Encendido',
				valor: result.tiempoPrendido
			}
			draicon.signatron.tiempos[1] = {
				nombre: 'Tiempo de Apagado',
				valor: result.tiempoApagado
			}
			
			$("#startd").html(result.fechaInicialStr);
			$("#endd").html(result.fechaFinalStr);
			$("#kilometraje_recorrido").html(result.distanciaRecorrida);
			$("#velocidad_maxima").html(result.velocidadMaxima);
			$("#velocidad_promedio").html(result.velocidadPromedio);
			$("#tiempo_encendido").html(result.tiempoPrendidoStr);
			$("#tiempo_apagado").html(result.tiempoApagadoStr);
			$("#tiempo_total").html(result.tiempoTotalStr);
			$("#altitud_promedio").html(result.altitudPromedio);
			
		}
	}
};