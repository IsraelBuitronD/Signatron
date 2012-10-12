<div id="dialogMensajeEspera" title="Signatron">
	<span style="font-weight: bold;">Espere por favor...</span>
</div>
 <script type="text/javascript" src="../js/header.js"></script>
    Tiempo restante:
	  <form name="counter" id="counter">
		<input class="box" type="text" size="2" name="d2">
	  </form> 
<div id="tabs" class="clear">
	<ul>
		<li><a href="#tabs-GL">GeoLocalizaci&oacute;n</a></li>
		<li><a href="#tabs-R">Consulta Hist&oacute;rica</a></li> 
		<li style="display:none"><a href="#tabs-G">Geocercas</a></li>
		<li><a href="#tabs-EC">Env&iacute;o de Comandos</a></li>
		<li><a href="#tabs-AL">Alarmas</a></li>
		<li><a href="#tabs-A">Ayuda</a></li>
	</ul>
	<div id="tabs-GL"><div class="height-tab"><div  class="float-left izq"><%@ include file="gmapFilter.jsp" %></div></div></div>
	<div id="tabs-R"><div class="height-tab"><div  class="float-left izq"><%@ include file="reporteTabular.jsp" %></div></div></div>
	<div id="tabs-G"><div class="height-tab"><div  class="float-left izq"></div></div></div>
	<div id="tabs-EC"><div class="height-tab"><div  class="float-left izq"><%@ include file="envioComandos.jsp" %></div></div></div>
	<div id="tabs-AL"><div class="height-tab"><div  class="float-left izq"><%@ include file="alarma.jsp" %></div></div></div>
	<div id="tabs-A"><div class="height-tab"><div  class="float-left izq"><%@ include file="ayuda.jsp" %></div></div></div>
</div>