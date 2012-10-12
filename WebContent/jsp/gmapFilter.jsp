<script src="../js/gmapFilter.js" type="text/javascript"></script>
<%String vehiculos = request.getParameter("cboVehiculos"); 
%>
<div>&nbsp;<span id="map_filter_mensaje" style="display:none">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="../images/loading3.gif"/>Actualizando informaci&oacute;n...</span><span id="map_filter_timer" style="display:none"></span>
</div>
<div id="accordion">
    <h3><a href="#">&nbsp;</a></h3>
	    <div class="clear ">
			<div id="geoLocalizacion" class="demo">
				<input type="radio" id="geoLocalizacionFiltro"    name="geoLocalizacion" onclick="draicon.signatron.gmapFilter.showTab(0)"/><label for="geoLocalizacionFiltro">&nbsp;&nbsp; Buscar Unidad &nbsp;&nbsp; </label>
				<input type="radio" id="geoLocalizacionResultado" name="geoLocalizacion" onclick="draicon.signatron.gmapFilter.showTab(1)"/><label for="geoLocalizacionResultado">&nbsp;&nbsp;Tabla de Resultados&nbsp;&nbsp;</label>
			</div> 
			<div id="geoLocalizacionFiltroCont" style="height: 540px"> 
				<div style="float: left; width: 500px" >
					<select name="cboVehiculos" id="cboVehiculos"  style="overflow:hidden" multiple="multiple" style="height:525px; width:480px">
					</select>
				</div>
				<div style="flat: left" >
					<input type="button" name="submit" value="Buscar en Mapa" src="../images/22.png" onclick="draicon.signatron.gmapFilter.sendID()" /> 
					<input type="button" name="reset" value="Actualizar Unidades" src="../images/48.png" onclick="draicon.signatron.gmapFilter.reset()"/> 
				<!-- <input type="button" name="submit" value="Limpiar Mapa" src="../images/49.png" onclick="draicon.signatron.onReady()" />-->
				</div>
				<div class="clear">&nbsp;</div>
			</div>
			<div id="geoLocalizacionResultadoCont" style="display:none; text-align: left; height: 130px">
				<div id="cedulaGrid"  style="height:130px;" class="autos font-acord">
			   		<table id="resultadosID"></table>
					<div id="pagerResultadosID"></div>
			   	</div>
		   	</div>
			<button id="zoomCliente">Posici&oacute;n y zoom del mapa</button>&nbsp;&nbsp;&nbsp;<span id="msgZoomCliente"><span>Autom&aacute;tico (by Google)</span><span style="display: none"><img src="../images/lock-icon16.png" /> Bloqueado por cliente</span></span>
		    <input type="hidden" name="hZoomCliente" id="hZoomCliente" value="0" />
	 </div> 	
</div>
<div id="map_canvas" style="width: 990px; height: 500px"></div> 

 

<input type="hidden" name="vehi" id="vehi"  value="<%=vehiculos%>"/>
<div id="dialogData" title="Faltan Datos...">
Escoja uno o más veh&iacute;culos (identificadores) para realizar una busqueda...
</div>