package com.draicon.signatron.factory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.draicon.signatron.test.XMLTest;

/**
 * Servlet implementation class xmlFactory
 */
public class xmlFactory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public xmlFactory() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		//Date today = (Date) Calendar.getInstance().getTime();
		Calendar fechaNowTemp = Calendar.getInstance();
		fechaNowTemp.set(fechaNowTemp.get(Calendar.YEAR), fechaNowTemp.get(Calendar.MONTH), fechaNowTemp.get(Calendar.DAY_OF_MONTH),0, 0, 0);
		
//		System.out.println("-----------today" + today);
		String fechaNow = formatter.format(fechaNowTemp);
		System.out.println("-----------fechaNow" + fechaNow);
		String fechaIni= "";
		String fechaFin= "";
		
		//Leyendo los valores provenientes del post
		Integer vehiInt   	= new Integer(request.getParameter("vehi"));
	    Integer tipoPerio   = new Integer(request.getParameter("tipoPerio"));
	    Integer combo 		= new Integer(request.getParameter("combo"));
	    String fecha1 		= request.getParameter("fecha1");
	    String fecha2 		= request.getParameter("fecha2");
	    
	    Integer page 		=  new Integer( request.getParameter("page") );
	    
	    //Variables internas de control


	    
		// si es intervalo de fechas
		
	    if (fecha1 != "" && fecha2 != ""){
		   // String day=fecha1.substring(0,fecha1.indexOf("/"));
		   // String month=fecha1.substring(fecha1.indexOf("/")+1,fecha1.lastIndexOf("/"));
		   // String year=fecha1.substring(fecha1.lastIndexOf("/")+1,fecha1.length());
		    
		    fechaIni =	fecha1.substring(fecha1.lastIndexOf("/")+1,fecha1.length()) + 
		    			fecha1.substring(fecha1.indexOf("/")+1,fecha1.lastIndexOf("/"))+ 
		    			fecha1.substring(0,fecha1.indexOf("/"));
		    			
		    fechaFin =	fecha2.substring(fecha2.lastIndexOf("/")+1,fecha2.length()) + 
						fecha2.substring(fecha2.indexOf("/")+1,fecha2.lastIndexOf("/"))+ 
						fecha2.substring(0,fecha2.indexOf("/"));
			
		    
		    /*
			Calendar fecha11 = new GregorianCalendar( year,  month-1, day);
			Timestamp Tfecha1 = new Timestamp(fecha11.getTimeInMillis());	
			
		    int day2=Integer.parseInt(fecha2.substring(0,fecha2.indexOf("/")));
			int month2=Integer.parseInt(fecha2.substring(fecha2.indexOf("/")+1,fecha2.lastIndexOf("/")));
			int year2=Integer.parseInt(fecha2.substring(fecha2.lastIndexOf("/")+1,fecha2.length()));
			
			Calendar fecha22 = new GregorianCalendar( year2,  month2-1, day2);
			Timestamp Tfecha2 = new Timestamp(fecha22.getTimeInMillis());	
			*/
		    //parametros.put("fecha1", Tfecha1);
		   // parametros.put("fecha2", Tfecha2);
	    }
	    
	    //si es combo de dias
	    else if(combo > 0) { 
	    	
	    	Calendar fechaDias; 
			fechaDias = Calendar.getInstance(); 
			fechaDias.set(fechaDias.get(Calendar.YEAR), fechaDias.get(Calendar.MONTH), fechaDias.get(Calendar.DAY_OF_MONTH));
			fechaDias.set(Calendar.MILLISECOND, 0);
			fechaDias.add( Calendar.DATE, ((-combo) + 1));
			
			fechaIni = formatter.format(fechaDias);
			fechaFin = fechaNow;

			/*fechaNow.add(Calendar.DATE, 1);
			fechaNow.add(Calendar.SECOND, -1);
			java.sql.Timestamp TfechaDias = new Timestamp(fechaDias.getTimeInMillis());
			java.sql.Timestamp TfechaNow = new Timestamp(fechaNow.getTimeInMillis());

		    //parametros.put("fecha1", TfechaDias);
		    //parametros.put("fecha2", TfechaNow);
		     * */
		     
			

	    }
	    
	    // si es hoy
	    else if (tipoPerio == 1){
	    	
	    	fechaIni = fechaFin =fechaNow;
	    	
	    	/*
	    	Calendar fechaDiaDespues = fechaNow.getInstance();
			fechaDiaDespues.set(fechaDiaDespues.get(Calendar.YEAR), fechaDiaDespues.get(Calendar.MONTH), fechaDiaDespues.get(Calendar.DAY_OF_MONTH), fechaNow.get(Calendar.HOUR_OF_DAY), fechaNow.get(Calendar.MINUTE),  fechaNow.get(Calendar.SECOND));
			fechaDiaDespues.set(Calendar.MILLISECOND, 0);
			fechaDiaDespues.add(Calendar.DATE, 1);
			fechaDiaDespues.add(Calendar.SECOND, -1);
			Timestamp TfechaNow = new Timestamp(fechaNow.getTimeInMillis());
			Timestamp TfechaDiaDespues = new Timestamp(fechaDiaDespues.getTimeInMillis());

			*/
		    //parametros.put("fecha1", TfechaNow);
		    //parametros.put("fecha2", TfechaDiaDespues);
	    }
	    
	    //  Timestamp fechaIni = (Timestamp) parametros.get("fecha1");
	    //  Timestamp fechaFin = (Timestamp) parametros.get("fecha2");
	    
	    fechaIni += " 00:00:00.000";
	    fechaFin += " 23:59:59.999";
	    
	   String  documento = XMLTest.reporteXML ( vehiInt, fechaIni, fechaFin,page );
	   
	   PrintWriter out = response.getWriter();
	   //out.print(documento);

	 

		
		
	}
	
	protected void doGet
	(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("entre al get");

		String  documento = XMLTest.reporteXML ( 342, "2010/09/08 00:00:00.000", "2010/09/08 23:59:59.999" , 1 );   
		PrintWriter out = response.getWriter();
		out.print(documento);


		
	}

}
