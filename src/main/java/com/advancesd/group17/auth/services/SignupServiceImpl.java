package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;

public class SignupServiceImpl {

	public boolean IsAlreadyUser(User user, UserDao dl)
	{
		return dl.isalreadyuser(user);
	}
	
	public boolean registeruser(User user, UserDao dl)
	{
		return dl.registeruser(user);
	}
}
