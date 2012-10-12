draicon.signatron.tsetup = {
	init : function() {
		jQuery("#listSetup").jqGrid( {
			height :250,
			colNames : ['Puerto de Entrada','Puerto de Salida', 'IP Servidor' ],
			colModel : [{
				name :'portIn',
				index :'port_in',
				width :40,
				align :'center',
				sortable: false,
				editable :true,
				editoptions : {
					size :15
				}
			}, {
				name :'portOut',
				index :'port_out',
				width :30,
				align :'center',
				sortable: false,
				editable :true,
				editoptions : {
					size :15
				}
			}, {
				name :'server',
				index :'server',
				align :'center',
				sortable: false,
				editable :true,
				editoptions : {
					rows :"3",
					cols :"40"
				}
			}],
			pager :'#pagerSetup',
			pgbuttons: false,
			pginput: false,
			viewrecords: false,
			hidegrid: false, 
			editurl :"..//serverSetup",
			width :990,
			height :45,
			rownumbers :true,
			gridview :true,
			caption :"Setup"
		}).navGrid('#pagerSetup',{ 
			view:true,
			edit:true,
			add:false,
			del:false,
			search:false
		},{ 
			height :200,
			reloadAfterSubmit :false,
			closeAfterAdd :true
		},{ // use default settings for edit
		},{ // use default settings for add
		},{ // delete instead that del:false we need this
		},{ // // search options
		},{
			closeOnEscape:true
		});
	},
	getGridCBSetup : function(result, error) {
		if (error) {
			alert("Error al obtener los parámetros de configuración.\n" + error);
		} else {
			var data = new Array();
			for(var i=0; i<result.list.length; i++) {
				var datos = result.list[i];
				data[i] = {
					id: datos.idSetup, 
					portIn: datos.portIn,
					portOut: datos.portOut,
					server: datos.server
				};
			};

			for(var i=0; i<data.length; i++) {
				jQuery("#listSetup").jqGrid('addRowData', data[i].id, data[i]);
			}
		}
	}
};