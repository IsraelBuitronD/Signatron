// filtro para las opcion de busqueda de vehiculo del tab de reportes
draicon.signatron.filterIndicadores = {
	sendID : function() {
		draicon.signatron.vehiR = $("#cboVehiculosR").val();
	},
	reset : function() {
		// document.getElementById("cboVehiculos").selectedIndex=-1;
		$("#cboVehiculosR").val(null);
	}
}