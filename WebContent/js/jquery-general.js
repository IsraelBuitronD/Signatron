$(function(){
	// Accordion
	$("#accordion").accordion({ header: "h3", collapsible: true, active:1});
	$("#envioComandos").accordion({ header: "h3", collapsible: true, active:12, clearStyle: true});
	$("#accordion3").accordion({ header: "h3", collapsible: true, active:3, clearStyle: true, autoHeight: false});
	
	// Tabs
	$('#tabs').tabs({
		ajaxOptions: { cache: false },
		cache: false
		});
	$('#tabs-GL').tabs({
		ajaxOptions: { cache: false },
		cache: false
		});
	
	$('#tabs2').tabs();

	// Dialog			
	$('#dialog').dialog({
		autoOpen: false,
		width: 600,
		modal: true,
		buttons: {
			"Ok": function() { 
				$(this).dialog("close"); 
			}, 
			"Cancel": function() { 
				$(this).dialog("close"); 
			} 
		}
	});
	
 	// Dialog Cargando Sin Botones		
	$('#dialogLoading').dialog({
		closeOnEscape: false,
	    autoOpen: false,
	    width: 300,
	    height: 50,
	    modal: true,
	    resizable: false
	  });			
	$('#dialogData').dialog({
	    autoOpen: false,
	    width: 300,
	    height: 50,
	    modal: true
	  });
	$('#dialogComandosFiltro').dialog({
		closeOnEscape: false,
		open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
	    autoOpen: false,
	    width: 300,
	    height: 110,
	    modal: true,
	    resizable: false,
	    buttons: { "Cerrar": function() { $(this).dialog("close"); }} 
	  });
	$('#dialogAlertas').dialog({
		closeOnEscape: true,
	    autoOpen: false,
	    width: 260,
	    height: 80,
	    modal: true,
	    resizable: false
	  });
	$('#dialogAtendiendoAlerta').dialog({
		closeOnEscape: false,
		open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
	    autoOpen: false,
	    width: 300,
	    height: 60,
	    modal: true,
	    resizable: false
	  });
		
	// Dialog Link
	$('#dialog_link').click(function(){
		$('#dialog').dialog('open');
		 
		return false;
	});
	$('#dialogMensajeEspera').dialog({
		closeOnEscape: false,
	    autoOpen: false,
	    width: 300,
	    height: 50,
	    modal: true,
	    resizable: false
	  });		
//	$('#map_filter_mensaje').dialog({
//		closeOnEscape: false,
//	    autoOpen: true,
//	    width: 380,
//	    height: 130,
//	    modal: true,
//	    resizable: false
//	  }); 


	// Datepicker
	//$('#datepicker').datepicker({
		//inline: true
	//});
	$('#datepicker').datepicker({
		showButtonPanel: true,
		changeMonth: true,
		changeYear: true
	});

	$('#startdate').datepicker({
		showButtonPanel: true,
		changeMonth: true,
		changeYear: true,
		dateFormat: 'dd/mm/yy',
		onSelect: function(dateText, inst) {
			var aFechas=dateText.split("/");
			$('#enddate').datepicker("option", "minDate", new Date(aFechas[2], aFechas[1] - 1, aFechas[0]));
		}
	});

	$('#enddate').datepicker({
		showButtonPanel: true,
		changeMonth: true,
		changeYear: true,
		dateFormat: 'dd/mm/yy',
		onSelect: function(dateText, inst) {
			var aFechas=dateText.split("/");
			$('#startdate').datepicker("option", "maxDate", new Date(aFechas[2], aFechas[1] - 1, aFechas[0]));
		}
	});
	
	//Radio botones
	$("#radioRangos").buttonset();
	$("#radioReportes").buttonset();
	$("#radioFiltro").buttonset();
	$("#geoLocalizacion").buttonset();

	//boton
	$("button, input:submit, a", ".demo").button();

//	$(function() {
//		$("#btnMostrarReporte")
//		.button().click( function() {
//				alert( "Mostrar Reporte" );
//			})
//	 });
//	$(function() {
//		$("#btnVerMapa")
//		.button()
//		.click( function() {
//				alert( "Ver en Mapa" );
//			})
//	 });
	
	
	//Dialog
	
	// Slider
	$('#slider').slider({
		range: true,
		values: [17, 67]
	});
	
	// Progressbar
	$("#progressbar").progressbar({
		value: 20 
	});
	
	//hover states on the static widgets
	$('#dialog_link, ul#icons li').hover(
		function() { $(this).addClass('ui-state-hover'); }, 
		function() { $(this).removeClass('ui-state-hover'); }
	);
});