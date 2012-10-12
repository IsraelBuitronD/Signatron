draicon.signatron.tgps = {
	init : function() {
		jQuery("#listGPS").jqGrid( {
			height :250,
			colNames : [ 'ID', 'IMEI','Serie', 'Descripci&oacute;n' ],
			colModel : [ {
				name :'id_gps',
				index :'id_gps',
				width :30,
				align :'center',
				
				editoptions : {
					readonly:true
				}
			}, {
				name :'imei',
				index :'imei', 
				width :35,
				align :'center',
				
				editoptions : {
					size :15
				}
			}, {
				name :'serie',
				index :'serie',
				width :30,
				align :'center',
				
				editoptions : {
					size :15
				}
			}, {
				name :'descripcion',
				index :'descripcion',
				align :'center',
				
				edittype :"textarea",
				editoptions : {
					rows :"3",
					cols :"40"
				}
			}

			],

			rowNum :10,
			rowList : [ 10, 20, 30 ],
			//pager :'#pagerGPS',
			sortname :'id_gps',
			viewrecords :true,
			sortorder :"desc",
			editurl :"..//serverGPS",
			width :990,
			height :460,
			rownumbers :true,
			gridview :true,
			toolbar : [ true, "top" ],
			caption :"GPS"
			
		
		});
		
	},
	getGridCBGPS : function(result, error) {
		if (error) {
			alert(error + "e2");
		} else {
			var data = new Array();
			for ( var i = 0; i < result.list.length; i++) {
				var datos = result.list[i];
				data[i] = {
					id_gps :datos.idGps,
					imei : datos.imei,
					serie :datos.serie,
					descripcion :datos.descripcion
				};
			}
			;
			for ( var i = 0; i <= data.length; i++) {
				jQuery("#listGPS").jqGrid('addRowData', i + 1, data[i]);
			}


			jQuery("#listGPS").jqGrid('navGrid', '#pagerGPS', {
				view :true
			}, // options
					{
						height :200,
						reloadAfterSubmit :true,
						closeAfterEdit :true
					}, // edit
					// options
					{
						height :200,
						reloadAfterSubmit :true,
						closeAfterAdd :true
					}, // add
					// options
					{
						height :110,
						reloadAfterSubmit :true,
						closeAfterEdit :true,
						onclickSubmit : function(eparams) {
						    var retarr = {};
						    var sr = jQuery("#listGPS").getGridParam('selrow');
						    rowdata = jQuery("#listGPS").getRowData(sr);
						         retarr = {id_gps:rowdata.id_gps};
						    return retarr;
						}
						
						
						
					}, // del
					// options
					{} // search options
					);

		}
	}
	
	
};