draicon.signatron.tcliente = {
	init : function() {
		jQuery("#listClientes").jqGrid({
			height : 250,
			colNames : ['ID', 'Nombre', 'Ap. Paterno', 'Ap.Materno', 'RFC', 'Razón Social',
					'Status', 'Calle', 'N.int', 'N.ext', 'CP', 'Ciudad',
					'Mail', 'Tel1', 'Ext1', 'Huso'],
			colModel : [{
						name : 'id_cliente',
						index : 'id_cliente',
						width : 25,
						align : 'center'
					}, {
						name : 'nombre',
						index : 'nombre',
						width : 70,
						align : 'center',
						editable : true,
						editoptions : {
							size : 40
						}
					}, {
						name : 'ap_paterno',
						index : 'ap_paterno', 
						width : 70,
						align : 'center',
						editable : true,
						editoptions : {
							size : 40
						}
					}, {
						name : 'ap_materno',
						index : 'ap_materno',
						width : 70,
						align : 'center',
						editable : true,
						editoptions : {
							size : 40
						}
					}, {
						name : 'rfc',
						index : 'rfc',
						width : 50,
						align : 'center',
						editable : true,
						editoptions : {
							size : 15
						}
					}, {
						name : 'razon',
						index : 'razon_soc',
						width : 60,
						align : 'center',
						editable : true,
						editoptions : {
							size : 15
						}
					}, {
						name : 'id_status',
						index : 'id_status',
						width : 30,
						align : 'center',
						editable : true,
						editoptions : {
							size : 15
						}
					}, {
						name : 'calle',
						index : 'calle',
						width : 50,
						align : 'center',
						editable : true,
						editoptions : {
							size : 40
						}
					}, {
						name : 'num_int',
						index : 'num_int',
						width : 30,
						align : 'center',
						editable : true,
						editoptions : {
							size : 8
						}
					}, {
						name : 'num_ext',
						index : 'num_ext',
						width : 30,
						align : 'center',
						editable : true,
						editoptions : {
							size : 8
						}
					}, {
						name : 'cp',
						index : 'cp',
						width : 30,
						align : 'center',
						editable : true,
						editoptions : {
							size : 15
						}
					}, {
						name : 'ciudad',
						index : 'ciudad',
						width : 50,
						align : 'right',
						editable : true,
						editoptions : {
							size : 40
						}
					}, {
						name : 'mail',
						index : 'mail',
						width : 50,
						align : 'right',
						editable : true,
						editoptions : {
							size : 40
						}
					}, {
						name : 'tel1',
						index : 'tel1',
						width : 50,
						align : 'right',
						editable : true,
						editoptions : {
							size : 30
						}
					}, {
						name : 'ext1',
						index : 'ext1',
						width : 25,
						align : 'right',
						editable : true,
						editoptions : {
							size : 15
						}
					}, {
						name : 'huso',
						index : 'huso',
						width : 25,
						align : 'center',
						editable : true,
						editoptions : {
							size : 30
						}
					}
				
					

			],
			rowNum : 10,
			rowList : [10, 20, 30],
			pager : '#pagerClientes',
			sortname : 'id_cliente',
			viewrecords : true,
			sortorder : "desc",
			editurl : "..//serverClientes",
			width : 990,
			height :460,
			rownumbers : true,
			gridview : true,
			toolbar : [true, "top"],

			caption : "Clientes"
		});

	},

	getGridCBCliente : function(result, error) {

		if (error) {
			alert(error + "e2");
		} else {
			var data = new Array();
			for (var i = 0; i < result.list.length; i++) {
				var datos = result.list[i];
				data[i] = {
					id_cliente : datos.idCliente,
					nombre : datos.nombre,
					ap_paterno : datos.apPaterno,
					ap_materno : datos.apMaterno,
					rfc : datos.rfc,
					razon : datos.razonSoc,
					id_status : datos.idStatus,
					calle : datos.calle,
					num_int : datos.numInt,
					num_ext : datos.numExt,
					cp : datos.cp,
					ciudad : datos.ciudad,
					mail : datos.mail,
					tel1 : datos.tel1,
					ext1 : datos.ext1,
					huso : datos.huso
				};
			};
			for (var i = 0; i <= data.length; i++) {
				jQuery("#listClientes").jqGrid('addRowData', i + 1, data[i]);
			}
			jQuery("#listClientes").jqGrid('navGrid', '#pagerClientes', {
						view : true
					}, // options
					{
						height : 470,
						reloadAfterSubmit : false,
						closeAfterEdit : true
					}, // edit options
					{
						height : 470,
						reloadAfterSubmit : false,
						closeAfterAdd : true
					}, // add options
					{
						height : 470,
						reloadAfterSubmit : false,
						closeAfterEdit : true,
						
						onclickSubmit : function(eparams) {
						    var retarr = {};
						    var sr = jQuery("#listClientes").getGridParam('selrow');
						    rowdata = jQuery("#listClientes").getRowData(sr);
						         retarr = {id_gps:rowdata.id_cliente};
						    return retarr;
						}
					}, // del options
					{} // search options
					);
		}
	}

};

