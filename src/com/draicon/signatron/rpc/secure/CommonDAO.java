package com.draicon.signatron.rpc.secure;

import java.util.List;

import javax.persistence.PersistenceException;

import com.draicon.signatron.security.exception.NonAuthorizedCallException;

public interface CommonDAO {

	public List<?> getGridData(String authToken)
			throws NonAuthorizedCallException, PersistenceException;

	public boolean remove(String authToken, int id)
			throws NonAuthorizedCallException, PersistenceException;

	public Object add(String authToken, Object object)
			throws NonAuthorizedCallException, PersistenceException;

	public Object getDTO(String authToken) throws NonAuthorizedCallException;
	
	public Object getDataById(String authToken,int id) throws NonAuthorizedCallException;
	
	public void update(String authToken, Object object) throws NonAuthorizedCallException;

}
