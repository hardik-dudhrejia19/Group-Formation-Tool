package com.advancesd.group17.auth.dao;

import com.advancesd.group17.user.models.User;

public interface AuthDao {

	public boolean loginAuthentication(User u);
	
	public boolean isAlreadyUser(User u);
	
    public boolean registeruser(User u);
	
    public boolean checkuserbyemail(User u);
    
    public User getusercred(String email);
}