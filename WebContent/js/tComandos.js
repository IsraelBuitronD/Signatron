draicon.signatron.tcomandos = {
	init : function() {
		jQuery("#listComandos").jqGrid( {
			height :250,
			colNames : [ 'ID', 'Motivo','C&oacute;digo', 'Comando' ],
			colModel : [ {
				name :'idMotivo',
				index :'id_motivo',
				width :10,
				align :'center'
			}, {
				name :'motivo',
				index :'motivo',
				width :40,
				align :'center',
				edittype :"textarea",
				editoptions : {
					rows :"2",
					cols :"40"
				},
				editable :true
				
			}, {
				name :'codigo',
				index :'codigo',
				width :30,
				align :'center',
				editable :true,
				editoptions : {
					size :10 
				}
			}, {
				name :'comando',
				index :'comando',
				width :10,
				align :'center',
				editable :true,
				editoptions : {
					size :10
				}
			}],
			rowNum :10,
			rowList : [ 10, 20, 30 ],
			pager :'#pagerComandos',
			sortname :'id_motivo',
			viewrecords :true,
			sortorder :"desc",
			editurl :"..//serverComandos",
			width :990,
			height :460,
			rownumbers :true,
			gridview :true,
			toolbar : [ true, "top" ],
			caption :"Motivo de Comandos de Env&iacute;o"
		});
	},
	getGridCBComandos : function(result, error) {
		if (error) {
			alert("Error al obtener los comandos.\n" + error);
		} else {
			var data = new Array();
			for ( var i = 0; i < result.list.length; i++) {
				var datos = result.list[i];
				data[i] = {
					idMotivo :datos.idMotivo,
					motivo : datos.motivo,
					codigo :datos.codigo,
					comando : datos.iscommand
				};
			}
			;
			for ( var i = 0; i <= data.length; i++) {
				jQuery("#listComandos").jqGrid('addRowData', i + 1, data[i]);
			}

			jQuery("#listComandos").jqGrid('navGrid', '#pagerComandos', {
				view :true
			}, // options
					{
						height :180,
						reloadAfterSubmit :false,
						closeAfterEdit :true
					}, // edit
					// options
					{
						height :180,
						reloadAfterSubmit :false,
						closeAfterAdd :true
					}, // add
					// options
					{
						height :180,
						reloadAfterSubmit :false,
						closeAfterEdit :true,
						onclickSubmit : function(eparams) {
						    var retarr = {};
						    var sr = jQuery("#listComandos").getGridParam('selrow');
						    rowdata = jQuery("#listComandos").getRowData(sr);
						         retarr = {id_gps:rowdata.id_motivo};
						    return retarr;
						}
					}, // del
					// options
					{} // search options
					);

		}
	}
};