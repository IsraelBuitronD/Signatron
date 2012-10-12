package com.draicon.signatron.actions;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.draicon.signatron.db.Conexion;
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		String passw = request.getParameter("passw");
		String id_user = null;
		String query;
		int Exito = 0;
		HttpSession session = request.getSession();
		
		query = "SELECT u.usuario, c.nombre, u.id_user ";
		query+= "FROM usuarios u ";
		query+= "INNER JOIN cliente c ";
		query+= "ON u.id_cliente = c.id_cliente ";
		query+= "WHERE u.usuario  = '" + user + "' ";
		query+= "AND   u.password = '" + passw + "' ";
		ResultSet rs = Conexion.getConexion().consultaSql(query);
		//System.out.println(query);

		try {
			if(rs.next()){
				Exito = 1;
				id_user = rs.getString("id_user");
				//System.out.println("..."+id_user);
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
    		String urlJSP = "login.jsp?username="+user+"&msgInfo=Verificar que el usuario y la contrase&ntilde;a sean correctas";
    		response.sendRedirect(response.encodeRedirectURL(urlJSP));
    	}
	}
}