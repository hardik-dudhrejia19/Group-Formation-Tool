package com.advancesd.group17.auth;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;

public class MockUserDaoImpl implements UserDao{

	@Override
	public boolean loginAuthentication(User u) {
		
		if("admin".equals(u.getBannerId()) && "admin".equals(u.getPassword()))
		{
			return true;
		}
		else
		{
			return false;	
		}	
	}

	@Override
	public boolean isalreadyuser(User u) {
		
		if("admin".equals(u.getBannerId()))
		{
			return true;
		}
		else
		{
			return false;
		}
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

	@Override
	public boolean checkuserbyemail(User u) {
		
		if("abc.xyz@gmail.com".equals(u.getEmail()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public User getusercred(String banner) {
		
		User u = new User();
		
		if("B00836202".equals(banner))
		{
			u.setEmail("patelpoojan602@gmail.com");
			u.setPassword("poojan");
			return u;
		}
		else
		{
			u.setEmail("krutarth");
			u.setPassword("krutarth");
			return u;
		}
	}

}
