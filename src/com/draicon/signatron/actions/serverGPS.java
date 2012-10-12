package com.draicon.signatron.actions;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.draicon.signatron.db.Conexion;
/**
 * Servlet implementation class Login
 */
public class serverGPS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serverGPS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serie = request.getParameter("serie");
		String descripcion = request.getParameter("descripcion");
		String imei = request.getParameter("imei");
		String id_gps = request.getParameter("id_gps");
	
		
		String oper = request.getParameter("oper");
		String query = "";
		int Exito = 0;
		
		System.out.println("oper..........." +oper);
		
		if (oper.equals("edit")){
			System.out.println("entro if");
			int id = Integer.parseInt(request.getParameter("id")); 
			query = "UPDATE gps SET ";
			query+=         "imei = '" + imei        + "', ";
			query+=         "serie = '" + serie        + "', ";
			query+=         "descripcion = '" + descripcion        + "' ";
			query+= "WHERE id_gps = '" + id_gps + "' ";
		}
		else if (oper.equals("add")){
			query = "INSERT INTO gps ";
			query+= "(imei, serie, descripcion) VALUES ";
			query+= "('" + imei + "', '" + serie + "', '" + descripcion + "') ";
	 
		}
		else if (oper.equals("del")){ 
			int id = Integer.parseInt(request.getParameter("id")); 

			System.out.println("<<<" + id);
			
			query = "DELETE FROM gps ";
			query+= "WHERE id_gps = '" + id_gps + "' ";
		}
		//ResultSet rs = Conexion.getConexion().consultaSql(query);
		Exito = Conexion.getConexion().ejecutaSql(query);
		System.out.println(query);
		
			if(Exito == 1){
				System.out.println("exito........"+ Exito);
			}
			else{
				System.out.println("fail........"+ Exito);
			}
		
    
	}
}