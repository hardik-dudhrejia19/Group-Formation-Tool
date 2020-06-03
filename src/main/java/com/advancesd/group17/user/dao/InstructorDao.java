package com.advancesd.group17.user.dao;

import java.util.List;

import com.advancesd.group17.user.models.User;

public interface InstructorDao {

	public List<User> listusersforInstructor();
	
	public boolean assignInstructor(String bannerid, Integer courseid);
}
