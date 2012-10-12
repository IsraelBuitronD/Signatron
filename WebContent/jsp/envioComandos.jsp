<script src="../js/envioComandos.js" type="text/javascript"></script>
<fieldset>
	<div id="step-1-comm"><img src="../images/step-1-comm.png"/></div>
	<div>
		<select name="cboVehiculosComandos" id="cboVehiculosComandos" style="font-size:16px; width:280px">
		</select>
	</div> 
</fieldset>
<br><br>
<fieldset>
	<div id="step-2-comm"><img src="../images/step-2-comm.png"/></div>
	<div>
		<select name="cboEnvCom" id="cboEnvCom"  style="font-size:16px;  width:250px" onchange="draicon.signatron.envioComados.cboEnvCom()" />
	<!-- 		<option value=""></option>
			<option value="1">Pedido de Posición</option>
			<option value="2">Seguimiento</option> 
			<option value="3">Apagado de Motor</option>
			<option value="4">Encendido de Motor</option>
			<option value="5">Geocerca</option>
			<option value="6">Marcar al tel&eacute;fono seleccionado</option>
			<option value="7">Cambiar a escucha</option>
			<option value="8">Programar n&uacute;mero tel&eacute;fonico</option>
			<option value="9">Enviar Mensaje</option>
			<option value="10">Liberar seguro de puerta trasera</option>
			<option value="11">Cerrar seguro de puerta trasera</option>
			<option value="12">Apagar alarma central</option>  -->
		</select>
	</div>
</fieldset>
<div>&nbsp;</div> 
<div id="numTel">N&uacute;mero tel&eacute;fonico: 
	&nbsp;<br/><input name="envComTel" type="text" id="envComTel" size="44" maxlength="10" />
</div>
<div>&nbsp;</div> 
<div id="msgText">Mensaje de texto: 
	&nbsp;<br/><textarea name="envComMens"	id="envComMens" cols="45" rows="3"></textarea>
</div> 
<div>
	&nbsp;<input type="button" name="submit" value="Enviar Comando" id="bEnviaComando" onclick="draicon.signatron.envioComados.enviaComando()" />
</div>
<div id="dialogLoading" title="Env&iacute;o de comando">
	Espere mientras se env&iacute;a la informaci&oacute;n...
</div>
<div id="dialogComandosFiltro" title="Env&iacute;o de comando">
	<span style="font-weight: bold;">Faltan datos...</span>
	<span id="msgdialogComandosFiltro"></span>
</div>
<div>&nbsp;</div> 
<div id="seccionHistoricoComandos"  class="autos font-acord float-left">
	<table id="historicoComandos"></table>
	<div id="pagerHistoricoComandos"></div>
</div>