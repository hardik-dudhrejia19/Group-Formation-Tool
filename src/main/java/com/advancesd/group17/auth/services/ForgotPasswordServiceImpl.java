package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.email.MailConfig;

public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Override
	public boolean checkuserbybanner(User u, UserDao ud) 
	{	
		return ud.isalreadyuser(u);
	}
	
	@Override
	public boolean mailsent(String banner, UserDao ud) 
	{	
		User u = ud.getusercred(banner);
		
		try
		{
			MailConfig mc = new MailConfig();
			
			mc.sendmail(u.getEmail(), banner, u.getPassword());
        	return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;	
		}
	}
}
