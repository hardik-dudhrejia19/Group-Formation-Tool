package com.advancesd.group17.user.dao;

import java.util.List;

public interface UserDao {
	
	Boolean isAlreadyUser(String bannerid);
	
	List<String> getUserRoleByBannerId(String bannerid);
	
	public boolean isEmailExist(String email);

}
