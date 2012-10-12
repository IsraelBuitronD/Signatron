package com.draicon.signatron.reporting;

import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.draicon.signatron.db.Conexion;


/**
 * Servlet implementation class reportePeriodoExcel
 */
public class reportePeriodoExcel extends HttpServlet {
	public static Connection conn;
	public static boolean flag;
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		
		try {
		conn =  Conexion.getConnection();	
	//ServletContext context = getServletContext();
	//String pathReport = context.getRealPath("reports") + "/";
	

		// Parametros
	    Map parametros = new HashMap();
//	    Integer vehiInt   = new Integer(req.getParameter("vehi"));
//	    Integer tipoPerio   = new Integer(req.getParameter("tipoPerio"));
//	    Integer combo = new Integer(req.getParameter("combo"));
//	    String fecha1 = req.getParameter("fecha1");
//	    String fecha2 = req.getParameter("fecha2");
//	    System.out.println("fecha1 "+fecha1);
//	    System.out.println("fecha2 "+fecha2);
	    
	 

	
	//String sNomRep = req.getParameter("reportePeriodo");
	String sNomRep = "test.jasper";

	// Se obtiene la conexión que se empleará para poblar el reporte
	conn =  Conexion.getConnection();

	   // Se obtiene la ruta fisica de la plantilla del reporte
		String pathReport = "C:\\Proyectos\\Signatron\\WebContent\\reports\\test.jasper";
	   //String strRutaPlantilla = “C:/”;
	   //String reportFile = strRutaPlantilla + "ReporteEjemplo.jrxml";

	   // Cargamos la plantilla
	   JasperDesign objJasperDesign = JRXmlLoader.load(pathReport);
	   
	  
	   // Compilamos la plantilla
	   JasperReport objJasperReport = JasperCompileManager.compileReport(objJasperDesign);
	   // Poblamos la plantilla con los datos de la BD y parametros
	   JasperPrint objJasperPrint = JasperFillManager.fillReport(objJasperReport, parametros, conn);
	   // Instanciamos la clase  exportadora
	   JExcelApiExporter xlsExporter = new JExcelApiExporter();
	   JRXlsExporter exporterXLS = new JRXlsExporter();

	   xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, objJasperPrint);
	   xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
	   xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"C:\\Proyectos\\Signatron\\WebContent\\reports\\test.xls");

	   /* Antes de exportar verificamos que el archivo no exista
	    * Crea un objeto File a fin de apuntar a la ruta que vamos a
	    * almacenar */
	   File objFile = new File("C:\\Proyectos\\Signatron\\WebContent\\reports\\test.xls");  

	   // Si el archivo existe...
	   if (objFile.exists()) {
	      // ...elimina el archivo y la variable apunta a 'null'
	      objFile.delete();
	      objFile = null;
	   }

	   // ...exporta!!!!
	   xlsExporter.exportReport();

	   // la variable flag se pasa a true para indicar que funcionó
	    flag = true;
	} catch (Exception e) {
	   flag = false; 
	   e.printStackTrace();
	   try {
		throw new Exception(e);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	} finally {
	   //pase lo que pase cierra la conexión
	   if (conn != null) {
		   
		   Conexion.getConexion().fin();
	   }
	}
	
}


protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	throws ServletException, IOException {
	doPost(arg0, arg1);
}
	
	
}
