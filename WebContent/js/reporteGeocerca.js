draicon.signatron.reporteGeocerca = {

	init : function() {

	},

	parametros : function() {
		draicon.signatron.startdateGC = $("#startdate").val();
		draicon.signatron.enddateGC = $("#enddate").val();
		draicon.signatron.cboVehiculosRGC = $("#cboVehiculosR").val();
		draicon.signatron.cboVehiculosNameGC = $("#cboVehiculosR option:selected")
				.text();
		$("#startdGC").html(draicon.signatron.startdateGC);
		$("#enddGC").html(draicon.signatron.enddateGC);
		$("#idVehGC").html(draicon.signatron.cboVehiculosNameGC);
	}

}