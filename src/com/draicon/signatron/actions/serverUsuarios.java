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
public class serverUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serverUsuarios() {
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
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String id = request.getParameter("id");
		String id_user = request.getParameter("id_user");
		
		int idInt = 0; 
		System.out.println("id..........." +id);
		if(!String.valueOf(id).equals("_empty")){
			 idInt = Integer.parseInt(id); 
		}
		
		String oper = request.getParameter("oper");
		String query = "";
		int Exito = 0;
		
		System.out.println("oper..........." +oper);
		
		if (oper.equals("edit")){
			System.out.println("entro if");
			
			query = "UPDATE usuarios SET ";
			query+= "usuario             = '" + usuario         + "', ";
			query+= "password             = '" + password         + "' ";
			query+= "WHERE id_user = '" + id_user + "' ";
		}
		else if (oper.equals("add")){
			query = "INSERT INTO usuarios ";
			query+= "(usuario, password) VALUES ";
			query+= "('" + usuario + "', '" + password + "') ";
	
		}
		else if (oper.equals("del")){
			
			query = "DELETE FROM usuarios ";
			query+= "WHERE id_user = '" + id_user + "' ";
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