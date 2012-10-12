package com.draicon.signatron.db;
import java.sql.*;

public class Conexion {
		static private Conexion conexion = null; 
		static private Connection conn = null;
		static private Statement stmt = null;
		static private CallableStatement cStmt = null;
		
		//static private final String user = "postgres";
		//static private final String pwd  = "draicon";
		//static private final String url  = "jdbc:postgresql://192.168.1.73:5432/db_signatron";
		
		static private final String user = "postgres";
		static private final String pwd  = "signadb764";
		static private final String url  = "jdbc:postgresql:db_signatron";


		static private boolean debug = false;
		
		private Conexion() {
			tomaConexion(url, user, pwd );
		}
		
		static public Conexion getConexion() {
			if(conexion == null) {
				conexion = new Conexion();
			}
			return conexion;
		}
		
		static public Connection getConnection() {
			if(conexion == null) {
				conexion = new Conexion();
			}
			return conn;
		}
		
		public void setDebug (boolean pDebug )	{
			debug = pDebug;
		}
		
/*		
		public Conexion (){
			tomaConexion(url, user, pwd );
		}
*/
		/*
		 *	getConnection 
		 *					pUrl = URL para conexion a base de datos
		 *					pUser = usuario
		 *					pPwd  = password 
		 *
		 *	Esta metodo es invocado para crear la conexion a la base de datos
		 *  y esta permacene activida durante toda la vida del objeto
		 *
		 */
		private static boolean tomaConexion (String pUrl, String pUser, String pPwd) {
			boolean rtnCode = true;
			try{
				Class.forName("org.postgresql.Driver").newInstance();
				//Class.forName("com.mysql.jdbc.Driver").newInstance();
				DriverManager.setLoginTimeout(30);
				conn = DriverManager.getConnection( pUrl, pUser, pPwd );
				stmt = conn.createStatement();
			} catch (SQLException sqe) {
				rtnCode = false;
				System.out.println("ManejadorDB::tomaConexion::SQLException == " + sqe.toString () );
				sqe.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println ("ManejadorDB::tomaConexion::Exception == " +  e.toString () );			
				rtnCode = false;			
			} finally {
				if ( rtnCode == false ){
					conn = null;
				}
			}
			return rtnCode;
		}
		
		/*
		 *	consultaSql :	
		 *					query = Comando que sera ejecutado por un objeto de Tipo Stmt
		 *
		 *	Este metodo regresa NULL si el query enviado no fue valido 
		 */
		 	
		public ResultSet consultaSql ( String query ){
			ResultSet rs = null;
			if ( conn == null ) {
				return rs;
			}
			try{
				//System.out.println( "intenta ejecutar Query" + query );
				rs = stmt.executeQuery ( query );
				//System.out.println( "Ejecuto Query OK...");
//			} catch (Exception e) {
//				pl ("ManejadorDB::ejecutaSql::c == " +  e.toString () );			
			} catch (SQLException sqe) {
				System.out.println("ManejadorDB::ejecutaSql::SQLException == " + sqe.toString () );
				rs = null;
			}  catch (Exception e) {
				rs = null;
				pl("ManejadorDB::ejecutaSql::Exception == " + e.toString () );			
			}
			return rs;
		}
		
		/*
		 *	ejecutaSql :	
		 *					query = Comando que sera ejecutado por un objeto de Tipo Stmt
		 *
		 *	Este metodo regresa NULL si el query enviado no fue valido 
		 */
		 	
		public int ejecutaSql ( String query ){
			int records=0;
			if ( conn == null ) 
				return records;
			try{
				//System.out.println ( "intenta ejecutar Query" + query );
				records = stmt.executeUpdate ( query );
				//System.out.println( "Ejecuto query OK...");
//			} catch (COM.ibm.db2.jdbc.DB2Exception db2e) {
//				pl ("ManejadorDB::ejecutaSql::COM.ibm.db2.jdbc.DB2Exception == " +  db2e.toString () );			
			} catch (SQLException sqe) {
				System.out.println("ManejadorDB::ejecutaSql::SQLException == " + sqe.toString () );
				// ERROR
				records = -1;
			}  catch (Exception e) {
				System.out.println("ManejadorDB::ejecutaSql::Exception == " + e.toString () );			
				// ERROR
				records = -1;
			}
			return records;
		}
		
		/*
		 *	preparaSP :
		 *
		 *				pNombre = nombre del procedimiento automatico (Sin llaves)
		 *	 *
		 */
		public CallableStatement preparaSP ( String pNombre ) {
			if ( conn == null ) {
				cStmt = null;
				return cStmt;
			}
			try{
				String pString = "\"{ call " + pNombre + "} \"";
				//System.out.println ( "intenta crear Llamado a SP" + pString );
				cStmt = conn.prepareCall ( pString);
				//System.out.println ( "creo llamado a SP OK...");
//			} catch (COM.ibm.db2.jdbc.DB2Exception db2e) {
//				pl ("ManejadorDB::preparaSP::COM.ibm.db2.jdbc.DB2Exception == " +  db2e.toString () );			
			} catch (SQLException sqe) {
				System.out.println("ManejadorDB::preparaSP::SQLException == " + sqe.toString () );
				cStmt = null;
			}  catch (Exception e) {
				cStmt = null;
				System.out.println("ManejadorDB::preparaSP::Exception == " + e.toString () );			
			}
			return cStmt;
		}
		/*
		 *	fin :	
		 *					
		 *	Este metodo cierre la conexion activa y otros recursos utilizados
		 */
		public void fin() {
			try {
				if ( stmt != null )
					stmt.close();
			} catch (SQLException sqe) {
				System.out.println("ManejadorDB::fin::SQLException 1== " + sqe.toString () );	
			}				

			try {
				if ( conn == null )
					conn.close();
			} catch (SQLException sqe) {
				System.out.println("ManejadorDB::fin::SQLException 2== " + sqe.toString () );	
			}				
		}

		public void pl( String texto ) {
			if ( debug ) 
				System.out.println( texto );
		}
}