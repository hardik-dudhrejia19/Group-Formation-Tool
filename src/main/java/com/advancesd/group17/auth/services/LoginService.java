package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;

public class LoginService {

	public boolean userauthentication(User usr, UserDao dl)
	{
		return dl.loginAuthentication(usr);
	}
	
	public boolean Isadmin(String bannerid, UserDao dl)
	{
		if("admin".equals(bannerid))
			return true;
		else
			return false;
	}
}
