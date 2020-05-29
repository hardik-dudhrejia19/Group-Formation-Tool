package com.advancesd.group17.auth.dao;

import com.advancesd.group17.auth.models.User;

public interface UserDao {

	boolean loginAuthentication(User u);
	
	boolean isalreadyuser(User u);
	
//	boolean registeruser(User u);
	
}
