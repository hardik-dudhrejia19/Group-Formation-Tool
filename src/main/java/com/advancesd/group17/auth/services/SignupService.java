package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;

public interface SignupService {

	public boolean IsAlreadyUser(User user, UserDao dl);
	
	public boolean registeruser(User user, UserDao dl);
	
}
