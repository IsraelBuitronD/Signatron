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
public class serverComandos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serverComandos() {
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
		String motivo = request.getParameter("motivo");
		String codigo = request.getParameter("codigo");
		String comando = request.getParameter("comando");
		String id_motivo = request.getParameter("id_motivo");
		
		
		String oper = request.getParameter("oper");
		String query = "";
		int Exito = 0;
		
		System.out.println("oper..........." +oper);
		
		if (oper.equals("edit")){
			System.out.println("entro if");
			int id = Integer.parseInt(request.getParameter("id")); 
			query = "UPDATE cat_motivo SET ";
			query+=         "motivo = '" + motivo        + "', ";
			query+=         "codigo = '" + codigo        + "' ";
			query+=         "iscommand = '" + comando        + "' ";
			query+= "WHERE id_motivo = '" + id_motivo + "' ";
		}
		else if (oper.equals("add")){
			query = "INSERT INTO cat_motivo ";
			query+= "(motivo, codigo, iscommand ) VALUES ";
			query+= "('" + motivo + "', '" + codigo + "', '" + comando+ "')";
	
		}
		else if (oper.equals("del")){
			int id = Integer.parseInt(request.getParameter("id")); 
			query = "DELETE FROM cat_motivo ";
			query+= "WHERE id_motivo = '" + id_motivo + "' ";
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