package com.draicon.signatron.rpc.secure;

import java.util.List;

import javax.persistence.PersistenceException;

import com.draicon.signatron.security.exception.NonAuthorizedCallException;

public interface CommonFilterDAO {

	public List<?> getGridFilterData(String authToken, String filterField, int valueFilter)
			throws NonAuthorizedCallException, PersistenceException;

//	public Object getFilterDataById(String authToken, String filterField, int valueFilter, int id) throws NonAuthorizedCallException;
	
}
