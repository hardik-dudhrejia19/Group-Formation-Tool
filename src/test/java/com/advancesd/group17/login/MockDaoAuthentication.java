package com.advancesd.group17.login;

public class MockDaoAuthentication implements IDaoAuthentication{
	
	@Override
	public boolean loginAuthentication(String bid, String pass) {
		
		if("admin".equals(bid) && "admin".equals(pass))
		{
			return true;
		}
		
		if("admin".equals(bid) && "asfaf".equals(pass))
		{
			return false;
		}
		
		return false;
	}
	
	@Override
	public Integer getuserid(String bid) {
		
		if("B00836202".equals(bid))
		{
			return 1;
		}
		
		if("B253262366202".equals(bid))
		{
			return 0;
		}
		
		return null;
	}

}
