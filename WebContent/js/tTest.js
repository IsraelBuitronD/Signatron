draicon.signatron.ttest = {
	init : function() {

		jQuery("#listCedula").jqGrid({
					height : 250,
					colNames : ['ID', 'Latitud', 'Longitud', 'S1', 'S2', 'S3',
							'S4'],
					colModel : [{
								name : 'id_posicion',
								index : 'id_posicion',
								width : 30,
								align : 'right'
							}, {
								name : 'latitud',
								index : 'latitud',
								width : 90,
								align : 'right'
							}, {
								name : 'longitud',
								index : 'longitud',
								width : 80,
								align : 'right'
							}, {
								name : 'sensor1',
								index : 'sensor1',
								width : 20,
								align : 'center'
							}, {
								name : 'sensor2',
								index : 'sensor2',
								width : 20,
								align : 'center'
							}, {
								name : 'sensor3',
								index : 'sensor3',
								width : 20,
								align : 'center'
							}, {
								name : 'sensor4',
								index : 'sensor4',
								width : 20,
								align : 'center'
							}

					],

					rowNum : 10,
					rowList : [10, 20, 30],
					pager : '#pagerCedula',
					sortname : 'id_posicion',
					viewrecords : true,
					sortorder : "desc",
					width : 990,
					rownumbers : true,
					gridview : true,

					caption : "Cedula"
				});

	},
	getGridCBCedula : function(result, error) {
		if (error) {
			alert(error + "e2");
		} else {
			var data = new Array();
			for (var i = 0; i < result.list.length; i++) {
				var datos = result.list[i];
				data[i] = {
					id_posicion : datos.idPosicion,
					latitud : datos.latitud,
					longitud : datos.longitud,
					sensor1 : datos.sensor1,
					sensor2 : datos.sensor2,
					sensor3 : datos.sensor3,
					sensor4 : datos.sensor4
				};
			};
			for (var i = 0; i <= data.length; i++) {
				jQuery("#listCedula").jqGrid('addRowData', i + 1, data[i]);
			}

		}
	}
},

jQuery(document).ready(function() {
			draicon.signatron.ttests.init();
		});

