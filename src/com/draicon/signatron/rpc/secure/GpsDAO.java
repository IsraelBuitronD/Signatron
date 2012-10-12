package com.draicon.signatron.rpc.secure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.draicon.signatron.data.Gps;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.draicon.signatron.rpc.em.DAOEntityManagerFactory;
import com.draicon.signatron.security.exception.NonAuthorizedCallException;


public class GpsDAO extends BaseCommonDAO {
	private static Log logger = LogFactory.getLog(GpsDAO.class);

	private final static String ENTITY_ID = "idGps";
	private final static String ENTITY_NAME = "Gps";
	private final static Class ENTITY_CLASS = Gps.class;

	public GpsDAO() {
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
	protected Class getPersistenceEntityClass() {
		logger.debug("getPersistenceEntityClass: "
				+ ENTITY_CLASS.getCanonicalName());
		return ENTITY_CLASS;
	}

	public Gps getDataByIdVehiculo(String authToken,int idVehiculo)
			throws NonAuthorizedCallException, PersistenceException {
		Gps result = null;
		EntityTransaction tx = null;
		EntityManager em = null;

		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();

			tx.begin();
			
			Query q = em.createQuery("SELECT v.idGps FROM Vehiculos v "
					+ "WHERE v.idVehiculo = "	+ idVehiculo);

			result = (Gps) q.getSingleResult();

		} catch (Exception e) {
			logger.error("Problema en getDataByIdVehiculo", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}

		return result;
	}

}
