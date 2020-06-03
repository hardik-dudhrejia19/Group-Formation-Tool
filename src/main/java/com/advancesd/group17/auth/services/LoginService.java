package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.user.models.User;

public interface LoginService {

	public boolean userauthentication(User usr, AuthDao dl);
	
	public boolean Isadmin(String bannerid, AuthDao dl);
	
}
