draicon.signatron.tadministrador = {
	init : function() {
		jQuery("#listAdministrador").jqGrid({
			height : 250,
			colNames : ['ID', 'Nombre', 'Ap. Paterno', 'Ap.Materno'],
			colModel : [{
						name : 'id_administrador',
						index : 'id_administrador',
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
					
					}
				
					

			],
			rowNum : 10,
			rowList : [10, 20, 30],
			pager : '#pagerAdministrador',
			sortname : 'id_administrador',
			viewrecords : true,
			sortorder : "desc",
			editurl : "..//serverAdministrador",
			width : 990,
			height :460,
			rownumbers : true,
			gridview : true,
			toolbar : [true, "top"],

			caption : "Administrador"
		});

	},

	getGridCBAdministrador : function(result, error) {

		if (error) {
			alert(error + "e2");
		} else {
			var data = new Array();
			for (var i = 0; i < result.list.length; i++) {
				var datos = result.list[i];
				data[i] = {
					id_administrador : datos.idAdministrador,
					nombre : datos.nombre,
					ap_paterno : datos.apPaterno,
					ap_materno : datos.apMaterno
				};
			};
			for (var i = 0; i <= data.length; i++) {
				jQuery("#listAdministrador").jqGrid('addRowData', i + 1, data[i]);
			}
			jQuery("#listAdministrador").jqGrid('navGrid', '#pagerAdministrador', {
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
						    var sr = jQuery("#listAdministrador").getGridParam('selrow');
						    rowdata = jQuery("#listAdministrador").getRowData(sr);
						         retarr = {id_gps:rowdata.id_administrador};
						    return retarr;
						}
					}, // del options
					{} // search options
					);
		}
	}

},
jQuery(document).ready(function() {
	draicon.signatron.tadministrador.init();
});
