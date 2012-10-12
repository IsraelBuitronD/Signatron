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
public class serverAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serverAdministrador() {
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
		
		String nombre         = request.getParameter("nombre"); 
		String ap_paterno     = request.getParameter("ap_paterno"); 
		String ap_materno     = request.getParameter("ap_materno"); 
	
		String id_administrador     = request.getParameter("idAdministrador"); 
		
		String oper = request.getParameter("oper");
		String query = "";
		int Exito = 0;
		
		System.out.println("oper..........." +oper);
		System.out.println("idAdministrador..........." +id_administrador);
		
		if (oper.equals("edit")){
			System.out.println("entro if");
			int id = Integer.parseInt(request.getParameter("id")); 
			query = "UPDATE administrador SET ";
			query+= "nombre             = '" + nombre         + "', ";
			query+= "ap_paterno         = '" + ap_paterno     + "', ";
			query+= "ap_materno 		= '" + ap_materno     + "', ";
			query+= "WHERE id_administrador = '" + id_administrador + "' ";
			System.out.println(query ); 
		}
		else if (oper.equals("add")){
					
			query = "INSERT INTO administrador(nombre, ap_paterno, ap_materno)";
			query+= "VALUES ('"+nombre+"', '"+ap_paterno+"', '"+ap_materno+"')";
		
	
		}
		else if (oper.equals("del")){
			int id = Integer.parseInt(request.getParameter("id")); 
			query = "DELETE FROM administrador ";
			query+= "WHERE id_administrador = '" + id_administrador + "' ";
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