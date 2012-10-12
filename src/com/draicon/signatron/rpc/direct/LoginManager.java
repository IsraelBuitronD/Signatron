package com.draicon.signatron.rpc.direct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginManager {
	
	private static Log logger = LogFactory.getLog(LoginManager.class);

	public LoginManager(){
		logger.debug("Creating instace of LoginManager");
	}
	
	public String login(String username,String password){
		logger.debug("Invoking method login with: "+username);
		return "Somedummystringbynow";
	}
	
}
