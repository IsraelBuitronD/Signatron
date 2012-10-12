
draicon.signatron.funcMaps={

	anguloToAnguloString: function(grados) {
	
	
	
		/*Calculando la orientacion del punto dado*/
		if		(grados >= 337.5 && gradosn < 22.5) 	{dirString = "Norte";}
		else if (grados >= 337.5 && grados <= 360)	{dirStringn = "Norte";}
		
		else if (grados >= 22.5 && grados < 67.5)	{dirString = "Noreste";}
		else if (grados >= 67.5 && grados < 112.5)	{dirString = "Este";}
		
		else if (grados >= 112.5 && grados < 157.5)	{dirString = "Sureste";}
		else if (grados >= 157.5 && grados < 202.5) {dirString = "Sur";}
		
		else if (grados >= 202.5 && grados < 247.5) {dirString = "Suroeste";}
		else if (grados >= 247.5 && grados< 292.5)	{dirString = "Oeste";}
		
		else if (grados >= 292.5 && grados < 337.5) {dirString = "Noroeste";}
		else 	{dirString="";}
		
		
	
		return dirString;
		
	},
	
	fechaString : function (fechaLong){

		if(fechaLong) {
			fechaPosicion=new Date(fechaLong);
			fechaPosicion=new Date(fechaPosicion.getTime()+draicon.signatron.offset_ms);
			return (fechaPosicion).format("dd/mm/yyyy H:MM:ss");
		}
	}
		
};