draicon.signatron.tvehiculos = {
	init : function() {

		jQuery("#listVehiculos").jqGrid(
				{
					height :250,
					colNames : [ 'ID', 'ID GPS', 'Marca', 'Nombre', 'Descripci&oacute;n',
							'Placa', 'Cliente' , 'Razon Social'],
					colModel : [ {
						name :'idVehiculo',
						index :'id_vehiculo',
						width :15,
						align :'center'
					}, {
						name :'gps',
						index :'gps', 
						width :15,
						align :'center',
						editable :true,
						editoptions : {
							size :15
						}		 
					}, {
						name :'marca',
						index :'marca', 
						width :30,
						align :'center',
						editable :true,
						editoptions : {
							size :15
						}
					}, {
						name :'nombre',
						index :'nombre',
						width :55,
						align :'center',
						editable :true,
						editoptions : {
							size :35
						}
					}, {
						name :'descripcion',
						index :'descripcion',
						align :'center',
						editable :true,
						edittype :"textarea",
						editoptions : {
							rows :"2",
							cols :"30"
						}
					}, {
						name :'placa',
						index :'placa',
						width :15,
						align :'center',
						editable :true,
						editoptions : {
							size :15
						}
					}, {
						name :'Cliente',
						index :'cliente',
						width :40,
						align :'center',
						edittype: 'select',
						editable :true,
						editoptions : {value:'',
								       size :40
									  }
				
					}, {
						name :'Razon',
						index :'id_cliente',
						width :40,
						editable :true,
						align :'right',
						editoptions : {
							size :40
						}
					}

					],

					rowNum :10,
					rowList : [ 10, 20, 30 ],
					pager :'#pagerVehiculos',
					sortname :'id_vehiculo',
					viewrecords :true,
					sortorder :"desc",
					editurl :"..//serverVehiculos",
					width :990,
					height :460,
					rownumbers :true,
					gridview :true,
					toolbar : [ true, "top" ],

					caption :"Vehiculos"
				});

	},
	getGridCBVehiculos : function(result, error) {
		if (error) {
			alert(error + "e2");
		} else {
			var data = new Array();
			for ( var i = 0; i < result.list.length; i++) {
				var datos = result.list[i];
				data[i] = {
					idVehiculo : datos.idVehiculo,
					gps : datos.idGps.idGps,
					marca :datos.marca,
					nombre :datos.nombre,
					descripcion :datos.descripcion,
					placa :datos.placa,
					Cliente :datos.idCliente.nombre + " " + datos.idCliente.apPaterno + " " +  datos.idCliente.apMaterno,
					Razon :datos.idCliente.razonSoc
				};
			}
			;
			for ( var i = 0; i <= data.length; i++) {
				jQuery("#listVehiculos").jqGrid('addRowData', i + 1, data[i]);
			}
			jQuery("#listVehiculos").jqGrid('navGrid', '#pagerVehiculos', {
				view :true,
				cloneToTop :true
			}, // options
					{
						height :280,
						reloadAfterSubmit :false,
						closeAfterEdit :true
					}, // edit
					// options
					{
						height :280,
						reloadAfterSubmit :false,
						closeAfterAdd :true
					}, // add
					// options
					{
						height :280,
						reloadAfterSubmit :false,
						closeAfterEdit :true,
						onclickSubmit : function(eparams) {
						    var retarr = {};
						    var sr = jQuery("#listVehiculos").getGridParam('selrow');
						    rowdata = jQuery("#listVehiculos").getRowData(sr);
						         retarr = {id_gps:rowdata.id_vehiculo};
						    return retarr;
						}
					}, // del
					// options
					{} // search options
					);
		}
	}
	,
	getGridCBCboCliente : function(result, error) {
		if (error) {
			alert(error + "e2 getGridCBCboCliente");
		} else {
			var dcliente = new Array();
			for (var i = 0; i < result.list.length; i++) {
				var datosC = result.list[i];
				dcliente[i] = {
					idCliente : datosC.idCliente,
					nombreCliente :datosC.idCliente.nombre + " " + datosC.idCliente.apPaterno + " " +  datosC.idCliente.apMaterno				
				};
			};
			var options = '';
			for (var j = 0; j < dcliente.length; j++) {
				options +=  dcliente[j].idCliente + ':' + dcliente[j].nombreCliente + ';"';
			}
			$("select#Cliente").html(options);
			$("select#Cliente").html('value:"' + options);
			
		
		}

		}
	
	
	
	
	
},

jQuery(document).ready( function() {
	draicon.signatron.tvehiculos.init();
});
