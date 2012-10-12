package com.draicon.signatron.rpc.secure;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.draicon.signatron.data.Usuarios;
import com.draicon.signatron.rpc.em.DAOEntityManagerFactory;

public class UsuariosDAO extends BaseCommonDAO {
	private static Log logger = LogFactory.getLog(UsuariosDAO.class);

	private final static String ENTITY_ID = "idUser";
	private final static String ENTITY_NAME = "Usuarios";
	private final static Class<?> ENTITY_CLASS = Usuarios.class;

	public UsuariosDAO() {
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
	
	public Usuarios getUsuarioByIdCliente(int idCliente) {
		Usuarios usuario = new Usuarios();
		EntityTransaction tx = null;
		EntityManager em = null;
		
		try {
			em = DAOEntityManagerFactory.createEntityManager();
			tx = em.getTransaction();
		
			tx.begin();
		
			Query q = em.createQuery("SELECT u FROM "
					+ this.getPersistenceEntityName() + " u " 
					+ "WHERE u.idCliente.idCliente=" + idCliente);
			
			usuario = (Usuarios) q.getSingleResult();
		} catch (Exception e) {
			logger.error("Problema en getUsuarioByIdCliente", e);
			throw new PersistenceException(e);
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			em.close();
		}
		return usuario;
	}

}
