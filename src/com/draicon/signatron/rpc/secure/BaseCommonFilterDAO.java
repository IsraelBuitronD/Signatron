package com.draicon.signatron.rpc.secure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.draicon.signatron.rpc.em.DAOEntityManagerFactory;
import com.draicon.signatron.security.exception.NonAuthorizedCallException;

public abstract class BaseCommonFilterDAO extends BaseCommonDAO implements CommonFilterDAO {
	private static Log logger = LogFactory.getLog(BaseCommonFilterDAO.class);

	public BaseCommonFilterDAO() {

	}
	
	public List<?> getGridFilterData(String authToken, String filterField, int valueFilter)
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
					+ "WHERE p." + filterField + " = "
					+ valueFilter + " "
					+ "ORDER BY p."
					+ this.getPersistenceEntityIdField());

			results = q.getResultList();

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
}
