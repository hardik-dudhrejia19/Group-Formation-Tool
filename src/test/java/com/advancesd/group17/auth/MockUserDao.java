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
		
		if("abcd".equals(u.getBannerId()))
		{
			return false;
		}
		
		return false;
	}

	@Override
	public boolean registeruser(User u) {
		
		if("B00000000".equals(u.getBannerId()) && 
		   "abc.xyz@mail.com".equals(u.getEmail()) &&
		   "abc".equals(u.getFirstName()) &&
		   "xyz".equals(u.getLastName()) && 
		   "abcxyz".equals(u.getPassword()))
		{
			return true;
		}
		else
			return false;
	}

}
