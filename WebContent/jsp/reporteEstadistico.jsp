<script src="../js/reporteEstadistico.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="../css/rep.estadistico.css" />
<%
	String totalR = request.getParameter("totalR");
	if(totalR==null) totalR = "";
%>
<div id="wrapper">
  <div id="headVehi">
   	  <div class="titData float-left  margin-top20"> &nbsp;&nbsp;Veh&iacute;culo: &nbsp;&nbsp;</div>
      <div id="idVeh" class="titData big result float-left " style="width: 700px">  </div>
       <div  class="titData float-left small " style="text-align:right"> Reporte Estad&iacute;stico </div>
  </div>
  <div id="center">
  	<div id="data">
  	  <div class="titData text-center">Per&iacute;odo</div>
      <div class="titData small">Inicial</div>
      <div class="titData remark text-center" id="startd"></div>
      <div class="titData small">Final</div>
      <div class="titData remark text-center" id="endd"></div>
  	</div>
    <div id="data">
   	  <div class="titData text-center">Distancia recorrida</div>
        <div class="Dato result"   id="kilometraje_recorrido"></div>
        <div id="titDataSub">Kil&oacute;metros
        </div>
    </div>
  

    <div id="dataT" >
    	<div class="titData" align="left">Tiempo Encendido</div>
      <div class="titData remark text-left font-medio"  id="tiempo_encendido"></div>
      <div class="titData">Tiempo Apagado</div>
      <div class="titData remark text-left font-medio"  id="tiempo_apagado"></div>
      <div class="titData">Tiempo Total</div>
      <div class="titData remark text-left font-medio"  id="tiempo_total"></div>
      	
      </div>

  </div>
</div>