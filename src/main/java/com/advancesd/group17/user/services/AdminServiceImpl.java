package com.advancesd.group17.user.services;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseService;
import com.advancesd.group17.course.services.CourseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
	
	private static Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Override
	public void createHomePage(Model model) {
		log.info("Entered AdminServiceImpl.createHomePage");
		CourseDao courseDao = new CourseDaoImpl();
		CourseService courseService = new CourseServiceImpl();
		List<Course> courseList = courseService.getAllCourses(courseDao);
		model.addAttribute("courseList", courseList);
		log.info("Exiting AdminServiceImpl.createHomePage");
		return ;
		
	}

}
