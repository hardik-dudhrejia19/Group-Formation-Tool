package com.advancesd.group17.auth.services;

import com.advancesd.group17.auth.dao.UserDao;
import com.advancesd.group17.auth.models.User;

public interface ForgotPasswordService {

	public boolean checkuserbybanner(User u, UserDao ud);
	public boolean mailsent(String banner, UserDao ud);
}
