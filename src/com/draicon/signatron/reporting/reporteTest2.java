package com.draicon.signatron.reporting;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Servlet implementation class reporteTest2
 */
public class reporteTest2 extends HttpServlet {
	public static Connection con;
	public static String base = "db_signatron"; // Nombre de la base de datos
	public static String usuario = "postgres"; // Usuario
	public static String pass = "postgres"; // contraseña
	
	private String user = "postgres";
	private String pwd  = "postgres";
	private String url  = "jdbc:postgresql://localhost:5432/db_signatron";

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		
		System.out.println("entro");
		conexion(); 
		
		// la variable pathJasper da la direccion de los archivos compilados de jasper
		//los cuales se encuentran en 
		
		//String pathJasper = getServletContext().getRealPath("\\reportes\\");
		String pathJasper = "C:\\Proyectos\\Signatron\\JavaSource\\com\\draicon\\signatron\\reporting\\";
		
		
		System.out.println("pathJasper " + pathJasper);
		
		// la variable path arma el camino del contexto real
		// ahi se guardan los pdf
		String path = "C:\\Proyectos\\Signatron\\JavaSource\\com\\draicon\\signatron\\reporting\\";
		
		System.out.println("path " + path);
		// Parametros 
		Map parametros = new HashMap();
	
		// parametros a pasar al reporte
		parametros.put("reporte", pathJasper + "/reportx.jasper");
		System.out.println("aqui 1");
		try {
			System.out.println("entra try");
			// se crea el reporte
			JasperPrint imprime = JasperFillManager.fillReport(pathJasper+ "reportx.jasper", parametros, con);
			
			System.out.println("aqui 2");
			// graba el pdf en disco
			String pathRep = "C:\\Proyectos\\Signatron\\JavaSource\\com\\draicon\\signatron\\reporting\\reportx.pdf";
			JasperManager.printReportToPdfFile(imprime, path + "/reportx.pdf");
			//JasperManager.printReportToPdfFile(imprime, pathRep);
			System.out.println("path..." + path);
			System.out.println("aqui 3");
			
			// Redireciona al pdf creado
			res.sendRedirect("C:\\Proyectos\\Signatron\\JavaSource\\com\\draicon\\signatron\\reporting\\reportx.pdf");
			System.out.println("aqui 4");
		} catch (Exception e) {
			res.getWriter().println("Error al generar el reporte: " + e);
		}
	}
	
	
	// Crea la conexion
	public void conexion() {
		try {
			if (con == null || con.isClosed()) {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,  usuario, pass);
		}
		} catch (Exception e) {
			System.out.println("No se puede establecer la conexion con la base de datos ->");
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
		throws ServletException, IOException {
		doPost(arg0, arg1);
	}
}
