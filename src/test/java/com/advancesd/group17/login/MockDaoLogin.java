package com.advancesd.group17.login;

public class MockDaoLogin implements IDaoLogin{
	
	@Override
	public String loginAuthentication(String bid,String pass)
	{
		if("admin".equals(bid) && "admin".equals(pass))
		{
			return "Admin";
		}
		
		if("B00836202".equals(bid) && "poojan".equals(pass))
		{
			return "Home";
		}
		
		if("B00239856".equals(bid) && "asaaaaa".equals(pass))
		{
			return "login";
		}
		
		return "login";
	}

}
