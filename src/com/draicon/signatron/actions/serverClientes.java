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
public class serverClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serverClientes() {
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
		String razon_soc      = request.getParameter("razon_soc"); 
		String rfc            = request.getParameter("rfc"); 
		String calle          = request.getParameter("calle"); 
		String num_int        = request.getParameter("num_int"); 
		String num_ext        = request.getParameter("num_ext");
		String cp             = request.getParameter("cp"); 
		String ciudad         = request.getParameter("ciudad"); 
		String lote           = request.getParameter("lote"); 
		String mail           = request.getParameter("mail"); 
		String tel1           = request.getParameter("tel1"); 
		String ext1           = request.getParameter("ext1"); 
		String tel2           = request.getParameter("tel2"); 
		String huso           = request.getParameter("huso");
//		String ext2           = request.getParameter("ext2"); 
		String id_cliente     = request.getParameter("idCliente"); 
		
		String oper = request.getParameter("oper");
		String query = "";
		int Exito = 0;
		
		System.out.println("oper..........." +oper);
		System.out.println("idCliente..........." +id_cliente);
		
		if (oper.equals("edit")){
			System.out.println("entro if");
			int id = Integer.parseInt(request.getParameter("id")); 
			query = "UPDATE cliente SET ";
			query+= "nombre             = '" + nombre         + "', ";
			query+= "ap_paterno         = '" + ap_paterno     + "', ";
			query+= "ap_materno 		= '" + ap_materno     + "', ";
			query+= "razon_soc 		    = '" + razon_soc      + "', ";
			query+= "rfc 				= '" + rfc            + "', ";
			query+= "calle 				= '" + calle          + "', ";
			query+= "num_ext			= '" + num_ext        + "', ";
			query+= "num_int 			= '" + num_int        + "', ";
			query+= "ciudad 			= '" + ciudad         + "', ";
			query+= "lote 		  		= '" + lote      	  + "', ";
			query+= "tel1 				= '" + tel1           + "', ";
			query+= "huso 				= '" + huso           + "', ";
			if(!cp.equals("")){
				query+= "cp	=  " + cp + " , ";}
			else{query+= "cp =  " + null + " , ";}
			if(!ext1.equals("")){
				query+=     "ext1 				=  " + ext1           + " , ";
			}	query+=     "tel2 				= '" + tel2           + "', ";
//			if(!ext2.equals("")){
//				query+=     "ext2 				=  " + ext2           + " , ";
//			}
			query+= "mail 		  		= '" + mail      	  + "' ";
			query+= "WHERE id_cliente = '" + id_cliente + "' ";
			System.out.println(query ); 
		}
		else if (oper.equals("add")){
					
			query = "INSERT INTO cliente(nombre, ap_paterno, ap_materno, razon_soc, rfc, calle, num_ext, num_int, ciudad, tel1, huso, ";
			
			if(!ext1.equals("")){
				query+= "ext1, ";
			}
			
			if(!cp.equals("")){
				query+= "cp, ";
			}
			
			
//			if(!ext2.equals("")){
//				query+= "ext2, ";
//			}
//			if(!lote.equals("")){
//				query+= "lote, ";
//			}
//		
			query+= "mail) VALUES ('"+nombre+"', '"+ap_paterno+"', '"+ap_materno+"','"+razon_soc+"','"+rfc+"','"+calle+"','"+num_int+"','"+num_ext+"','" + ciudad + "','" + tel1 + "','" + huso + "','";
			if(!ext1.equals("")){
				query+= ext1+"','";
			}
			if(!cp.equals("")){
				query+= cp+"','";
			}
			else{
				query+= "null, ";
			}
//			if(!ext2.equals("")){
//				query+= ext2+"','";
//			}	 
//			if(!lote.equals("")){
//				query+= lote+"','";
//			}
			query+=  mail + "')";
			System.out.println(query );
	
		}
		else if (oper.equals("del")){
			int id = Integer.parseInt(request.getParameter("id")); 
			query = "DELETE FROM cliente ";
			query+= "WHERE id_cliente = '" + id_cliente + "' ";
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