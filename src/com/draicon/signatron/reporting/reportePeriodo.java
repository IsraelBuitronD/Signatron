package com.draicon.signatron.reporting;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.draicon.signatron.db.*;

public class reportePeriodo extends HttpServlet {
	public static Connection conn;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
	
		conn =  Conexion.getConnection();	
	//ServletContext context = getServletContext();
	//String pathReport = context.getRealPath("reports") + "/";
	//String pathReport = "C:\\Proyectos\\Signatron\\WebContent\\reports\\";
	String pathReport = "/var/lib/tomcat6/webapps/signatron/reports/";
	

	// Parametros
    Map parametros = new HashMap();
    Integer vehiInt   = new Integer(req.getParameter("vehi"));
    Integer tipoPerio   = new Integer(req.getParameter("tipoPerio"));
    Integer combo = new Integer(req.getParameter("combo"));
    String fecha1 = req.getParameter("fecha1");
    String fecha2 = req.getParameter("fecha2");
//    System.out.println("fecha1 "+fecha1);
//    System.out.println("fecha2 "+fecha2);
    
    
	Calendar fechaNow = Calendar.getInstance();
	fechaNow.set(fechaNow.get(Calendar.YEAR), fechaNow.get(Calendar.MONTH), fechaNow.get(Calendar.DAY_OF_MONTH),0, 0, 0);
	fechaNow.set(Calendar.MILLISECOND, 0);
	
    
    if (fecha1 != "" && fecha2 != ""){// si es intervalo de fechas
	    int day=Integer.parseInt(fecha1.substring(0,fecha1.indexOf("/")));
		int month=Integer.parseInt(fecha1.substring(fecha1.indexOf("/")+1,fecha1.lastIndexOf("/")));
		int year=Integer.parseInt(fecha1.substring(fecha1.lastIndexOf("/")+1,fecha1.length()));
		Calendar fecha11 = new GregorianCalendar( year,  month-1, day);
		Timestamp Tfecha1 = new Timestamp(fecha11.getTimeInMillis());	
		
	    int day2=Integer.parseInt(fecha2.substring(0,fecha2.indexOf("/")));
		int month2=Integer.parseInt(fecha2.substring(fecha2.indexOf("/")+1,fecha2.lastIndexOf("/")));
		int year2=Integer.parseInt(fecha2.substring(fecha2.lastIndexOf("/")+1,fecha2.length()));
		Calendar fecha22 = new GregorianCalendar( year2,  month2-1, day2);
		Timestamp Tfecha2 = new Timestamp(fecha22.getTimeInMillis());	
		//    System.out.println("fecha date1---"+ Tfecha1);
		//    System.out.println("fecha date2---"+ Tfecha2);
	    parametros.put("fecha1", Tfecha1);
	    parametros.put("fecha2", Tfecha2);
    }else if(combo > 0) { //si es combo de dias
    	Calendar fechaDias; 
		fechaDias = fechaNow.getInstance(); 
		fechaDias.set(fechaDias.get(Calendar.YEAR), fechaDias.get(Calendar.MONTH), fechaDias.get(Calendar.DAY_OF_MONTH), fechaNow.get(Calendar.HOUR_OF_DAY), fechaNow.get(Calendar.MINUTE),  fechaNow.get(Calendar.SECOND));
		fechaDias.set(Calendar.MILLISECOND, 0);
		fechaDias.add( Calendar.DATE, ((-combo) + 1));
//		fechaDias.set(fechaDias.get(Calendar.YEAR), fechaDias
//			.get(Calendar.MONTH), fechaDias.get(Calendar.DAY_OF_MONTH),0, 0, 0 );
//		fechaDias.set(Calendar.MILLISECOND, 0);
//		fechaNow.set(fechaNow.get(Calendar.YEAR), fechaNow.get(Calendar.MONTH), fechaNow.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//		fechaNow.set(Calendar.MILLISECOND, 0);
		fechaNow.add(Calendar.DATE, 1);
		fechaNow.add(Calendar.SECOND, -1);
		java.sql.Timestamp TfechaDias = new Timestamp(fechaDias.getTimeInMillis());
		java.sql.Timestamp TfechaNow = new Timestamp(fechaNow.getTimeInMillis());
		System.out.println("TfechaDias.... " + TfechaDias);
		System.out.println("TfechaNow.... " + TfechaNow);
	    parametros.put("fecha1", TfechaDias);
	    parametros.put("fecha2", TfechaNow);
    }else if (tipoPerio == 1){ // si es hoy
    	Calendar fechaDiaDespues = fechaNow.getInstance();
		fechaDiaDespues.set(fechaDiaDespues.get(Calendar.YEAR), fechaDiaDespues.get(Calendar.MONTH), fechaDiaDespues.get(Calendar.DAY_OF_MONTH), fechaNow.get(Calendar.HOUR_OF_DAY), fechaNow.get(Calendar.MINUTE),  fechaNow.get(Calendar.SECOND));
		fechaDiaDespues.set(Calendar.MILLISECOND, 0);
		fechaDiaDespues.add(Calendar.DATE, 1);
		fechaDiaDespues.add(Calendar.SECOND, -1);
		Timestamp TfechaNow = new Timestamp(fechaNow.getTimeInMillis());
			Timestamp TfechaDiaDespues = new Timestamp(fechaDiaDespues.getTimeInMillis());
//			System.out.println(">>>>>>>>>> HOY");
		System.out.println("TfechaNow.... " + TfechaNow);
		System.out.println("TfechaDiaDespues.... " + TfechaDiaDespues);
	    parametros.put("fecha1", TfechaNow);
	    parametros.put("fecha2", TfechaDiaDespues);
    }
    
    
    parametros.put("vehi", vehiInt);

	
	//String sNomRep = req.getParameter("reportePeriodo");
	String sNomRep = "reportePeriodo.jasper";

	try {
		// Ruta y nombre de archivo
		String fileRep = pathReport + sNomRep;
System.out.println("fileRep.. " + fileRep);
		// se crea el reporte
		//JasperPrint imprime = JasperFillManager.fillReport("C:\\Proyectos\\Signatron\\WebContent\\reports\\reportePeriodo.jasper", parametros, conn);
		
		JasperPrint imprime = JasperFillManager.fillReport("/var/lib/tomcat6/webapps/signatron/reports/reportePeriodo.jasper", parametros, conn);
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
