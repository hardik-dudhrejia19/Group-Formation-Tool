package com.advancesd.group17.user.mock;

import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.user.dao.UserDao;

public class UserMockDao implements UserDao {

	@Override
	public Boolean isAlreadyUser(String bannerid) {
		boolean isuser = false;
		if ("B00835071".equals(bannerid)) {
			isuser = true;
		}
		if ("B99999999".equals(bannerid)) {
			isuser = false;
		}
		return isuser;
	}

	@Override
	public List<String> getUserRoleByBannerId(String bannerid) {
		List<String> list = new ArrayList<>();
		if ("B00836202".equals(bannerid)) {
			list.add("TA");
			return list;
		}

		if ("B00000000".equals(bannerid)) {
			list.add("Guest");
			return list;
		}
		return null;
	}

	@Override
	public boolean isEmailExist(String email) {
		if(email.equals("test@test.com"))
			return true;
		else
			return false;
	}

}
