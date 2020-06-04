package com.advancesd.group17.user.mock;

import java.util.ArrayList;
import java.util.List;

import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.user.dao.InstructorDao;
import com.advancesd.group17.user.models.User;

public class InstructorMockDao implements InstructorDao {

	Course course = null;

	@Override
	public List<User> listusersforInstructor() {
		List<User> instructorList = new ArrayList<>();
		User u1 = new User();
		u1.setBannerId("B1");
		instructorList.add(u1);
		User u2 = new User();
		u1.setBannerId("B2");
		instructorList.add(u2);
		
		return instructorList;
	}

	@Override
	public boolean assignInstructor(String bannerid, Integer courseid) {
		course = new Course();
		course.setCourseId(courseid);
		User u1 = new User();
		u1.setBannerId(bannerid);
		course.setInstructor(u1);
		return true;
	}

	@Override
	public User getCourseInstructor(Integer courseId) {
		return course.getInstructor();
	}

}
