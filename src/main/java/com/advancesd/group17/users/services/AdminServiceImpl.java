package com.advancesd.group17.users.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.advancesd.group17.course.model.Course;
import com.advancesd.group17.course.services.CourseService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	CourseService courseService;
	
	private static Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Override
	public void createHomePage(Model model) {
		log.info("Entered AdminServiceImpl.createHomePage");
		List<Course> courseList = courseService.listOfCourses();
		model.addAttribute("courseList", courseList);
		
		log.info("Exiting AdminServiceImpl.createHomePage");
		return ;
		
	}

}
