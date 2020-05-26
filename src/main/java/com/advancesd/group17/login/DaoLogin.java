package com.advancesd.group17.login;

public class DaoLogin implements Ilogin{
	
	@Override
	public String loginAuthentication(String bid,String pass)
	{
		// write logic to fetch user from database
		if("admin".equals(bid) && "admin".equals(pass))
			return "Home";
		else
			return "login";
	}
}
