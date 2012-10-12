draicon.signatron.envioComados = {
	vehiculo : null,
	comando : null,
	telefono : null,
	mensaje : null,
	registros : 0,
	init : function() {
		jQuery('#bEnviaComando').show();
		jQuery('#numTel').hide();
		jQuery('#msgText').hide();

		jQuery('#seccionHistoricoComandos').hide();

		jQuery("#historicoComandos").jqGrid({
			height : 250,
			colNames : ['Veh&iacute;culo', 'Comando', 'Tel&eacute;fono',
					'Mensaje enviado', 'Estatus', 'IMEI', 'Fecha env&iacute;o',
					'Mensaje sistema'],
			colModel : [{
						name : 'vehiculo',
						index : 'vehiculo',
						width : 130,
						align : 'left'
					}, {
						name : 'comando',
						index : 'comando',
						width : 120,
						align : 'left'
					}, {
						name : 'telefono',
						index : 'telefono',
						width : 100,
						align : 'left'
					}, {
						name : 'mensaje',
						index : 'mensaje',
						width : 200,
						align : 'left'
					}, {
						name : 'estatus',
						index : 'estatus',
						width : 60,
						align : 'center'
					}, {
						name : 'imei',
						index : 'imei',
						width : 100,
						align : 'center'
					}, {
						name : 'fechaEnvioStr',
						index : 'fechaEnvio',
						width : 100,
						align : 'center'
					}, // , formatter: 'date'}, // , formatoptions: {srcformat:
						// 'd/m/y',newformat: 'd/m/Y'}
					{
						name : 'sistema',
						index : 'sistema',
						width : 180,
						align : 'center'
					}],
			// pager: '#pagerResultadosID',
			// rowNum:10,
			// rowList:[10,20,30],
			// viewrecords: true,
			hidegrid: false,
			caption : "Hist&oacute;rico de env&iacute;o de comandos"
		});
		
		$("#envComTel").keydown(function(event) {
			// Backspace y delete
			if ( event.keyCode == 46 || event.keyCode == 8 ) {
				
			} else {
				// Solo número, caso contrario, se detiene el keypress
				if (event.keyCode < 95) {
					// Flecha izquierda y derecha
					if (event.keyCode == 37 || event.keyCode == 39) {
						
					} else if (event.keyCode < 48 || event.keyCode > 57 ) {
						event.preventDefault();	
					}
				} else if (event.keyCode < 96 || event.keyCode > 105 ) {
					event.preventDefault();	
				}
			}
		});
	},
	getComandos: function() {
		draicon.signatron.jsonrpc.ComandosDAO.getCommands(
				draicon.signatron.envioComados.getComandosCB,
				draicon.signatron.authToken);
	},
	getComandosCB: function(result, error) {
		if(error){
			alert("Error al obtener los comandos\n"+error);
		}else{
			var options='<option value=""></option>';
			for(var i=0, len=result.list.length, command; i<len;i++){
				command=result.list[i];
				options+='<option value="' + command.idMotivo + '">' + command.motivo + '</option>';
			}
			$("select#cboEnvCom").html(options);
		}
	},
	cboEnvCom : function() {
		this.comando = $("#cboEnvCom").val();
		this.reset();
		if (this.comando == "69") {
			jQuery('#numTel').show();
			jQuery('#msgText').hide();
		} else if (this.comando == "55") {
			jQuery('#msgText').show();
			jQuery('#numTel').hide();
		} else {
			jQuery('#numTel').hide();
			jQuery('#msgText').hide();
		}
	},
	enviaComando : function() {
		var that = this, faltanDatos = false, mensaje = "";
		this.vehiculo = $("#cboVehiculosComandos").val();
		this.comando = $("#cboEnvCom").val();
		this.telefono = $("#envComTel").val();
		this.mensaje = $("#envComMens").val();

		if (this.vehiculo === null || this.vehiculo == ""
				|| this.comando === null || this.comando == "") {
			mensaje += "<br/>Seleccione un veh&iacute;culo y un comando para enviar.";
			faltanDatos = true;
		}
		if ((this.comando == "6" || this.comando == "8")
				&& (this.telefono === null || this.telefono == "")) {
			mensaje += "<br/>El n&uacute;mero telef&oacute;nico es obligatorio.";
			faltanDatos = true;
		}
		if (this.comando == "55"
				&& (this.mensaje === null || this.mensaje == "")) {
			mensaje += "<br/>El mensaje es obligatorio.";
			faltanDatos = true;
		}
		if (this.comando == "69"
			&& (this.telefono === null || this.telefono == "")) {
			mensaje += "<br/>El n&uacute;mero telef&oacute;nico es obligatorio.";
			faltanDatos = true;
		}

		if (!faltanDatos) {
			$('#dialogLoading').dialog('open');
			setTimeout(function() {
						draicon.signatron.jsonrpc.ComandosDAO.sendCommand(
								draicon.signatron.envioComados.sendCB,
								draicon.signatron.authToken, that.vehiculo,
								that.comando, that.telefono, that.mensaje);
						that.vehiculo = $('#cboVehiculosComandos option:selected')
								.text();
						that.comando = $('#cboEnvCom option:selected').text();
					}, 1000);
		} else {
			$("#msgdialogComandosFiltro").html(mensaje);
			$('#dialogComandosFiltro').dialog('open');
		}
	},
	sendCB : function(result, error) {
		jQuery('#seccionHistoricoComandos').show();
		$('#dialogLoading').dialog('close');
		if (error) {
			alert(error + " error al enviar comando");
		} else {
			var that = draicon.signatron.envioComados;
			that.registros++;
			var historico = {
				vehiculo : that.vehiculo,
				comando : that.comando,
				telefono : that.telefono,
				mensaje : that.mensaje,
				estatus : (result.status == 'S') ? 'Enviado' : 'Error',
				imei : (result.idVehiculo === null || result.idVehiculo.idGps === null)
						? ''
						: result.idVehiculo.idGps.imei,
				fechaEnvio : (new Date(result.fechaComando.time)), // .format(dateFormat.masks.isoDateTime)
				fechaEnvioStr : (new Date(result.fechaComando.time))
						.format("d/mm/yy HH:MM:ss"),
				sistema : result.alerta
			};
			jQuery("#historicoComandos").jqGrid('addRowData', that.registros,
					historico, 'first');
		}
	},
	reset : function() {
		$("#envComTel").val("");
		$("#envComMens").val("");
	}
};