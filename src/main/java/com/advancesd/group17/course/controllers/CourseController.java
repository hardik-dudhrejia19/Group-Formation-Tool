package com.advancesd.group17.course.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.advancesd.group17.course.dao.CourseDao;
import com.advancesd.group17.course.dao.CourseDaoImpl;
import com.advancesd.group17.course.models.Course;
import com.advancesd.group17.course.services.CourseService;

@Controller
public class CourseController {

	@GetMapping("/home")
	public String homepage(@RequestParam("bannerid") String bannerid, Model model)
	{
		CourseDao dc = new CourseDaoImpl();
		CourseService lc = new CourseService();
		
		List<Course> listofcourses = lc.listcourses(bannerid, dc);
		
		model.addAttribute("courses", listofcourses);
		model.addAttribute("bannerid", bannerid);
		
		return "Home";
	}
}
