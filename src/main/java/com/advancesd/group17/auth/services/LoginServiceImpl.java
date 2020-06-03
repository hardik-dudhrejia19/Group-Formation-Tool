package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.user.models.User;

public class LoginServiceImpl {

	public boolean userauthentication(User usr, AuthDao dl)
	{
		return dl.loginAuthentication(usr);
	}
	
	public boolean Isadmin(String bannerid, AuthDao dl)
	{
		if("admin".equals(bannerid))
			return true;
		else
			return false;
	}
}
