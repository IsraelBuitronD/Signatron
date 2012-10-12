<script src="../js/reporteTabular.js" type="text/javascript"></script>
<script src="../js/filterIndicadores.js" type="text/javascript"></script>
<script src="../js/gmapFilterReport.js" type="text/javascript"></script>
<style>
.ui-jqgrid .ui-jqgrid-btable { cursor : pointer; }
</style>



<div id="botones" class="demo">
	<button id="FL" onclick="draicon.signatron.reporteTab.selectVehi()">Buscar Unidad</button>
	<button id="MR" onclick="draicon.signatron.reporteTab.showGrid()">Mostrar Reporte</button>
	<button id="VM" onclick="draicon.signatron.reporteTab.showMapa()" style="display:none">Ver en Mapa</button> 
</div>		 
<div id="gridRepTab"><br /> 
     Dar click en un rengl&oacute;n de la tabla para ver la ubicaci&oacute;n del veh&iacute;culo en el mapa  
	<table id="reporteTabular"></table>
	<div id="pgreporteTabular"></div>
</div><br />

<div id="radioReportes" class="demo">
	<div id="step-1"><img src="../images/step-1.png"/></div>
	<input type="radio" id="RP" name="radioReportes" onclick="draicon.signatron.reporteTab.showRP()"/><label for="RP">&nbsp;&nbsp; Reporte Tabular &nbsp;&nbsp; </label>
	<input type="radio" id="MD" name="radioReportes" onclick="draicon.signatron.reporteTab.showMD()" /><label for="MD">&nbsp;&nbsp;Mapa de recorrido&nbsp;&nbsp;</label>
	<input type="radio" id="E" name="radioReportes" onclick="draicon.signatron.reporteTab.showE()" /><label for="E">&nbsp;&nbsp;Estad&iacute;stico&nbsp;&nbsp;</label>
	<input type="radio" id="EG" name="radioReportes" onclick="draicon.signatron.reporteTab.showEG()" style="display:none"/><label for="EG" style="display:none">&nbsp;&nbsp;Estad&iacute;stico Geocercas&nbsp;&nbsp;</label>
</div> <br />

<div id="consHist"><strong>REPORTE TABULAR</strong><br /><div id="step-2"><img src="../images/step-2.png"/></div></div>
<div id="mapaRec"><strong>MAPA CON RECORRIDO</strong><br /><div id="step-2"><img src="../images/step-2.png"/></div></div>
<div id="repEst"><strong>REPORTE ESTADISTICO</strong><br /><div id="step-2"><img src="../images/step-2.png"/></div></div>
<div id="repEstGeo"><strong>REPORTE ESTADISTICO POR GEOCERCAS</strong><br /><div id="step-2"><img src="../images/step-2.png"/></div></div>

<div id="cboVehi">
	Seleccione el identificador de la unidad <br />
	<select name="cboVehiculosR" id="cboVehiculosR"  style="font-size:14px;  width:200px"> 
	</select> <br /> <br />
</div>
<div id="radioRangos">
	<div id="step-3"><img src="../images/step-3.png"/></div>
	<input type="radio" id="rHoy" name="radioRangos" onclick="draicon.signatron.reporteTab.showHoy()"/><label for="rHoy">&nbsp;&nbsp; Hoy &nbsp;&nbsp; </label>
	<input type="radio" id="rUltimosD" name="radioRangos" onclick="draicon.signatron.reporteTab.showComboDias()" /><label for="rUltimosD">&nbsp;&nbsp;Ultimos d&iacute;as&nbsp;&nbsp;</label>
	<input type="radio" id="rRangoF" name="radioRangos" onclick="draicon.signatron.reporteTab.showDatepickers()" /><label for="rRangoF">&nbsp;&nbsp;Rango de fechas&nbsp;&nbsp;</label>
</div> <br />
<div id="hoy">
	Busqueda de la actividad de Hoy
</div>
<div id="ComboDias">
	Ultimos 
	<select name="cboDias" id="cboDias">
		<option value=""></option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option>
		<option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option>
		<option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option>
		<option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option>
		<option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option>
		<option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option>
		<option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option>
		<option value="29">29</option><option value="30">30</option>
	</select>
	d&iacute;as
</div>
<div id="datepickers">
	Fecha de Inicio:<br />
	<input type="text" name="startdate" id="startdate"  /><br />
	Fecha Final:<br />
	<input type="text" name="enddate" id="enddate"  /><br />
</div><br/>

<input type="button" name="submit" value="Buscar"  id="bBusquedaR" onclick="draicon.signatron.reporteTab.bBusquedaR()"/>

<div id="mapa">
	<div id="map_reporte" style="width: 990px; height: 500px"></div>
</div> 

<div id="rEstadistico">
	<%@ include file="reporteEstadistico.jsp" %>
</div>
<div id="rEstadisticoGC">
	<%@ include file="reporteGeocerca.jsp" %>
</div>