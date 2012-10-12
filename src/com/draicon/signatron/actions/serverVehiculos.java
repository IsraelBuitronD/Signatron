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
public class serverVehiculos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public serverVehiculos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String marca = request.getParameter("marca");
		String descripcion = request.getParameter("descripcion");
		String nombre = request.getParameter("nombre");
		String placa = request.getParameter("placa");
		String gps = request.getParameter("gps");
		String cliente = request.getParameter("Cliente");
		String id_vehiculo = request.getParameter("id_vehiculo");
		
		String oper = request.getParameter("oper");
		String query = "";
		int Exito = 0;

		System.out.println("oper..........." + oper);

		if (oper.equals("edit")) {
			System.out.println("entro if");
			int id = Integer.parseInt(request.getParameter("id"));
			query = "UPDATE vehiculos SET ";
			query += "marca			 = '" + marca + "', ";
			query += "descripcion	 = '" + descripcion + "', ";
			query += "nombre		 = '" + nombre + "', ";
			query += "id_gps		 = '" + gps + "', ";
			query += "id_cliente	 = '" + cliente + "', ";
			query += "placa			 = '" + placa + "' ";
			query += "WHERE id_vehiculo = '" + id_vehiculo + "' ";
		} else if (oper.equals("add")) {
			query = "INSERT INTO vehiculos ";
			query += "(marca, descripcion, nombre, placa, id_gps, id_cliente) VALUES ";
			query += "('" + marca + "', '" 
			 			  + descripcion + "', '" 
			 			  + nombre 	+ "','" 
			 			  + placa	+ "','"
			 			  + gps	+ "','"
			 			  + cliente + "')";

		} else if (oper.equals("del")) {
			int id = Integer.parseInt(request.getParameter("id"));
			query = "DELETE FROM vehiculos ";
			query += "WHERE id_vehiculo = '" + id_vehiculo + "' ";
		}
		// ResultSet rs = Conexion.getConexion().consultaSql(query);
		Exito = Conexion.getConexion().ejecutaSql(query);
		System.out.println(query);

		if (Exito == 1) {
			System.out.println("exito........" + Exito);
		} else {
			System.out.println("fail........" + Exito);
		}

	}
}