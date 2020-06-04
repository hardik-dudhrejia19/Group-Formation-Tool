package com.advancesd.group17.user.services;

import java.util.List;

import com.advancesd.group17.user.dao.InstructorDao;
import com.advancesd.group17.user.models.User;

public interface InstructorService {

	public List<User> listUsersforinstructor(InstructorDao Instructordao);
	
	public boolean addInstructor(String banner , Integer courseid, InstructorDao instructordao);
	
	public User getCourseInstructor(Integer courseId, InstructorDao instructordao);
}
