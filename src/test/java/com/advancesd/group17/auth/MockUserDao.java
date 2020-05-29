package com.advancesd.group17.auth;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;

public class MockUserDao implements UserDao{

	@Override
	public boolean loginAuthentication(User u) {
		
		if("admin".equals(u.getBannerId()) && "admin".equals(u.getPassword()))
		{
			return true;
		}
		
		if("poojan".equals(u.getBannerId()) && "admin".equals(u.getPassword()))
		{
			return false;
		}
		
		return false;
	}

	@Override
	public boolean isalreadyuser(User u) {
		
		if("admin".equals(u.getBannerId()))
		{
			return true;
		}
		
		if("krutarth".equals(u.getBannerId()))
		{
			return false;
		}
		
		return false;
	}

//	@Override
//	public boolean registeruser(User u) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
