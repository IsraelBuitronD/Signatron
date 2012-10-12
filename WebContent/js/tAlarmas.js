draicon.signatron.talarmas = {
	load: false,
	timer: null,
	data: null,
	status: {
		atendida: 'Atendida',
		no_atendida: 'Falta por atender'
	},
	clearTimer: function() {
		if(draicon.signatron.talarmas.timer!=null) {
			clearInterval(draicon.signatron.talarmas.timer);
			draicon.signatron.talarmas.timer=null;
		}
	},
	init: function() {
		jQuery("#listAlarmas").jqGrid({
			//datatype : "clientSide",
			//colNames: ['ID', 'Vehiculo', 'Motivo', 'Descripci&oacute;n','Fecha'],
			colModel: [{
				name : 'imei',
				label : 'Id Veh&iacute;culo',
				width : 100,
				align : 'center',
				sorttype : 'text'
			}, {
				name : 'id_alarma',
				//index : 'id_alarma',
				label : 'ID',
				width : 80,
				align : 'center',
				formatter:'number', 
				formatoptions:{decimalPlaces: 0, thousandsSeparator: ","},
				sorttype:'int',
				//sortable:false
				hidden: true
			}, {
				name : 'id_vehiculo',
				//index : 'id_vehiculo',
				label : 'Veh&iacute;culo',
				width : 170,
				align : 'center',
				sorttype : 'text'
			}, {
				name : 'fecha',
				//index : 'fecha',
				label : 'Fecha evento',
				width : 110,
				align : "center",
				sortable:false
			}, {
				name : 'id_motivo',
				//index : 'id_motivo',
				label : 'Motivo',
				width : 60,
				align : 'center',
				sorttype : 'text',
				hidden: true
			}, {
				name : 'ubicacion',
				label : 'Ubicaci&oacute;n',
				width : 60,
				align : 'center',
				sorttype : 'text'
			}, {
				name : 'atendida',
				label : 'Atender',
				width : 140,
				align : 'center',
				sorttype : 'text'
			}, {
				name : 'fecha_atencion',
				label : 'Fecha Atenci&oacute;n',
				width : 110,
				align : "center",
				sortable:false,
				hidden: true
			}, {
				name : 'estatus',
				label : 'Estatus',
				width : 120,
				align : 'center',
				sorttype : 'text',
				hidden: true
			}],
			hidegrid: false, 
			multiselect : false,
			rowNum : 20,
			//width : 990,
			height : 460,
			rowList : [20, 40, 60],
			sortname : 'id',
			viewrecords : true,
			sortorder : "desc",
			rownumbers: true,
			//gridview : true,
			//toolbar : [true, "top"],
			caption : "Tablero de Alarmas"
//			pager: jQuery('#pagerListAlarmas'),
//			onPaging: function(pgButton){
////				alert("onPaging\n" + pgButton);
//				draicon.signatron.talarmas.load=true;
//				jQuery("#listAlarmas").clearGridData();
//			},
///*			jsonReader: {
//			    repeatitems: false,
//			    id: "idAlarma"
//			    root: function (obj) { return obj; },
//			    page: function (obj) { return 1; },
//			    total: function (obj) { return 1; },
//			    records: function (obj) { return obj.length; }
//			},*/
//			loadComplete: function() {
//				if(draicon.signatron.talarmas.load) {
//					alert("OK")
//					draicon.signatron.talarmas.load=false;
//				}else{
//					alert("load init")
//				}
//			},
//			datatype: function(postdata){
//				alert("datatype : " + postdata.rows + " - " + postdata.page);
///*				AAA_final = postdata;
//				alert("datatype : " + postdata.rows + " - " + postdata.page);
//				jQuery("#listAlarmas").clearGridData();
//				draicon.signatron.jsonrpc.AlarmasDAO.getGridFilterDataIntWithPager(draicon.signatron.talarmas.getGridCBAlarmas, draicon.signatron.authToken,  "idVehiculo.idCliente.idCliente", draicon.signatron.id_user, postdata.page, postdata.rows);
//				*/
//				if(draicon.signatron.talarmas.load){
//					draicon.signatron.jsonrpc.AlarmasDAO.getGridFilterDataIntWithPager(draicon.signatron.talarmas.getGridCBAlarmas, draicon.signatron.authToken,  "idVehiculo.idCliente.idCliente", draicon.signatron.id_user, postdata.page, postdata.rows);
//					draicon.signatron.talarmas.load=false;
//				}else{
//					draicon.signatron.jsonrpc.AlarmasDAO.getTotalDataIntWithPager(draicon.signatron.talarmas.getTotalCBAlarmas, draicon.signatron.authToken,  "idVehiculo.idCliente.idCliente", draicon.signatron.id_user);
//				}
//			}
//		}).navGrid('#pagerListAlarmas',{
//			refresh:true,
//			edit:false,
//			add:false,
//			del:false,
//			search:false
		});
		$("#dialogAlertas button").click(function() {
			$('#dialogAlertas').dialog('close');
			$('#tabs').tabs('select', 4);
		});
	},
	call: function() {
		draicon.signatron.jsonrpc.PosicionesDAO.getAlarmas(draicon.signatron.talarmas.getGridCBAlarmas, draicon.signatron.authToken, draicon.signatron.id_user);
	},
	getTotalCBAlarmas : function(result, error) {
		if (error) {
			alert(error + "\n error en getTotalCBAlarmas");
		} else {
			jQuery("#listAlarmas").setGridParam({page:1, total:result, records:result});
		}
	},
	verUbicacion: function(idVehiculo) {
		$('#tabs').tabs('select', 0);
		draicon.signatron.gmapFilter.verUltimaUbicacionVehiculo(idVehiculo);
	},
	atenderAlarma: function(idPosicion) {
		$('#dialogAtendiendoAlerta').dialog('open');
		draicon.signatron.talarmas.clearTimer();
		draicon.signatron.jsonrpc.PosicionesDAO.atenderAlarma(draicon.signatron.talarmas.getGridCBAlarmas, draicon.signatron.authToken, draicon.signatron.id_user, idPosicion);
	},
	getGridCBAlarmas : function(result, error) {
		if (error) {
			//alert("Error en Tablero de alertas");
			draicon.signatron.talarmas.clearTimer();
		} else {
			var data = new Array(), aIni='', aFin='', alertasNoAtendidas=0, actualizarGrid=false, mostrarDialog=false, paq=draicon.signatron.talarmas;
			for (var i = 0, fechaAlarma; i < result.list.length; i++) {
				var datos = result.list[i];
				aIni=(datos.alarmaStatus)?'':'<span class="alarmaNoAtendida">';
				aFin=(datos.alarmaStatus)?'':'</span>';
				if(datos.alarmaStatus==0) alertasNoAtendidas++;
				fechaAlarma=new Date(datos.fecha.time);
				fechaAlarma=new Date(fechaAlarma.getTime()+draicon.signatron.offset_ms);
				data[i] = {
					id_alarma : datos.idPosicion,
					id_vehiculo : (aIni+datos.idVehiculo.nombre+aFin),
					imei: (aIni+datos.idGps.imei+aFin),
					id_motivo : (aIni+((datos.idMotivo)?datos.idMotivo.motivo:draicon.signatron.mensajes.motivoDesconocido)+aFin),
					estatus: (aIni+((datos.alarmaStatus)? draicon.signatron.talarmas.status.atendida:draicon.signatron.talarmas.status.no_atendida)+aFin),
					fecha : (aIni+(fechaAlarma.format("d/mm/yy HH:MM:ss"))+aFin),
					fecha_atencion: (aIni+((datos.alarmaFecha && datos.alarmaFecha.time)? (new Date(datos.alarmaFecha.time)).format("d/mm/yy HH:MM:ss"):'')+aFin),
					ubicacion: '<button type="button" onclick="draicon.signatron.talarmas.verUbicacion('+datos.idVehiculo.idVehiculo+')">Ver</button>',
					atendida:  ((datos.alarmaStatus)?'':('<button type="button" onclick="draicon.signatron.talarmas.atenderAlarma('+datos.idPosicion+')">Marcar como atendida</button>'))
				};
			};
			if(paq.data==null){
				actualizarGrid=true;
				mostrarDialog=true;
			}else{
				if(paq.data.length!=data.length) {
					actualizarGrid=true;
					mostrarDialog=true;
				}else{
/*					for(var i=0, l=paq.data.length; i<l;i++){
						if( paq.data[i].id_alarma!=data[i].id_alarma) {
							mostrarDialog=true;
							break;
						}
					}*/
					for(var i=0, l=data.length; i<l;i++){
						if( paq.data[i].id_alarma     !=data[i].id_alarma ||
							paq.data[i].estatus       !=data[i].estatus   ||
							paq.data[i].fecha_atencion!=data[i].fecha_atencion) {
							actualizarGrid=true;
							break;
						}
					}
				}
			}
			if(actualizarGrid){
				var grid=jQuery("#listAlarmas");
				paq.data=data
				grid.clearGridData();
				for (var i=0; i<paq.data.length; i++) {
					grid.jqGrid('addRowData', (i+1), paq.data[i]); // data[i].id_alarma
				}
			}
			if(alertasNoAtendidas){
				$("#marcoAlerta").show();
				if(mostrarDialog){
					$('#dialogAlertas').dialog('open');
				}
			}else{
				$("#marcoAlerta").hide();
				$('#dialogAlertas').dialog('close');
			}
			
			$('#dialogAtendiendoAlerta').dialog('close');
			if(draicon.signatron.talarmas.timer===null) {
				draicon.signatron.talarmas.timer=setInterval("draicon.signatron.talarmas.call()", 10000);
			}
		}
	}
};