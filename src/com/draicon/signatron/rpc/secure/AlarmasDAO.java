package com.draicon.signatron.rpc.secure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.draicon.signatron.data.Alarmas;

public class AlarmasDAO extends BaseCommonDAO {
	private static Log logger = LogFactory.getLog(AlarmasDAO.class);

	private final static String ENTITY_ID = "idAlarma";
	private final static String ENTITY_NAME = "Alarmas";
	private final static Class<?> ENTITY_CLASS = Alarmas.class;

	public AlarmasDAO() {
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
}
