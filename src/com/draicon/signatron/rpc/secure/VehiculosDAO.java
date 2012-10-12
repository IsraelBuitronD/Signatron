package com.draicon.signatron.rpc.secure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.draicon.signatron.data.Vehiculos;
import com.draicon.signatron.rpc.em.DAOEntityManagerFactory;
import com.draicon.signatron.security.exception.NonAuthorizedCallException;

public class VehiculosDAO extends BaseCommonDAO {
	private static Log logger = LogFactory.getLog(VehiculosDAO.class);

	private final static String ENTITY_ID = "idVehiculo";
	private final static String ENTITY_NAME = "Vehiculos";
	private final static Class<?> ENTITY_CLASS = Vehiculos.class;

	public VehiculosDAO() {
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

	public List<?> getVehiculosCliente(String authToken, int idCliente)
			throws NonAuthorizedCallException, PersistenceException {
		List<?> results = null;
		EntityTransaction tx = null;
		EntityManager em = null;
		
		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();
		
			tx.begin();
		
			Query q = em.createQuery("SELECT v FROM "
					+ this.getPersistenceEntityName() + " v "
					+ "WHERE v.idCliente.idCliente=" + idCliente + " "
					+ "ORDER BY v.idVehiculo.nombre");
		
			results = q.getResultList();
		
		} catch (Exception e) {
			logger.error("Problema en getVehiculosCliente", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
		
		return results;
	}
	
}