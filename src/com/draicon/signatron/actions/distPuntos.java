package com.draicon.signatron.actions;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.draicon.signatron.db.Conexion;
/**
 * Servlet implementation class Login
 */
public class distPuntos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public distPuntos() {
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
		String user = request.getParameter("username");
		String passw = request.getParameter("passw");
		String id_user = null;
		String query;
		int Exito = 0;
		HttpSession session = request.getSession();
		
		query = "SELECT latitud, longitud ";
		query+= "FROM posiciones ";
		query+= "WHERE id_vehiculo = 43 AND (latitud <> 0 AND longitud <>0) ";
		
//		query+= "AND   u.password = '" + passw + "' ";
		ResultSet rs = Conexion.getConexion().consultaSql(query);
		System.out.println(query);

		try {
			if(rs.next()){
				Exito = 1;
				id_user = rs.getString("id_user");
				System.out.println("..."+id_user);
				session.setAttribute("id_user", id_user);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    	if(Exito==1){
    		session.setAttribute("username", user);
    		response.sendRedirect(response.encodeRedirectURL("jsp/portal.jsp"));
    	}else{
    		String urlJSP = "index.jsp?username="+user+"&msgInfo=Verificar que el usuario y la contraseña sean correctas";
    		response.sendRedirect(response.encodeRedirectURL(urlJSP));
    	}
	}
}