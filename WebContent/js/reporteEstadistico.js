draicon.signatron.reporteEstadistico = {

	init : function() {

	},

	parametros : function() {
		draicon.signatron.startdate = $("#startdate").val();
		draicon.signatron.enddate = $("#enddate").val();
		draicon.signatron.cboVehiculosR = $("#cboVehiculosR").val();
		draicon.signatron.cboVehiculosName = $("#cboVehiculosR option:selected")
				.text();
		$("#startd").html(draicon.signatron.startdate);
		$("#endd").html(draicon.signatron.enddate);
		$("#idVeh").html(draicon.signatron.cboVehiculosName);
	},

	calTiempos : function() {

	}

}