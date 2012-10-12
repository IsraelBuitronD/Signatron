draicon.signatron.fuel = {

		init: function(){
			/*
			draicon.signatron.lat1 = 19.3916902;
			draicon.signatron.lon1 = -99.0719817;
			draicon.signatron.lat2 = 19.3923298;
			draicon.signatron.lon2 = -99.1423598;
			draicon.signatron.unit = "K";
		
			draicon.signatron.fuel.distance(draicon.signatron.lat1, draicon.signatron.lon1, draicon.signatron.lat2, draicon.signatron.lon2, draicon.signatron.unit);
			*/
		},
		
		

		distance: function(lat1, lat2, lon1, lon2, unit){
		
			alert('datos que se pasaron:' + lat1 +','+ lat2+','+ lon1+','+ lon2+','+ unit);
			draicon.signatron.radlat1 = Math.PI * lat1/180;
			draicon.signatron.radlat2 = Math.PI * lat2/180;
			draicon.signatron.radlon1 = Math.PI * lon1/180;
			draicon.signatron.radlon2 = Math.PI * lon2/180;
			//alert('draicon.signatron.radlat1 :' + draicon.signatron.radlat1 );
			//alert('draicon.signatron.radlat2 :' + draicon.signatron.radlat2 );
			//alert('draicon.signatron.radlon1 :' + draicon.signatron.radlon1 );
			//alert('draicon.signatron.radlon2 :' + draicon.signatron.radlon2 );
			draicon.signatron.theta = lon1-lon2;
			draicon.signatron.radtheta = Math.PI * draicon.signatron.theta/180;
			draicon.signatron.dist = Math.sin(draicon.signatron.radlat1) * Math.sin(draicon.signatron.radlat2) + Math.cos(draicon.signatron.radlat1) * Math.cos(draicon.signatron.radlat2) * Math.cos(draicon.signatron.radtheta);
			//alert('draicon.signatron.dist 1:' + draicon.signatron.dist);
			draicon.signatron.dist = Math.acos(draicon.signatron.dist);
			//alert('draicon.signatron.dist 2:' + draicon.signatron.dist);
			draicon.signatron.dist = draicon.signatron.dist * 180/Math.PI;
			//alert('draicon.signatron.dist 3:' + draicon.signatron.dist);
			draicon.signatron.dist = draicon.signatron.dist * 60 * 1.1515;
			//alert('draicon.signatron.dist 4:' + draicon.signatron.dist);
			if (unit=="K") { draicon.signatron.dist = draicon.signatron.dist * 1.609344;};
			//if (draicon.signatron.unit=="N") { draicon.signatron.dist = draicon.signatron.dist * 0.8684 }
			draicon.signatron.dist = Math.round(( draicon.signatron.dist ) * 100) / 100;
			//alert('distancia:' + draicon.signatron.dist);
			return draicon.signatron.dist;
		},   


		 redondeo: function(num, dec){
			alert('num:' + num)
			draicon.signatron.num = parseFloat(draicon.signatron.num);
			draicon.signatron.dec  = parseFloat(draicon.signatron.dec);
			draicon.signatron.dec = (!draicon.signatron.dec ? 2: draicon.signatron.dec);
			return Math.round(draicon.signatron.num * Math.pow(10,draicon.signatron.dec))/Math.pow(10,draicon.signatron.dec);
		}

	


},
jQuery(document).ready(function() {
	draicon.signatron.fuel.init();
});