package com.advancesd.group17.user.services;

import java.util.List;

import com.advancesd.group17.user.dao.InstructorDao;
import com.advancesd.group17.user.models.User;

public class InstructorServiceImpl implements InstructorService {

	@Override
	public List<User> listUsersforinstructor(InstructorDao instructordao) {
		
		return instructordao.listusersforInstructor();
	}

	@Override
	public boolean addInstructor(String banner , Integer courseid, InstructorDao instructordao) {
		
		return instructordao.assignInstructor(banner, courseid);
	}
	
}
