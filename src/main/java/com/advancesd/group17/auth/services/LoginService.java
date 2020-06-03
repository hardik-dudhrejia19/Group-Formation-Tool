package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;

public interface LoginService {

	public boolean userauthentication(User usr, UserDao dl);
	
	public boolean Isadmin(String bannerid, UserDao dl);
	
}
