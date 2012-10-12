package com.draicon.signatron.rpc.em;

import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOEntityManagerFactory {

	private static Log logger = LogFactory
			.getLog(DAOEntityManagerFactory.class);

	private static EntityManagerFactory emf = null;

	protected DAOEntityManagerFactory() {
	}

	public static EntityManager createEntityManager() {
		if (emf == null) {
			try {
				Context initCtx = new InitialContext();

				DataSource ds = (DataSource) initCtx
						.lookup("java:comp/env/jdbc/SignatronDB");

				HashMap<String, Object> properties = new HashMap<String, Object>();
				properties.put("openjpa.ConnectionFactory",ds);
				
				emf = Persistence.createEntityManagerFactory("SignatronData",
						properties);
			} catch (NamingException e) {
				logger.info("Problem Creating EntityManager", e);
			}
		}
		return emf.createEntityManager();
	}

}
