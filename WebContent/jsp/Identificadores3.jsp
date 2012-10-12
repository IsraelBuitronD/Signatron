<script type="text/javascript">
draicon={};
draicon.signatron={};
draicon.signatron.dataCalls={};
draicon.signatron={			
		onReady: function (){
		
		draicon.signatron.jsonrpc = new JSONRpcClient(draicon.signatron.jsonRPCCallBack,"/signatron/JSON-RPC");
},
 	

	jsonRPCCallBack: function(result, error) {
	
	if(error) {
		alert(error);
	} else {
		
		draicon.signatron.jsonrpc.VehiculosDAO.getGridData(draicon.signatron.getGridCB, "draicon");
		
	}
},
getGridCB: function(result, error) {
	
	if(error) {
		
		alert(error+"e2" );
	} else {
		var data = new Array();
		for(var i=0;i<result.list.length;i++){
			var datos = result.list[i];
			data[i] = {nombre: datos.nombre, id_vehiculo:datos.idVehiculo};
		};
		
		var options = '';
		for (var i = 0; i < data.length; i++) {
		    options += '<option value="' + data[i].id_vehiculo + '">' + data[i].nombre + '</option>';
		    }
		
		 $("select#cboVehiculos").html(options);
		
	    }
	}
}

jQuery(document).ready(function(){
	draicon.signatron.onReady();
});

</script>


<form action="" method="post" id="envioID"> 
	
	<p> 
		Indentificadores: <br/>
		<select name="cboVehiculos" id="cboVehiculos" multiple="multiple" >
     
		</select> 
	</p> 

<div class="autos ">
<input type="submit" name="submit" value="Buscar en Mapa" onclick= /> 
<input type="reset" name="reset" value="Reset" /> 
</div>
</form> 
