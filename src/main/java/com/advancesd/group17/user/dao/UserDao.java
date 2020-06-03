package com.advancesd.group17.user.dao;

import java.util.List;

import com.advancesd.group17.user.models.NewStudent;

public interface UserDao {
	
	Boolean isAlreadyUser(String bannerid);
	
	List<String> getUserRoleByBannerId(String bannerid);
	
	List<NewStudent> getNewStudents(List<NewStudent> newstudents);

}
