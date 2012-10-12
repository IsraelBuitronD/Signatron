package com.draicon.signatron.rpc.secure;

import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.draicon.signatron.beans.Estadistico;
import com.draicon.signatron.rpc.em.DAOEntityManagerFactory;
import com.draicon.signatron.security.exception.NonAuthorizedCallException;
import com.draicon.util.Formatter;
import com.draicon.signatron.data.Posiciones;
import com.draicon.signatron.data.Usuarios;

public abstract class BaseCommonDAO implements CommonDAO {
	private static Log logger = LogFactory.getLog(BaseCommonDAO.class);

	public BaseCommonDAO() {

	}

	protected abstract String getPersistenceEntityName();

	protected abstract String getPersistenceEntityIdField();

	protected abstract Class<?> getPersistenceEntityClass();

	public List<?> getGridData(String authToken)
			throws NonAuthorizedCallException, PersistenceException {
		List<?> results = null;
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			Query q = em.createQuery("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p ORDER BY p."
					+ this.getPersistenceEntityIdField());

			results = q.getResultList();

		} catch (Exception e) {
			logger.error("Problema en getGridData", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}

		return results;
	}


	public List<?> getGridData2(String authToken, int user )
			throws NonAuthorizedCallException, PersistenceException {
		List<?> results = null;
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			Query q = em.createQuery("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p " 
					+ "  WHERE "
					+ "p.idVehiculo.idCliente.idCliente = "
					+ user
					+ " p ORDER BY p."
					+ this.getPersistenceEntityIdField());
			results = q.getResultList();

		} catch (Exception e) {
			logger.error("Problema en getGridData", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}

		return results;
	}
	public List<?> getGridDataFilter(String authToken, String filterField,
			String[] valueFilter) throws NonAuthorizedCallException,
			PersistenceException {
		List<?> results = null;
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			String query = "(";
			for (int i = 0; i < valueFilter.length; i++) {
				if (i != 0) {
					query += " OR ";
				}
				query += "p." + filterField + " = " + valueFilter[i];

			}
			query += ") ";
			Query q = em.createQuery("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p " 
					+ "WHERE "
					+ query 
					+ "ORDER BY p."
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

	public List<?> getGridDataFilterWhere(String authToken, String filterField, int valueFilter,
									      String fecha1, String fecha2, int dias, int param, int user, int order)
			throws NonAuthorizedCallException, PersistenceException {
		List<?> results = null;
		EntityTransaction tx = null;
		EntityManager em = null;
		String orderResults = " ASC";
		Usuarios usuario = (Usuarios)(new UsuariosDAO()).getDataById(authToken, user);
		
		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();
			String query = "";
			
			if(order==2){
				orderResults=" DESC";
			}
			
			if (param == 1){
				query += " AND p.fecha BETWEEN ?1 AND ?2 ";
			}else if(param == 2){
				query += " AND p.fecha BETWEEN ?1 AND ?2 ";
			}else if(param == 3){
				query += " AND p.fecha BETWEEN ?1 AND ?2 ";
			}

			Query q = em.createQuery("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p " + "WHERE p."
					+ filterField + " = " + valueFilter + " " 
					+ " AND p.idVehiculo.idCliente.idCliente = "
					+ user
					+ query 
					+ "ORDER BY p.fecha " + orderResults + ", p.idPosicion " + orderResults);
//					+ this.getPersistenceEntityIdField() + orderResults);
/*			System.out.println("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p " + "WHERE p."
					+ filterField + " = " + valueFilter + " " 
					+ " AND p.idVehiculo.idCliente.idCliente = "
					+ user
					+ query 
					+ "ORDER BY p."
					+ this.getPersistenceEntityIdField() + orderResults);*/
			Calendar fechaNow = Calendar.getInstance();
			fechaNow.set(fechaNow.get(Calendar.YEAR), fechaNow
					.get(Calendar.MONTH), fechaNow.get(Calendar.DAY_OF_MONTH),0, 0, 0);
			fechaNow.set(Calendar.MILLISECOND, 0);
			if(usuario.getIdCliente().getHuso()!=0){
				fechaNow.add(Calendar.HOUR, -usuario.getIdCliente().getHuso());
			}
		
			if (param == 1){
				Calendar fechaDiaDespues = fechaNow.getInstance();
				fechaDiaDespues.set(fechaDiaDespues.get(Calendar.YEAR), fechaDiaDespues.get(Calendar.MONTH), fechaDiaDespues.get(Calendar.DAY_OF_MONTH), fechaNow.get(Calendar.HOUR_OF_DAY), fechaNow.get(Calendar.MINUTE),  fechaNow.get(Calendar.SECOND));
				fechaDiaDespues.set(Calendar.MILLISECOND, 0);
				fechaDiaDespues.add(Calendar.DATE, 1);
				fechaDiaDespues.add(Calendar.SECOND, -1);
				Timestamp TfechaNow = new Timestamp(fechaNow.getTimeInMillis());
 				Timestamp TfechaDiaDespues = new Timestamp(fechaDiaDespues.getTimeInMillis());
// 				System.out.println(">>>>>>>>>> HOY");
//				System.out.println("TfechaNow.... " + TfechaNow);
//				System.out.println("TfechaDiaDespues.... " + TfechaDiaDespues);
				q.setParameter(1, TfechaNow);
				q.setParameter(2, TfechaDiaDespues);
			}else if(param == 2){
				Calendar fechaDias; 
				fechaDias = fechaNow.getInstance();
				fechaDias.set(fechaDias.get(Calendar.YEAR), fechaDias.get(Calendar.MONTH), fechaDias.get(Calendar.DAY_OF_MONTH), fechaNow.get(Calendar.HOUR_OF_DAY), fechaNow.get(Calendar.MINUTE),  fechaNow.get(Calendar.SECOND));
				fechaDias.set(Calendar.MILLISECOND, 0);
				fechaDias.add( Calendar.DATE, ((-dias) + 1));
//				fechaDias.set(fechaDias.get(Calendar.YEAR), fechaDias
//					.get(Calendar.MONTH), fechaDias.get(Calendar.DAY_OF_MONTH),0, 0, 0 );
//				fechaDias.set(Calendar.MILLISECOND, 0);
//				fechaNow.set(fechaNow.get(Calendar.YEAR), fechaNow.get(Calendar.MONTH), fechaNow.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
//				fechaNow.set(Calendar.MILLISECOND, 0);
				fechaNow.add(Calendar.DATE, 1);
				fechaNow.add(Calendar.SECOND, -1);
				java.sql.Timestamp TfechaDias = new Timestamp(fechaDias.getTimeInMillis());
				java.sql.Timestamp TfechaNow = new Timestamp(fechaNow.getTimeInMillis());
//				System.out.println(">>>>>>>>> DIAS ANTES");
//				System.out.println("TfechaNow.... " + TfechaNow);
//				System.out.println("TfechaDias.... " + TfechaDias);
				q.setParameter(1, TfechaDias);
				q.setParameter(2, TfechaNow);     
			}else if(param == 3){
				int day=Integer.parseInt(fecha1.substring(0,fecha1.indexOf("/")));
		  		int month=Integer.parseInt(fecha1.substring(fecha1.indexOf("/")+1,fecha1.lastIndexOf("/")));
		  		int year=Integer.parseInt(fecha1.substring(fecha1.lastIndexOf("/")+1,fecha1.length()));
				Calendar fecha = new GregorianCalendar( year,  month-1, day);
//		        System.out.println("Tfecha===.. " + Tfecha1);
		      	int day2=Integer.parseInt(fecha2.substring(0,fecha2.indexOf("/")));
			  	int month2=Integer.parseInt(fecha2.substring(fecha2.indexOf("/")+1,fecha2.lastIndexOf("/")));
			  	int year2=Integer.parseInt(fecha2.substring(fecha2.lastIndexOf("/")+1,fecha2.length()));
				Calendar fecha22 = new GregorianCalendar( year2, month2-1, day2);
				fecha22.set(fecha22.get(Calendar.YEAR), fecha22.get(Calendar.MONTH), fecha22.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				fecha22.set(Calendar.MILLISECOND, 0);
				fecha22.add(Calendar.DATE, 1);
				fecha22.add(Calendar.SECOND, -1);
				
				if(usuario.getIdCliente().getHuso()!=0){
					fecha.add(Calendar.HOUR, -usuario.getIdCliente().getHuso());
					fecha22.add(Calendar.HOUR, -usuario.getIdCliente().getHuso());
				}

				Timestamp Tfecha1 = new Timestamp(fecha.getTimeInMillis());			
			    Timestamp Tfecha2 = new Timestamp(fecha22.getTimeInMillis());
//			    System.out.println(">>>>>>>>>>>> RANGO FECHAS");
//			    System.out.println("Tfecha1... " + Tfecha1);
//		        System.out.println("Tfecha2... " + Tfecha2);
				q.setParameter(1, Tfecha1);
				q.setParameter(2, Tfecha2);
			}
			results = q.getResultList();
		} catch (Exception e) {
			logger.error("Problema en getGridDataFilterWhere", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
		return results;
	}

	public Estadistico getDatosVehiculo(String authToken, String filterField, int valueFilter,
		      String fecha1, String fecha2, int dias, int param, int user, int order)
		      throws NonAuthorizedCallException, PersistenceException {
		List<Posiciones> results = null;
		EntityTransaction tx = null;
		EntityManager em = null;
		String orderResults = " ASC";
		Estadistico resultado= new Estadistico();
		
		Usuarios usuario = (Usuarios)(new UsuariosDAO()).getDataById(authToken, user);
		
		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();
		
			tx.begin();
			String query = "";
		
			if(order==2){
				orderResults=" DESC";
			}
		
			if (param == 1){
				query += " AND p.fecha BETWEEN ?1 AND ?2 ";
			}else if(param == 2){
				query += " AND p.fecha BETWEEN ?1 AND ?2 ";
			}else if(param == 3){
				query += " AND p.fecha BETWEEN ?1 AND ?2 ";
			}
			
			Query q = em.createQuery("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p " + "WHERE p."
				+ filterField + " = " + valueFilter + " " 
				+ " AND p.idVehiculo.idCliente.idCliente = "
				+ user
				+ query 
				+ "ORDER BY p.fecha " + orderResults + ", p.idPosicion " + orderResults);
			
			//para obtener la velocidad maxima
			Query vel = em.createQuery("SELECT max(p.velocidad) FROM "
					+ this.getPersistenceEntityName() + " p " + "WHERE p."
				+ filterField + " = " + valueFilter + " " 
				+ " AND p.idVehiculo.idCliente.idCliente = "
				+ user
				+ query );
			
			//+ this.getPersistenceEntityIdField() + orderResults);
				/*			System.out.println("SELECT p FROM "
				+ this.getPersistenceEntityName() + " p " + "WHERE p."
				+ filterField + " = " + valueFilter + " " 
				+ " AND p.idVehiculo.idCliente.idCliente = "
				+ user
				+ query 
				+ "ORDER BY p."
				+ this.getPersistenceEntityIdField() + orderResults);*/
			Calendar fechaNow = Calendar.getInstance();
			fechaNow.set(fechaNow.get(Calendar.YEAR), fechaNow.get(Calendar.MONTH), fechaNow.get(Calendar.DAY_OF_MONTH),0, 0, 0);
			fechaNow.set(Calendar.MILLISECOND, 0);
			
			if(usuario.getIdCliente().getHuso()!=0){
				fechaNow.add(Calendar.HOUR, -usuario.getIdCliente().getHuso());
			}
			
			if (param == 1){
				Calendar fechaDiaDespues = fechaNow.getInstance();
				//fechaNow.set(fechaDiaDespues.get(Calendar.YEAR), fechaDiaDespues.get(Calendar.MONTH), fechaDiaDespues.get(Calendar.DAY_OF_MONTH), 0, 0,  0);
				fechaDiaDespues.set(fechaDiaDespues.get(Calendar.YEAR), fechaDiaDespues.get(Calendar.MONTH), fechaDiaDespues.get(Calendar.DAY_OF_MONTH), 23, 59,  59);
				fechaDiaDespues.set(Calendar.MILLISECOND, 0);
				//fechaDiaDespues.add(Calendar.DATE, 1);
				//fechaDiaDespues.add(Calendar.SECOND, -1);
				//fechaDiaDespues.setTimeInMillis(0);
				
				Timestamp TfechaNow = new Timestamp(fechaNow.getTimeInMillis());
				Timestamp TfechaDiaDespues = new Timestamp(fechaDiaDespues.getTimeInMillis());
//				System.out.println(">>>>>>>>>> HOY");
//				System.out.println("TfechaNow.... " + TfechaNow);
//				System.out.println("TfechaDiaDespues.... " + TfechaDiaDespues);
				q.setParameter(1, TfechaNow);
				q.setParameter(2, TfechaDiaDespues);
				vel.setParameter(1, TfechaNow);
				vel.setParameter(2, TfechaDiaDespues);
				resultado.setFechaInicial(TfechaNow);
				resultado.setFechaFinal( TfechaDiaDespues);
			
				resultado.setFechaInicialStr(Formatter.getStringFromDate(TfechaDiaDespues));
				resultado.setFechaFinalStr(Formatter.getStringFromDate(TfechaNow));
			}else if(param == 2){
				Calendar fechaDias; 
				fechaDias = fechaNow.getInstance();
				fechaNow.set(fechaNow.get(Calendar.YEAR), fechaNow.get(Calendar.MONTH), fechaNow.get(Calendar.DAY_OF_MONTH),23, 59, 59);
				fechaDias.set(fechaDias.get(Calendar.YEAR), fechaDias.get(Calendar.MONTH), fechaDias.get(Calendar.DAY_OF_MONTH), 0,0,0);
				//fechaDias.set(Calendar.MILLISECOND, 0);
				fechaDias.add( Calendar.DATE, ((-dias) +1));
				//fechaDias.set(fechaDias.get(Calendar.YEAR), fechaDias
				//.get(Calendar.MONTH), fechaDias.get(Calendar.DAY_OF_MONTH),0, 0, 0 );
				//fechaDias.set(Calendar.MILLISECOND, 0);
				//fechaNow.set(fechaNow.get(Calendar.YEAR), fechaNow.get(Calendar.MONTH), fechaNow.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
				//fechaNow.set(Calendar.MILLISECOND, 0);
				//fechaNow.add(Calendar.DATE, 1);
				//fechaNow.add(Calendar.SECOND, -1);
				java.sql.Timestamp TfechaDias = new Timestamp(fechaDias.getTimeInMillis());
				java.sql.Timestamp TfechaNow = new Timestamp(fechaNow.getTimeInMillis());
//				System.out.println(">>>>>>>>> DIAS ANTES");
//				System.out.println("TfechaNow.... " + TfechaNow);
//				System.out.println("TfechaDias.... " + TfechaDias);
				q.setParameter(1, TfechaDias);
				q.setParameter(2, TfechaNow);
				vel.setParameter(1, TfechaDias);
				vel.setParameter(2, TfechaNow);
				resultado.setFechaInicial(TfechaDias);
				resultado.setFechaFinal(TfechaNow);
				resultado.setFechaInicialStr(Formatter.getStringFromDate(TfechaDias));
				resultado.setFechaFinalStr(Formatter.getStringFromDate(TfechaNow));
			}else if(param == 3){
				int day=Integer.parseInt(fecha1.substring(0,fecha1.indexOf("/")));
				int month=Integer.parseInt(fecha1.substring(fecha1.indexOf("/")+1,fecha1.lastIndexOf("/")));
				int year=Integer.parseInt(fecha1.substring(fecha1.lastIndexOf("/")+1,fecha1.length()));
				Calendar fecha = new GregorianCalendar( year,  month-1, day);
				fecha.set(year, month-1, day, 0, 0, 0);
				//System.out.println("Tfecha===.. " + Tfecha1);
				int day2=Integer.parseInt(fecha2.substring(0,fecha2.indexOf("/")));
				int month2=Integer.parseInt(fecha2.substring(fecha2.indexOf("/")+1,fecha2.lastIndexOf("/")));
				int year2=Integer.parseInt(fecha2.substring(fecha2.lastIndexOf("/")+1,fecha2.length()));
				Calendar fecha22 = new GregorianCalendar( year2, month2-1, day2);
				fecha22.set(fecha22.get(Calendar.YEAR), fecha22.get(Calendar.MONTH), fecha22.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
				//fecha22.set(Calendar.MILLISECOND, 0);
				//fecha22.add(Calendar.DATE, 1);
				//fecha22.add(Calendar.SECOND, -1);
				
				if(usuario.getIdCliente().getHuso()!=0){
					fecha.add(Calendar.HOUR, -usuario.getIdCliente().getHuso());
					fecha22.add(Calendar.HOUR, -usuario.getIdCliente().getHuso());
				}
			
				Timestamp Tfecha1 = new Timestamp(fecha.getTimeInMillis());			
				Timestamp Tfecha2 = new Timestamp(fecha22.getTimeInMillis());
//				System.out.println(">>>>>>>>>>>> RANGO FECHAS");
//				System.out.println("Tfecha1... " + Tfecha1);
//				System.out.println("Tfecha2... " + Tfecha2);
				q.setParameter(1, Tfecha1);
				q.setParameter(2, Tfecha2);
				vel.setParameter(1, Tfecha1);
				vel.setParameter(2, Tfecha2);
				
				resultado.setFechaInicial(Tfecha1);
				resultado.setFechaFinal(Tfecha2);
				resultado.setFechaInicialStr(Formatter.getStringFromDate(Tfecha1));
				resultado.setFechaFinalStr(Formatter.getStringFromDate(Tfecha2));
			}
			
			//en q se guarda el resultado de la consulta
			if(vel.getSingleResult()== null){
				resultado.setVelocidadMaxima(0);
			}else{
				resultado.setVelocidadMaxima(((BigDecimal)vel.getSingleResult()).doubleValue());
			}
			
			results = q.getResultList();
//			System.out.println("resultados:" + results.size());
			//regresa las posiciones para las graficas
			
			resultado.setPosiciones(results);
			
			resultado = PosicionesDAO.calculaDistancia(results, resultado);
			resultado.setId_vehiculo(valueFilter);
			resultado = PosicionesDAO.calculoTiempos(resultado);
			
		} catch (Exception e) {
			logger.error("Problema en getDatosVehiculo", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
			
		return resultado;
	}
	
	public List<?> getGridFilterDataInt(String authToken, String filterField,
			int valueFilter) throws NonAuthorizedCallException,
			PersistenceException {

		List<?> results = null;
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			Query q = em.createQuery("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p " + "WHERE p."
					+ filterField + " = " + valueFilter + " " + "ORDER BY p."
					+ this.getPersistenceEntityIdField() + " DESC ");
			results = q.getResultList();
//			System.out.println("results:" + results);
		} catch (Exception e) {
			logger.error("Problema en getGridFilterData", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}

		return results;
	}

	public List<?> getGridFilterDataIntWithPager(String authToken, String filterField,
			int valueFilter, int page, int rows) throws NonAuthorizedCallException,
			PersistenceException {

		List<?> results = null;
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			Query q = em.createQuery("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p " + "WHERE p."
					+ filterField + " = " + valueFilter + " " + "ORDER BY p."
					+ this.getPersistenceEntityIdField() + " DESC ");
			q.setMaxResults(rows);
			q.setFirstResult((page-1)*rows);
			results = q.getResultList();
//			System.out.println("results:" + results);
		} catch (Exception e) {
			logger.error("Problema en getGridFilterData", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}

		return results;
	}

	public int getTotalDataIntWithPager(String authToken, String filterField,
			int valueFilter) throws NonAuthorizedCallException,
			PersistenceException {
		int total = 0;
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			Query q = em.createQuery("SELECT count(p) FROM "
					+ this.getPersistenceEntityName() + " p " + "WHERE p."
					+ filterField + " = " + valueFilter);
			//List<?> results = q.getResultList();
			//if(results.size()==1) {
			total = ((Number) q.getSingleResult()).intValue();
			//}
//			System.out.println("results:" + results);
		} catch (Exception e) {
			logger.error("Problema en getGridFilterData", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}

		return total;
	}
	
	public boolean remove(String authToken, int idCompras)
			throws NonAuthorizedCallException, PersistenceException {
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			Query q = em.createQuery("SELECT p FROM "
					+ this.getPersistenceEntityName() + " p WHERE p."
					+ this.getPersistenceEntityIdField() + " = " + idCompras);

			Object obj = q.getSingleResult();

			em.remove(obj);

		} finally {
			if (tx.isActive()) {
				tx.commit();
			}
			em.close();
		}

		return true;
	}

	public Object add(String authToken, Object object)
			throws NonAuthorizedCallException, PersistenceException {
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			em.persist(object);

			tx.commit();

		} finally {
			if (tx.isActive()) {
				tx.commit();
			}
			em.close();
		}

		String name = this.getPersistenceEntityIdField();
		name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
		logger.info("add method- Calling method persitent object method: "
				+ name);
		try {
			Method idMethod = this.getPersistenceEntityClass().getMethod(name,null);
			return idMethod.invoke(object, null);
		} catch (SecurityException e) {
			logger.info(e);
			throw new PersistenceException(e);
		} catch (NoSuchMethodException e) {
			logger.info(e);
			throw new PersistenceException(e);
		} catch (IllegalArgumentException e) {
			logger.info(e);
			throw new PersistenceException(e);
		} catch (IllegalAccessException e) {
			logger.info(e);
			throw new PersistenceException(e);
		} catch (InvocationTargetException e) {
			logger.info(e);
			throw new PersistenceException(e);
		}

	}

	@Override
	public Object getDTO(String authToken) throws NonAuthorizedCallException {
		Class<?> clazz = this.getPersistenceEntityClass();
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		}

		return null;
	}

	@Override
	public Object getDataById(String authToken, int id)
			throws NonAuthorizedCallException {

		EntityManager em = null;

		Object result = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();

			result = em.find(this.getPersistenceEntityClass(), id);

		} catch (Exception e) {
			logger.error("Problema en getDataById", e);
			throw new PersistenceException(e);
		} finally {

			em.close();
		}
		return result;
	}

	@Override
	public void update(String authToken, Object object)
			throws NonAuthorizedCallException {
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();

			em.merge(object);

			tx.commit();

		} finally {

			if (tx.isActive()) {
				tx.commit();
			}
			em.close();
		}

	}

}
