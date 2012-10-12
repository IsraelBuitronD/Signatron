package com.draicon.signatron.rpc.secure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.draicon.signatron.data.Geocercas;

public class GeocercasDAO extends BaseCommonDAO {
	private static Log logger = LogFactory.getLog(GeocercasDAO.class);

	private final static String ENTITY_ID = "idGeocerca";
	private final static String ENTITY_NAME = "Geocercas";
	private final static Class ENTITY_CLASS = Geocercas.class;

	public GeocercasDAO() {
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

}
