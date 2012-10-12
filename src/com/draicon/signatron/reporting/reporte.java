package com.draicon.signatron.reporting;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import com.draicon.signatron.db.*;
public class reporte extends HttpServlet {
public static Connection conn;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
	
		conn =  Conexion.getConnection();	
	//ServletContext context = getServletContext();
	//String pathReport = context.getRealPath("reports") + "/";
	String pathReport = "C:\\Proyectos\\Signatron\\WebContent\\reports\\";

	// Parametros
    Map parametros = new HashMap();
   
	
	//String sNomRep = req.getParameter("reportePeriodo");
	String sNomRep = "gmapRep.jasper";

	try {
		// Ruta y nombre de archivo
		String fileRep = pathReport + sNomRep;
System.out.println("fileRep.. " + fileRep);
		// se crea el reporte
		JasperPrint imprime = JasperFillManager.fillReport("C:\\Proyectos\\Signatron\\WebContent\\reports\\gmapRep.jasper", parametros, conn);
System.out.println("imprime " + imprime);
		// graba el pdf en disco
		// JasperManager.printReportToPdfFile(imprime, fileRep+".pdf");
		JasperExportManager.exportReportToPdfFile(imprime, fileRep + ".pdf");
System.out.println("export " );
		JasperExportManager.exportReportToPdfStream(imprime, res.getOutputStream());
System.out.println("output stream " );
		
	} catch (Exception e) {
		res.getWriter().println("Error al generar el reporte: " + e);
	}
}
	
	

protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	throws ServletException, IOException {
	doPost(arg0, arg1);
}
	
	
}
