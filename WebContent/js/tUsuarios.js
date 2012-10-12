draicon.signatron.tusuarios = {
	init : function() {
		jQuery("#listUsuarios").jqGrid({
					height : 250,
					colNames : ['ID', 'Usuario', 'Password', 'Cliente'],
					colModel : [{
								name : 'idUser',
								index : 'id_user',
								width : 30,
								align : 'center'
							}, {
								name : 'usuario',
								index : 'usuario',
								width : 90,
								align : 'center',
								editable : true,
								editoptions : {
									size : 15 
								}
							}, {
								name : 'password',
								index : 'password',
								width : 80,
								type : 'password',
								align : 'center',
								editable : true,
								editoptions : {
									size : 15
								}
							}, {
								name : 'idCliente',
								index : 'id_cliente',
								width : 80,
								align : 'center'
							
							}

					],
					rowNum : 10,
					rowList : [10, 20, 30],
					pager : '#pagerUsuarios',
					sortname : 'id_user',
					viewrecords : true,
					sortorder : "desc",
					editurl : "..//serverUsuarios",
					width : 990,
					height :460,
					rownumbers : true,
					gridview : true,
					toolbar : [true, "top"],

					caption : "Usuarios"
				});

	},

	getGridCBUsuarios : function(result, error) {

		if (error) {
			alert(error + "e2");
		} else {
			var data = new Array();
			for (var i = 0; i < result.list.length; i++) {
				var datos = result.list[i];
				data[i] = {
					idUser    : datos.idUser,
					usuario    : datos.usuario,
					password   : datos.password,
					idCliente  : datos.idCliente.nombre + " " + datos.idCliente.apPaterno + " " + datos.idCliente.apMaterno
					
				};
			};
			for (var i = 0; i <= data.length; i++) {
				jQuery("#listUsuarios").jqGrid('addRowData', i + 1, data[i]);
			}
			jQuery("#listUsuarios").jqGrid('navGrid', '#pagerUsuarios', {
						view : true,
						addedrow : 'last'
					}, // options
					{
						height : 140,
						reloadAfterSubmit : false,
						closeAfterEdit : true,
						addedrow : 'last'
					}, // edit options
					{
						height : 140,
						reloadAfterSubmit : false,
						closeAfterAdd : true
					}, // add options
					{
						height : 140,
						reloadAfterSubmit : false,
						closeAfterEdit : true,
						onclickSubmit : function(eparams) {
						    var retarr = {};
						    var sr = jQuery("#listUsuarios").getGridParam('selrow');
						    rowdata = jQuery("#listUsuarios").getRowData(sr);
						         retarr = {id_gps:rowdata.id_user};
						    return retarr;
						}
					}, // del options
					{} // search options
					);
		}
	}

},

jQuery(document).ready(function() {
			draicon.signatron.tusuarios.init();
		});
