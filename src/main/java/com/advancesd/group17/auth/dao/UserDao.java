package com.advancesd.group17.auth.dao;

import com.advancesd.group17.auth.models.User;

public interface UserDao {

	public boolean loginAuthentication(User u);
	
	public boolean isalreadyuser(User u);
	
    public boolean registeruser(User u);
	
}