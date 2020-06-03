package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.AuthDao;
import com.advancesd.group17.user.models.User;

public class SignupServiceImpl {

	public boolean IsAlreadyUser(User user, AuthDao dl)
	{
		return dl.isAlreadyUser(user);
	}
	
	public boolean registeruser(User user, AuthDao dl)
	{
		return dl.registeruser(user);
	}
}
