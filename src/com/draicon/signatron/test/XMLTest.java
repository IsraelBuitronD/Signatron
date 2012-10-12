package com.draicon.signatron.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.draicon.signatron.db.Conexion;

public class XMLTest {

	private final static Integer  TOTALRECORDS =20;
	
	public static void main(String[] args) {
		

		System.out.println(reporteXML (339, "2010/09/08 00:00:00.000", "2010/09/08 23:59:59.999", 2 ));

		
	}
	
	
	public static String reporteXML (int vehiculo,String fechaIni,String fechaFin, int page){

		page =  page > 0 ? page :1;
	

		//Query para pedir las posciones del vehiculo en el raqngo de fechas dadas
				String query = "SELECT  v.nombre as nom_vehi, p.latitud, p.longitud, p.calidad,  m.motivo, " +
							" p.sensor1, p.sensor2, p.sensor3,p.sensor4, p.sensor5, p.sensor6, " +
							" p.orientacion, p.velocidad, p.fecha FROM posiciones p" +
							" INNER JOIN cat_motivo m ON p.id_motivo = m.id_motivo" +
							" INNER JOIN vehiculos v ON p.id_vehiculo = v.id_vehiculo" +
							" where p.id_vehiculo = " + Integer.toString(vehiculo) +
							" AND p.fecha > '"+ fechaIni +"'" +
							" AND p.fecha < '"+ fechaFin +"'" +
							" ORDER BY p.fecha DESC" +
							
							" limit " + TOTALRECORDS.toString() +
							" offset " + Integer.toString((page-1)*TOTALRECORDS ) + ";";
		
		//System.out.println(query);
		ResultSet rs = Conexion.getConexion().consultaSql(query);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy, hh:mm:ss aaa");
		
		Element raiz = new Element("root");
		Document document = new Document(raiz);

		Element request = new Element("request");
		request.setText("true");
		raiz.addContent(request);
		
		Element currentpage = new Element("currentpage");
		currentpage.setText(Integer.toString(page));
		raiz.addContent(currentpage);
		
		/*
		Element totalpages = new Element("totalpages");
		totalpages.setText("10");
		raiz.addContent(totalpages);
		*/
		
		Element totalrecords = new Element("totalrecords");
		raiz.addContent(totalrecords);

		Element posiciones = new Element("posiciones");
		raiz.addContent(posiciones);
		
		
		//Attribute genre = new Attribute("nombre",nombreVehiculo);
		//raiz.setAttribute(genre);
		
		int i= 0;
		try {
			while(rs.next()){
				
					Element pos = new Element("pos");
					posiciones.addContent(pos);
					
					//id
					Element id = new Element("id");
					id.setText( Integer.toString( ((page-1)*TOTALRECORDS)+ ++i )  );
					pos.addContent(id);
					
					//id
					Element veh = new Element("veh");
					veh.setText( rs.getString("nom_vehi")   );
					pos.addContent(veh);
					
					//latitud
					Element lat = new Element("lat");
					lat.setText( Double.toString(rs.getDouble("latitud"))  );
					pos.addContent(  lat );
					
					//longitud
					Element log = new Element("log");
					log.setText( Double.toString(rs.getDouble("longitud"))  );
					pos.addContent(  log );
					
					
					//calidad
					Element calidad = new Element("cal");
					calidad.setText( calidadtoString ( rs.getString("calidad") )  );
					pos.addContent(  calidad );
					
					//id_motivo
					Element id_motivo = new Element("mot");
					id_motivo.setText( rs.getString("motivo")  );
					pos.addContent(  id_motivo );
					
					//Sensores 1...6
					Element sensor1 = new Element("s1");
					sensor1.setText( Integer.toString(rs.getInt("sensor1"))  );
					pos.addContent(  sensor1 );
					
					Element sensor2 = new Element("s2");
					sensor2.setText( Integer.toString(rs.getInt("sensor2"))  );
					pos.addContent(  sensor2 );

					Element sensor3 = new Element("s3");
					sensor3.setText( Integer.toString(rs.getInt("sensor3"))  );
					pos.addContent(  sensor3 );
					
					Element sensor4 = new Element("s4");
					sensor4.setText( Integer.toString(rs.getInt("sensor4"))  );
					pos.addContent(  sensor4 );
					
					Element sensor5 = new Element("s5");
					sensor5.setText( Integer.toString(rs.getInt("sensor5"))  );
					pos.addContent(  sensor5 );
					
					Element sensor6 = new Element("s6");
					sensor6.setText( Integer.toString(rs.getInt("sensor6"))  );
					pos.addContent(  sensor6 );
					
					//Orientacion
					Element orientacion = new Element("ori");
					orientacion.setText(  orientaciontoString(rs.getInt("orientacion"))  );
					pos.addContent(  orientacion );
					
					//velocidad
					Element velocidad = new Element("vel");
					velocidad.setText( Double.toString(rs.getDouble("velocidad")) +" km/h" );
					pos.addContent(  velocidad );
					
					//fecha
					Element fecha = new Element("fec");
					fecha.setText( sdf.format(rs.getTimestamp("fecha"))  );
					pos.addContent(  fecha );

			}
			rs.close();
			
			totalrecords.setText(Integer.toString(i));
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
		// Mostrando la salida XML
		try {
			// We use classic output format with getPrettyFormat()
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.output(document, System.out);
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		
		// Salvando el xml a un archivo "dvd.xml" 
		try {
		// We use classic output format with getPrettyFormat()
		XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
		xmlOutputter.output(document, new FileOutputStream("posiciones.xml"));
		}
		catch (java.io.IOException e) {
		e.printStackTrace();
		}
		*/
		
		//Format.getCompactFormat()
		//Format.getPrettyFormat()
		
		//XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat()); 
		XMLOutputter xmlOutputter = new XMLOutputter(Format.getCompactFormat());
		return xmlOutputter.outputString(document);
	}
	
	
	private static String orientaciontoString(int orientacion){
		String orientacionStr= "";
		
        if    	(orientacion >= 0 && orientacion < 22.5) {orientacionStr = "Norte";}
        else if (orientacion >= 337.5 && orientacion <= 360) {orientacionStr = "Norte";}
        else if (orientacion >= 22.5 && orientacion < 67.5) { orientacionStr = "Noreste";}
        else if (orientacion >= 67.5 && orientacion < 112.5) {orientacionStr = "Este";}
        else if (orientacion >= 112.5 && orientacion < 157.5) {orientacionStr = "Sureste";}
        else if (orientacion >= 157.5 && orientacion < 202.5) {orientacionStr = "Sur";}
        else if (orientacion >= 202.5 && orientacion < 247.5) {orientacionStr = "Suroeste";}
        else if (orientacion >= 247.5 && orientacion < 292.5) {orientacionStr = "Oeste";}
        else if (orientacion >= 292.5 && orientacion < 337.5) {orientacionStr = "Noroeste";}
        else 
        	{orientacionStr="";}
		
		
		return orientacionStr;
	}
	
	private static String calidadtoString (String calidad){
		String calidadStr = null;
		
		 if (calidad.equals("11"))
		 	{calidadStr="Excelente";} 
         else if (calidad.equals("10"))
         	{calidadStr="Buena";} 
         else if (calidad.equals("01") || calidad.equals("00"))
         	{calidadStr="Mala";}
         else
        	 calidadStr = "";
		
		return calidadStr;
	}
	
	
	
}
