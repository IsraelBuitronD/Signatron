<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="JSONRPCBridge" scope="session" class="org.jabsorb.JSONRPCBridge"/>

<%@page import="com.draicon.signatron.rpc.secure.ClienteDAO"%>
<%//secure
	JSONRPCBridge.registerObject("ClienteDAO",new ClienteDAO()); 
	 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>tabla Clientes</title>
<link rel="stylesheet" type="text/css" media="screen" href="../themes/blitzer-old/jquery-ui-1.7.2.custom.css" />

<link rel="stylesheet" type="text/css" media="screen" href="../css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/ui.multiselect.css" />


<script src="../lib/jabsorb/jsonrpc.js" type="text/javascript" ></script>
<script src="../lib/jquery/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../lib/jquery/jquery-ui-1.8rc3.custom.min.js" type="text/javascript" ></script>
<script src="../lib/jquery/grid.locale-sp.js" type="text/javascript"></script>
<script src="../lib/jquery/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="../lib/ui/ui.multiselect.js" type="text/javascript"></script> 

<script type="text/javascript">
draicon={};
draicon.signatron={};
draicon.signatron.dataCalls={};
signatron = {
	init: function () {
		jQuery("#listClientes").jqGrid({
			datatype: "local",
			height: 250,
			colNames:['ID','Nombre', 'Ap. Paterno', 'Ap.Materno' ],
		   	colModel :[ 
		   	        {name:'id_cliente', index:'id_posicion', width:30, align:'right'}, 
		   	        {name:'nombre', index:'nombre', width:90, align:'right'}, 
		   	        {name:'ap_paterno', index:'ap_paterno', width:80, align:'right'},
		   	        {name:'ap_materno', index:'ap_materno', width:60, align:'right'}
		   	       
		   		    
		   	  
		   	     	
		   	        
		   	      ],
		   	treeGrid: true,
		    ExpandColumn: "menu",
		    autowidth: true,
		   	multiselect: false,
		    rowNum:200,
		    ExpandColClick: true,
		    treeIcons: {leaf:'ui-icon-document-b'},
		    viewrecords: true,
		    width: 1000,
		    height: "100%",
		    rowList:[10,20,30],
		   	caption: "Clientes",
		   	subGrid: true,


		   	subGridRowExpanded: function(subgrid_id, row_id) {
				// we pass two parameters
				// subgrid_id is a id of the div tag created whitin a table data
				// the id of this elemenet is a combination of the "sg_" + id of the row
				// the row_id is the id of the row
				// If we wan to pass additinal parameters to the url we can use
				// a method getRowData(row_id) - which returns associative array in type name-value
				// here we can easy construct the flowing
				var subgrid_table_id, pager_id;
				subgrid_table_id = subgrid_id+"_t";
				pager_id = "p_"+subgrid_table_id;
				$("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
				jQuery("#"+subgrid_table_id).jqGrid({
					url:"subgrid.jsp?q=2&id="+row_id,
					datatype: "xml",
					colNames: ['Calle','Num int','Num ext','CP','Ciudad','Mail'],
					colModel: [
						   {name:'calle', index:'calle',width:50, align:'right'},
						   {name:'num_int', index:'num_int',width:50, align:'right'},
						   {name:'num_ext', index:'num_ext',width:50, align:'right'},
						   {name:'cp', index:'cp',width:50, align:'right'},
						   {name:'ciudad', index:'ciudad',width:50, align:'right'},
						   {name:'mail', index:'mail',width:50, align:'right'}
					],
				   	rowNum:20,
				   	pager: pager_id,
				   	sortname: 'num', 
				    sortorder: "asc",
				    height: '100%'
				});
				jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:false})
		    },

		    subGridRowColapsed: function(subgrid_id, row_id) {
				// this function is called before removing the data
				//var subgrid_table_id;
				//subgrid_table_id = subgrid_id+"_t";
				//jQuery("#"+subgrid_table_id).remove();
			}
		   	
		});
		signatron.jsonrpc = new JSONRpcClient(signatron.jsonRPCCallBack,"/signatron/JSON-RPC");
	},
	jsonRPCCallBack: function(result, error) {
		if(error) {
			alert(error);
		} else {
			signatron.jsonrpc.ClienteDAO.getGridData(signatron.getGridCB, "draicon");
		}
	},
	getGridCB: function(result, error) {
		
		if(error) {
			alert(error+" e2" );
		} else {
			var data = new Array();
			for(var i=0;i<result.list.length;i++){
				var datos = result.list[i];
				data[i] = {id_cliente: datos.idCliente, nombre: datos.nombre, ap_paterno: datos.apPaterno, ap_materno: datos.apMaterno,  rfc: datos.rfc,
						razon_soc: datos.razonSoc, id_status: datos.idStatus, calle: datos.calle, num_int: datos.numInt, num_ext: datos.numExt, cp: datos.cp,
						ciudad: datos.ciudad, mail: datos.mail, tel1: datos.tel1, ext1: datos.ext1, tel2: datos.tel2, ext2: datos.ext2};
			};
			for(var i=0;i<=data.length;i++) {
				jQuery("#listClientes").jqGrid('addRowData',i+1,data[i]); 
			}
		}
	}
}
jQuery(document).ready(function(){
	signatron.init();
});
</script>

</head>
<body>
<table id="listClientes"></table>
</body>
</html>