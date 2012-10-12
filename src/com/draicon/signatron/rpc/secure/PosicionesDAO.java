package com.draicon.signatron.rpc.secure;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.draicon.signatron.beans.Estadistico;
import com.draicon.signatron.db.Conexion;
import com.draicon.signatron.rpc.em.DAOEntityManagerFactory;
import com.draicon.signatron.security.exception.NonAuthorizedCallException;
import com.draicon.util.Formatter;
import com.draicon.signatron.data.Posiciones;
import com.draicon.signatron.data.Usuarios;

public class PosicionesDAO extends BaseCommonDAO {
	private static Log logger = LogFactory.getLog(PosicionesDAO.class);

	private final static String ENTITY_ID = "idPosicion";
	private final static String ENTITY_NAME = "Posiciones";
	private final static String ENTITY_VEL = "velodidad";
	
	private final static Class<?> ENTITY_CLASS = Posiciones.class;

	public PosicionesDAO() {
	}

	@Override
	protected String getPersistenceEntityIdField() {
		logger.debug("getPersistenceEntityIdField: " + ENTITY_ID);
		return ENTITY_ID;
	}

	@Override
	protected String getPersistenceEntityName() {
		logger.debug("getPersistenceEntityName: " + ENTITY_NAME);
		return ENTITY_NAME;
	}

	@Override
	protected Class<?> getPersistenceEntityClass() {
		logger.debug("getPersistenceEntityClass: "
				+ ENTITY_CLASS.getCanonicalName());
		return ENTITY_CLASS;
	}
	
	public List<?> getUltimasPosicionesVehiculos(String authToken, String[] vehiculos) throws NonAuthorizedCallException,
			PersistenceException {
		List<?> results = null;
		
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			String queryVehiculos = "(";
			for (int i = 0; i < vehiculos.length; i++) {
				queryVehiculos +=  (i!=0)? " OR ": "";
				queryVehiculos += "p.idVehiculo.idVehiculo=" + vehiculos[i];

			}
			queryVehiculos += ")";
			
			Query q = em.createQuery("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p " 
					+ "WHERE p." + this.getPersistenceEntityIdField() + " IN ("
					+   "SELECT MAX(p." + this.getPersistenceEntityIdField() + ") "
					+   "FROM " + this.getPersistenceEntityName() + " p "
					+   "WHERE " + queryVehiculos
					+   "GROUP BY p.idVehiculo.idVehiculo "
					+ ") ORDER BY p."
					+ this.getPersistenceEntityIdField());
			
			results = q.getResultList();
		} catch (Exception e) {
			logger.error("Problema en getGridDataFilter", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
		return results;
	}
	
	public List<?> getAlarmas(String authToken, int idCliente) throws NonAuthorizedCallException,
			PersistenceException {
		List<?> results = null;
		
		EntityTransaction tx = null;
		EntityManager em = null;
		
		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();
		
			tx.begin();
		
			Query q = em.createQuery("SELECT a FROM "
					+ this.getPersistenceEntityName() + " a " 
					+ "WHERE a.alarmaStatus=0 "
					+ "AND a.idVehiculo IS NOT NULL "
					+ "AND a.idVehiculo.idCliente.idCliente=" + idCliente + " "
					+ "ORDER BY a.alarmaStatus ASC, a.fecha DESC, a.idPosicion DESC ");//LIMIT 20");
//					+ "ORDER BY a.idPosicion DESC"); // OR a.alarmaStatus=1
			
			results = q.getResultList();
		} catch (Exception e) {
			logger.error("Problema en getAlarmas", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
		return results;
	}
	
	public List<?> atenderAlarma(String authToken, int idCliente, int idPosicion) throws NonAuthorizedCallException,
			PersistenceException {
		Posiciones posicion = (Posiciones) this.getDataById(authToken, idPosicion);
		if(posicion.getAlarmaStatus()==0){
			Usuarios usuario = (new UsuariosDAO()).getUsuarioByIdCliente(idCliente);
			Calendar fechaNow = Calendar.getInstance();
			posicion.setAlarmaStatus(1);
			posicion.setAlarmaFecha(new Timestamp(fechaNow.getTimeInMillis()));
			posicion.setIdUser(usuario);
			this.update(authToken, posicion);
		}
		return this.getAlarmas(authToken, idCliente);
	}
	
	public static Estadistico calculaDistancia(List<Posiciones> lista, Estadistico resultado){
		try{
			double distancia = 0;
			double sumaVelocidad=0;
			double sumaAltitud=0;
			
			BigDecimal cero = new BigDecimal(0);
		
			if(lista.size()==0){
				
				resultado.setDistanciaRecorrida(distancia);
				resultado.setVelocidadPromedio(sumaVelocidad);
				resultado.setAltitudPromedio(sumaAltitud);
			}
			if(lista.size() > 1){
				BigDecimal latitud1 =cero, latitud2=cero;
				BigDecimal longitud1=cero, longitud2=cero;
					
				for (int  i=0; i<lista.size(); i++) {
					Posiciones dato = lista.get(i);
					sumaVelocidad = sumaVelocidad + dato.getVelocidad().doubleValue();
					//sumaAltitud = sumaAltitud + dato.getAltitud().doubleValue();
					
					//aqui saca la distancia
					if(i==0){
						latitud1 = dato.getLatitud();
						longitud1 = dato.getLongitud();
					}else{
						latitud2 = dato.getLatitud();
						longitud2 = dato.getLongitud();
						
						distancia = distancia + distance(latitud1, latitud2, longitud1, longitud2, 'K');
					}					
					
					//aqui va a verificar la velodidad maxima
				}
			
				distancia = Formatter.redondea2Decimales(distancia);
				
				sumaVelocidad = Formatter.redondea2Decimales(sumaVelocidad/lista.size());
				sumaAltitud = Formatter.redondea2Decimales(sumaAltitud/lista.size());
				
				resultado.setDistanciaRecorrida(distancia);
				resultado.setVelocidadPromedio(sumaVelocidad);
				resultado.setAltitudPromedio(sumaAltitud);
				
			}
			
			return resultado;
			
		}catch(Exception e){
			e.printStackTrace();
			return resultado;
		}
	}
	
	public static double distance(BigDecimal lat1, BigDecimal lat2, BigDecimal lon1, BigDecimal lon2, char unit){
		/*
		System.out.println("datos que se le pasaron: ");
		System.out.println("lat1:" + lat1);
		System.out.println("lat2:" + lat2);
		System.out.println("lon1:" + lon1);
		System.out.println("lon2:" + lon2);
		*/
		double radlat1 = Math.PI * lat1.doubleValue()/180;
		double radlat2 = Math.PI * lat2.doubleValue()/180;
		double radlon1 = Math.PI * lon1.doubleValue()/180;
		double radlon2 = Math.PI * lon2.doubleValue()/180;
		
		double theta = lon1.doubleValue()-lon2.doubleValue();
		double radtheta = Math.PI * theta/180;
		double dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
		//alert('draicon.boxio.dist 1:' + draicon.boxio.dist);
		//System.out.println("distancia 1:" + dist);
		dist = Math.acos(dist);
		//System.out.println("distancia 2:" + dist);
		//alert('draicon.boxio.dist 2:' + draicon.boxio.dist);
		dist = dist * 180/Math.PI;
		//System.out.println("distancia 3:" + dist);
		//alert('draicon.boxio.dist 3:' + draicon.boxio.dist);
		dist = dist * 60 * 1.1515;
		//System.out.println("distancia 4:" + dist);
		//alert('draicon.boxio.dist 4:' + draicon.boxio.dist);
		if (unit=='K') { 
			dist = dist * 1.609344; 
		}
		//if (draicon.boxio.unit=="N") { draicon.boxio.dist = draicon.boxio.dist * 0.8684 }
		//System.out.println("distancia 5:" + dist);
		
		//System.out.println("distancia 6:" + dist);
		return dist;
	}
	
	public static Estadistico calculoTiempos(Estadistico datos){
		String query ="";

		Integer motivo = null;
		Integer motivoAnt = null ;
		Date fechaAnterior=null;
		Map resultadoMap = new HashMap();
		long sumaHoras=0, sumaHorasApa=0;
		long sumaMin=0, sumaMinApa=0;
		long sumaSegundos=0, sumaSegundosApa=0;
		
		query = "SELECT * FROM posiciones where id_vehiculo =" + datos.getId_vehiculo() +
				"and (id_motivo = 11 or id_motivo =12)" +
				"order by id_posicion asc;";
		
		ResultSet rs = Conexion.getConexion().consultaSql(query);
		
		try {
			
			int contador =0;
			motivoAnt =0;
			
			while(rs.next()){
				Date fecha = rs.getTimestamp("fecha");
				motivo = rs.getInt("id_motivo");
				
				if(contador ==0){
					fechaAnterior= fecha;
					motivoAnt= motivo;
				}else{
					if(motivo != motivoAnt){
						resultadoMap = getDiferencia(fecha, fechaAnterior);
						
						if (motivo == 11){
							//apagado
							sumaHorasApa= sumaHoras + Long.parseLong(resultadoMap.get("horas").toString());
							sumaMinApa = sumaMin + Long.parseLong(resultadoMap.get("minutos").toString());
							sumaSegundosApa = sumaSegundos + Long.parseLong(resultadoMap.get("segundos").toString());
						}else{
							//encendido
							sumaHoras= sumaHoras + Long.parseLong(resultadoMap.get("horas").toString());
							sumaMin = sumaMin + Long.parseLong(resultadoMap.get("minutos").toString());
							sumaSegundos = sumaSegundos + Long.parseLong(resultadoMap.get("segundos").toString());							
						}
							
						fechaAnterior= fecha;
						motivoAnt=motivo;
							
					}
					
				}
				
				contador = contador + 1;
			}
			rs.close();
			
			datos.setTiempoPrendidoStr(formateaTiempo(sumaHoras, sumaMin, sumaSegundos));
			datos.setTiempoApagadoStr(formateaTiempo(sumaHorasApa, sumaMinApa, sumaSegundosApa));
			datos.setTiempoTotalStr(formateaTiempo(sumaHoras + sumaHorasApa, sumaMin+  sumaMinApa, sumaSegundos+sumaSegundosApa));
			
			double tiempoP =0, tiempoA;
			double porcentajeP=0, porcentajeA=0;
			
			tiempoP = sumaHoras * 60;//ya se tiene en minutos
			tiempoP = ((sumaHoras + sumaMin)*60) + sumaSegundos; 
			
			
			
			tiempoA = sumaHorasApa * 60;//ya se tiene en minutos
			tiempoA = ((sumaHorasApa + sumaMinApa)*60) + sumaSegundosApa; 
			
			porcentajeP = (tiempoP*100)/(tiempoP+tiempoA);
			porcentajeP = Formatter.redondea2Decimales(porcentajeP);
			
			porcentajeA = (tiempoA*100)/(tiempoP+tiempoA);
			porcentajeA = Formatter.redondea2Decimales(porcentajeA);
			
			datos.setTiempoPrendido(porcentajeP);
			datos.setTiempoApagado(porcentajeA);
			
			datos.setTiempoTotal(tiempoP+tiempoA);
			
			return datos;
		
		}
		catch (SQLException e) {
			e.printStackTrace();
			datos.setTiempoPrendidoStr(formateaTiempo(0, 0, 0));
			datos.setTiempoApagadoStr(formateaTiempo(0, 0, 0));
			datos.setTiempoTotalStr(formateaTiempo(0, 0, 0));
			
			return datos;
		}		
	}
	
	
	public static Map getDiferencia(java.util.Date fecha1, java.util.Date fecha2){
		   java.util.Date fechaMayor = null;
		   java.util.Date fechaMenor = null;
		   Map resultadoMap = new HashMap();
		   
		   /* Verificamos cual es la mayor de las dos fechas, para no tener sorpresas al momento
		    * de realizar la resta.
		    */
		   if (fecha1.compareTo(fecha2) > 0){
		    fechaMayor = fecha1;
		    fechaMenor = fecha2;
		   }else{
		    fechaMayor = fecha2;
		    fechaMenor = fecha1;
		   }
	
		  //los milisegundos
		   long diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();
		 
		   //obtenemos los segundos
		   long segundos = diferenciaMils / 1000;
		 
		   //obtenemos los dias
		   //long dias = segundos / 86400;
		   
		   //restamos los dias para continuar con las horas
		   //segundos -= dias*86400;
		   
		   //obtenemos las horas
		   long horas = segundos / 3600;
		 
		   //restamos las horas para continuar con minutos
		   segundos -= horas*3600;
		 
		   //igual que el paso anterior
		   long minutos = segundos /60;
		   segundos -= minutos*60;
	
		   resultadoMap.put("horas",horas);
		   resultadoMap.put("minutos",minutos);
		   resultadoMap.put("segundos",segundos);
		   
		   return resultadoMap;
		}
	
	public static String formateaTiempo(long horas, long minutos, long segundos){
		
		String cadena="";
		long dias=0;
		
		if(horas >= 24 ){
			
			dias = Math.round(horas/24);
			horas = horas % 24;
			
		}
		
		if(minutos >= 60){
			
			horas = horas + Math.round(minutos/60);
			
			minutos = minutos % 60;
		}
		
		if(segundos >=60){
			
			minutos = minutos + Math.round(segundos);
			
			segundos = segundos % 60;
		}
		
		if(dias > 0){
			cadena = dias + " dias " + horas + " hr. " + minutos + " min. " + segundos + " seg.";
		}else{
			cadena = horas + " hr. " + minutos + " min. " + segundos + " seg.";
		}
		
		return cadena;
		
	}

}